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
package nl.mpi.tg.eg.experiment.client.util;

import java.util.HashMap;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.service.GroupScoreService;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Jul 19, 2017 3:37:34 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class HtmlTokenFormatterTest {

    public HtmlTokenFormatterTest() {
    }

    /**
     * Test of formatString method, of class HtmlTokenFormatter.
     */
    @Test
    public void testFormatString() {
        System.out.println("formatString");

        String inputString = "Q<groupScore>W<channelScore>E"
                + "R<channelLoop>Duo <channelLabel> heeft <channelScore> punten.<br/><br/></channelLoop>Y"
                + "C<groupOtherMemberCodes>D"
                + "E<stimulusId>F"
                + "G<stimulusCode>H"
                + "I<stimulusLabel>J"
                + "I<stimulusRatingLabels>J"
                + "I<stimulusPauseMs>J"
                + "I<stimulusAudio>J"
                + "I<stimulusVideo>J"
                + "I<stimulusImage>J"
                + "I<stimulusTags>J"
                + "I<stimulusCorrectResponses>J";
        // todo: implement the channelLoop
        final String expectedString = "QGroupScoreWChannelScoreERDuo A-B heeft 6 punten.<br/><br/>Duo C-D heeft 2 punten.<br/><br/>YCA,B,C,D,E,FDEd1e286FGcodeHIOneJIRating,LabelsJI0JIAudioJIVideoJIImageJItag_number,tag_interestingJICorrect|ResponsesJ";
        HtmlTokenFormatter instance = getInstance();
        final String formattedString = instance.formatString(inputString);
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of formatString method to ExtractNextFromList, of class HtmlTokenFormatter.
     */
    @Test
    public void testExtractNextFromList() {
        System.out.println("testExtractNextFromList");
        final String testRegex = "lilbq4_([^_]*)[_$]";
        String inputString = "<metadataField_session_steps>";
        final String expectedString = "audioas2";
        HtmlTokenFormatter instance = getInstance();
        final String formattedString = instance.formatReplaceString(inputString, testRegex);
        System.out.println("inputString:" + inputString);
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    private HtmlTokenFormatter getInstance() {
        final HashMap<String, String> channelScores = new HashMap<>();
        channelScores.put("A-B", "6");
        channelScores.put("C-D", "2");
        final UserData userData = new UserData();
        final MetadataField session_steps = new MetadataField("session_steps", "session_steps", "session_steps", "session_steps", "session_steps");
        final MetadataField session_step = new MetadataField("session_step", "session_step", "session_step", "session_step", "session_step");
        userData.setMetadataValue(session_steps, "audiosimplereactiontime_lilbq4_audioas2_peabodyas_audiononwordmonitoring_grammaras_visualsimplereactiontime");
        HtmlTokenFormatter instance = new HtmlTokenFormatter(GeneratedStimulus.values[0], null, new GroupScoreService() {

            @Override
            public String getActiveChannel() {
                return "ActiveChannel";
            }

            @Override
            public String getAllMemberCodes() {
                return ",A,B,,C,D,,X,E,F,";
            }

            @Override
            public String getChannelScore() {
                return "ChannelScore";
            }

            @Override
            public String getChannelScore(String channel) {
                return channelScores.get(channel);
            }

            @Override
            public Set<String> getChannelScoreKeys() {
                return channelScores.keySet();
            }

            @Override
            public String getGroupCommunicationChannels() {
                return "GroupCommunicationChannels";
            }

            @Override
            public String getGroupScore() {
                return "GroupScore";
            }

            @Override
            public String getMemberCode() {
                return "X";
            }

            @Override
            public String getMessageString() {
                return "MessageString";
            }

            @Override
            public String getUserLabel() {
                return "UserLabel";
            }

            @Override
            public String getGroupId() {
                return "GroupId";
            }

        }, userData, new TimerService(), new MetadataField[]{
            session_steps, session_step});
        return instance;
    }

    /**
     * Test of evaluateTokens method, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testEvaluateTokens() throws EvaluateTokensException {
        System.out.println("evaluateTokens");
        HtmlTokenFormatter instance = getInstance();
        assertEquals((2 - (3 * 6 + (7 - 4 * (7.0 / 2)))) + 4, instance.evaluateTokens("(2-(3*6+(7-4*(7/2))))+4"));
        assertEquals((2 - (3 * 6 + (7 - 4 * (7.0 + 2)))) + 4, instance.evaluateTokens("(2-(3*6+(7-4*(7+2))))+4"));
        assertEquals(-(-2 - (-3 * -6 + (-7 - -4 * (-7.0 + -2)))) + -4, instance.evaluateTokens("-(-2-(-3*-6+(-7--4*(-7+-2))))+-4"));
        assertEquals(-(-2 - -(-3 * -6 + -(-7 - -4 * -(-7.0 + -2)))) + -4, instance.evaluateTokens("-(-2--(-3*-6+-(-7--4*-(-7+-2))))+-4"));
        assertEquals(2.0 - 3 + 4.0, instance.evaluateTokens("2-3+4"));
        assertEquals(2.0 + 3 - 4.0, instance.evaluateTokens("2+3-4"));
        assertEquals(4.0 / 5.0 * 6, instance.evaluateTokens("4/5*6"));
        assertEquals(4.0 * 5.0 / 6, instance.evaluateTokens("4*5/6"));
        assertEquals(-2.0 - 3 + 4.0, instance.evaluateTokens("-2-3+4"));
        assertEquals(-2.0 + 3 - 4.0, instance.evaluateTokens("-2+3-4"));
        assertEquals(-4.0 / 5.0 * 6, instance.evaluateTokens("-4/5*6"));
        assertEquals(-4.0 * 5.0 / 6, instance.evaluateTokens("-4*5/6"));
        assertEquals(3 + 4.0 / 5.0 * 6, instance.evaluateTokens("3+4/5*6"));
        assertEquals(2.0 - 4.0 / 5.0 * 6, instance.evaluateTokens("2-4/5*6"));
        assertEquals(2.0 - 3 + 4.0 / 5.0 * 6, instance.evaluateTokens("2-3+4/5*6"));
        assertEquals((1.0 + 5) % 3, instance.evaluateTokens("(1 +5) % 3"));
        assertEquals(1.0 + (5 % 3), instance.evaluateTokens("1+(5 % 3)"));
        assertEquals(1.0 + 5 % 3, instance.evaluateTokens("1+5 % 3"));
        assertEquals(1.0 + 5, instance.evaluateTokens("1 + 5"));
        assertEquals(-1.0 + 5, instance.evaluateTokens("-1 + 5"));
        assertEquals(1.0 + 5 * (1 * 3), instance.evaluateTokens("1 + 5 * (1*3)"));
        assertEquals((1.0 + 5.1) * (1 * 3), instance.evaluateTokens("(1 + 5.1) * (1 *3 ) "));
        assertEquals(5.1 / (1 - 3), instance.evaluateTokens("5.1 / ( 1- 3) "));
        assertEquals(-5.1 / (1 - 3), instance.evaluateTokens("-5.1 / ( 1- 3) "));
        assertEquals(2.0 * 3 - 4 + 5.0 / 6, instance.evaluateTokens("2*3-4+5/6"));
        assertEquals(2.0 / 3 * 4 - 5 + 6, instance.evaluateTokens("2/3*4-5+6"));
        assertEquals(2.0 + 3 / 4.0 * 5 - 6, instance.evaluateTokens("2+3/4*5-6"));
    }
}
