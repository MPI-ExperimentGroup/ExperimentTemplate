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
import java.sql.PreparedStatement;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nl.mpi.tg.eg.frinex.model.AudioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

/**
 * @since 07 May, 2025 16:58 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Service

public class AudioDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
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

    public void streamToZip(final ZipOutputStream zipStream, String fileName, AudioData audioData) {
        jdbcTemplate.query(
                con -> {
                    PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
                    ps.setLong(1, audioData.getId());
                    return ps;
                },
                (ResultSetExtractor<Void>) rs -> {
                    if (rs.next()) {
                        try (InputStream blobStream = rs.getBinaryStream("data_blob")) {
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
                        }
                    }
                    return null;
                }
        );
    }

    public void streamToResponse(final OutputStream outputStream, AudioData audioData) {
        jdbcTemplate.query(
                con -> {
                    PreparedStatement ps = con.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
                    ps.setLong(1, audioData.getId());
                    return ps;
                },
                (ResultSetExtractor<Void>) rs -> {
                    if (rs.next()) {
                        try (InputStream blobStream = rs.getBinaryStream("data_blob");) {

                            byte[] buffer = new byte[8192];
                            int bytesRead;
                            while ((bytesRead = blobStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            outputStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException("Error streaming media data", e);
                        }
                    }
                    return null;
                }
        );
    }
}
