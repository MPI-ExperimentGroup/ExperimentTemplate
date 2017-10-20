/*
 * Copyright (C) 2017 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.fasttrack;

import java.util.LinkedList;
import nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.Constants;

/**
 *
 * @author olhshk
 */
public class FastTrackHistory {
    
    private final LinkedList<FastTrackRecord> records = new LinkedList<>();
    
    //Each class variable, instance variable, or array component is initialized with a default value when it is created (§15.9, §15.10) [...] For type int, the default value is zero, that is, 0.
    private final int[] bandvisits = new int[Constants.NUMBER_OF_BANDS];
  
    public LinkedList<FastTrackRecord> getRecords(){
        return this.records;
    }
    
    public int[] getVisits(){
        return this.bandvisits;
    }
    
    public void addRecord(FastTrackRecord record) {
        this.records.push(record);
    }
    
    public void addVisitToBand(int i) {
        this.bandvisits[i]++;
    }
    
}
