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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;
import java.util.Date;
import java.util.Random;

/**
 * @since 6 June, 2019 17:09:32 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class LocalNotifications {

    protected void setNotification(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final String notificationCommand) {
        logNotificationRequest(notificationCommand);
        clearNotifications();
        setNotificationLater(notificationTitle, notificationText, notificationActions, notificationCommand);
    }

    protected void setNotificationLater(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final String notificationCommand) {
        logNotificationRequest(notificationCommand);
        Timer timer = new Timer() {
            @Override
            public void run() {
                setNotificationRun(notificationTitle, notificationText, notificationActions, notificationCommand);
            }
        };
        timer.schedule(100);
    }

    protected void setNotificationRun(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final String notificationCommand) {
        int notificationCount = 0;
        for (final String currentEntry : notificationCommand.split(" ")) {
            boolean onWeekends = false;
            logNotificationRequest(currentEntry);
            final String[] currentParts = currentEntry.split(":");
            if (currentParts.length > 1) {
                switch (currentParts[0]) {
                    case "in_minutes":
                        setNotificationInMinutes(notificationCount, notificationTitle, notificationText /*+ "\n" + currentEntry*/, notificationActions, Integer.parseInt(currentParts[1]));
                        notificationCount++;
                        break;
                    case "weekends_between":
                        onWeekends = true;
                    case "weekdays_between":
                        if (currentParts.length > 5) {
                            if (!currentParts[1].isEmpty() && !currentParts[2].isEmpty() && !currentParts[3].isEmpty() && !currentParts[4].isEmpty() && !currentParts[5].isEmpty()) {
                                for (int[] repetitionTimes : findNotificationRepetitions(Integer.parseInt(currentParts[1]), Integer.parseInt(currentParts[2]), Integer.parseInt(currentParts[3]), Integer.parseInt(currentParts[4]), Integer.parseInt(currentParts[5]))) {
                                    notificationCount += findNotificationDays(notificationCount, notificationTitle, notificationText /*+ "\n" + currentEntry*/, notificationActions, 7, onWeekends, repetitionTimes[0], repetitionTimes[1]);
                                }
                            }
                        }
                        break;
                    case "weekends":
                        onWeekends = true;
                    case "weekdays":
                        notificationCount += findNotificationDays(notificationCount, notificationTitle, notificationText /*+ "\n" + currentEntry*/, notificationActions, 7, onWeekends, Integer.parseInt(currentParts[1]), (currentParts.length > 2) ? Integer.parseInt(currentParts[2]) : 0);
                        break;
                }
            }
        }
        setNotificationSucceded();
    }

    @SuppressWarnings("deprecation")
    protected int[][] findNotificationRepetitions(final int hourFromInt, final int minuteFromInt, final int hourUntilInt, final int minuteUntilInt, final int repetitionCount) {
        // we cannot use java.text.DateFormat, java.text.SimpleDateFormat, java.util.Calendar hence the deprecated usages here
        Date fromDate = new Date();
        fromDate.setHours(hourFromInt);
        fromDate.setMinutes(minuteFromInt);
        Date untilDate = new Date();
        untilDate.setHours((hourFromInt * 60 + minuteFromInt < hourUntilInt * 60 + minuteUntilInt) ? hourUntilInt : hourUntilInt + 24);
        untilDate.setMinutes(minuteUntilInt);
        logNotificationRequest("fromDate: " + fromDate);
        logNotificationRequest("untilDate: " + untilDate);
        long viableRange = untilDate.getTime() - fromDate.getTime();
        final int repetitionRange = (int) viableRange / repetitionCount;
        int paddingValue = (int) (repetitionRange * 0.1);
        logNotificationRequest("viableRange: " + viableRange / 1000 / 60);
        logNotificationRequest("repetitionRange: " + repetitionRange / 1000 / 60);
        logNotificationRequest("paddingValue: " + paddingValue / 1000 / 60);
        final int[][] repetitionArray = new int[repetitionCount][2];
        for (int repetitionIndex = 0; repetitionIndex < repetitionCount; repetitionIndex++) {
            final int nextInt = new Random().nextInt(repetitionRange - paddingValue * 2);
            final int nextPeriod = repetitionRange + (repetitionRange * repetitionIndex) - nextInt - (paddingValue);
            logNotificationRequest("nextInt: " + nextInt / 1000 / 60);
            logNotificationRequest("nextPeriod: " + nextPeriod / 1000 / 60);
            final Date repetitionDate = new Date(fromDate.getTime() + nextPeriod);
            repetitionArray[repetitionCount - 1 - repetitionIndex] = new int[]{repetitionDate.getHours(), repetitionDate.getMinutes()};
        }
        return repetitionArray;
    }

    @SuppressWarnings("deprecation")
    protected int findNotificationDays(final int notificationCount, final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int maxDaysInAdvance, final boolean onWeekends, final int hourInt, final int minuteInt) {
        // we cannot use java.text.DateFormat, java.text.SimpleDateFormat, java.util.Calendar in GWT hence the deprecated usages here
        int numberAdded = 0;
        Date startDate = new Date();
        startDate.setHours(hourInt);
        startDate.setMinutes(minuteInt);
        final long msPerDay = 1000 * 60 * 60 * 24;
        final int minimumTimeWindow = 1000 * 60 * 5;
        for (int daysInAdvance = maxDaysInAdvance; daysInAdvance >= 0; daysInAdvance--) {
            Date currentDate = new Date(startDate.getTime() + (msPerDay * daysInAdvance));
            if ((currentDate.getDay() == 1 && !onWeekends)
                    || (currentDate.getDay() == 2 && !onWeekends)
                    || (currentDate.getDay() == 3 && !onWeekends)
                    || (currentDate.getDay() == 4 && !onWeekends)
                    || (currentDate.getDay() == 5 && !onWeekends)
                    || (currentDate.getDay() == 6 && onWeekends)
                    || (currentDate.getDay() == 0 && onWeekends)) {
                if (currentDate.getTime() - new Date().getTime() > minimumTimeWindow) {
                    logNotificationRequest("adding date time notification: " + currentDate);
                    setDayNotification(notificationCount + numberAdded, notificationTitle,
                            notificationText /*+ " : " + maxDaysInAdvance + "_" + onWeekends + "_" + hourInt + "_" + minuteInt*/,
                            notificationActions, currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDate(), hourInt, minuteInt);
                    numberAdded++;
                } else {
                    logNotificationRequest("not setting because time too close to now: " + currentDate);
                }
            }
        }
        return numberAdded;
    }

    protected native void clearNotifications() /*-{
        if($wnd.cordova) $wnd.cordova.plugins.notification.local.clearAll();
     }-*/;

    protected native void setNotificationInMinutes(final int notificationId, final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int minutes) /*-{
        if($wnd.cordova) $wnd.cordova.plugins.notification.local.schedule({
            id: notificationId,
            title: notificationTitle,
            text: notificationText,
            trigger: { 'in': minutes, unit: 'minute' },
            actions: notificationActions
        });
     }-*/;

    protected native void setDayNotification(final int notificationId, final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int yearInt, final int monthInt, final int dayInt, final int hourInt, final int minuteInt) /*-{
        var localNotifications = this;
        console.log("setDayNotification", yearInt, monthInt, dayInt, hourInt, minuteInt);
        if($wnd.cordova) $wnd.cordova.plugins.notification.local.schedule({
            id: notificationId,
            title: notificationTitle,
            text: notificationText,
            trigger: { at: new Date(yearInt, monthInt, dayInt, hourInt, minuteInt) },
            actions: notificationActions
        });
     }-*/;

    public native void requestPermissions() /*-{
        var localNotifications = this;
        if($wnd.cordova){
        //console.log("$wnd: " + $wnd);
        //console.log("$wnd.plugins: " + $wnd.plugins);
        //console.log("$wnd.cordova.plugins: " + $wnd.cordova.plugins);
            $wnd.cordova.plugins.notification.local.hasPermission(function (granted) {
                if (!granted) {
                    $wnd.cordova.plugins.notification.local.requestPermission(function (grantedInner) {
                        console.log("requestPermission: " + grantedInner);
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::logNotificationRequest(Ljava/lang/String;)("requestPermission failed");
                    });
                }
            });
            $wnd.cordova.plugins.notification.local.hasDoNotDisturbPermissions(function (granted) {
                if (!granted) {
                    $wnd.cordova.plugins.notification.local.requestDoNotDisturbPermissions(function (grantedInner) {
                        console.log("requestDoNotDisturbPermissions: " + grantedInner);
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::logNotificationRequest(Ljava/lang/String;)("requestDoNotDisturb failed");
                    });
                }
            });
            $wnd.cordova.plugins.notification.local.isIgnoringBatteryOptimizations(function (granted) {
                if (!granted) {
                    $wnd.cordova.plugins.notification.local.requestIgnoreBatteryOptimizations(function (grantedInner) {
                        console.log("requestIgnoreBatteryOptimizations: " + grantedInner);
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::logNotificationRequest(Ljava/lang/String;)("requestIgnoreBattery failed");
                    });
                }
            });
        }
    }-*/;

    public native void requestNotification(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final String notificationCommand) /*-{
        var localNotifications = this;
        if($wnd.cordova){
            //console.log("$wnd: " + $wnd);
            //console.log("$wnd.plugins: " + $wnd.plugins);
            //console.log("$wnd.cordova.plugins: " + $wnd.cordova.plugins);
            $wnd.cordova.plugins.notification.local.hasPermission(function (granted) {
                if (granted) {
                    localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotification(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(notificationTitle, notificationText, notificationActions, notificationCommand);
                } else {
                    localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotificationFailed()();
                }
            });
        } else {
            localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotificationFailed()();
        }
     }-*/;

    protected abstract void setNotificationSucceded();

    protected abstract void setNotificationFailed();

    // protected abstract void permissionFailed(String message);

//    public native void notificationLog(final String logString) /*-{
//            console.log(logString);
//    }-*/;

    protected abstract void logNotificationRequest(String debugValue);
}
