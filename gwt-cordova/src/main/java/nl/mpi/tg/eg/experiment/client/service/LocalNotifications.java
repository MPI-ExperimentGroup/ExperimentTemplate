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

/**
 * @since 6 June, 2019 17:09:32 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public abstract class LocalNotifications {

    protected native void setNotification(final String notificationTitle, final String notificationText, final Integer yearInt, final Integer monthInt, final Integer dayInt, final Integer hourInt) /*-{
        var localNotifications = this;
        cordova.plugins.notification.local.schedule({
            title: notificationTitle,
            text: notificationText,
            trigger: { at: new Date(yearInt, monthInt, dayInt, hourInt) }
        });
        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotificationSucceded()();
     }-*/;

    public native void requestNotification(final String notificationTitle, final String notificationText, final int yearInt, final int monthInt, final int dayInt, final int hourInt) /*-{
        var localNotifications = this;
        if($wnd.plugins){
            $wnd.cordova.plugins.notification.local.hasPermission(function (granted) {
            if (granted) {
                localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotification(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)(notificationTitle, notificationText, yearInt, monthInt, dayInt, hourInt);
            } else {
                cordova.plugins.notification.local.requestPermission(function (grantedInner) {
                    if (grantedInner) {
                        localNotifications.@nl.mpi.tg.eg.experiment.client.service.LocalNotifications::setNotification(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)(notificationTitle, notificationText, yearInt, monthInt, dayInt, hourInt);
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
}
