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
import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import java.util.logging.Logger;
import nl.mpi.tg.eg.experiment.client.listener.AudioExceptionListner;
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
import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
import nl.mpi.tg.eg.experiment.client.service.TimerService;

/**
 * @since Oct 7, 2014 11:07:35 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public abstract class AppController implements AppEventListner/*, AudioExceptionListner*/ {

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

    public AppController(RootLayoutPanel widgetTag, String userIdGetParam) throws UserIdException {
        this.widgetTag = widgetTag;
        boolean obfuscationDisabled = false;
        String debugValue = Window.Location.getParameter("debug");
        if (debugValue != null && !submissionService.isProductionVersion()) {
            localStorage.disableObfuscation();
            obfuscationDisabled = true;
            isDebugMode = true;
        } else {
            isDebugMode = false;
        }
        final UserId lastUserId = localStorage.getLastUserId(userIdGetParam);
        if (lastUserId == null) {
            userResults = new UserResults(new UserData());
            // we save the results here so that the newly created user id is preserved even if the user refreshes
            localStorage.storeData(userResults, metadataFieldProvider);
        } else {
            userResults = new UserResults(localStorage.getStoredData(lastUserId, metadataFieldProvider));
        }
        if (obfuscationDisabled) {
            submissionService.submitScreenChange(userResults.getUserData().getUserId(), "obfuscationDisabled");
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
        if (debugValue != null) {
            try {
                localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.valueOf("about"));
            } catch (IllegalArgumentException iae) {
                // there is not "about" presenter so we have nothing to do here but show the version
                localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.version);
            }
        }
        if (Window.Location.getParameter("version") != null) {
            localStorage.saveAppState(userResults.getUserData().getUserId(), ApplicationState.version);
        }
//        detectWindowDefocus(widgetTag);
    }

    final protected void preventWindowClose(final String messageString) {

        // on page close, back etc. provide a warning that their session will be invalide and they will not be paid etc.
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

//    private void detectWindowDefocus(RootLayoutPanel widgetTag) {
//        // this will most likely not work on a non input tag, however we are interested in stats on cases where it does
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
                        ApplicationState lastAppState = ApplicationState.valueOf(event.getValue());
                        requestApplicationState(lastAppState);
                    } catch (IllegalArgumentException argumentException) {
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
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.platform", Window.Navigator.getPlatform(), 0);
            submissionService.submitTagValue(userResults.getUserData().getUserId(), "ApplicationStarted", "navigator.userAgent", Window.Navigator.getUserAgent(), 0);
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
            ApplicationState lastAppState = ApplicationState.start;
            try {
                final String appState = localStorage.getAppState(userResults.getUserData().getUserId());
                // if the app state is preserved, then only the last saved state is used
                lastAppState = (appState != null) ? ApplicationState.valueOf(appState) : lastAppState;
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
            lastAppState = (splashPresenter() != null) ? splashPresenter() : lastAppState;
            if (!submissionService.isProductionVersion()) {
                this.presenter = new TestingVersionPresenter(widgetTag, lastAppState);
                presenter.setState(this, null, null);
            } else {
                requestApplicationState(lastAppState);
            }
            addKeyboardEvents();
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

    protected native boolean hasCordova() /*-{
     if ($wnd.device) return true; else return false;
     }-*/;

    protected native String getCordovaVersion() /*-{
     return $wnd.device.cordova;
     }-*/;

    public void requestStateFromString(final String targetState) {
        try {
            ApplicationState lastAppState = ApplicationState.valueOf(targetState);
            requestApplicationState(lastAppState);
        } catch (IllegalArgumentException argumentException) {
        }
    }

    final protected native void enableNotificationCallbacks() /*-{
        var appController = this;
        if ($wnd.cordova) {
            if ($wnd.cordova.plugins) {
                if (typeof(Storage) !== "undefined") {
                    var enableNotificationCallbacksClick = localStorage.getItem("enableNotificationCallbacksClick");
                    localStorage.removeItem("enableNotificationCallbacksClick");
                    if (enableNotificationCallbacksClick !== "undefined") {
                        appController.submissionService.submitTimestamp(userResults.getUserData().getUserId(), enableNotificationCallbacksClick + " was clicked", 0);
                    }
                    var enableNotificationCallbacksSchedule = localStorage.getItem("enableNotificationCallbacksSchedule");
                    localStorage.removeItem("enableNotificationCallbacksSchedule");
                    if (enableNotificationCallbacksSchedule !== "undefined") {
                        appController.submissionService.submitTimestamp(userResults.getUserData().getUserId(), enableNotificationCallbacksSchedule + " was scheduled", 0);
                    }
                    var enableNotificationCallbacksTrigger = localStorage.getItem("enableNotificationCallbacksTrigger");
                    localStorage.removeItem("enableNotificationCallbacksTrigger");
                    if (enableNotificationCallbacksTrigger !== "undefined") {
                        appController.submissionService.submitTimestamp(userResults.getUserData().getUserId(), enableNotificationCallbacksTrigger + " was triggered", 0);
                    }
                }
                if ($wnd.cordova.plugins.notification) {
                    console.log("enableNotificationCallbacks");
        //            $wnd.cordova.plugins.notification.local.fireQueuedEvents();
        //            console.log($wnd.cordova.plugins.notification.local.launchDetails);
                    $wnd.cordova.plugins.notification.local.on("click", function (notification, state) {
                        console.log(notification.id + " was clicked");
                        if (typeof(Storage) !== "undefined") {
                            localStorage.setItem("enableNotificationCallbacksClick", notification.id);
                        }
                    }, this);
                    $wnd.cordova.plugins.notification.local.on("schedule", function (notification, state) {
                        console.log(notification.id + " was scheduled");
                        if (typeof(Storage) !== "undefined") {
                            localStorage.setItem("enableNotificationCallbacksSchedule", notification.id);
                        }
                    }, this);
                    $wnd.cordova.plugins.notification.local.on("trigger", function (notification, state) {
                        console.log(notification.id + " was triggered");
                        if (typeof(Storage) !== "undefined") {
                            localStorage.setItem("enableNotificationCallbacksTrigger", notification.id);
                        }
                    }, this);
        //            // list the currently scheduled notifications as debug output
        //            $wnd.cordova.plugins.notification.local.getScheduled(function (notificationData) {
        //            $.each(notificationData, function(index, value) {console.log(value.text);});
        //            });
                }
            }
        }
     }-*/;

    final protected native void addNotificationCallback(final String targetState) /*-{
        var appController = this;
        if ($wnd.cordova) {
            if ($wnd.cordova.plugins) {
                if (typeof(Storage) !== "undefined") {
                    var storedNotification = localStorage.getItem("NotificationCallback");
                    localStorage.removeItem("NotificationCallback");
                    if (storedNotification !== "undefined") {
                        appController.submissionService.submitTimestamp(userResults.getUserData().getUserId(), "addNotificationCallback: " + storedNotification, 0);
                        appController.@nl.mpi.tg.eg.experiment.client.AppController::requestStateFromString(Ljava/lang/String;)(storedNotification);
                    }
                }   
                if ($wnd.cordova.plugins.notification) {
                    console.log("addNotificationCallback", targetState);
                    $wnd.cordova.plugins.notification.local.on(targetState, function(notification, eopts) {
                        console.log("notificationCallback", targetState);
                        console.log(notification, eopts);
                        if (typeof(Storage) !== "undefined") {
                            localStorage.setItem("NotificationCallback", targetState);
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
