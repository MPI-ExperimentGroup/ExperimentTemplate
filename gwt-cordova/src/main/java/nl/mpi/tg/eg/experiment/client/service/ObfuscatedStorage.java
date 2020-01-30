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

import com.google.gwt.http.client.URL;
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
        this.enableObfuscation = enableObfuscation;
        this.appNameInternal = obfuscateString(appNameInternal, appNameInternal);
    }

    public int getStorageLength() {
        // we could check the size of each item, but for now the length is enough
        return dataStore.getLength();
    }

    private String processString(String storageKey, String input) {
        if (enableObfuscation) {
            while (input.length() < 50) {
                input += " ";
            }
            byte[] outputBytes = input.getBytes();
            byte[] storageKeyBytes = storageKey.getBytes();
            for (int index = 0; index < input.length(); index++) {
                final int keyIndex = storageKeyBytes.length - ((index * 3) % storageKeyBytes.length) - 1;
                outputBytes[index] ^= storageKeyBytes[keyIndex];
            }
            return new String(outputBytes);
        } else {
            return input;
        }
    }

    final protected String obfuscateString(String storageKey, String input) {
        return processString(storageKey, urlEncode(input));
    }

    protected String urlEncode(String input) {
        return input;// this is not required: URL.encode(input);
    }

    protected String urlDecode(String input) {
        return input;// this is not required: URL.decode(input);
    }

    protected String revealString(String storageKey, String input) {
        return urlDecode(processString(storageKey, input).trim());
    }

    public ObfuscatedStorage loadStorage() {
        if (dataStore == null) {
            dataStore = Storage.getLocalStorageIfSupported();
        }
        return (dataStore != null) ? this : null;
    }

    protected String obfuscateStorageKey(UserId userId, String storageVariable) {
        return obfuscateString(userId.toString(), userId.toString() + storageVariable);
    }

    protected String getAPP_STATE(UserId userId) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".AppState");
    }

    protected String getCOMPLETION_CODE(UserId userId) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".completionCode");
    }

    protected String getUSER_RESULTS(UserId userId, String valueName) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".UserResults." + valueName);
    }

    protected String getUSER_METADATA(UserId userId, String postName) {
        return appNameInternal + "." + userId.toString() + obfuscateString(appNameInternal, ".UserMetadata." + postName);
    }

    protected String isUSER_METADATA(String keyName, String postName) {
        final String endString = obfuscateString(appNameInternal, ".UserMetadata." + postName);
        if (keyName.startsWith(appNameInternal) && keyName.endsWith(endString)) {
            return keyName.substring(appNameInternal.length() + 1, keyName.length() - endString.length());
        }
        return null;
    }

    protected String getUSER_METADATA_CONNECTION(UserId userId, String valueName) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".UserMetadataConnection." + valueName);
    }

    protected String getLAST_USER_ID() {
        return appNameInternal + obfuscateString(appNameInternal, ".LastUserId");
    }

    protected String getGAME_DATA(UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return appNameInternal + "." + obfuscateStorageKey(userId, ".GameData");
    }

    protected String getGAME_DATA(String label, UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return appNameInternal + "." + obfuscateStorageKey(userId, ".GameData." + label);
    }

    protected String getSTIMULI_DATA(UserId userId, Stimulus stimulus) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".StimuliData." + stimulus.getUniqueId());
    }

    protected String getSTIMULI_DATA(UserId userId, String stimulusId) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".StimuliData." + stimulusId);
    }

    protected String getSCREEN_DATA(String endPoint, UserId userId) {
        return appNameInternal + "." + obfuscateStorageKey(userId, ".ScreenData." + endPoint); // this is an exception in the order of the key parts, is this avoidable?
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
        final String item = dataStore.getItem(key);
        return (item != null) ? revealString(key, item) : null;
    }

    public void setItem(String key, String data) {
        dataStore.setItem(key, obfuscateString(key, data));
    }

    public void removeItem(String key) {
        dataStore.removeItem(key);
    }

    public void clear() {
        dataStore.clear();
    }

    public void clearUserData(UserId userId) {
        final String userIdString1 = obfuscateString(userId.toString(), userId.toString()).substring(0, userId.toString().length());
        for (int itemIndex = getLength() - 1; itemIndex > -1; itemIndex--) {
            final String key = key(itemIndex);
            if (key.startsWith(appNameInternal + "." + userIdString1)) {
                removeItem(key);
            } else if (key.startsWith(appNameInternal + "." + userId.toString())) {
                removeItem(key);
            }
        }
    }
}
