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
import java.util.Date;
import java.util.Random;

/**
 * @since 6 June, 2019 17:09:32 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class LocalNotifications {

    protected void setNotification(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final String notificationCommand) {
        clearNotifications();
        for (final String currentEntry : notificationCommand.split(" ")) {
            boolean onWeekends = false;
            notificationLog(currentEntry);
            final String[] currentParts = currentEntry.split(":");
            if (currentParts.length > 1) {
                switch (currentParts[0]) {
                    case "in_minutes":
                        setNotificationInMinutes(notificationTitle, notificationText + "\n" + currentEntry, notificationActions, Integer.parseInt(currentParts[1]));
                        break;
                    case "weekends_between":
                        onWeekends = true;
                    case "weekdays_between":
                        for (int[] repetitionTimes : findNotificationRepetitions(Integer.parseInt(currentParts[1]), Integer.parseInt(currentParts[2]), Integer.parseInt(currentParts[3]), Integer.parseInt(currentParts[4]), Integer.parseInt(currentParts[5]))) {
                            findNotificationDays(notificationTitle, notificationText + "\n" + currentEntry, notificationActions, 7, onWeekends, repetitionTimes[0], repetitionTimes[1]);
                        }
                        break;
                    case "weekends":
                        onWeekends = true;
                    case "weekdays":
                        findNotificationDays(notificationTitle, notificationText + "\n" + currentEntry, notificationActions, 7, onWeekends, Integer.parseInt(currentParts[1]), (currentParts.length > 2) ? Integer.parseInt(currentParts[2]) : 0);
                        break;
                }
            }
        }
        setNotificationSucceded();
    }

    protected int[][] findNotificationRepetitions(final int hourFromInt, final int minuteFromInt, final int hourUntilInt, final int minuteUntilInt, final int repetitionCount) {
        Date fromDate = new Date();
        fromDate.setHours(hourFromInt);
        fromDate.setMinutes(minuteFromInt);
        Date untilDate = new Date();
        untilDate.setHours((hourFromInt < hourUntilInt) ? hourUntilInt : hourUntilInt + 24);
        untilDate.setMinutes(minuteUntilInt);
        long viableRange = untilDate.getTime() - fromDate.getTime();
        final int repetitionRange = (int) viableRange / repetitionCount;
        int paddingValue = (int) (repetitionRange * 0.1);
//        System.out.println("viableRange: " + viableRange / 1000 / 60);
//        System.out.println("repetitionRange: " + repetitionRange / 1000 / 60);
//        System.out.println("paddingValue: " + paddingValue / 1000 / 60);
        final int[][] repetitionArray = new int[repetitionCount][2];
        for (int repetitionIndex = 0; repetitionIndex < repetitionCount; repetitionIndex++) {
            final int nextInt = new Random().nextInt(repetitionRange - paddingValue * 2);
            final int nextPeriod = repetitionRange + (repetitionRange * repetitionIndex) - nextInt - (paddingValue);
//            System.out.println("nextInt: " + nextInt / 1000 / 60);
//            System.out.println("nextPeriod: " + nextPeriod / 1000 / 60);
            final Date repetitionDate = new Date(fromDate.getTime() + nextPeriod);
            repetitionArray[repetitionIndex] = new int[]{repetitionDate.getHours(), repetitionDate.getMinutes()};
        }
        return repetitionArray;
    }

    protected void findNotificationDays(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int maxDaysInAdvance, final boolean onWeekends, final int hourInt, final int minuteInt) {
        Date startDate = new Date();
        startDate.setHours(hourInt);
        startDate.setMinutes(minuteInt);
        final int secondsPerDay = 1000 * 60 * 60 * 24;
        final int minimumTimeWindow = 1000 * 60 * 5;
        for (int daysInAdvance = 0; daysInAdvance <= maxDaysInAdvance; daysInAdvance++) {
            Date currentDate = new Date(startDate.getTime() + (secondsPerDay * daysInAdvance));
            if ((currentDate.getDay() == 1 && !onWeekends)
                    || (currentDate.getDay() == 2 && !onWeekends)
                    || (currentDate.getDay() == 3 && !onWeekends)
                    || (currentDate.getDay() == 4 && !onWeekends)
                    || (currentDate.getDay() == 5 && !onWeekends)
                    || (currentDate.getDay() == 6 && onWeekends)
                    || (currentDate.getDay() == 0 && onWeekends)) {
                if (currentDate.getTime() - new Date().getTime() > minimumTimeWindow) {
                    notificationLog("adding date time notification: " + currentDate);
                    setDayNotification(notificationTitle, notificationText, notificationActions, currentDate.getYear() + 1900, currentDate.getMonth() + 1, currentDate.getDate(), hourInt, minuteInt);
                } else {
                    notificationLog("not setting because time too close to now: " + currentDate);
                }
            }
        }
    }

    protected native void clearNotifications() /*-{
        $wnd.cordova.plugins.notification.local.clearAll();
     }-*/;

    protected native void setNotificationInMinutes(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int minutes) /*-{
        $wnd.cordova.plugins.notification.local.schedule({
            title: notificationTitle,
            text: notificationText,
            trigger: { 'in': minutes, unit: 'minute' },
            actions: notificationActions
        });
     }-*/;

    protected native void setDayNotification(final String notificationTitle, final String notificationText, final JavaScriptObject notificationActions, final int yearInt, final int monthInt, final int dayInt, final int hourInt, final int minuteInt) /*-{
        var localNotifications = this;
        console.log("setDayNotification", yearInt, monthInt, dayInt, hourInt, minuteInt);
        $wnd.cordova.plugins.notification.local.schedule({
            title: notificationTitle,
            text: notificationText,
            trigger: { at: new Date(yearInt, monthInt, dayInt, hourInt, minuteInt) },
            actions: notificationActions
        });
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
                $wnd.cordova.plugins.notification.local.requestPermission(function (grantedInner) {
                    if (grantedInner) {
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotification(Ljava/lang/String;Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(notificationTitle, notificationText, notificationActions, notificationCommand);
                    } else {
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotificationFailed()();
                    }
                });
            }
            });
        } else {
            localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotificationFailed()();
        }
     }-*/;

    protected abstract void setNotificationSucceded();

    protected abstract void setNotificationFailed();

    public native void notificationLog(final String logString) /*-{
            console.log(logString);
    }-*/;
}
