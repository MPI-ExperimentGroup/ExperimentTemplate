/*
 * Copyright (C) 2014 Language In Interaction
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.Messages;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserLabelData;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;

/**
 * @since Oct 24, 2014 3:01:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalStorage {

    protected final Messages messages = GWT.create(Messages.class);
    private Storage dataStore = null;
    protected final String MAX_SCORE = "maxScore";
    protected final String GAMES_PLAYED = "gamesPlayed";
    protected final String CURRENT_POTENTIAL = "currentPotential";
    protected final String TOTAL_POTENTIAL = "totalPotential";
    protected final String CURRENT_SCORE = "currentScore";
    protected final String TOTAL_SCORE = "totalScore";
    private final String COMPLETION_CODE = "completionCode";
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();

    private String getAPP_STATE(UserId userId) {
        return messages.appNameInternal() + ".AppState." + userId.toString();
    }

    private String getUSER_RESULTS(UserId userId, String valueName) {
        return messages.appNameInternal() + ".UserResults." + userId.toString() + "." + valueName;
    }

    private boolean isUSER_RESULTS(String keyName, String postName) {
        return keyName.startsWith(messages.appNameInternal() + ".UserResults.") && keyName.endsWith(postName);
    }

    private String getUSER_RESULTS_CONNECTION(UserId userId, String valueName) {
        return messages.appNameInternal() + ".UserResults." + userId.toString() + "." + valueName + ".connectedUserId";
    }

    private String getLAST_USER_ID() {
        return messages.appNameInternal() + ".LastUserId";
    }

    private String getGAME_DATA(UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return messages.appNameInternal() + ".GameData." + userId.toString();
    }

    private String getGAME_DATA(String label, UserId userId) {
        // todo: perhaps merge game and screen data concepts
        return messages.appNameInternal() + ".GameData." + label + "." + userId.toString();
    }

    private String getSTIMULI_DATA(UserId userId, Stimulus stimulus) {
        return messages.appNameInternal() + ".StimuliData." + userId.toString() + "." + stimulus.getUniqueId();
    }

    private String getSCREEN_DATA(String endPoint, UserId userId) {
        return messages.appNameInternal() + ".ScreenData." + endPoint + "." + userId.toString(); // this is an exception in the order of the key parts, is this avoidable?
//        STOWED_DATA = messages.appNameInternal() + ".SentData.";
//        FAILED_DATA = messages.appNameInternal() + ".FailedData.";
    }

    private Storage loadStorage() {
        if (dataStore == null) {
            dataStore = Storage.getLocalStorageIfSupported();
        }
        return dataStore;
    }

    public void clearApplicationData(UserId userId) {
        loadStorage();
        // todo: it would be good to do this on an application basis
//        clear();
        if (dataStore != null) {
            for (int itemIndex = dataStore.getLength() - 1; itemIndex > -1; itemIndex--) {
                final String key = dataStore.key(itemIndex);
                if (key.startsWith(messages.appNameInternal() + "." + userId.toString())) {
                    dataStore.removeItem(key);
                }
            }
        }
    }

    public String getStoredGameData(UserId userId) {
        loadStorage();
        return getCleanStoredData(getGAME_DATA(userId));
    }

//    public String getSowedData(UserId userId) {
//        loadStorage();
//        return getCleanStoredData(STOWED_DATA + userId.toString());
//    }
    public void addStoredGameData(UserId userId, String serialisedGameData) {
        loadStorage();
        dataStore.setItem(getGAME_DATA(userId), getCleanStoredData(getGAME_DATA(userId)) + serialisedGameData);
    }

//    public void addFailedData(String serialisedGameData) {
//        loadStorage();
//        dataStore.setItem(FAILED_DATA, getCleanStoredData(FAILED_DATA) + serialisedGameData);
//    }
//    private void stowSentData(UserId userId, String sendData) {
//        loadStorage();
//        final String sentStoredData = getCleanStoredData(STOWED_DATA + userId.toString());
//        if (sentStoredData.isEmpty()) {
//            dataStore.setItem(STOWED_DATA + userId.toString(), sendData);
//        } else {
//            dataStore.setItem(STOWED_DATA + userId.toString(), sentStoredData + "," + sendData);
//        }
//    }
    public String getStoredScreenData(UserId userId, String endpoint) {
        loadStorage();
        return getCleanStoredData(getSCREEN_DATA(endpoint, userId));
    }

    public void deleteStoredScreenData(UserId userId, String endpoint, String segmentToDelete) {
        loadStorage();
        final String sentStoredData = getCleanStoredData(getSCREEN_DATA(endpoint, userId));
        String remainingStoredData = sentStoredData.replaceFirst("^,", "") + ",";
// replacing this segment will sometimes fail due to non matching strings, but the result of failure is only a second transmission of the data which is a preferred option over complexity
        for (String segment : segmentToDelete.split("\\},\\{")) {
            segment = (segment.startsWith("{")) ? segment : "{" + segment;
            segment = segment.replaceFirst(",$", "");
            segment = (segment.endsWith("}")) ? segment + "," : segment + "},";
            remainingStoredData = remainingStoredData.replace(segment, "");
        }
        dataStore.setItem(getSCREEN_DATA(endpoint, userId), remainingStoredData.replaceFirst("^,", "").replaceFirst(",$", ""));
//        stowSentData(userId, segmentToDelete);
    }

    public JSONObject getStoredJSONObject(UserId userId, Stimulus stimulus) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(getSTIMULI_DATA(userId, stimulus));
        if (cleanStoredData.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) JSONParser.parseStrict(cleanStoredData);
        return jsonObject;
    }

    public void setStoredJSONObject(UserId userId, Stimulus stimulus, JSONObject jSONObject) {
        loadStorage();
        dataStore.setItem(getSTIMULI_DATA(userId, stimulus), jSONObject.toString());
        // with android versions this JSON data is saved to the SDcard via the StimulusPresenter
    }

    public String getStoredDataValue(UserId userId, String label) {
        loadStorage();
        return getCleanStoredData(getGAME_DATA(label, userId));
    }

    public void deleteStoredDataValue(UserId userId, String label) {
        loadStorage();
        dataStore.removeItem(getGAME_DATA(label, userId));
    }

    public void appendStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(getGAME_DATA(label, userId));
        dataStore.setItem(getGAME_DATA(label, userId), cleanStoredData + value);
    }

    public void setStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        dataStore.setItem(getGAME_DATA(label, userId), value);
    }

    public void addStoredScreenData(UserId userId, String endpoint, String serialisedScreenData) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(getSCREEN_DATA(endpoint, userId));
        if (cleanStoredData.isEmpty()) {
            dataStore.setItem(getSCREEN_DATA(endpoint, userId), cleanStoredData + serialisedScreenData);
        } else {
            dataStore.setItem(getSCREEN_DATA(endpoint, userId), cleanStoredData + "," + serialisedScreenData);
        }
    }

    public UserData getStoredData(UserId userId) throws UserIdException {
        UserData userData = new UserData(userId);
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                userData.setMetadataValue(metadataField, getCleanStoredData(getUSER_RESULTS(userData.getUserId(), metadataField.getPostName())));
                final String cleanedUserId = getCleanStoredData(getUSER_RESULTS_CONNECTION(userData.getUserId(), metadataField.getPostName()));
                if (cleanedUserId != null && !cleanedUserId.isEmpty()) {
                    userData.setMetadataConnection(metadataField, new UserId(cleanedUserId));
                }
            }
        }
        userData.updateBestScore(getCleanStoredDouble(getUSER_RESULTS(userData.getUserId(), MAX_SCORE)));
        userData.setGamesPlayed(getCleanStoredInt(getUSER_RESULTS(userData.getUserId(), GAMES_PLAYED)));
        userData.setCurrentScore(getCleanStoredInt(getUSER_RESULTS(userData.getUserId(), CURRENT_SCORE)));
        userData.setTotalScore(getCleanStoredInt(getUSER_RESULTS(userData.getUserId(), TOTAL_SCORE)));
        userData.setPotentialScore(getCleanStoredInt(getUSER_RESULTS(userData.getUserId(), CURRENT_POTENTIAL)));
        userData.setTotalPotentialScore(getCleanStoredInt(getUSER_RESULTS(userData.getUserId(), TOTAL_POTENTIAL)));
        return userData;
    }

    private int getCleanStoredInt(String keyString) {
        final String cleanStoredData = getCleanStoredData(keyString);
        try {
            return Integer.parseInt(cleanStoredData);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    private double getCleanStoredDouble(String keyString) {
        final String cleanStoredData = getCleanStoredData(keyString);
        try {
            return Double.parseDouble(cleanStoredData);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }

    private String getCleanStoredData(String keyString) {
        final String storedValue = dataStore.getItem(keyString);
        return (storedValue == null || "undefined".equals(storedValue)) ? "" : storedValue;
    }

    public void clear() {
        loadStorage();
        if (dataStore != null) {
            dataStore.clear();
        }
    }

    public void storeUserScore(UserResults userResults) {
        loadStorage();
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_SCORE), Double.toString(userResults.getUserData().getBestScore()));
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), GAMES_PLAYED), Integer.toString(userResults.getUserData().getGamesPlayed()));
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_SCORE), Integer.toString(userResults.getUserData().getCurrentScore()));
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), TOTAL_SCORE), Integer.toString(userResults.getUserData().getTotalScore()));
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_POTENTIAL), Integer.toString(userResults.getUserData().getPotentialScore()));
        dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), TOTAL_POTENTIAL), Integer.toString(userResults.getUserData().getTotalPotentialScore()));
    }

    public void storeData(UserResults userResults) {
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                dataStore.setItem(getUSER_RESULTS(userResults.getUserData().getUserId(), metadataField.getPostName()), userResults.getUserData().getMetadataValue(metadataField));
                final UserId metadataConnection = userResults.getUserData().getMetadataConnection(metadataField);
                if (metadataConnection != null && !metadataConnection.toString().isEmpty()) {
                    dataStore.setItem(getUSER_RESULTS_CONNECTION(userResults.getUserData().getUserId(), metadataField.getPostName()), metadataConnection.toString());
                } else {
                    dataStore.removeItem(getUSER_RESULTS_CONNECTION(userResults.getUserData().getUserId(), metadataField.getPostName()));
                }
            }
        }
        storeUserScore(userResults);
        if ((Window.Location.getParameter("testuser") == null)) {
            // only store the last user id if the id is not a URL defined test user
            dataStore.setItem(getLAST_USER_ID(), userResults.getUserData().getUserId().toString());
        }
    }

    public void saveAppState(UserId userId, String appState) {
        loadStorage();
        dataStore.setItem(getAPP_STATE(userId), appState);
    }

    public String getAppState(UserId userId) {
        loadStorage();
        if (dataStore != null) {
            final String appState = getCleanStoredData(getAPP_STATE(userId));
            if (!appState.isEmpty()) {
                return appState;
            }
        }
        return null;
    }

    public void saveCompletionCode(UserId userId, String completionCode) {
        loadStorage();
        dataStore.setItem(COMPLETION_CODE + "." + userId.toString(), completionCode);
    }

    public String getCompletionCode(UserId userId) {
        loadStorage();
        if (dataStore != null) {
            final String completionCode = getCleanStoredData(COMPLETION_CODE + "." + userId.toString());
            if (!completionCode.isEmpty()) {
                return completionCode;
            }
        }
        return null;
    }

    public UserId getLastUserId() throws UserIdException {
        loadStorage();
        if (Window.Location.getParameter("testuser") != null) {
            return new UserId("testuser-" + Window.Location.getParameter("testuser")); // 
        } else if (Window.Location.getParameter("prolific_pid") != null) {
            return new UserId("prolific_pid-" + Window.Location.getParameter("prolific_pid")); // 
        } else if (dataStore != null) {
            final String storedUserId = getCleanStoredData(getLAST_USER_ID());
            if (!storedUserId.isEmpty()) {
                return new UserId(storedUserId);
            }
        }
        return null;
    }

    public List<UserLabelData> getUserIdList(MetadataField metadataField) {
        final String postName = metadataField.getPostName();
        ArrayList<UserLabelData> userIdList = new ArrayList<>();
        loadStorage();
        if (dataStore != null) {
            for (int itemIndex = 0; itemIndex < dataStore.getLength(); itemIndex++) {
                final String key = dataStore.key(itemIndex);
                if (isUSER_RESULTS(key, postName)) {
                    final String userIdString = key.split("\\.")[2];
                    final String cleanStoredData = getCleanStoredData(key);
//                    if (!cleanStoredData.isEmpty()) {
                    try {
                        userIdList.add(new UserLabelData(new UserId(userIdString), cleanStoredData));
                    } catch (UserIdException exception) {
                        // this should never occur since only UserId opbects should have been stored
                    }
//                    }
                }
            }
        }
        return userIdList;
    }
}
