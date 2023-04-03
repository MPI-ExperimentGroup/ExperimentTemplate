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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException;
import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
import nl.mpi.tg.eg.experiment.client.model.MetadataField;
import nl.mpi.tg.eg.experiment.client.model.UserData;
import nl.mpi.tg.eg.experiment.client.model.UserId;
import nl.mpi.tg.eg.experiment.client.service.GroupScoreService;
import nl.mpi.tg.eg.experiment.client.service.LocalStorageInterface;
import nl.mpi.tg.eg.experiment.client.service.TimerService;
import nl.mpi.tg.eg.experimentdesigner.model.TokenMethod;
import nl.mpi.tg.eg.experimentdesigner.model.TokenText;
import nl.mpi.tg.eg.frinex.common.model.AbstractStimulus;
import nl.mpi.tg.eg.frinex.common.model.Stimulus;
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

        String inputString = "Q::groupScore::W::channelScore::E"
                + "R::channelLoop::Duo ::channelLabel:: heeft ::channelScore:: punten.<br/><br/>::/channelLoop::Y"
                + "C::groupOtherMemberCodes::D"
                + "E::stimulusId::F"
                + "G::stimulusCode::H"
                + "I::stimulusLabel::J"
                + "I::stimulusRatingLabels::J"
                + "I::stimulusPauseMs::J"
                + "I::stimulusAudio::J"
                + "I::stimulusVideo::J"
                + "I::stimulusImage::J"
                + "I::stimulusTags::J"
                + "I::stimulusCorrectResponses::J"
                + "K::groupRequestedPhase::L"
                + "M::metadataField_session_step::::stimulusId::N"
                + "O ::metadataField_session_step:: ::stimulusId:: P";
        final String expectedString = "Q8W6ERDuo A-B heeft 6 punten.<br/><br/>Duo C-D heeft 2 punten.<br/><br/>YCB,C,D,X,E,FDEd1e286FGcodeHIOneJIRating,LabelsJI0JIAudioJIVideoJIImageJItag_number,tag_interestingJICorrect|ResponsesJK1LMa_value_for_session_stepd1e286NO a_value_for_session_step d1e286 P";
        HtmlTokenFormatter instance = getInstance();
        final String formattedString = instance.formatString(inputString);
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of formatString method, of class HtmlTokenFormatter.
     */
    @Test
    public void testFormatEncodedString() {
        System.out.println("formatEncodedString");

        String inputString = "K::stimulusRatingLabel_0::M::stimulusRatingLabel_1::N::stimulusRatingLabel_2::O::stimulusRatingLabel_3::P::stimulusRatingLabel_4::Q::stimulusRatingLabel_5::U::stimulusRatingLabel_6::R::stimulusRatingLabel_7::S::stimulusRatingLabel_8::T::stimulusRatingLabel_9::U::stimulusRatingLabel_10::V::stimulusRatingLabel_11::W::stimulusRatingLabel_12::X::stimulusRatingLabel_13::L";
        final String expectedString = "Kgood[brackets]Mgood,commaNgood{braces}Ogood(parentheses)PmeowQback\\slashUtwo^caretRdollar$signSfour*asteriskTfive+plusUquestion?markVfull.stopWeight|pipeXexclamation!markL";
        final String ratingString = "good&#x5B;brackets&#x5D;,good&#x2C;comma,good&#x7B;braces&#x7D;,good&#x28;parentheses&#x29;,meow,back&#x5C;slash,two&#x5E;caret,dollar&#x24;sign,four&#x2A;asterisk,five&#x2B;plus,question&#x3F;mark,full&#x2E;stop,eight&#x7C;pipe,exclamation&#x21;mark";
        HtmlTokenFormatter instance = new HtmlTokenFormatter(new AbstractStimulus("a", new Stimulus.Tag[]{}, null, null, 0, null, null, null, ratingString, null) {
            @Override
            public boolean isCorrect(String value) {
                return false;
            }
        }, null, null, new UserData(new UserId()), new TimerService(), null, null);
        final String formattedString = instance.formatString(inputString);
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of formatString currentDateDDMMYYYY method, of class
     * HtmlTokenFormatter.
     */
    @Test
    public void testFormatDateTime() {
        System.out.println("testFormatDateTime");
        String inputString = "qwerqwer::formatDateTime_HH:mm::qwrwerqwer";
        HtmlTokenFormatter instance = getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        final String formattedString = instance.formatString(inputString);
        final String expectedString = "qwerqwer08:30qwrwerqwer";
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of evaluateResolve addTime method, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testFormatAddDate() throws EvaluateTokensException {
        System.out.println("testAddTime");
        HtmlTokenFormatter instance = getInstance();
        assertEquals("14:09", instance.evaluateTokensString("addTime(12:24,01:45)"));
        assertEquals("10:39", instance.evaluateTokensString("addTime(12:24,-01:45)"));
        assertEquals("14:09", instance.evaluateTokensString("addTime(12:24,+01:45)"));
        assertEquals("00:00", instance.evaluateTokensString("addTime(23:24,00:36)"));
        assertEquals("04:00", instance.evaluateTokensString("addTime(3:04,00:56)"));
        assertEquals("04:01", instance.evaluateTokensString("addTime(3:04,00:57)"));
        assertEquals("03:59", instance.evaluateTokensString("addTime(3:04,00:55)"));
        assertEquals("02:08", instance.evaluateTokensString("addTime(3:04,-00:56)"));
        assertEquals("02:07", instance.evaluateTokensString("addTime(3:04,-00:57)"));
        assertEquals("02:09", instance.evaluateTokensString("addTime(3:04,-00:55)"));
    }

    /**
     * Test of formatString currentDateDDMMYYYY method, of class
     * HtmlTokenFormatter.
     */
    @Test
    public void testFormatStringCurrentDate() {
        System.out.println("testFormatStringCurrentDate");
        String inputString = "qwerqwer::currentDateDDMMYYYY::qwrwerqwer";
        HtmlTokenFormatter instance = getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final String formattedString = instance.formatString(inputString);
        final String expectedString = "qwerqwer12/03/2020qwrwerqwer";//.replaceAll("##/##/####", dateFormat.format(new Date()));
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of formatString currentDateDDMMYYYY-45D+1Y-13M method, of class
     * HtmlTokenFormatter.
     */
    @Test
    public void testFormatStringCurrentDatePlus() {
        System.out.println("testFormatStringCurrentDate");
        String inputString = "qwerqwer::currentDateDDMMYYYY-45D+1Y-13M::qwrwerqwer";
        HtmlTokenFormatter instance = getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        final Date currentDate = new Date();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -45);
        calendar.add(Calendar.MONTH, -13);
        calendar.add(Calendar.YEAR, 1);
        final String formattedString = instance.formatString(inputString);
        final String expectedString = "qwerqwer33/10/2021qwrwerqwer";//.replaceAll("##/##/####", dateFormat.format(calendar.getTime()));
        System.out.println("expectedString:" + expectedString);
        System.out.println("formattedString: " + formattedString);
        assertEquals(expectedString, formattedString);
    }

    /**
     * Test of formatString method to ExtractNextFromList, of class
     * HtmlTokenFormatter.
     */
    @Test
    public void testExtractNextFromList() {
        System.out.println("testExtractNextFromList");
        final String testRegex = "lilbq4_([^_]*)[_$]";
        String inputString = "::metadataField_session_steps::";
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
        final LocalStorageInterface localStorage = new LocalStorageInterface() {
            @Override
            public String getCompletionCode(UserId userId) {
                return "YourCompletionCode";
            }

            @Override
            public String getStoredStimulusValue(UserId userId, String stimulusId, String key) {
                return "StoredStimulusValue(stimulusId:" + stimulusId + ",key:" + key + ")";
            }
        };
        final MetadataField session_steps = new MetadataField("session_steps", "session_steps", "session_steps", "session_steps", "session_steps");
        final MetadataField notificationWeekendUntilSettings = new MetadataField("notificationWeekendUntilSettings", "notificationWeekendUntilSettings", "notificationWeekendUntilSettings", "notificationWeekendUntilSettings", "notificationWeekendUntilSettings");
        final MetadataField dateOfBirth = new MetadataField("dateOfBirth", "dateOfBirth", "dateOfBirth", "dateOfBirth", "dateOfBirth");
        final MetadataField session_step = new MetadataField("session_step", "session_step", "session_step", "session_step", "session_step");
        userData.setMetadataValue(session_steps, "audiosimplereactiontime_lilbq4_audioas2_peabodyas_audiononwordmonitoring_grammaras_visualsimplereactiontime");
        userData.setMetadataValue(session_step, "a_value_for_session_step");
        userData.setMetadataValue(notificationWeekendUntilSettings, "15:20");
        userData.setMetadataValue(dateOfBirth, "25/01/2020");
        HtmlTokenFormatter instance = new HtmlTokenFormatter(GeneratedStimulus.values[0], localStorage, new GroupScoreService() {

            @Override
            public String getActiveChannel() {
                return "A-B";
            }

            @Override
            public String getAllMemberCodes() {
                return ",A,B,,C,D,,X,E,F,";
            }

            @Override
            public String getChannelScore() {
                return "6";
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
                return "8";
            }

            @Override
            public String getMemberCode() {
                return "A";
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

            @Override
            public String getGroupUUID() {
                return "GroupUUID";
            }

            @Override
            public String getAsignedMemberCodes() {
                return "AsignedMemberCodes";
            }

            @Override
            public Integer getRequestedPhase() {
                return 1;
            }

        }, userData, new TimerService(), new MetadataField[]{
            session_steps, session_step, notificationWeekendUntilSettings, dateOfBirth}, null) {
            @Override
            public String formatDDMMYYYCurrentDate(int addDays, int addMonths, int addYears) {
                return Math.abs(addDays + 12) + "/" + String.format("%02d", Math.abs(addMonths + 3)) + "/" + (2020 + addYears);
            }

            @Override
            public Date parseDDMMYYYDate(String inputString) throws EvaluateTokensException {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(inputString);
                } catch (ParseException exception) {
                    throw new EvaluateTokensException(exception.getMessage());
                }
            }

            @Override
            public String formatCurrentDateTime(String formatString) {
                return new SimpleDateFormat(formatString).format(new Date(2023 - 1900, 12 - 1, 25, 8, 30));
            }

        };
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
        assertEquals((2 - (3 * 6 + (7 - 4 * (7.0 / 2)))) + 4, instance.evaluateTokensNumber("(2-(3*6+(7-4*(7/2))))+4"));
        assertEquals((2 - (3 * 6 + (7 - 4 * (7.0 + 2)))) + 4, instance.evaluateTokensNumber("(2-(3*6+(7-4*(7+2))))+4"));
        assertEquals(-(-2 - (-3 * -6 + (-7 - -4 * (-7.0 + -2)))) + -4, instance.evaluateTokensNumber("-(-2-(-3*-6+(-7--4*(-7+-2))))+-4"));
        assertEquals(-(-2 - -(-3 * -6 + -(-7 - -4 * -(-7.0 + -2)))) + -4, instance.evaluateTokensNumber("-(-2--(-3*-6+-(-7--4*-(-7+-2))))+-4"));
        assertEquals(2.0 - 3 + 4.0, instance.evaluateTokensNumber("2-3+4"));
        assertEquals(2.0 + 3 - 4.0, instance.evaluateTokensNumber("2+3-4"));
        assertEquals(4.0 / 5.0 * 6, instance.evaluateTokensNumber("4/5*6"));
        assertEquals(4.0 * 5.0 / 6, instance.evaluateTokensNumber("4*5/6"));
        assertEquals(-2.0 - 3 + 4.0, instance.evaluateTokensNumber("-2-3+4"));
        assertEquals(-2.0 + 3 - 4.0, instance.evaluateTokensNumber("-2+3-4"));
        assertEquals(-4.0 / 5.0 * 6, instance.evaluateTokensNumber("-4/5*6"));
        assertEquals(-4.0 * 5.0 / 6, instance.evaluateTokensNumber("-4*5/6"));
        assertEquals(3 + 4.0 / 5.0 * 6, instance.evaluateTokensNumber("3+4/5*6"));
        assertEquals(2.0 - 4.0 / 5.0 * 6, instance.evaluateTokensNumber("2-4/5*6"));
        assertEquals(2.0 - 3 + 4.0 / 5.0 * 6, instance.evaluateTokensNumber("2-3+4/5*6"));
        assertEquals((1.0 + 5) % 3, instance.evaluateTokensNumber("(1 +5) % 3"));
        assertEquals(1.0 + (5 % 3), instance.evaluateTokensNumber("1+(5 % 3)"));
        assertEquals(1.0 + 5 % 3, instance.evaluateTokensNumber("1+5 % 3"));
        assertEquals(1.0 + 5, instance.evaluateTokensNumber("1 + 5"));
        assertEquals(-1.0 + 5, instance.evaluateTokensNumber("-1 + 5"));
        assertEquals(1.0 + 5 * (1 * 3), instance.evaluateTokensNumber("1 + 5 * (1*3)"));
        assertEquals((1.0 + 5.1) * (1 * 3), instance.evaluateTokensNumber("(1 + 5.1) * (1 *3 ) "));
        assertEquals(5.1 / (1 - 3), instance.evaluateTokensNumber("5.1 / ( 1- 3) "));
        assertEquals(-5.1 / (1 - 3), instance.evaluateTokensNumber("-5.1 / ( 1- 3) "));
        assertEquals(2.0 * 3 - 4 + 5.0 / 6, instance.evaluateTokensNumber("2*3-4+5/6"));
        assertEquals(2.0 / 3 * 4 - 5 + 6, instance.evaluateTokensNumber("2/3*4-5+6"));
        assertEquals(2.0 + 3 / 4.0 * 5 - 6, instance.evaluateTokensNumber("2+3/4*5-6"));
        assertEquals(Boolean.toString(1 < 2 + 3 / 4 * 5 - 6), instance.evaluateTokensString("1<2+3/4*5-6"));
        assertEquals(Boolean.toString(3 < (2 + 3 / 4 * 5 - 6)), instance.evaluateTokensString("3< (2+3/4*5-6)"));
        assertEquals(Boolean.toString(10 > (2 + 3 / 4 * 5 - 6)), instance.evaluateTokensString("10 >(2+3/4*5-6)"));
        assertEquals(Boolean.toString(true && 10 > (2 + 3 / 4 * 5 - 6)), instance.evaluateTokensString("true && 10 >(2+3/4*5-6)"));
        assertEquals(Boolean.toString(true && true || false), instance.evaluateTokensString("true && true || false"));
        assertEquals(Boolean.toString(true && true && false), instance.evaluateTokensString("true && true && false"));
        assertEquals(Boolean.toString((true || false) && false), instance.evaluateTokensString("(true||false)&&false"));
        assertEquals(Boolean.toString(12 != 1), instance.evaluateTokensString("12!=1"));
        assertEquals(Boolean.toString(12 != 1 && 12 > 2), instance.evaluateTokensString("12!=1&&12>2"));
        assertEquals(Boolean.toString(12 != 1 && 12 < 2), instance.evaluateTokensString("12!=1&&12<2"));
        assertEquals(Boolean.toString(12 != 1 && 12 <= 12), instance.evaluateTokensString("12!=1&&12<=12"));
        assertEquals(Boolean.toString(12 == 1 && 12 <= 12), instance.evaluateTokensString("12 == 1 && 12 <= 12"));
        assertEquals(Boolean.toString(12 != 1 && 12 >= 12), instance.evaluateTokensString("12!=1&&12>=12"));
        assertEquals(Boolean.toString(12 != 1 && true != false), instance.evaluateTokensString("12!=1&&true!= false"));
        assertEquals(Boolean.toString(12 != 1 || true == false), instance.evaluateTokensString("12!=1||true== false"));
    }

    /**
     * Test of evaluateTokensMethods method, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testEvaluateTokensMethods() throws EvaluateTokensException {
        System.out.println("evaluateTokensMethods");
        HtmlTokenFormatter instance = getInstance();
        assertEquals(6.0, instance.evaluateTokensNumber("length('123456')"));
        assertEquals(9.0, instance.evaluateTokensNumber("length('123456789')"));
        assertEquals(12.0, instance.evaluateTokensNumber("length('123456123456')"));
        assertEquals("testEvaluateReplacementMethod", instance.evaluateTokensString("replaceAll('replaceAll('testEvaluateTokensMethods', 'Tokens', 'Replacement')', 'ds', 'd')"));
        assertEquals("The result is: TFJQYPK", instance.evaluateTokensString("The result is: replaceAll('[Tt][Ff][Jf][Qq][Yy][Pp][Kk]|TFJQYPK', '[^|]*\\|', '')"));
        assertEquals("Als je ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan. Als je besluit niet deel te nemen aan het experiment, kunt je de pagina sluiten of naar een andere website gaan.", instance.evaluateTokensString("replaceAll('Als u ermee instemt om door te gaan met dit experiment, klik dan op 'Akkoord' om verder te gaan. Als u besluit niet deel te nemen aan het experiment, kunt u de pagina sluiten of naar een andere website gaan.', ' u ', ' je ')"));
        assertTrue(instance.evaluateTokensString("random(10)random(10)random(10)random(10)random(10)random(10)random(10)").matches("[0-9]{7,7}"));
        assertTrue(instance.evaluateTokensString("Arandom(10)Brandom(10)Crandom(10)Drandom(10)Erandom(10)Frandom(10)Grandom(10)H").matches("A[0-9]B[0-9]C[0-9]D[0-9]E[0-9]F[0-9]G[0-9]H"));
        assertEquals(12.0, instance.evaluateTokensNumber("daysBetween('01/12/2019', '13/12/2019')"));
        final Number evaluateTokensRandom = instance.evaluateTokensNumber("random(21)");
        assertTrue("random(21) < 21", evaluateTokensRandom.intValue() < 21);
        assertTrue("random(21) >= 0", evaluateTokensRandom.intValue() >= 0);
    }

    /**
     * Test of evaluateTokensMethods method, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testStimulusSubResponse() throws EvaluateTokensException {
        System.out.println("StimulusSubResponse");
        HtmlTokenFormatter instance = getInstance();
        assertEquals("StoredStimulusValue(stimulusId:321,key:AnotherResponse)", instance.formatString("::stimulusResponse_321_AnotherResponse::"));
        assertEquals("Prevalue_StoredStimulusValue(stimulusId:123,key:CodeResponse)_Postvalue", instance.formatString("Prevalue_::stimulusResponse_123_CodeResponse::_Postvalue"));
        assertEquals("Prevalue_StoredStimulusValue(stimulusId:123,key:CodeResponse)_Midvalue_StoredStimulusValue(stimulusId:321,key:AnotherResponse)_Postvalue", instance.formatString("Prevalue_::stimulusResponse_123_CodeResponse::_Midvalue_::stimulusResponse_321_AnotherResponse::_Postvalue"));
        assertEquals("Prevalue_StoredStimulusValue(stimulusId:d1e286,key:CodeResponse)_Postvalue", instance.formatString("Prevalue_::stimulusResponse__CodeResponse::_Postvalue"));
        assertEquals("Prevalue_StoredStimulusValue(stimulusId:123,key:)_Postvalue", instance.formatString("Prevalue_::stimulusResponse_123::_Postvalue"));
        assertEquals("Prevalue_StoredStimulusValue(stimulusId:d1e286,key:)_Postvalue", instance.formatString("Prevalue_::stimulusResponse::_Postvalue"));
    }

    /**
     * Test of documented TokenText entries, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testDocumentedTokenTextValues() throws EvaluateTokensException {
        System.out.println("documented TokenText entries");
        HtmlTokenFormatter instance = getInstance();
        for (TokenText currentToken : TokenText.values()) {
            System.out.println(currentToken.name() + ": " + currentToken.usageDescription);
            System.out.println("exampleUsage: " + currentToken.exampleUsage);
            System.out.println("exampleResult: " + currentToken.exampleResult);
            assertEquals(currentToken.exampleResult, instance.evaluateTokensString(currentToken.exampleUsage));
        }
    }

    /**
     * Test of documented TokenText entries, of class HtmlTokenFormatter.
     *
     * @throws nl.mpi.tg.eg.experiment.client.exception.EvaluateTokensException
     */
    @Test
    public void testDocumentedTokenMethodValues() throws EvaluateTokensException {
        System.out.println("documented TokenMethod entries");
        HtmlTokenFormatter instance = getInstance();
        for (TokenMethod currentToken : TokenMethod.values()) {
            System.out.println(currentToken.name() + ": " + currentToken.usageDescription);
            System.out.println("exampleUsage: " + currentToken.exampleUsage);
            System.out.println("exampleResult: " + currentToken.exampleResult);
            if (currentToken == TokenMethod.random) {
                assertTrue(currentToken.exampleResult.contains(instance.evaluateTokensString(currentToken.exampleUsage)));
            } else {
                assertEquals(currentToken.exampleResult, instance.evaluateTokensString(currentToken.exampleUsage));
            }
        }
    }
}
