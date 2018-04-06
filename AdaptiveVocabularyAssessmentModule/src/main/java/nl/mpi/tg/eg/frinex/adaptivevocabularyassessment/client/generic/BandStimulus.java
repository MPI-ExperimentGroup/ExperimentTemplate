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
package nl.mpi.tg.eg.frinex.adaptivevocabularyassessment.client.generic;

import java.util.HashMap;
import java.util.Map;
import nl.mpi.tg.eg.frinex.common.model.AbstractStimulus;

/**
 * @since Oct 27, 2017 2:13:03 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class BandStimulus extends AbstractStimulus {

    public final String bandLabel;
    public final int bandIndex;
    private final static String FLDS = "[uniqueId, tags, label, code, pauseMs, audioPath, videoPath, imagePath, ratingLabels, correctResponses, bandIndex, bandLabel]";

    public BandStimulus(String uniqueId, Tag[] tags, String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath,
            String ratingLabels, String correctResponses, String bandLabel, int bandIndex) {
        super(uniqueId, tags, label, code, pauseMs, audioPath, videoPath, imagePath, ratingLabels, correctResponses);
        this.bandIndex = bandIndex;
        this.bandLabel = bandLabel;
    }

    public String getbandLabel() {
        return this.bandLabel;
    }

    public int getbandIndex() {
        return this.bandIndex;
    }

    public HashMap<String, Object> getSerialisationMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("fields", FLDS);
        map.put("uniqueId", this.getUniqueId());
        map.put("tags", this.getTags());
        map.put("label", this.getLabel());
        map.put("code", this.getCode());
        map.put("pauseMs", this.getPauseMs());
        map.put("audioPath", this.getAudio());
        map.put("videoPath", this.getVideo());
        map.put("imagePath", this.getImage());
        map.put("ratingLabels", this.getRatingLabels());
        map.put("correctResponses", this.getCorrectResponses());
        map.put("bandIndex", this.getbandIndex());
        map.put("bandLabel", this.getbandLabel());
        return map;
    }

    @Override
    public String toString() {
        HashMap<String, Object> map = this.getSerialisationMap();
        return map.toString();
    }

    public static BandStimulus stringToObject(String str) {
        try {
            Map<String, Object> map = UtilsJSONdialect.stringToObjectMap(str, BandStimulus.FLDS);
            BandStimulus retVal = mapToBandStimulusObject(map);
            return retVal;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static BandStimulus mapToBandStimulusObject(Map<String, Object> map) {
        try {
            String uid = map.get("uniqueId").toString();

            Object tagsObj = map.get("tags");
            Tag[] tgs = UtilsJSONdialect.toTagArray(tagsObj);

            String lab = map.get("label").toString();

            String cd = map.get("code").toString();
            int pause = Integer.parseInt(map.get("pauseMs").toString());
            String audio = map.get("audioPath").toString();
            String video = map.get("videoPath").toString();
            String image = map.get("imagePath").toString();
            String corrResp = map.get("correctResponses").toString();
            String rating = map.get("ratingLabels").toString();
            String bandLab = map.get("bandLabel").toString();
            int bandInd = Integer.parseInt(map.get("pauseMs").toString());

            //(String uniqueId, Tag[] tags, String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath,
            //    String ratingLabels, String correctResponses, String bandLabel, int bandIndex)
            return new BandStimulus(uid, tgs, lab, cd, pause, audio, video, image, rating, corrResp, bandLab, bandInd);

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
