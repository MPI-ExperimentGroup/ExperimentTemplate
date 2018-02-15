/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.service.audioaspool;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olhshk
 */
public class AudioIndexMapTest {
    
    private final String postfix  = "Db";
    
    public AudioIndexMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testOrder() {
        int length = AudioIndexMap.INDEX_ARRAY[0].trim().length() - postfix.length();
        int previous = Integer.parseInt(AudioIndexMap.INDEX_ARRAY[0].trim().substring(0, length).trim());
        int current;
        for (int i=1; i<AudioIndexMap.INDEX_ARRAY.length; i++){
            String buffer = AudioIndexMap.INDEX_ARRAY[i].trim();
            length = buffer.length() - postfix.length();
            current = Integer.parseInt(buffer.substring(0, length).trim());
            assertTrue(current>previous);
            previous = current;
        }
    }
    
}
