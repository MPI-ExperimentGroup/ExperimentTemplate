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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public InputStream getMediaStream(Long id) {
        return entityManager.unwrap(Session.class).doReturningWork(connection -> {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement("SELECT data_blob FROM audio_data WHERE id = ?");
            stmt.setLong(1, id);
            // TODO: remove after debugging
            System.out.println(id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBinaryStream("data_blob");
            }
            throw new SQLException("No BLOB found for ID: " + id);
        });
    }
}
