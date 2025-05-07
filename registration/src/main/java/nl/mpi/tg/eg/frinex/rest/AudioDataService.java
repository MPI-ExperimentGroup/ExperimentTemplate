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
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import nl.mpi.tg.eg.frinex.model.AudioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since 07 May, 2025 16:58 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Service

public class AudioDataService {

    @Autowired
    private AudioDataRepository audioDataRepository;

    @Transactional(readOnly = true)
    public void addToZipArchive(final ZipOutputStream zipStream, String fileName, AudioData audioData) throws IOException {
        try ( Stream<byte[]> stream = audioDataRepository.streamDataBlob(audioData.getId())) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipStream.putNextEntry(zipEntry);
            stream.forEach(chunk -> {
                try {
                    zipStream.write(chunk);
                } catch (IOException e) {
                    throw new RuntimeException("Error writing blob chunk to ZIP", e);
                }
            });
            zipStream.closeEntry();
            zipStream.flush();
        }
    }
}
