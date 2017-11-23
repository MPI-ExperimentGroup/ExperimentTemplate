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
    private final String APP_STATE;
    private final String USER_RESULTS;
    private final String LAST_USER_ID;
    private final String GAME_DATA; // todo: perhaps merge game and screen data concepts
    private final String STIMULI_DATA;
    private final String SCREEN_DATA;
//    private final String STOWED_DATA; // todo: send the stowed data to the server when the user has completed the entire application
//    private final String FAILED_DATA;
    protected final String CONNECTION = "userId";
    protected final String MAX_SCORE;
    protected final String GAMES_PLAYED;
    protected final String CURRENT_POTENTIAL;
    protected final String TOTAL_POTENTIAL;
    protected final String CURRENT_SCORE;
    protected final String TOTAL_SCORE;
    private final String COMPLETION_CODE;
    final MetadataFieldProvider metadataFieldProvider = new MetadataFieldProvider();

    public LocalStorage() {
        APP_STATE = messages.appNameInternal() + ".AppState.";
        USER_RESULTS = messages.appNameInternal() + ".UserResults.";
        LAST_USER_ID = messages.appNameInternal() + ".LastUserId";
        GAME_DATA = messages.appNameInternal() + ".GameData.";
        STIMULI_DATA = messages.appNameInternal() + ".StimuliData.";
        SCREEN_DATA = messages.appNameInternal() + ".ScreenData.";
//        STOWED_DATA = messages.appNameInternal() + ".SentData.";
//        FAILED_DATA = messages.appNameInternal() + ".FailedData.";
        MAX_SCORE = messages.appNameInternal() + ".maxScore";
        GAMES_PLAYED = messages.appNameInternal() + ".gamesPlayed";
        CURRENT_POTENTIAL = messages.appNameInternal() + ".currentPotential";
        TOTAL_POTENTIAL = messages.appNameInternal() + ".totalPotential";
        CURRENT_SCORE = messages.appNameInternal() + ".currentScore";
        TOTAL_SCORE = messages.appNameInternal() + ".totalScore";
        COMPLETION_CODE = messages.appNameInternal() + ".completionCode";
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
                if (key.startsWith(messages.appNameInternal())) { // && key.contains(userId.toString())) {
                    dataStore.removeItem(key);
                }
            }
        }
//        dataStore.setItem(APP_STATE, "");
//        dataStore.setItem(USER_RESULTS + userId.toString(), "");
//        dataStore.setItem(LAST_USER_ID + userId.toString(), "");
//        dataStore.setItem(GAME_DATA + userId.toString(), "");
//        dataStore.setItem(SCREEN_DATA + userId.toString(), "");
//        dataStore.setItem(STOWED_DATA + userId.toString(), "");
//        dataStore.setItem(MAX_SCORE + userId.toString(), "");
//        dataStore.setItem(GAMES_PLAYED + userId.toString(), "");
//        dataStore.setItem(COMPLETION_CODE + userId.toString(), "");
//
//        dataStore.removeItem(APP_STATE);
//        dataStore.removeItem(USER_RESULTS + userId.toString());
//        dataStore.removeItem(LAST_USER_ID + userId.toString());
//        dataStore.removeItem(GAME_DATA + userId.toString());
//        dataStore.removeItem(SCREEN_DATA + userId.toString());
//        dataStore.removeItem(STOWED_DATA + userId.toString());
//        dataStore.removeItem(MAX_SCORE + userId.toString());
//        dataStore.removeItem(GAMES_PLAYED + userId.toString());
//        dataStore.removeItem(COMPLETION_CODE + userId.toString());
    }

    public String getStoredGameData(UserId userId) {
        loadStorage();
        return getCleanStoredData(GAME_DATA + userId.toString());
    }

//    public String getSowedData(UserId userId) {
//        loadStorage();
//        return getCleanStoredData(STOWED_DATA + userId.toString());
//    }
    public void addStoredGameData(UserId userId, String serialisedGameData) {
        loadStorage();
        dataStore.setItem(GAME_DATA + userId.toString(), getCleanStoredData(GAME_DATA + userId.toString()) + serialisedGameData);
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
        return getCleanStoredData(SCREEN_DATA + endpoint + "." + userId.toString());
    }

    public void deleteStoredScreenData(UserId userId, String endpoint, String segmentToDelete) {
        loadStorage();
        final String sentStoredData = getCleanStoredData(SCREEN_DATA + endpoint + "." + userId.toString());
        String remainingStoredData = sentStoredData.replaceFirst("^,", "") + ",";
// replacing this segment will sometimes fail due to non matching strings, but the result of failure is only a second transmission of the data which is a preferred option over complexity
        for (String segment : segmentToDelete.split("\\},\\{")) {
            segment = (segment.startsWith("{")) ? segment : "{" + segment;
            segment = segment.replaceFirst(",$", "");
            segment = (segment.endsWith("}")) ? segment + "," : segment + "},";
            remainingStoredData = remainingStoredData.replace(segment, "");
        }
        dataStore.setItem(SCREEN_DATA + endpoint + "." + userId.toString(), remainingStoredData.replaceFirst("^,", "").replaceFirst(",$", ""));
//        stowSentData(userId, segmentToDelete);
    }

    public JSONObject getStoredJSONObject(UserId userId, Stimulus stimulus) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(STIMULI_DATA + userId.toString() + "." + stimulus.getUniqueId());
        if (cleanStoredData.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) JSONParser.parseStrict(cleanStoredData);
        return jsonObject;
    }

    public void setStoredJSONObject(UserId userId, Stimulus stimulus, JSONObject jSONObject) {
        loadStorage();
        dataStore.setItem(STIMULI_DATA + userId.toString() + "." + stimulus.getUniqueId(), jSONObject.toString());
        // with android versions this JSON data is saved to the SDcard via the StimulusPresenter
    }

    public String getStoredDataValue(UserId userId, String label) {
        loadStorage();
        return getCleanStoredData(GAME_DATA + label + "." + userId.toString());
    }

    public void deleteStoredDataValue(UserId userId, String label) {
        loadStorage();
        dataStore.removeItem(GAME_DATA + label + "." + userId.toString());
    }

    public void appendStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(GAME_DATA + label + "." + userId.toString());
        dataStore.setItem(GAME_DATA + label + "." + userId.toString(), cleanStoredData + value);
    }

    public void setStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        dataStore.setItem(GAME_DATA + label + "." + userId.toString(), value);
    }

    public void addStoredScreenData(UserId userId, String endpoint, String serialisedScreenData) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(SCREEN_DATA + endpoint + "." + userId.toString());
        if (cleanStoredData.isEmpty()) {
            dataStore.setItem(SCREEN_DATA + endpoint + "." + userId.toString(), cleanStoredData + serialisedScreenData);
        } else {
            dataStore.setItem(SCREEN_DATA + endpoint + "." + userId.toString(), cleanStoredData + "," + serialisedScreenData);
        }
    }

    public UserData getStoredData(UserId userId) throws UserIdException {
        UserData userData = new UserData(userId);
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                userData.setMetadataValue(metadataField, getCleanStoredData(USER_RESULTS + userData.getUserId().toString() + "." + metadataField.getPostName()));
                final String cleanedUserId = getCleanStoredData(USER_RESULTS + userData.getUserId().toString() + "." + metadataField.getPostName() + "." + CONNECTION);
                if (cleanedUserId != null && !cleanedUserId.isEmpty()) {
                    userData.setMetadataConnection(metadataField, new UserId(cleanedUserId));
                }
            }
        }
        userData.updateBestScore(getCleanStoredDouble(USER_RESULTS + userData.getUserId().toString() + "." + MAX_SCORE));
        userData.setGamesPlayed(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + GAMES_PLAYED));
        userData.setCurrentScore(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + CURRENT_SCORE));
        userData.setTotalScore(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + TOTAL_SCORE));
        userData.setPotentialScore(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + CURRENT_POTENTIAL));
        userData.setTotalPotentialScore(getCleanStoredInt(USER_RESULTS + userData.getUserId().toString() + "." + TOTAL_POTENTIAL));
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
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + MAX_SCORE, Double.toString(userResults.getUserData().getBestScore()));
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + GAMES_PLAYED, Integer.toString(userResults.getUserData().getGamesPlayed()));
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + CURRENT_SCORE, Integer.toString(userResults.getUserData().getCurrentScore()));
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + TOTAL_SCORE, Integer.toString(userResults.getUserData().getTotalScore()));
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + CURRENT_POTENTIAL, Integer.toString(userResults.getUserData().getPotentialScore()));
        dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + TOTAL_POTENTIAL, Integer.toString(userResults.getUserData().getTotalPotentialScore()));
    }

    public void storeData(UserResults userResults) {
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.metadataFieldArray) {
                dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + metadataField.getPostName(), userResults.getUserData().getMetadataValue(metadataField));
                final UserId metadataConnection = userResults.getUserData().getMetadataConnection(metadataField);
                if (metadataConnection != null && !metadataConnection.toString().isEmpty()) {
                    dataStore.setItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + metadataField.getPostName() + "." + CONNECTION, metadataConnection.toString());
                } else {
                    dataStore.removeItem(USER_RESULTS + userResults.getUserData().getUserId().toString() + "." + metadataField.getPostName() + "." + CONNECTION);
                }
            }
        }
        storeUserScore(userResults);
        if ((Window.Location.getParameter("testuser") == null)) {
            // only store the last user id if the id is not a URL defined test user
            dataStore.setItem(LAST_USER_ID, userResults.getUserData().getUserId().toString());
        }
    }

    public void saveAppState(UserId userId, String appState) {
        loadStorage();
        dataStore.setItem(APP_STATE + "." + userId.toString(), appState);
    }

    public String getAppState(UserId userId) {
        loadStorage();
        if (dataStore != null) {
            final String appState = getCleanStoredData(APP_STATE + "." + userId.toString());
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
            final String storedUserId = getCleanStoredData(LAST_USER_ID);
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
                if (key.startsWith(USER_RESULTS) && key.endsWith(postName)) {
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
