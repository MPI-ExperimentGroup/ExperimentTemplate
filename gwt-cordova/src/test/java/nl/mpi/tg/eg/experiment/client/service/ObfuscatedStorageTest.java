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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.mpi.tg.eg.experiment.client.exception.UserIdException;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 8 July 2019 17:46:09 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class ObfuscatedStorageTest {

    private ObfuscatedStorage getObfuscatedStorage(String appNameInternal, final Map<String, String> storageMap) {
        final List<String> keyList = new ArrayList<>(storageMap.keySet());
        return new ObfuscatedStorage(appNameInternal, true) {
            @Override
            public void removeItem(String key) {
                storageMap.remove(key);
                keyList.remove(key);
            }

            @Override
            public void setItem(String key, String data) {
                storageMap.put(key, data);
                keyList.add(key);
            }

            @Override
            public String key(int itemIndex) {
                return keyList.get(itemIndex);
            }

            @Override
            protected String urlEncode(String input) {
                try {
                    return URLEncoder.encode(input, "UTF-8");
                } catch (UnsupportedEncodingException encodingException) {
                    return encodingException.getMessage();
                }
            }

            @Override
            protected String urlDecode(String input) {
                try {
                    return URLDecoder.decode(input, "UTF-8");
                } catch (UnsupportedEncodingException encodingException) {
                    return encodingException.getMessage();
                }
            }

            @Override
            public int getLength() {
                return keyList.size();
            }
        };
    }

    /**
     * Test of revealString method, of class ObfuscatedStorage.
     */
    @Test
    public void testEncodeRevealString() {
        System.out.println("testEncodeRevealString");
        String storageKey = "testEncodeRevealString";
        ObfuscatedStorage instance = getObfuscatedStorage("invitation_validation_example", new HashMap<>());
        final String input = "\"ประเพณีบุญบั้งไฟ\", \"Rocket\", \"Festival\", \"Lao\", \"Thai\", \"ບຸນບັ້ງໄຟ\" ประเพณีบุญบั้งไฟ Lao ບຸນບັ້ງໄຟ";
        String obfuscateString = instance.obfuscateString(storageKey, input);
        String revealString = instance.revealString(storageKey, obfuscateString);
        System.out.println("obfuscateString: " + obfuscateString.charAt(16) + " : " + Integer.toBinaryString(obfuscateString.charAt(16)));
        System.out.println("revealString: " + obfuscateString.charAt(16) + " : " + Integer.toBinaryString(revealString.charAt(16)));
        assertNotEquals(input, obfuscateString);
        assertEquals(input, revealString);
    }

    /**
     * Test of clearUserData method, of class ObfuscatedStorage.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.UserIdException
     */
    @Test
    public void testClearUserData() throws UserIdException {
        System.out.println("clearUserData");
        UserId userId = new UserId("userId");
        Stimulus stimulus = GeneratedStimulus.values[0];
        String stimulusId = "stimulusId";
        String label = "label";
        String endPoint = "endPoint";
        String keyName = "keyName";
        String postName = "postName";
        ObfuscatedStorage instance = getObfuscatedStorage("testApp", new HashMap<String, String>());
        instance.setItem(instance.getAPP_STATE(userId), "test");
        instance.setItem(instance.getCOMPLETION_CODE(userId), "test");
        for (String valueName : new String[]{"maxScore", "gamesPlayed", "currentPotential", "totalPotential", "currentScore", "correctStreak", "errorStreak", "totalScore", "maxErrors", "maxPotential", "maxCorrectStreak", "maxErrorStreak"}) {
            instance.setItem(instance.getUSER_RESULTS(userId, valueName), "test");
            instance.setItem(instance.getUSER_METADATA(userId, valueName), "test");
            instance.setItem(instance.getUSER_METADATA_CONNECTION(userId, valueName), "test");
        }
        instance.setItem(instance.getLAST_USER_ID(), "test");
        instance.setItem(instance.getGAME_DATA(userId), "test");
        instance.setItem(instance.getGAME_DATA(label, userId), "test");
        instance.setItem(instance.getSTIMULI_DATA(userId, stimulus), "test");
        instance.setItem(instance.getSTIMULI_DATA(userId, stimulusId), "test");
        instance.setItem(instance.getSCREEN_DATA(endPoint, userId), "test");
        assertTrue(instance.isUSER_METADATA(instance.getUSER_METADATA(userId, postName), postName));
        assertFalse(instance.isUSER_METADATA(instance.getUSER_RESULTS(userId, postName), postName));
        assertEquals(44, instance.getLength());
        instance.clearUserData(userId);
        assertEquals(1, instance.getLength());
    }
}
