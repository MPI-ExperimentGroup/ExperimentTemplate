/*
 * Copyright (C) 2025 Max Planck Institute for Psycholinguistics, Nijmegen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.mpi.tg.eg.frinex.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.mpi.tg.eg.frinex.model.AudioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.jdbc.core.ConnectionCallback;

/**
 * @since 07 May, 2025 16:58 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Service

public class AudioDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveAudioData(AudioData audioData, MultipartFile dataBlob) {
        entityManager.persist(audioData);
        entityManager.flush();
        // for OID use:
        Long generatedId = audioData.getId();
        if (generatedId == null) {
            throw new IllegalStateException("AudioData ID was not generated");
        }
        jdbcTemplate.execute((Connection conn) -> {
            PGConnection pgConn = conn.unwrap(PGConnection.class);
            LargeObjectManager lobj = pgConn.getLargeObjectAPI();
            long oid = lobj.createLO(LargeObjectManager.WRITE);
            try (LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE); OutputStream os = obj.getOutputStream(); InputStream is = dataBlob.getInputStream()) {
                is.transferTo(os);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store audio blob", e);
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE audio_data SET data_blob = ? WHERE id = ?")) {
                ps.setLong(1, oid);
                ps.setLong(2, generatedId);
                int rows = ps.executeUpdate();
                if (rows != 1) {
                    throw new IllegalStateException("Failed to update data_blob for ID " + generatedId);
                }
            }

            return null;
        });
        // for BYTEA use:
        // jdbcTemplate.update(
        //         (Connection conn) -> {
        //             try {
        //                 PreparedStatement ps = conn.prepareStatement("UPDATE audio_data SET data_blob = ? WHERE id = ?");
        //                 ps.setBinaryStream(1, dataBlob.getInputStream(), dataBlob.getSize());
        //                 ps.setLong(2, audioData.getId());
        //                 return ps;
        //             } catch (IOException e) {
        //                 throw new RuntimeException("Error storing audio", e);
        //             }
        //         }
        // );
    }

//    @Autowired
//    private AudioDataRepository audioDataRepository;
//    @Transactional(readOnly = true)
//    public void addToZipArchive(final ZipOutputStream zipStream, String fileName, AudioData audioData) throws IOException {
//        try ( Stream<Byte> stream = audioDataRepository.streamDataBlob(audioData.getId())) {
//            ZipEntry zipEntry = new ZipEntry(fileName);
//            zipStream.putNextEntry(zipEntry);
//            stream.forEach(chunk -> {
//                try {
//                    zipStream.write(chunk);
//                } catch (IOException e) {
//                    throw new RuntimeException("Error writing blob chunk to ZIP", e);
//                }
//            });
//            zipStream.closeEntry();
//            zipStream.flush();
//        }
//    }
    
    @Transactional
    public void streamToZip(final ZipOutputStream zipStream, String fileName, AudioData audioData) {
        // for OID use:
        jdbcTemplate.execute((ConnectionCallback<Void>) con -> {
            PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
            ps.setLong(1, audioData.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long oid = rs.getLong("data_blob");
                if (rs.wasNull() || oid == 0) {
                    System.err.println("[ERROR] data_blob OID is null or 0 for id: " + audioData.getId());
                } else {
                    PGConnection pgCon = con.unwrap(PGConnection.class);
                    LargeObjectManager lobj = pgCon.getLargeObjectAPI();
                    LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
                    try (InputStream blobStream = obj.getInputStream()) {
                        ZipEntry entry = new ZipEntry(fileName);
                        zipStream.putNextEntry(entry);
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = blobStream.read(buffer)) != -1) {
                            zipStream.write(buffer, 0, bytesRead);
                        }
                        zipStream.closeEntry();
                    } catch (IOException e) {
                        throw new RuntimeException("Error writing blob chunk to ZIP", e);
                    } finally {
                        obj.close();
                    }
                }
            }
            return null;
        });
        // for BYTEA use:
        //jdbcTemplate.query(
        //con -> {
        //    PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
        //    ps.setLong(1, audioData.getId());
        //    return ps;
        //},
        //(ResultSetExtractor<Void>) rs -> {
        //    if (rs.next()) {
        //        try (InputStream blobStream = rs.getBinaryStream("data_blob")) {
        //            ZipEntry entry = new ZipEntry(fileName);
        //            zipStream.putNextEntry(entry);
        //            byte[] buffer = new byte[8192];
        //            int bytesRead;
        //            while ((bytesRead = blobStream.read(buffer)) != -1) {
        //                zipStream.write(buffer, 0, bytesRead);
        //            }
        //            zipStream.closeEntry();
        //        } catch (IOException e) {
        //            throw new RuntimeException("Error writing blob chunk to ZIP", e);
        //        }
        //    }
        //    return null;
        //}
        //);
    }

    @Transactional
    public void streamToResponse(final OutputStream outputStream, AudioData audioData) {
        // for OID use:
        jdbcTemplate.execute((ConnectionCallback<Void>) con -> {
            PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
            ps.setLong(1, audioData.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long oid = rs.getLong("data_blob");
                if (rs.wasNull() || oid == 0) {
                    System.err.println("[ERROR] data_blob OID is null or 0 for id: " + audioData.getId());
                } else {
                    PGConnection pgConn = con.unwrap(PGConnection.class);
                    LargeObjectManager lobj = pgConn.getLargeObjectAPI();
                    LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
                    try (InputStream blobStream = obj.getInputStream()) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = blobStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException("Error streaming media data", e);
                    } finally {
                        obj.close();
                    }
                }
            }
            return null;
        });
        //for BYTEA use:
        //jdbcTemplate.query(
        //        con -> {
        //            PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
        //            ps.setLong(1, audioData.getId());
        //            return ps;
        //        },
        //        (ResultSetExtractor<Void>) rs -> {
        //            if (rs.next()) {
        //                try (InputStream blobStream = rs.getBinaryStream("data_blob");) {
        //
        //                    byte[] buffer = new byte[8192];
        //                    int bytesRead;
        //                    while ((bytesRead = blobStream.read(buffer)) != -1) {
        //                        outputStream.write(buffer, 0, bytesRead);
        //                    }
        //                    outputStream.flush();
        //                } catch (IOException e) {
        //                    throw new RuntimeException("Error streaming media data", e);
        //                }
        //            }
        //            return null;
        //        }
        //);
    }
}
