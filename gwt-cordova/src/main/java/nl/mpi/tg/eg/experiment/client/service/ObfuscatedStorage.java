/*
 * Copyright (C) 2019 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experiment.client.service;

import com.google.gwt.storage.client.Storage;

/**
 * @since Jan 16, 2019 3:26:06 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ObfuscatedStorage {

    private Storage dataStore = null;

    public ObfuscatedStorage loadStorage() {
        if (dataStore == null) {
            dataStore = Storage.getLocalStorageIfSupported();
        }
        return (dataStore != null) ? this : null;
    }

    public int getLength() {
        return dataStore.getLength();
    }

    public String key(int itemIndex) {
        return dataStore.key(itemIndex);
    }

    public String getItem(String key) {
        return dataStore.getItem(key);
    }

    public void setItem(String key, String data) {
        dataStore.setItem(key, data);
    }

    public void removeItem(String key) {
        dataStore.removeItem(key);
    }

    public void clear() {
        dataStore.clear();
    }
}
