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

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * @since 06 May, 2025 4:34:41 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Repository
@Transactional(readOnly = true)
public class MediaDataStreamerImpl implements MediaDataStreamer {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public InputStream getMediaStream(UUID mediaUUID) {
        return entityManager.unwrap(Session.class).doReturningWork(connection -> {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT data_blob FROM audio_data WHERE media_uuid = ? ORDER BY part_number ASC"
            );
            stmt.setObject(1, mediaUUID);
            ResultSet rs = stmt.executeQuery();
            List<InputStream> streams = new ArrayList<>();
            while (rs.next()) {
                InputStream partStream = rs.getBinaryStream("data_blob");
                if (partStream != null) {
                    streams.add(partStream);
                }
            }
            if (streams.isEmpty()) {
                throw new SQLException("No media parts found for UUID: " + mediaUUID);
            }
            return new SequenceInputStream(Collections.enumeration(streams));
        });
    }
}
