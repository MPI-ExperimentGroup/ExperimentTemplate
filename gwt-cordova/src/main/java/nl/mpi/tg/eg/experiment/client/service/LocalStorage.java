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

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.List;
import nl.mpi.tg.eg.experiment.client.ApplicationController;
import nl.mpi.tg.eg.experiment.client.exception.LocalStorageException;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserLabelData;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import nl.mpi.tg.eg.experiment.client.model.MetadataFieldProvider;

/**
 * @since Oct 24, 2014 3:01:35 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class LocalStorage implements LocalStorageInterface {

    protected final String appNameInternal;
    protected boolean enableObfuscation = true;
    protected ObfuscatedStorage dataStore = null;
    protected final String MAX_SCORE = "maxScore";
    protected final String GAMES_PLAYED = "gamesPlayed";
    protected final String CURRENT_POTENTIAL = "currentPotential";
    protected final String TOTAL_POTENTIAL = "totalPotential";
    protected final String CURRENT_SCORE = "currentScore";
    protected final String CURRENT_CORRECT_STREAK = "correctStreak";
    protected final String CURRENT_ERROR_STREAK = "errorStreak";
    protected final String TOTAL_SCORE = "totalScore";
    protected final String MAX_ERRORS = "maxErrors";
    protected final String MAX_POTENTIAL = "maxPotential";
    protected final String MAX_CORRECT_STREAK = "maxCorrectStreak";
    protected final String MAX_ERROR_STREAK = "maxErrorStreak";
    private LocalStorageException localStorageException = null;

    public LocalStorage(final String appNameInternal/*, final boolean enableObfuscation*/) {
        this.appNameInternal = appNameInternal;
    }

    public void disableObfuscation() {
        enableObfuscation = false;
        dataStore = null;
    }

    protected ObfuscatedStorage loadStorage() {
        if (dataStore == null) {
            dataStore = new ObfuscatedStorage(appNameInternal, enableObfuscation).loadStorage();
        }
        return dataStore;
    }

    public int getStorageLength() {
        return loadStorage().getStorageLength();
    }

    public void clearUserData(UserId userId) {
        loadStorage();
        // todo: it would be good to do this on an application basis
        if (dataStore != null) {
            dataStore.clearUserData(userId);
        }
    }

    public String getStoredGameData(UserId userId) {
        loadStorage();
        return getCleanStoredData(dataStore.getGAME_DATA(userId));
    }

//    public String getSowedData(UserId userId) {
//        loadStorage();
//        return getCleanStoredData(STOWED_DATA + userId.toString());
//    }
    public void addStoredGameData(UserId userId, String serialisedGameData) {
        loadStorage();
        dataStoreSetItem(dataStore.getGAME_DATA(userId), getCleanStoredData(dataStore.getGAME_DATA(userId)) + serialisedGameData);
    }

//    public void addFailedData(String serialisedGameData) {
//        loadStorage();
//        dataStoreSetItem(FAILED_DATA, getCleanStoredData(FAILED_DATA) + serialisedGameData);
//    }
//    private void stowSentData(UserId userId, String sendData) {
//        loadStorage();
//        final String sentStoredData = getCleanStoredData(STOWED_DATA + userId.toString());
//        if (sentStoredData.isEmpty()) {
//            dataStoreSetItem(STOWED_DATA + userId.toString(), sendData);
//        } else {
//            dataStoreSetItem(STOWED_DATA + userId.toString(), sentStoredData + "," + sendData);
//        }
//    }
    public String getStoredScreenData(UserId userId, String endpoint) {
        loadStorage();
        return getCleanStoredData(dataStore.getSCREEN_DATA(endpoint, userId));
    }

    public void deleteStoredScreenData(UserId userId, String endpoint, String segmentToDelete) {
        loadStorage();
        final String storedData = getCleanStoredData(dataStore.getSCREEN_DATA(endpoint, userId));
        String remainingStoredData = removeSubmittedPortion(segmentToDelete, storedData);
        dataStoreSetItem(dataStore.getSCREEN_DATA(endpoint, userId), remainingStoredData);
//        stowSentData(userId, segmentToDelete);
    }

    private String removeSegment(String segment, String remainingStoredData) {
        int startIndex = 0;
        int endIndex = 0;
        for (int index = 0; index < remainingStoredData.length() && segment.length() > (index - startIndex); index++) {
            if (remainingStoredData.charAt(index) == segment.charAt(index - startIndex)) {
                endIndex = index;
            } else {
                startIndex = index;
                endIndex = index;
            }
        }
        if (startIndex == endIndex) {
            return remainingStoredData;
        } else {
            return remainingStoredData.substring(0, startIndex) + remainingStoredData.substring(endIndex + 1);
        }
    }

    protected String removeSubmittedPortion(final String segmentToDelete, final String storedData) {
        String remainingStoredData = storedData.replaceFirst("^,", "") + ",";
        // replacing this segment will sometimes fail due to non matching strings, but the result of failure is only a second transmission of the data which is a preferred option over complexity
        for (String segment : segmentToDelete.split("\\},\\{")) {
            segment = (segment.startsWith("{")) ? segment : "{" + segment;
            segment = segment.replaceFirst(",$", "");
            segment = (segment.endsWith("}")) ? segment + "," : segment + "},";
//            remainingStoredData = remainingStoredData.replace(segment, "");
            remainingStoredData = removeSegment(segment, remainingStoredData);
        }
        return remainingStoredData.replaceFirst("^,", "").replaceFirst(",$", "");
    }

    public JSONObject getStoredJSONObject(UserId userId, Stimulus stimulus) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(dataStore.getSTIMULI_DATA(userId, stimulus));
        if (cleanStoredData.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) JSONParser.parseStrict(cleanStoredData);
        return jsonObject;
    }

    @Override
    public String getStoredStimulusValue(UserId userId, String stimulusId, String responseKey) {
        String resultString = "";
        final JSONObject storedJSONObject = getStoredJSONObject(userId, stimulusId);
        if (storedJSONObject != null) {
            if (responseKey == null || responseKey.isEmpty()) {
                for (String currentKey : storedJSONObject.keySet()) {
                    final JSONValue currentValue = storedJSONObject.get(currentKey);
                    if (currentValue != null) {
                        resultString += currentValue.toString().replaceAll("(^\")|(\"$)", "");
                    }
                }
            } else {
                final JSONValue currentValue = storedJSONObject.get(responseKey);
                if (currentValue != null) {
                    resultString += currentValue.toString().replaceAll("(^\")|(\"$)", "");
                }
            }
        }
        return resultString;
    }

    public JSONObject getStoredJSONObject(UserId userId, String stimulusId) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(dataStore.getSTIMULI_DATA(userId, stimulusId));
        if (cleanStoredData.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) JSONParser.parseStrict(cleanStoredData);
        return jsonObject;
    }

    public void setStoredJSONObject(UserId userId, Stimulus stimulus, JSONObject jSONObject) {
        loadStorage();
        dataStoreSetItem(dataStore.getSTIMULI_DATA(userId, stimulus), jSONObject.toString());
        // with android versions this JSON data is saved to the SDcard via the StimulusPresenter
    }

    public boolean getDataAgreementValue(final UserId userId, final MetadataFieldProvider metadataFieldProvider) {
        if (metadataFieldProvider.getDataAgreementFieldName() == null) {
            // when the administration/dataAgreementField/@fieldName is not set in the configuration file then permission is always true
            return true;
        } else if (metadataFieldProvider.getDataAgreementMatch() == null) {
            // when the administration/dataAgreementField/@fieldName exists in the configuration file but has no matchingRegex then permission is always false
            return false;
        } else {
            loadStorage();
            final String agreementValue = getCleanStoredData(dataStore.getUSER_METADATA(userId, metadataFieldProvider.getDataAgreementFieldName()));
            return metadataFieldProvider.getDataAgreementMatch().equals(agreementValue);
        }
    }

    public String getStoredDataValue(UserId userId, String label) {
        loadStorage();
        return getCleanStoredData(dataStore.getGAME_DATA(label, userId));
    }

    public void deleteStoredDataValue(UserId userId, String label) {
        loadStorage();
        dataStore.removeItem(dataStore.getGAME_DATA(label, userId));
    }

    public void appendStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        final String cleanStoredData = getCleanStoredData(dataStore.getGAME_DATA(label, userId));
        dataStoreSetItem(dataStore.getGAME_DATA(label, userId), cleanStoredData + value);
    }

    public void setStoredDataValue(UserId userId, String label, String value) {
        loadStorage();
        dataStoreSetItem(dataStore.getGAME_DATA(label, userId), value);
    }

    public void addStoredScreenData(UserId userId, String endpoint, String serialisedScreenData) {
        if (serialisedScreenData != null && !serialisedScreenData.isEmpty()) {
            loadStorage();
            final String cleanStoredData = getCleanStoredData(dataStore.getSCREEN_DATA(endpoint, userId));
            if (cleanStoredData.isEmpty()) {
                dataStoreSetItem(dataStore.getSCREEN_DATA(endpoint, userId), cleanStoredData + serialisedScreenData);
            } else {
                dataStoreSetItem(dataStore.getSCREEN_DATA(endpoint, userId), cleanStoredData + "," + serialisedScreenData);
            }
        }
    }

    public UserData getStoredData(UserId userId, final MetadataFieldProvider metadataFieldProvider) throws UserIdException {
        UserData userData = new UserData(userId);
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.getMetadataFieldArray()) {
                userData.setMetadataValue(metadataField, getCleanStoredData(dataStore.getUSER_METADATA(userData.getUserId(), metadataField.getPostName())));
                final String cleanedUserIds = getCleanStoredData(dataStore.getUSER_METADATA_CONNECTION(userData.getUserId(), metadataField.getPostName()));
                if (cleanedUserIds != null && !cleanedUserIds.isEmpty()) {
                    List<UserId> userIdList = new ArrayList<>();
                    for (String cleanedUserId : cleanedUserIds.split(",")) {
                        userIdList.add(new UserId(cleanedUserId));
                    }
                    userData.setMetadataConnection(metadataField, userIdList);
                }
            }
            userData.clearMetadataChanged();
        }
        userData.updateMaxScore(getCleanStoredDouble(dataStore.getUSER_RESULTS(userData.getUserId(), MAX_SCORE)),
                getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), MAX_ERRORS)),
                getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), MAX_POTENTIAL)),
                getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), MAX_CORRECT_STREAK)),
                getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), MAX_ERROR_STREAK)));
        userData.setGamesPlayed(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), GAMES_PLAYED)));
        userData.setCurrentScore(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), CURRENT_SCORE)));
        userData.setCorrectStreak(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), CURRENT_CORRECT_STREAK)));
        userData.setErrorStreak(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), CURRENT_ERROR_STREAK)));
        userData.setTotalScore(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), TOTAL_SCORE)));
        userData.setPotentialScore(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), CURRENT_POTENTIAL)));
        userData.setTotalPotentialScore(getCleanStoredInt(dataStore.getUSER_RESULTS(userData.getUserId(), TOTAL_POTENTIAL)));
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
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_SCORE), Double.toString(userResults.getUserData().getMaxScore()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_ERRORS), Integer.toString(userResults.getUserData().getMaxErrors()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_POTENTIAL), Integer.toString(userResults.getUserData().getMaxPotentialScore()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_CORRECT_STREAK), Integer.toString(userResults.getUserData().getMaxCorrectStreak()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), MAX_ERROR_STREAK), Integer.toString(userResults.getUserData().getMaxErrorStreak()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), GAMES_PLAYED), Integer.toString(userResults.getUserData().getGamesPlayed()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_SCORE), Integer.toString(userResults.getUserData().getCurrentScore()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_CORRECT_STREAK), Integer.toString(userResults.getUserData().getCorrectStreak()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_ERROR_STREAK), Integer.toString(userResults.getUserData().getErrorStreak()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), TOTAL_SCORE), Integer.toString(userResults.getUserData().getTotalScore()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), CURRENT_POTENTIAL), Integer.toString(userResults.getUserData().getPotentialScore()));
        dataStoreSetItem(dataStore.getUSER_RESULTS(userResults.getUserData().getUserId(), TOTAL_POTENTIAL), Integer.toString(userResults.getUserData().getTotalPotentialScore()));
    }

    public void storeData(UserResults userResults, final MetadataFieldProvider metadataFieldProvider) {
        boolean notMockUser = (Window.Location.getParameter("mockuser") == null); // only store the last user id if the id is not a URL defined mock user
        storeData(userResults, metadataFieldProvider, notMockUser);
    }

    public void storeData(UserResults userResults, final MetadataFieldProvider metadataFieldProvider, boolean notMockUser) {
        loadStorage();
        if (dataStore != null) {
            for (MetadataField metadataField : metadataFieldProvider.getMetadataFieldArray()) {
                dataStoreSetItem(dataStore.getUSER_METADATA(userResults.getUserData().getUserId(), metadataField.getPostName()), userResults.getUserData().getMetadataValue(metadataField));
                final List<UserId> metadataConnections = userResults.getUserData().getMetadataConnection(metadataField);
                if (metadataConnections != null && !metadataConnections.isEmpty()) {
                    String metadataConnectionString = "";
                    for (UserId userId : metadataConnections) {
                        if (metadataConnectionString.length() > 0) {
                            metadataConnectionString += ",";
                        }
                        metadataConnectionString += userId.toString();
                    }
                    dataStoreSetItem(dataStore.getUSER_METADATA_CONNECTION(userResults.getUserData().getUserId(), metadataField.getPostName()), metadataConnectionString);
                } else {
                    dataStore.removeItem(dataStore.getUSER_METADATA_CONNECTION(userResults.getUserData().getUserId(), metadataField.getPostName()));
                }
            }
        }
        storeUserScore(userResults);
        if (notMockUser) {
            // only store the last user id if the id is not a URL defined mock user
            dataStoreSetItem(dataStore.getLAST_USER_ID(), userResults.getUserData().getUserId().toString());
        }
    }

    public void saveAppState(UserId userId, ApplicationController.ApplicationState appState) {
        loadStorage();
        if ((Window.Location.getParameter("mockuser") == null)) {
            // only store the last user id if the id is not a URL defined mock user
            dataStoreSetItem(dataStore.getLAST_USER_ID(), userId.toString());
        }
        dataStoreSetItem(dataStore.getAPP_STATE(userId), appState.selfName);
    }

    public String getAppState(UserId userId) {
        loadStorage();
        if (dataStore != null) {
            final String appState = getCleanStoredData(dataStore.getAPP_STATE(userId));
            if (!appState.isEmpty()) {
                return appState;
            }
        }
        return null;
    }

    public void saveCompletionCode(UserId userId, String completionCode) {
        loadStorage();
        dataStoreSetItem(dataStore.getCOMPLETION_CODE(userId), completionCode);
    }

    @Override
    public String getCompletionCode(UserId userId) {
        loadStorage();
        if (dataStore != null) {
            final String completionCode = getCleanStoredData(dataStore.getCOMPLETION_CODE(userId));
            if (!completionCode.isEmpty()) {
                return completionCode;
            }
        }
        return null;
    }

    public UserId getLastUserId(String userIdGetParam) throws UserIdException {
        loadStorage();
        // todo: enable this field name "prolific_pid" being set in the configuration 
        if (Window.Location.getParameter("mockuser") != null) {
            if (dataStore.getLAST_USER_ID() == null) {
                // while unlikely if there is no last user id then we must set one here
                dataStoreSetItem(dataStore.getLAST_USER_ID(), new UserId().toString());
            }
            return new UserId("mockuser-" + getCleanStoredData(dataStore.getLAST_USER_ID()) + "-" + Window.Location.getParameter("mockuser")); // 
        } else if (Window.Location.getParameter("prolific_pid") != null) {
            return new UserId("prolific_pid-" + Window.Location.getParameter("prolific_pid")); // 
        } else if (userIdGetParam != null && !userIdGetParam.isEmpty() && Window.Location.getParameter(userIdGetParam) != null) {
            return new UserId(userIdGetParam + "-" + Window.Location.getParameter(userIdGetParam)); // 
        } else if (dataStore != null) {
            final String storedUserId = getCleanStoredData(dataStore.getLAST_USER_ID());
            if (!storedUserId.isEmpty()) {
                return new UserId(storedUserId);
            }
        }
        return null;
    }

    public List<UserLabelData> getUserIdList(MetadataField labelMetadataField) {
        final String postName = labelMetadataField.getPostName();
        ArrayList<UserLabelData> userIdList = new ArrayList<>();
        loadStorage();
        if (dataStore != null) {
            for (int itemIndex = 0; itemIndex < dataStore.getLength(); itemIndex++) {
                final String key = dataStore.key(itemIndex);
                final String userIdString = dataStore.isUSER_METADATA(key, postName);
                if (userIdString != null) {
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

    protected void dataStoreSetItem(String key, String data) {
        try {
            dataStore.setItem(key, data);
        } catch (LocalStorageException exception) {
            localStorageException = exception;
        } catch (Exception domException) {
            // Because the storage of data is done in so many autogenerated sections of code it is impractical to catch and nicely handle them all, 
            // such a change would also be application wide and be very difficult to verify that it does not change existing behaviour
            // however it is important that in the case of an error participant data collection should be terminated because the data cannot be recorded
            // as a result the application will check for for the error state before showing stimuli or changing presenters etc.
            localStorageException = new LocalStorageException(domException.getMessage());
        }
    }

    public void checkStorageException() throws LocalStorageException {
        if (localStorageException != null) {
            // any recorded localStorageException should not be cleared, only a page reload should reset the error state
            throw localStorageException;
        }
    }
}
