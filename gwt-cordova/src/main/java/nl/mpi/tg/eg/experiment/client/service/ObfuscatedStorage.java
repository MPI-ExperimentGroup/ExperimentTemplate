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
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Jan 16, 2019 3:26:06 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ObfuscatedStorage {

    protected final String appNameInternal;
    protected final boolean enableObfuscation;
    private Storage dataStore = null;

    public ObfuscatedStorage(String appNameInternal, boolean enableObfuscation) {
        this.appNameInternal = appNameInternal;
        this.enableObfuscation = enableObfuscation;
    }

    public ObfuscatedStorage loadStorage() {
        if (dataStore == null) {
            dataStore = Storage.getLocalStorageIfSupported();
        }
        return (dataStore != null) ? this : null;
    }

    protected String getAPP_STATE(UserId userId) {
        return appNameInternal + "." + userId.toString() + ".AppState";
    }

    protected String getCOMPLETION_CODE(UserId userId) {
        return appNameInternal + "." + userId.toString() + ".completionCode";
    }

    protected String getUSER_RESULTS(UserId userId, String valueName) {
        return appNameInternal + "." + userId.toString() + ".UserResults." + valueName;
    }

    protected String getUSER_METADATA(UserId userId, String valueName) {
        return appNameInternal + "." + userId.toString() + ".UserMetadata." + valueName;
    }

    protected boolean isUSER_METADATA(String keyName, String postName) {
        return keyName.startsWith(appNameInternal) && keyName.endsWith(".UserMetadata." + postName);
    }

    protected String getUSER_METADATA_CONNECTION(UserId userId, String valueName) {
        return appNameInternal + "." + userId.toString() + ".UserMetadataConnection." + valueName;
    }

    protected String getLAST_USER_ID() {
        return appNameInternal + ".LastUserId";
    }

    protected String getGAME_DATA(UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return appNameInternal + "." + userId.toString() + ".GameData";
    }

    protected String getGAME_DATA(String label, UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return appNameInternal + "." + userId.toString() + ".GameData." + label;
    }

    protected String getSTIMULI_DATA(UserId userId, Stimulus stimulus) {
        return appNameInternal + "." + userId.toString() + ".StimuliData." + stimulus.getUniqueId();
    }

    protected String getSTIMULI_DATA(UserId userId, String stimulusId) {
        return appNameInternal + "." + userId.toString() + ".StimuliData." + stimulusId;
    }

    protected String getSCREEN_DATA(String endPoint, UserId userId) {
        return appNameInternal + "." + userId.toString() + ".ScreenData." + endPoint; // this is an exception in the order of the key parts, is this avoidable?
//        STOWED_DATA = appNameInternal + ".SentData.";
//        FAILED_DATA = appNameInternal + ".FailedData.";
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
