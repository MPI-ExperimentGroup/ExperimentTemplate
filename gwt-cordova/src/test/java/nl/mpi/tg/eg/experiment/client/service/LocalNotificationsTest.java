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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Aug 13, 2019 4:00:24 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class LocalNotificationsTest {

    public LocalNotificationsTest() {
    }

    /**
     * Test of findNotificationRepetitions method, of class LocalNotifications.
     */
    @Test
    public void testFindNotificationRepetitions() {
        System.out.println("findNotificationRepetitions:10:30:22:30:24");
        int hourFromInt = 10;
        int minuteFromInt = 30;
        int hourUntilInt = 22;
        int minuteUntilInt = 30;
        int repetitionCount = 24;
        LocalNotifications instance = new LocalNotificationsImpl();
        int[][] result = instance.findNotificationRepetitions(hourFromInt, minuteFromInt, hourUntilInt, minuteUntilInt, repetitionCount);
        assertEquals(repetitionCount, result.length);
        for (int repetitionIndex = 0; repetitionIndex < repetitionCount; repetitionIndex++) {
            System.out.println(result[repetitionIndex][0] + ":" + result[repetitionIndex][1]);
        }

        int expectedHour = hourFromInt;
        for (int repetitionIndex = repetitionCount - 1; repetitionIndex >= 0; repetitionIndex--) {
            System.out.println(result[repetitionIndex][0] + ":" + result[repetitionIndex][1]);
            if (result[repetitionIndex][1] < 31) {
                assertTrue(result[repetitionIndex][1] >= 2);
                assertTrue(result[repetitionIndex][1] <= 28);
            } else {
                assertTrue(result[repetitionIndex][1] >= 32);
                assertTrue(result[repetitionIndex][1] <= 58);
            }
            System.out.println("expectedHourF: " + expectedHour);
            assertEquals(expectedHour, result[repetitionIndex][0]);
            if (repetitionIndex % 2 == 1) {
                expectedHour++;
            }
            if (result[repetitionIndex][0] == hourFromInt) {
                assertTrue(result[repetitionIndex][1] >= minuteFromInt);
            } else if (result[repetitionIndex][0] == hourUntilInt) {
                assertTrue(result[repetitionIndex][1] <= minuteUntilInt);
            }
        }
    }

    /**
     * Test of findNotificationRepetitions method, of class LocalNotifications.
     */
    @Test
    public void testFindNotificationRepetitions2() {
        System.out.println("findNotificationRepetitions weekdays_between:17:20:17:50:5");
        int hourFromInt = 17;
        int minuteFromInt = 20;
        int hourUntilInt = 17;
        int minuteUntilInt = 50;
        int repetitionCount = 5;
        LocalNotifications instance = new LocalNotificationsImpl();
        int[][] result = instance.findNotificationRepetitions(hourFromInt, minuteFromInt, hourUntilInt, minuteUntilInt, repetitionCount);
        assertEquals(repetitionCount, result.length);
        System.out.println("repetitionCountN: " + result.length);
        for (int repetitionIndex = 0; repetitionIndex < repetitionCount; repetitionIndex++) {
            System.out.println(result[repetitionIndex][0] + ":" + result[repetitionIndex][1]);
        }
        int expectedHour = hourFromInt;
        for (int repetitionIndex = 0; repetitionIndex < repetitionCount; repetitionIndex++) {
            System.out.println(result[repetitionIndex][0] + ":" + result[repetitionIndex][1]);
//            if (result[repetitionIndex][1] < 31) {
            assertTrue(result[repetitionIndex][1] >= 20);
            assertTrue(result[repetitionIndex][1] <= 50);
//            } else {
//                assertTrue(result[repetitionIndex][1] >= 32);
//                assertTrue(result[repetitionIndex][1] <= 58);
//            }
            System.out.println("expectedHourN: " + expectedHour);
            assertEquals(expectedHour, result[repetitionIndex][0]);
            if (result[repetitionIndex][0] == hourFromInt) {
                assertTrue(result[repetitionIndex][1] >= minuteFromInt);
            } else if (result[repetitionIndex][0] == hourUntilInt) {
                assertTrue(result[repetitionIndex][1] <= minuteUntilInt);
            }
        }
    }

    /**
     * Test of setNotification method, of class LocalNotifications.
     */
    @Test
    public void testSetNotification() {
        System.out.println("setNotification");
        final StringBuilder resultStringBuilder = new StringBuilder();

        LocalNotifications instance = new LocalNotifications() {
            @Override
            protected int[][] findNotificationRepetitions(int hourFromInt, int minuteFromInt, int hourUntilInt, int minuteUntilInt, int repetitionCount) {
                resultStringBuilder.append(":");
                resultStringBuilder.append(hourFromInt);
                resultStringBuilder.append(":");
                resultStringBuilder.append(minuteFromInt);
                resultStringBuilder.append(":");
                resultStringBuilder.append(hourUntilInt);
                resultStringBuilder.append(":");
                resultStringBuilder.append(minuteUntilInt);
                resultStringBuilder.append(":");
                resultStringBuilder.append(repetitionCount);
                return new int[0][0];
            }

            @Override
            protected void setNotificationLater(String notificationTitle, String notificationText, JavaScriptObject notificationActions, String notificationCommand) {
                super.setNotificationRun(notificationTitle, notificationText, notificationActions, notificationCommand);
            }

            @Override
            protected void setNotificationSucceded() {
            }

            @Override
            protected void setNotificationFailed() {
            }

            @Override
            protected void clearNotifications() {
            }

            @Override
            public void logNotificationRequest(String logString) {
                System.out.println(logString);
            }

        };
        resultStringBuilder.append("weekdays_between");
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:::9");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between::30:15:30:9");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10::15:30:9");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10:30::30:9");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10:30:15::9");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10:30:15:30:");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10:30:15:309");
        assertEquals("weekdays_between", resultStringBuilder.toString());
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:10:30:15:30:9");
        assertEquals("weekdays_between:10:30:15:30:9", resultStringBuilder.toString());
        resultStringBuilder.append(" haswhitespace");
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:09:00:17:00:5                             weekends_between:10:00:18:00:5");
        assertEquals("weekdays_between:10:30:15:30:9 haswhitespace:9:0:17:0:5:10:0:18:0:5", resultStringBuilder.toString());
        resultStringBuilder.append(" weekdays_between");
        instance.setNotification("notificationTitle", "notificationText", null, "weekdays_between:17:20:17:50:5");
        assertEquals("weekdays_between:10:30:15:30:9 haswhitespace:9:0:17:0:5:10:0:18:0:5 weekdays_between:17:20:17:50:5", resultStringBuilder.toString());
    }

    /**
     * Test of reverse findNotificationRepetitions method, of class
     * LocalNotifications.
     */
    @Test
    public void testFindNotificationRepetitionsReverse() {
        System.out.println("findNotificationRepetitions reverse");
        int hourFromInt = 20;
        int minuteFromInt = 30;
        int hourUntilInt = 8;
        int minuteUntilInt = 30;
        int repetitionCount = 24;
        LocalNotifications instance = new LocalNotificationsImpl();
        int[][] result = instance.findNotificationRepetitions(hourFromInt, minuteFromInt, hourUntilInt, minuteUntilInt, repetitionCount);
        assertEquals(repetitionCount, result.length);
        int prevHour = 25;
        int prevMinute = 61;
        for (int[] values : result) {
            System.out.println(values[0] + ":" + values[1]);
            assertTrue("the notifications must be in reverse time order ", prevHour > values[0] || (prevHour == values[0] && values[1] <= prevMinute) || prevHour == 0);
            prevHour = values[0];
            prevMinute = values[1];
        }
        int expectedHour = hourFromInt;
        boolean isSecond = true;
        for (int repetitionIndex = repetitionCount - 1; repetitionIndex >= 0; repetitionIndex--) {
            System.out.println(result[repetitionIndex][0] + ":" + result[repetitionIndex][1]);

            System.out.println("expectedMinutesR: " + minuteFromInt + " - " + minuteUntilInt);
            if (result[repetitionIndex][1] < 31) {
                assertTrue(result[repetitionIndex][1] >= 2);
                assertTrue(result[repetitionIndex][1] <= 28);
            } else {
                assertTrue(result[repetitionIndex][1] >= 32);
                assertTrue(result[repetitionIndex][1] <= 58);
            }
            //System.out.println("expectedHourR: " + expectedHour + " == " + result[repetitionIndex][0]);
            assertEquals(expectedHour, result[repetitionIndex][0]);
            if (isSecond) {
                isSecond = false;
                expectedHour++;
                if (expectedHour > 23) {
                    expectedHour = 0;
                }
            } else {
                isSecond = true;
            }
            if (result[repetitionIndex][0] == hourFromInt) {
                assertTrue(result[repetitionIndex][1] >= minuteFromInt);
            } else if (result[repetitionIndex][0] == hourUntilInt) {
                assertTrue(result[repetitionIndex][1] <= minuteUntilInt);
            }
        }
    }

    public class LocalNotificationsImpl extends LocalNotifications {

        @Override
        public void setNotificationSucceded() {
        }

        @Override
        public void setNotificationFailed() {
        }

        @Override
        public void logNotificationRequest(String logString) {
            System.out.println(logString);
        }
    }
}
