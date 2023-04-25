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
package nl.mpi.tg.eg.experiment.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import nl.mpi.tg.eg.experiment.client.listener.AppEventListener;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experiment.client.listener.AudioExceptionListener;
import nl.mpi.tg.eg.experiment.client.presenter.Presenter;
import nl.mpi.tg.eg.experiment.client.presenter.ErrorPresenter;
import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
import nl.mpi.tg.eg.experiment.client.exception.AudioException;
import nl.mpi.tg.eg.experiment.client.exception.DataSubmissionException;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.listener.DataSubmissionListener;
import nl.mpi.tg.eg.experiment.client.model.DataSubmissionResult;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.MetadataFieldProvider;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.model.UserResults;
import nl.mpi.tg.eg.experiment.client.presenter.StorageFullPresenter;
import nl.mpi.tg.eg.experiment.client.presenter.TestingVersionPresenter;
import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService;
import nl.mpi.tg.eg.experiment.client.model.ExperimentMetadataFieldProvider;
import nl.mpi.tg.eg.experiment.client.presenter.TemplateVersionPresenter;
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AppController implements AppEventListener/*, AudioExceptionListener*/ {

    protected static final Logger logger = Logger.getLogger(AppController.class.getName());
    private final Version version = GWT.create(Version.class);
    protected static final Messages messages = GWT.create(Messages.class);
    final LocalStorage localStorage = new LocalStorage(messages.appNameInternal());
    final DataSubmissionService submissionService = new DataSubmissionService(localStorage);
    final TimerService timerService = new TimerService();
    protected final RootLayoutPanel widgetTag;
    protected Presenter presenter;
    protected final UserResults userResults;
    final MetadataFieldProvider metadataFieldProvider = new ExperimentMetadataFieldProvider();
    final boolean isDebugMode;
    boolean canAcceptNotifications = false;

    public AppController(RootLayoutPanel widgetTag, String userIdGetParam, boolean disableBrowserStorage) throws UserIdException {
        this.widgetTag = widgetTag;
        boolean obfuscationDisabled = false;
        String debugValue = Window.Location.getParameter("debug");
        isDebugMode = (debugValue != null && !submissionService.isProductionVersion());
        if (isDebugMode || disableBrowserStorage) {
            localStorage.disableObfuscation();
            obfuscationDisabled = true;
        }
        final UserId lastUserId = localStorage.getLastUserId(userIdGetParam);
        if (lastUserId == null) {
            userResults = new UserResults(new UserData());
            // we save the results here so that the newly created user id is preserved even if the user refreshes
            localStorage.storeData(userResults, metadataFieldProvider);
        } else {
            userResults = new UserResults(localStorage.getStoredData(lastUserId, metadataFieldProvider));
        }
        if (hasCordova()) {
            obfuscationDisabled = true; // always disable obfuscation for iOS
        }
        if (obfuscationDisabled) {
            submissionService.submitScreenChange(userResults.getUserData().getUserId(), "obfuscationDisabled");
        }
        if (isDebugMode) {
            submissionService.submitScreenChange(userResults.getUserData().getUserId(), "isDebugMode");
        }
        boolean hasNewMetadata = false;
        for (MetadataField metadataField : metadataFieldProvider.getMetadataFieldArray()) {
            final String postName = metadataField.getPostName();
            String value = Window.Location.getParameter(postName);
            if (value != null) {
                userResults.getUserData().setMetadataValue(metadataField, value);
                hasNewMetadata = true;
            }
        }
        if (hasNewMetadata) {
            // todo: should we transmit this change here
            localStorage.storeData(userResults, metadataFieldProvider);
        }
        if (isDebugMode) {
            try {
                localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.valueOf("xml_about"));
            } catch (IllegalArgumentException iae) {
                // there is not "about" presenter so we have nothing to do here but show the version
                localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.version);
            }
        }
        if (Window.Location.getParameter("version") != null) {
            localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.version);
        }
        addWindowOnBlurEvents();
//        detectWindowDefocus(widgetTag);
    }

    final protected void preventWindowClose(final String messageString) {

        // on page close, back etc. provide a warning that their session will be invalid and they will not be paid etc.
        Window.addWindowClosingHandler(new Window.ClosingHandler() {

            @Override
            public void onWindowClosing(ClosingEvent event) {
                event.setMessage(messageString);
            }
        });

        // on page close, back etc. send a screen event to the server
        Window.addCloseHandler(new CloseHandler<Window>() {

            @Override
            public void onClose(CloseEvent<Window> event) {
                submissionService.submitScreenChange(userResults.getUserData().getUserId(), "BrowserWindowClosed");
                presenter.fireWindowClosing();
            }
        });
    }

    private native void addWindowOnBlurEvents() /*-{
        //console.log('adding focus listeners');
        var appController = this;
        $wnd.addEventListener('blur', function() {
            //console.log('blur');
            appController.@nl.mpi.tg.eg.experiment.client.AppController::submitWindowBlurEvent(Ljava/lang/String;)("WindowFocusLost");
        });
        $wnd.addEventListener('focus', function() {
            //console.log('focus');
            appController.@nl.mpi.tg.eg.experiment.client.AppController::submitWindowBlurEvent(Ljava/lang/String;)("WindowFocusGained");
        });
    }-*/;

    private void submitWindowBlurEvent(String eventString) {
        submissionService.submitScreenChange(userResults.getUserData().getUserId(), eventString);
    }

//    private void detectWindowDefocus(RootLayoutPanel widgetTag) {
//        // this will most likely not work on a non input tag, however we are interested in stats on cases where it does
//        // this method would be usable if the widgetTag is given a tabindex="-1" but $wnd.addEventListener in addWindowOnBlurEvents does the job
//        widgetTag.addHandler(new BlurHandler() {
//
//            @Override
//            public void onBlur(BlurEvent event) {
//                submissionService.submitScreenChange(userResults.getUserData().getUserId(), "widgetTag.onBlur");
//            }
//        }, BlurEvent.getType());
//        widgetTag.addHandler(new FocusHandler() {
//
//            @Override
//            public void onFocus(FocusEvent event) {
//                submissionService.submitScreenChange(userResults.getUserData().getUserId(), "widgetTag.onFocus");
//            }
//        }, FocusEvent.getType());
//    }
//    @Override
//    public void audioExceptionFired(AudioException audioException) {
//        logger.warning(audioException.getMessage());
//        this.presenter = new ErrorPresenter(widgetTag, audioException.getMessage());
//        presenter.setState(this, ApplicationState.start, null);
//    }
    abstract boolean preserveLastState();

    abstract boolean compiledAsTemplate();

    abstract ApplicationState splashPresenter();

    public void start() {
        setBackButtonAction();
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                if (preserveLastState()) {
                    // when the navigation is blocked a new item is added over the last back event 
                    // this prevents both back and forward history actions triggering the back action, however no forward navigation will available unless the back action is to hide/show the stimuli menu, in which case it is ok
                    History.newItem(localStorage.getAppState(userResults.getUserData().getUserId()), false);
                    backAction();
                } else if (event != null) {
                    presenter.savePresenterState();
                    try {
                        // this allows the browser navigation buttons to control the screen shown
                        ApplicationState lastAppState = null;
                        try {
                            lastAppState = ApplicationState.valueOf("xml_" + event.getValue());
                        } catch (IllegalArgumentException iae) {
                            lastAppState = null;
                        }
                        if (lastAppState == null) {
                            try {
                                // if the application state does not exist from the XML then fall back to the built in state if it exists
                                lastAppState = ApplicationState.valueOf(event.getValue());
                            } catch (IllegalArgumentException iae) {
                                lastAppState = null;
                            }
                        }
                        if (lastAppState == null) {
                            requestApplicationState(lastAppState);
                        }
                    } catch (IllegalArgumentException argumentException) {
                        // the application state from the URL cannot be found so there is nothing else to do
                    }
                }
            }
        });
        Window.addWindowClosingHandler(new Window.ClosingHandler() {

            @Override
            public void onWindowClosing(ClosingEvent event) {
                presenter.savePresenterState();
                submissionService.submitAllData(userResults, new DataSubmissionListener() {
                    @Override
                    public void scoreSubmissionFailed(DataSubmissionException exception) {
                    }

                    @Override
                    public void scoreSubmissionComplete(JsArray<DataSubmissionResult> highScoreData) {
                    }
                });
            }
        });
        try {
            submissionService.submitScreenChange(userResults.getUserData().getUserId(), "ApplicationStarted");
            // application specific information
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "projectVersion", version.projectVersion(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "lastCommitDate", version.lastCommitDate().replace("\"", ""), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "compileDate", version.compileDate(), 0);
            // TODO: platform, userAgent and appVersion are being replaced by userAgentData - https://developer.chrome.com/articles/user-agent-client-hints/
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.platform", Window.Navigator.getPlatform(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.userAgent", Window.Navigator.getUserAgent(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.userAgentData", getUserAgentData(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.appVersion", Window.Navigator.getAppVersion(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.appName", Window.Navigator.getAppName(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.appCodeName", Window.Navigator.getAppCodeName(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.cookieEnabled", Boolean.toString(Window.Navigator.isCookieEnabled()), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "storageLength", Integer.toString(localStorage.getStorageLength()), 0);
            if (hasCordova()) {
                // cordova specific information
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "cordovaVersion", getCordovaVersion(), 0);
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "deviceModel", getDeviceModel(), 0);
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "devicePlatform", getDevicePlatform(), 0);
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "deviceUUID", getDeviceUUID(), 0);
                submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "deviceVersion", getDeviceVersion(), 0);
            }
            // force the previously chosen locale if it has been seen before and allow it to be updated if a new locale is on the URL
            // TODO: test this locale preservation on start up, testing should include when a ? get parameter does and does not exist and when a # target does and does not exist in the URL
            final String providedLocale = Window.Location.getParameter("locale");
            if (providedLocale != null) {
                // store the localeName in the local storage for use when reloading without the locale get parameter
                localStorage.setStoredDataValue(userResults.getUserData().getUserId(), "chosenLocale", providedLocale);
            } else {
                String storedLocale = localStorage.getStoredDataValue(userResults.getUserData().getUserId(), "chosenLocale");
                if (!storedLocale.isEmpty()) {
                    final String queryString = Window.Location.getQueryString();
                    final String updatedPathValue;
                    // if there are no values already there then use ? otherwise append with &
                    String separator = (queryString.isEmpty()) ? "?" : "&";
                    updatedPathValue = queryString + separator + "locale=" + storedLocale;
                    Window.Location.replace(Window.Location.getPath() + updatedPathValue);
                    // as we are replacing the location in the browser we do not want to proceed from here
                    return;
                }
            }
            ApplicationState lastAppState = ApplicationState.start;
            try {
                final String appState = localStorage.getAppState(userResults.getUserData().getUserId());
                // if the app state is preserved, then only the last saved state is used
                if ((appState != null)) {
                    try {
                        lastAppState = ApplicationState.valueOf("xml_" + appState);
                    } catch (IllegalArgumentException iae) {
                        try {
                            lastAppState = ApplicationState.valueOf(appState);
                        } catch (IllegalArgumentException iae2) {
                        }
                    }
                }
            } catch (IllegalArgumentException argumentException) {
            }
            if (!preserveLastState() || isDebugMode /* checking for debug mode here and allowing presenter navigation here if true */) {
                // if the history token is valid then that is used otherwise the last saved or the start states are used
                final String token = History.getToken();
                if (token != null) {
                    try {
                        submissionService.submitScreenChange(userResults.getUserData().getUserId(), "usingHistoryToken");
                        // this allows the URL to control the screen shown
                        lastAppState = ApplicationState.valueOf(token);
                    } catch (IllegalArgumentException argumentException) {
                    }
                }
            }
            triggerNotificationCallbacks();
            canAcceptNotifications = true;
            final boolean notificationSetsTarget = checkNotificationCallbacks();
            if (!notificationSetsTarget) {
                lastAppState = (splashPresenter() != null) ? splashPresenter() : lastAppState;
                if (compiledAsTemplate()) {
                    this.presenter = new TemplateVersionPresenter(widgetTag, lastAppState);
                    presenter.setState(this, null, null);
                } else if (!submissionService.isProductionVersion()) {
                    this.presenter = new TestingVersionPresenter(widgetTag, lastAppState);
                    presenter.setState(this, null, null);
                } else {
                    requestApplicationState(lastAppState);
                }
            }
            addKeyboardEvents();
            checkNotificationCallbacks();
        } catch (Exception exception) {
            this.presenter = new StorageFullPresenter(widgetTag, exception.getMessage());
            presenter.setState(this, ApplicationState.start, null);
        }
    }

    public void backAction() {
        presenter.fireBackEvent();
    }

    public void resizeAction() {
        presenter.fireResizeEvent();
    }

    private native void setBackButtonAction() /*-{
     var appController = this;
     $doc.addEventListener("backbutton", function(e){
     e.preventDefault();
     appController.@nl.mpi.tg.eg.experiment.client.AppController::backAction()();
     }, false);
     }-*/;

    private native void addKeyboardEvents() /*-{
     var appController = this;
     if($wnd.Keyboard) {
     $wnd.Keyboard.onshow = function () {
     $doc.getElementById("platformTag").innerHTML = "Keyboard.onshow GWT called";
     appController.@nl.mpi.tg.eg.experiment.client.AppController::resizeAction()();
     }
     $wnd.Keyboard.onhide = function () {
     $doc.getElementById("platformTag").innerHTML = "Keyboard.onhide GWT called";
     appController.@nl.mpi.tg.eg.experiment.client.AppController::resizeAction()();
     }
     }
     }-*/;

    protected native void exitApplication() /*-{
     $doc.navigator.app.exitApp();
     }-*/;

    public native String getUserAgentData() /*-{
      return $wnd.navigator.userAgentData;
    }-*/;

    protected native boolean hasCordova() /*-{
     if ($wnd.device) return true; else return false;
     }-*/;

    protected native String getCordovaVersion() /*-{
     return $wnd.device.cordova;
     }-*/;

    public boolean requestStateFromString(final String targetState) {
        try {
            ApplicationState lastAppState = ApplicationState.valueOf(targetState);
            requestApplicationState(lastAppState);
            return true;
        } catch (IllegalArgumentException argumentException) {
            return false;
        }
    }

    final protected native boolean triggerNotificationCallbacks() /*-{
        if ($wnd.cordova) if ($wnd.cordova.plugins) $wnd.cordova.plugins.notification.local.fireQueuedEvents();
    }-*/;

    final protected native boolean checkNotificationCallbacks() /*-{
        var appController = this;
        var notificationSetsTarget = false;
        console.log("checkNotificationCallbacks");
        if(this.@nl.mpi.tg.eg.experiment.client.AppController::canAcceptNotifications) {
            console.log("canAcceptNotifications");
            if ($wnd.cordova) {
                if ($wnd.cordova.plugins) {
                    if (typeof(Storage) !== "undefined") {
                        var storedNotification = $wnd.localStorage.getItem("NotificationCallback");
                        console.log("storedNotification: " + storedNotification);
                        if (storedNotification !== null) {
                            try {
                                appController.@nl.mpi.tg.eg.experiment.client.AppController::logNotificationFromString(Ljava/lang/String;)("addNotificationCallback: " + storedNotification);
                                notificationSetsTarget = appController.@nl.mpi.tg.eg.experiment.client.AppController::requestStateFromString(Ljava/lang/String;)(storedNotification);
                                $wnd.localStorage.removeItem("NotificationCallback");
                                console.log("cleared storedNotification");
                            } catch (error) {
                                console.error(error);
                            }
                        }
                    }   
                    if (typeof(Storage) !== "undefined") {
                        var enableNotificationCallbacksClick = $wnd.localStorage.getItem("enableNotificationCallbacksClick");
                        if (enableNotificationCallbacksClick !== null) {
                            try {
                                appController.@nl.mpi.tg.eg.experiment.client.AppController::logNotificationFromString(Ljava/lang/String;)(enableNotificationCallbacksClick + " was clicked");
                                $wnd.localStorage.removeItem("enableNotificationCallbacksClick");
                            } catch (error) {
                                console.error(error);
                            }
                        }
                        var enableNotificationCallbacksSchedule = $wnd.localStorage.getItem("enableNotificationCallbacksSchedule");
                        if (enableNotificationCallbacksSchedule !== null) {
                            try {
                                appController.@nl.mpi.tg.eg.experiment.client.AppController::logNotificationFromString(Ljava/lang/String;)(enableNotificationCallbacksSchedule + " was scheduled");
                                $wnd.localStorage.removeItem("enableNotificationCallbacksSchedule");
                            } catch (error) {
                                console.error(error);
                            }
                        }
                        var enableNotificationCallbacksTrigger = $wnd.localStorage.getItem("enableNotificationCallbacksTrigger");
                        if (enableNotificationCallbacksTrigger !== null) {
                            try {
                                appController.@nl.mpi.tg.eg.experiment.client.AppController::logNotificationFromString(Ljava/lang/String;)(enableNotificationCallbacksTrigger + " was triggered");
                                $wnd.localStorage.removeItem("enableNotificationCallbacksTrigger");
                            } catch (error) {
                                console.error(error);
                            }
                        }
                    }
                }
            }
        }
        return notificationSetsTarget;
     }-*/;

    public void logNotificationFromString(final String notification) {
        submissionService.submitImmediateTimestamp(userResults.getUserData().getUserId(), notification, 0);
    }

    final protected native void enableNotificationCallbacks() /*-{
        var appController = this;
        if ($wnd.cordova) {
            if ($wnd.cordova.plugins) {
                if ($wnd.cordova.plugins.notification) {
                    console.log("enableNotificationCallbacks");
        //            $wnd.cordova.plugins.notification.local.fireQueuedEvents();
        //            console.log($wnd.cordova.plugins.notification.local.launchDetails);
                    $wnd.cordova.plugins.notification.local.on("click", function (notification, state) {
                        console.log(notification.id + " was clicked");
                        if (typeof(Storage) !== "undefined") {
                            $wnd.localStorage.setItem("enableNotificationCallbacksClick", notification.id);
                            appController.@nl.mpi.tg.eg.experiment.client.AppController::checkNotificationCallbacks()();
                        }
                    }, this);
                    $wnd.cordova.plugins.notification.local.on("schedule", function (notification, state) {
                        console.log(notification.id + " was scheduled");
                        if (typeof(Storage) !== "undefined") {
                            $wnd.localStorage.setItem("enableNotificationCallbacksSchedule", notification.id);
                            appController.@nl.mpi.tg.eg.experiment.client.AppController::checkNotificationCallbacks()();
                        }
                        }, this);
                    $wnd.cordova.plugins.notification.local.on("trigger", function (notification, state) {
                        console.log(notification.id + " was triggered");
                        if (typeof(Storage) !== "undefined") {
                            $wnd.localStorage.setItem("enableNotificationCallbacksTrigger", notification.id);
                            appController.@nl.mpi.tg.eg.experiment.client.AppController::checkNotificationCallbacks()();
                        }
                    }, this);
        //            // list the currently scheduled notifications as debug output
        //            $wnd.cordova.plugins.notification.local.getScheduled(function (notificationData) {
        //            $.each(notificationData, function(index, value) {console.log(value.text);});
        //            });
//                    appController.@nl.mpi.tg.eg.experiment.client.AppController::logNotificationFromString(Ljava/lang/String;)($wnd.cordova.plugins.notification.local.launchDetails);
                }
            }
        }
     }-*/;

    final protected native void addNotificationCallback(final String targetState) /*-{
        var appController = this;
        if ($wnd.cordova) {
            if ($wnd.cordova.plugins) {
                if ($wnd.cordova.plugins.notification) {
                    console.log("addNotificationCallback", targetState);
                    $wnd.cordova.plugins.notification.local.on(targetState, function(notification, eopts) {
                        console.log("notificationCallback", targetState);
                        console.log(notification, eopts);
                        if (typeof(Storage) !== "undefined") {
                            $wnd.localStorage.setItem("NotificationCallback", targetState);
                            appController.@nl.mpi.tg.eg.experiment.client.AppController::checkNotificationCallbacks()();
                        }
                    });
                }
            }
        }
     }-*/;

    protected native String getDevicePlatform() /*-{
     return $wnd.device.platform;
     }-*/;

    protected native String getDeviceUUID() /*-{
     return $wnd.device.uuid;
     }-*/;

    protected native String getDeviceVersion() /*-{
     return $wnd.device.version;
     }-*/;

    protected native String getDeviceModel() /*-{
     return $wnd.device.model;
     }-*/;
}
