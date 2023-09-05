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
package nl.mpi.tg.eg.experimentdesigner.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @since Jun 7, 2018 5:02:22 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class XpathExperimentValidatorTest {

    public XpathExperimentValidatorTest() {
    }

    private Document getDocument(final String stringXml) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(stringXml.getBytes("UTF-8"));
        return builder.parse(inputStream);
    }

    /**
     * Test of extractFrinexVersion method, of class XpathExperimentValidator.
     */
    @Test
    public void testExtractFrinexVersion() throws Exception {
        System.out.println("extractFrinexVersion");
        String defaultSchemaName = "testdefault";
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment><deployment frinexVersion=\"found\"/></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment><deployment frinexVersion      =       \"found\"         /></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://frinexbuild.mpi.nl/usethefrinexVersionattributnotthis.xsd\" ><deployment frinexVersion=\"found\"/></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://frinexbuild.mpi.nl/usethefrinexVersionattributnotthis.xsd\" ><deployment \nfrinexVersion  \n   =   \n  \"found\"\n/>\n</experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://frinexbuild.mpi.nl/found.xsd\" ><metadata/><deployment frinexVersion=\"shouldhavestoppedatmetadata\"/></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://frinexbuild.mpi.nl/found.xsd\" ><deployment/></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \nxsi:noNamespaceSchemaLocation      \t    =      \t    \"http://frinexbuild.mpi.nl/found.xsd\"\n >\n<deployment/>\n</experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation  =  \"http://frinexbuild.mpi.nl/found.xsd\"    ><deployment/></experiment>"), defaultSchemaName));
        assertEquals("found", instance.extractFrinexVersion(new StringReader("<experiment xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://frinexbuild.mpi.nl/found.xsd\" ></experiment>"), defaultSchemaName));
        assertEquals(defaultSchemaName, instance.extractFrinexVersion(new StringReader("<experiment><deployment frinexVersion=\"\"/></experiment>"), defaultSchemaName));
        assertEquals(defaultSchemaName, instance.extractFrinexVersion(new StringReader("<experiment><deployment/></experiment>"), defaultSchemaName));
        assertEquals(defaultSchemaName, instance.extractFrinexVersion(new StringReader("<experiment></experiment>"), defaultSchemaName));
    }

    /**
     * Test of validateInternalName method, of class XpathExperimentValidator.
     *
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.xpath.XPathExpressionException
     */
    @Test
    public void testValidateInternalName() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("validateInternalName");
        String fileOkName1 = "generic_example.xml";
        String fileFailName1 = "geNeric_example.xml";
        String fileFailName2 = "geNeric_example.xml";
        String fileOkName2 = "generic_example";
        Document xmlOkDocument = getDocument("<experiment appNameDisplay=\"generic_example\" appNameInternal=\"generic_example\"/>\n");
        Document xmlOkDocument2 = getDocument("<experiment appNameDisplay=\"generic_example\"/>\n");
        Document xmlFailDocument = getDocument("<experiment appNameDisplay=\"generic_example\" appNameInternal=\"generic_exaMple\"/>\n");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateInternalName(fileOkName1, xmlOkDocument));
        assertEquals("", instance.validateInternalName(fileOkName2, xmlOkDocument));
        assertEquals("", instance.validateInternalName(fileOkName2, xmlOkDocument2));
        assertNotEquals("", instance.validateInternalName(fileFailName1, xmlOkDocument));
        assertNotEquals("", instance.validateInternalName(fileFailName2, xmlOkDocument));
        assertNotEquals("", instance.validateInternalName(fileOkName1, xmlFailDocument));
        assertNotEquals("", instance.validateInternalName(fileOkName2, xmlFailDocument));
        assertNotEquals("", instance.validateInternalName(fileFailName1, xmlFailDocument));
        assertNotEquals("", instance.validateInternalName(fileFailName2, xmlFailDocument));
    }

    /**
     * Test of validatePresenterNames method, of class XpathExperimentValidator.
     *
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.xpath.XPathExpressionException
     */
    @Test
    public void testValidatePresenterNames() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("validatePresenterNames");
        Document xmlOkDocument = getDocument("<experiment><presenter self=\"Toestemming\"></presenter><presenter self=\"Informatie\"></presenter></experiment>\n");
        Document xmlFailDocument1 = getDocument("<experiment><presenter self=\"Informatie\"></presenter><presenter self=\"Informatie\"></presenter></experiment>\n");
        Document xmlFailDocument2 = getDocument("<experiment><presenter self=\"Snooze\"></presenter><presenter self=\"snooze\"></presenter></experiment>\n");
//        Document xmlOkAccentDocument = getDocument("<experiment><presenter self=\"Informatié\"></presenter><presenter self=\"Informatie\"></presenter><presenter back=\"Menu\" menuLabel=\"Terug\" type=\"text\" self=\"cafe_test\" title=\"café encoding test\"><htmlText featureText=\"Please make sure that the accent is correctly displayed on this screen: café\"/><targetButton featureText=\"volgende [ spatiebalk ]\" hotKey=\"SPACE\" target=\"Second\"/></presenter></experiment>\n");
//        Document xmlFailAccentDocument = getDocument("<experiment><presenter self=\"Informatié\"></presenter><presenter self=\"Informatie\"></presenter><presenter back=\"Menu\" menuLabel=\"Terug\" type=\"text\" self=\"café_test\" title=\"café encoding test\"><htmlText featureText=\"Please make sure that the accent is correctly displayed on this screen: café\"/><targetButton featureText=\"volgende [ spatiebalk ]\" hotKey=\"SPACE\" target=\"Second\"/></presenter></experiment>\n");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validatePresenterNames(xmlOkDocument));
//        assertEquals("", instance.validatePresenterNames(xmlOkAccentDocument));
        assertNotEquals("", instance.validatePresenterNames(xmlFailDocument1));
        assertNotEquals("", instance.validatePresenterNames(xmlFailDocument2));
//        assertNotEquals("", instance.validatePresenterNames(xmlFailAccentDocument));
    }

    /**
     * Test of validatePresenterLinks method, of class XpathExperimentValidator.
     *
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.xpath.XPathExpressionException
     */
    @Test
    public void testValidatePresenterLinks() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("validatePresenterLinks");
        Document xmlOkDocument = getDocument("<experiment><presenter self=\"Toestemming\" next=\"Informatie\" back=\"Informatie\"><eraseUsersDataButton target=\"Informatie\"/><targetButton target=\"Informatie\"/></presenter><presenter self=\"Informatie\"></presenter></experiment>\n");
        Document xmlFailNextDocument = getDocument("<experiment><presenter self=\"Toestemming\" next=\"Missing\" back=\"Informatie\"><eraseUsersDataButton target=\"Informatie\"/><targetButton target=\"Informatie\"/></presenter><presenter self=\"Informatie\"></presenter></experiment>");
        Document xmlFailBackDocument = getDocument("<experiment><presenter self=\"Toestemming\" next=\"Informatie\" back=\"Missing\"><eraseUsersDataButton target=\"Informatie\"/><targetButton target=\"Informatie\"/></presenter><presenter self=\"Informatie\"></presenter></experiment>");
        Document xmlFailTarget1Document = getDocument("<experiment><presenter self=\"Toestemming\" next=\"Informatie\" back=\"Informatie\"><eraseUsersDataButton target=\"missing\"/><targetButton target=\"Informatie\"/></presenter><presenter self=\"Informatie\"></presenter></experiment>");
        Document xmlFailTarget2Document = getDocument("<experiment><presenter self=\"Toestemming\" next=\"Informatie\" back=\"Informatie\"><eraseUsersDataButton target=\"Informatie\"/><targetButton target=\"missing\"/></presenter><presenter self=\"Informatie\"></presenter></experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validatePresenterLinks(xmlOkDocument));
        assertNotEquals("", instance.validatePresenterLinks(xmlFailNextDocument));
        assertNotEquals("", instance.validatePresenterLinks(xmlFailBackDocument));
        assertNotEquals("", instance.validatePresenterLinks(xmlFailTarget1Document));
        assertNotEquals("", instance.validatePresenterLinks(xmlFailTarget2Document));
    }

    /**
     * Test of validateStimuliTags method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateStimuliTags() throws Exception {
        System.out.println("validateStimuliTags");
        Document xmlOkTagDocument = getDocument("<experiment><presenter self=\"Informatie\"><loadStimulus eventTag=\"loadTargetPicture\" randomise=\"false\" repeatRandomWindow=\"0\" maxStimuli=\"1000\" repeatCount=\"1\"><hasMoreStimulus></hasMoreStimulus><endOfStimulus></endOfStimulus><randomGrouping/><stimuli><tag>Testrun1</tag></stimuli></loadStimulus></presenter><stimuli><stimulus audioPath=\"daga3_cut\" identifier=\"daga3_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga3_cut\"/><stimulus audioPath=\"daga11_cut\" identifier=\"daga11_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga11_cut Testrun1\"/><stimulus audioPath=\"daga5_cut\" identifier=\"daga5_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga5_cut\"/></stimuli></experiment>");
        Document xmlFailTagSpaceDocument = getDocument("<experiment><presenter self=\"Informatie\"><loadStimulus eventTag=\"loadTargetPicture\" randomise=\"false\" repeatRandomWindow=\"0\" maxStimuli=\"1000\" repeatCount=\"1\"><hasMoreStimulus></hasMoreStimulus><endOfStimulus></endOfStimulus><randomGrouping/><stimuli><tag>Testrun1 space</tag></stimuli></loadStimulus></presenter><stimuli><stimulus audioPath=\"daga3_cut\" identifier=\"daga3_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga3_cut\"/><stimulus audioPath=\"daga11_cut\" identifier=\"daga11_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga11_cut Testrun1\"/><stimulus audioPath=\"daga5_cut\" identifier=\"daga5_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga5_cut\"/></stimuli></experiment>");
        Document xmlFailTagDocument = getDocument("<experiment><presenter self=\"Informatie\"><loadStimulus eventTag=\"loadTargetPicture\" randomise=\"false\" repeatRandomWindow=\"0\" maxStimuli=\"1000\" repeatCount=\"1\"><hasMoreStimulus></hasMoreStimulus><endOfStimulus></endOfStimulus><randomGrouping/><stimuli><tag>Testrun1</tag></stimuli></loadStimulus></presenter><stimuli><stimulus audioPath=\"daga3_cut\" identifier=\"daga3_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga3_cut\"/><stimulus audioPath=\"daga11_cut\" identifier=\"daga11_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga11_cut\"/><stimulus audioPath=\"daga5_cut\" identifier=\"daga5_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga5_cut\"/></stimuli></experiment>");
        Document xmlOkTagsDocument = getDocument("<experiment><presenter self=\"Informatie\"><loadStimulus eventTag=\"loadTargetPicture\" randomise=\"false\" repeatRandomWindow=\"0\" maxStimuli=\"1000\" repeatCount=\"1\"><hasMoreStimulus><currentStimulusHasTag tags=\"ประเพณีบุญบั้งไฟ Rocket Festival Lao Thai ບຸນບັ້ງໄຟ\" msToNext=\"60\"><conditionTrue><plainText featureText=\"plainText in conditionTrue\"/></conditionTrue><conditionFalse><plainText featureText=\"plainText in conditionFalse\"/></conditionFalse></currentStimulusHasTag></hasMoreStimulus><endOfStimulus></endOfStimulus><randomGrouping/><stimuli></stimuli></loadStimulus></presenter><stimuli><stimulus audioPath=\"daga3_cut\" identifier=\"daga3_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"ประเพณีบุญบั้งไฟ Rocket daga3_cut\"/><stimulus audioPath=\"daga11_cut\" identifier=\"daga11_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"Festival Lao Thai daga11_cut\"/><stimulus audioPath=\"daga5_cut\" identifier=\"daga5_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"ບຸນບັ້ງໄຟ daga5_cut\"/></stimuli></experiment>");
        Document xmlFailTagsDocument = getDocument("<experiment><presenter self=\"Informatie\"><loadStimulus eventTag=\"loadTargetPicture\" randomise=\"false\" repeatRandomWindow=\"0\" maxStimuli=\"1000\" repeatCount=\"1\"><hasMoreStimulus><currentStimulusHasTag tags=\"ประเพณีบุญบั้งไฟ Rocket Festival Lao Thai ບຸນບັ້ງໄຟ\" msToNext=\"60\"><conditionTrue><plainText featureText=\"plainText in conditionTrue\"/></conditionTrue><conditionFalse><plainText featureText=\"plainText in conditionFalse\"/></conditionFalse></currentStimulusHasTag></hasMoreStimulus><endOfStimulus></endOfStimulus><randomGrouping/><stimuli></stimuli></loadStimulus></presenter><stimuli><stimulus audioPath=\"daga3_cut\" identifier=\"daga3_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga3_cut\"/><stimulus audioPath=\"daga11_cut\" identifier=\"daga11_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga11_cut\"/><stimulus audioPath=\"daga5_cut\" identifier=\"daga5_cut\" correctResponses=\"1\" pauseMs=\"0\" tags=\"daga5_cut\"/></stimuli></experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateStimuliTags(xmlOkTagDocument));
        assertNotEquals("", instance.validateStimuliTags(xmlFailTagDocument));
        assertEquals("", instance.validateStimuliTags(xmlOkTagsDocument));
        assertNotEquals("", instance.validateStimuliTags(xmlFailTagsDocument));
        assertNotEquals("", instance.validateStimuliTags(xmlFailTagSpaceDocument));
    }

    /**
     * Test of validateMetadataFields method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateMetadataFields() throws Exception {
        System.out.println("validateMetadataFields");
        Document xmlOkPostNameDocument = getDocument("<experiment>"
                + "<administration>\n"
                + "<dataAgreementField fieldName=\"age\"/>\n"
                + "<validation errorField=\"age\"/>\n"
                + "</administration>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"workerId\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"age\"/>"
                + "<randomGrouping storageField=\"gender\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailErrorFieldDocument = getDocument("<experiment>"
                + "<administration>\n"
                + "<dataAgreementField fieldName=\"age\"/>\n"
                + "<validation errorField=\"missing\"/>\n"
                + "</administration>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"workerId\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"age\"/>"
                + "<randomGrouping storageField=\"gender\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailDataAgreementFieldDocument = getDocument("<experiment>"
                + "<administration>\n"
                + "<dataAgreementField fieldName=\"missing\"/>\n"
                + "<validation errorField=\"age\"/>\n"
                + "</administration>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"workerId\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"age\"/>"
                + "<randomGrouping storageField=\"gender\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailStorageFieldDocument = getDocument("<experiment>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"workerId\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"age\"/>"
                + "<randomGrouping storageField=\"missing\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailLinkedFieldNameDocument = getDocument("<experiment>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"workerId\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"missing\"/>"
                + "<randomGrouping storageField=\"gender\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailFieldNameDocument = getDocument("<experiment>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"missing\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"workerId\"/>"
                + "<randomGrouping storageField=\"gender\"/>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailAllDocument = getDocument("<experiment>"
                + "<metadata><field postName=\"workerId\"/><field postName=\"age\"/><field postName=\"gender\"/></metadata>"
                + "<presenter self=\"Informatie\">"
                + "<metadataField fieldName=\"one\"/>"
                + "<metadataFieldConnection fieldName=\"workerId\" linkedFieldName=\"two\"/>"
                + "<randomGrouping storageField=\"three\"/>"
                + "</presenter>"
                + "</experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateMetadataFields(xmlOkPostNameDocument));
        System.out.println(instance.validateMetadataFields(xmlFailStorageFieldDocument));
        assertEquals("Each 'errorField' attribute must reference a valid metadata field, but 'missing' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailErrorFieldDocument));
        assertEquals("Each 'fieldName' attribute must reference a valid metadata field, but 'missing' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailDataAgreementFieldDocument));
        assertEquals("Each 'storageField' attribute must reference a valid metadata field, but 'missing' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailStorageFieldDocument));
        assertEquals("Each 'linkedFieldName' attribute must reference a valid metadata field, but 'missing' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailLinkedFieldNameDocument));
        assertEquals("Each 'fieldName' attribute must reference a valid metadata field, but 'missing' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailFieldNameDocument));
        assertEquals("Each 'fieldName' attribute must reference a valid metadata field, but 'one' is not specified the postName attribute of any metadata field.\n"
                + "Each 'linkedFieldName' attribute must reference a valid metadata field, but 'two' is not specified the postName attribute of any metadata field.\n"
                + "Each 'storageField' attribute must reference a valid metadata field, but 'three' is not specified the postName attribute of any metadata field.\n", instance.validateMetadataFields(xmlFailAllDocument));
    }

    /**
     * Test of validateDocument method, of class XpathExperimentValidator.
     *
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.xpath.XPathExpressionException
     * @throws nl.mpi.tg.eg.experimentdesigner.util.XpathExperimentException
     * @throws java.net.URISyntaxException
     */
    @Test
    public void testValidateDocument() throws IOException, IllegalArgumentException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException, URISyntaxException {
        System.out.println("validateDocument");
        final String inputDirectory = "/frinex-rest-output/";
        URI outputDirectoryUri = this.getClass().getResource(inputDirectory).toURI();
        System.out.println(inputDirectory);
        XpathExperimentValidator instance = new XpathExperimentValidator();
        instance.validateDocument(new File(new File(outputDirectoryUri), "advocas1.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "corsi.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "heoexp01.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "multiparticipant.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "playhouse_studyJ.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "short-for-unittest-to-delete.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "advocas2.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "digitspanbackward.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "hrpretest.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "nonwacq.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "ppvt.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "shortmultiparticipant01.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "alloptions.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "dobes_annotator.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "hrpretest02.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "online_emotions.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "questionnaire.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "synquiz2.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "antwoordraden.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "engadvocas.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "joseco01.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "onlinepretest.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "vanuatufieldkit.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "audioas2.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "exampleronald.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "joseco02.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "parcours01.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "wellspringssamoan.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "audiononwordmonitoring.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "french_audio.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "kinship_example.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "rosselfieldkit.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "with_stimulus_example.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "audiononwordmonitoring_old.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "french_conversation.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "ld_screensize.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "sentences_rating_task.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "zinnen_afmaken.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "autoprediction.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "generic_example.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "leeservaring.xml"));
        // instance.validateDocument(new File(new File(outputDirectoryUri), "playback_preference.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "sentveri_exp3.xml"));
        instance.validateDocument(new File(new File(outputDirectoryUri), "zinnen_beoordelen.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "bq4english.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "grammaras.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "lilbq4.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "playhouse_study.xml")); // TODO: update this test and add back in
        instance.validateDocument(new File(new File(outputDirectoryUri), "shawifieldkit.xml"));
//        instance.validateDocument(new File(new File(outputDirectoryUri), "sentenceplausibility.xml"));
    }

    /**
     * Test of validateStimuliIds method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateStimuliIds() throws Exception {
        System.out.println("validateStimuliIds");
        Document xmlOkIdentifierDocument = getDocument("<experiment>"
                + "<stimuli>"
                + "<stimulus identifier=\"daga3_cut\" />"
                + "<stimulus identifier=\"daga11_cut\" />"
                + "<stimulus identifier=\"daga5_cut\" />"
                + "</stimuli>"
                + "</experiment>");
        Document xmlFailIdentifierDocument = getDocument("<experiment>"
                + "<stimuli>"
                + "<stimulus identifier=\"daga11_cut\" />"
                + "<stimulus identifier=\"daga11_cut\" />"
                + "<stimulus identifier=\"daga5_cut\" />"
                + "</stimuli>"
                + "</experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateStimuliIds(xmlOkIdentifierDocument));
        assertEquals("The stimulus identifier 'daga11_cut' has been used more than once. Each stimulus identifier must be unique.", instance.validateStimuliIds(xmlFailIdentifierDocument));
    }

    /**
     * Test of validateMetadataFieldPostNames method, of class
     * XpathExperimentValidator.
     */
    @Test
    public void testValidateMetadataFieldPostNames() throws Exception {
        System.out.println("validateMetadataFieldPostNames");
        Document xmlOkIdentifierDocument = getDocument("<experiment>"
                + "<metadata>"
                + "<field postName=\"daga3_cut\" />"
                + "<field postName=\"daga11_cut\" />"
                + "<field postName=\"daga5_cut\" />"
                + "</metadata>"
                + "</experiment>");
        Document xmlFailIdentifierDocument = getDocument("<experiment>"
                + "<metadata>"
                + "<field postName=\"daga3_cut\" />"
                + "<field postName=\"daga3_cut\" />"
                + "<field postName=\"daga5_cut\" />"
                + "</metadata>"
                + "</experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateMetadataFieldPostNames(xmlOkIdentifierDocument));
        assertEquals("The metadata field postName 'daga3_cut' has been used more than once. Each postName must be unique.", instance.validateMetadataFieldPostNames(xmlFailIdentifierDocument));
    }

    /**
     * Test of validateRegexStrings method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateRegexStrings() throws Exception {
        System.out.println("validateRegexStrings");
        final String validateRegexString = "<experiment>"
                + "<validation>"
                + "<recordMatch validationRegex=\"regex_string\" />\n"
                + "<fieldMatch validationRegex=\"regex_string\" />\n"
                + "</validation>"
                + "<administration>"
                + "<dataAgreementField matchingRegex=\"regex_string\" validationRegex=\"regex_string\" />\n"
                + "</administration>"
                + "<metadata>"
                + "<field controlledRegex=\"regex_string\" />\n"
                + "</metadata>"
                + "<presenter>"
                + "<disableButtonGroup matchingRegex=\"regex_string\" />\n"
                + "<enableButtonGroup matchingRegex=\"regex_string\" />\n"
                + "<hasMetadataValue matchingRegex=\"regex_string\" />\n"
                + "<hideButtonGroup matchingRegex=\"regex_string\" />\n"
                + "<loadSdCardStimulus "
                + "matchingRegex=\"regex_string\" "
                + "excludeRegex=\"regex_string\" "
                + "replacementRegex=\"regex_string\" "
                + "/>\n"
                + "<matchOnEvalTokens matchingRegex=\"regex_string\" />\n"
                + "<metadataFieldDateTriggered "
                + "enabledRegex=\"regex_string\" "
                + "visibleRegex=\"regex_string\" "
                + " />\n"
                + "<metadataFieldVisibilityDependant "
                + "enabledRegex=\"regex_string\" "
                + "visibleRegex=\"regex_string\" "
                + " />\n"
                + "<setMetadataValue replacementRegex=\"regex_string\" />\n"
                + "<stimulusPresent replacementRegex=\"regex_string\" />\n"
                + "<showButtonGroup matchingRegex=\"regex_string\" />\n"
                + "<withMatchingStimulus matchingRegex=\"regex_string\" />\n"
                + "<switchUserIdButton validationRegex=\"regex_string\" />\n"
                + "<transmitResults sendingRegex=\"regex_string\" receivingRegex=\"regex_string\" />\n"
                + "<triggerRandom matchingRegex=\"regex_string\" />\n"
                + "<addStimulusCodeResponseValidation validationRegex=\"regex_string\" />\n"
                + "<stimulusFreeText validationRegex=\"regex_string\" />\n"
                + "<removeMatchingStimulus matchingRegex=\"regex_string\" />\n"
                + "<stimulusHasResponse matchingRegex=\"regex_string\" />\n"
                + "<startAudioRecorderWeb deviceRegex=\"regex_string\" />\n"
                + "</presenter>"
                + "</experiment>";
        //System.out.println(validateRegexString);
        Document xmlOkIdentifierDocument = getDocument(validateRegexString.replaceAll("regex_string", ".*"));
        Document xmlFailIdentifierDocument = getDocument(validateRegexString.replaceAll("regex_string", "*"));
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateRegexStrings(xmlOkIdentifierDocument));
        assertEquals("Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  controlledRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  excludeRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  replacementRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  enabledRegex\n"
                + "Invalid REGEX \"*\" found in attribute  visibleRegex\n"
                + "Invalid REGEX \"*\" found in attribute  enabledRegex\n"
                + "Invalid REGEX \"*\" found in attribute  visibleRegex\n"
                + "Invalid REGEX \"*\" found in attribute  replacementRegex\n"
                + "Invalid REGEX \"*\" found in attribute  replacementRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  receivingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  sendingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  validationRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  matchingRegex\n"
                + "Invalid REGEX \"*\" found in attribute  deviceRegex\n", instance.validateRegexStrings(xmlFailIdentifierDocument));
    }

    /**
     * Test of validateMetadataFieldPostNames method, of class
     * XpathExperimentValidator.
     */
    @Test
    public void testValidateMetadataFieldNamesExist() throws Exception {
        System.out.println("validateMetadataFieldNamesExist");
        Document xmlOkIdentifierDocument = getDocument("<experiment>"
                + "<metadata>"
                + "<field postName=\"referenceAngle\" />"
                + "<field postName=\"blagNegativePositive\" />"
                + "<field postName=\"discTransferOrder\" />"
                + "<field postName=\"discLabelOrder\" />"
                + "<field postName=\"subjectID\" />"
                + "</metadata>"
                + "<presenter>"
                + "<hasMetadataValue fieldName=\"subjectID\">"
                + "<conditionTrue>"
                + "<matchOnEvalTokens>\n"
                + "<conditionTrue>"
                + "<setMetadataValue fieldName=\"referenceAngle\"/>\n"
                + "<setMetadataValue fieldName=\"blagNegativePositive\"/>\n"
                + "<setMetadataValue fieldName=\"discTransferOrder\"/>\n"
                + "<setMetadataValue fieldName=\"discLabelOrder\"/>\n"
                + "<gotoNextPresenter/>\n"
                + "</conditionTrue>\n"
                + "<conditionFalse/>\n"
                + "<onError/>\n"
                + "</matchOnEvalTokens>"
                + "</conditionTrue>\n"
                + "<conditionFalse>\n"
                + "</conditionFalse>\n"
                + "</hasMetadataValue>"
                + "</presenter>"
                + "</experiment>");
        Document xmlFailIdentifierDocument = getDocument("<experiment>"
                + "<metadata>"
                + "<field postName=\"daga3_cut\" />"
                + "<field postName=\"daga11_cut\" />"
                + "<field postName=\"daga5_cut\" />"
                + "</metadata>"
                + "<presenter>"
                + "<hasMetadataValue fieldName=\"subjectID\">"
                + "<conditionTrue>"
                + "<matchOnEvalTokens>\n"
                + "<conditionTrue>"
                + "<setMetadataValue fieldName=\"referenceAngle\"/>\n"
                + "<setMetadataValue fieldName=\"blagNegativePositive\"/>\n"
                + "<setMetadataValue fieldName=\"discTransferOrder\"/>\n"
                + "<setMetadataValue fieldName=\"discLabelOrder\"/>\n"
                + "<gotoNextPresenter/>\n"
                + "</conditionTrue>\n"
                + "<conditionFalse/>\n"
                + "<onError/>\n"
                + "</matchOnEvalTokens>"
                + "</conditionTrue>\n"
                + "<conditionFalse>\n"
                + "</conditionFalse>\n"
                + "</hasMetadataValue>"
                + "</presenter>"
                + "</experiment>");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        assertEquals("", instance.validateMetadataFields(xmlOkIdentifierDocument));
        assertEquals("Each 'fieldName' attribute must reference a valid metadata field, but 'subjectID' is not specified the postName attribute of any metadata field.\n"
                + "Each 'fieldName' attribute must reference a valid metadata field, but 'referenceAngle' is not specified the postName attribute of any metadata field.\n"
                + "Each 'fieldName' attribute must reference a valid metadata field, but 'blagNegativePositive' is not specified the postName attribute of any metadata field.\n"
                + "Each 'fieldName' attribute must reference a valid metadata field, but 'discTransferOrder' is not specified the postName attribute of any metadata field.\n"
                + "Each 'fieldName' attribute must reference a valid metadata field, but 'discLabelOrder' is not specified the postName attribute of any metadata field.\n"
                + "", instance.validateMetadataFields(xmlFailIdentifierDocument));
    }

    /**
     * Test of validatePresenterTypes method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidatePresenterTypes() throws Exception {
        System.out.println("validatePresenterTypes");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        String commonFaultTests[][] = {
            {"<experiment><presenter self=\"loadStimulusMenu\" type=\"menu\"><loadStimulus/></presenter></experiment>", "The Presenter loadStimulusMenu is of the type menu and cannot be used with loadStimulus. "},
            {"<experiment><presenter self=\"loadStimulusMenu\" type=\"menu\"><table><row><col><loadStimulus/></col></row></table></presenter></experiment>", "The Presenter loadStimulusMenu is of the type menu and cannot be used with loadStimulus. "},
            {"<experiment><presenter self=\"loadStimulusMenu\" type=\"menu\"><menuItem/></presenter></experiment>", ""},
            {"<experiment><presenter self=\"loadStimulusMenu\" type=\"stimulus\"><loadStimulus/></presenter></experiment>", ""},
            {"<experiment><presenter self=\"loadStimulusMenu\" type=\"menu\"><menuItem/></presenter><presenter self=\"loadStimulusMenu\" type=\"stimulus\"><loadStimulus/></presenter></experiment>", ""}
        };
        for (String currentFault[] : commonFaultTests) {
            Document loadStimulusMenuFalutDocument = getDocument(currentFault[0]);
            assertEquals(currentFault[1], instance.validatePresenterTypes(loadStimulusMenuFalutDocument));
        }
    }

    /**
     * Test of validateEvaluateTokens method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateEvaluateTokens() throws Exception {
        System.out.println("validateEvaluateTokens");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        String commonFaultTests[][] = {
            {"<experiment><setMetadataEvalTokens evaluateTokens=\"replaceAll(&quot;&lt;metadataField_storedDate&gt;&quot;, &quot; u &quot;, &quot; je &quot;)\"/><setMetadataEvalTokens evaluateTokens=\"replaceAll('&lt;metadataField_storedDate&gt;', ' u ', ' je ')\"/><setMetadataEvalTokens evaluateTokens=\"replaceAll(&quot;::metadataField_storedDate::&quot;, &quot; u &quot;, &quot; je &quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "replaceAll(\"<metadataField_storedDate>\", \" u \", \" je \")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "replaceAll('<metadataField_storedDate>', ' u ', ' je ')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "replaceAll(\"::metadataField_storedDate::\", \" u \", \" je \")\n"
            },
            {"<experiment><htmlTokenText featureText=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><htmlTokenText featureText=\"length('&lt;metadataField_educationOther&gt;')\"/><htmlTokenText featureText=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            },
            {"<experiment><triggerMatching listenerId=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><triggerMatching listenerId=\"length('&lt;metadataField_educationOther&gt;')\"/><triggerMatching listenerId=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            },
            {"<experiment><regionCodeStyle regionId=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><regionCodeStyle regionId=\"length('&lt;metadataField_educationOther&gt;')\"/><regionCodeStyle regionId=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            },
            {"<experiment><setStimulusCodeResponse codeFormat=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><setStimulusCodeResponse codeFormat=\"length('&lt;metadataField_educationOther&gt;')\"/><setStimulusCodeResponse codeFormat=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            },
            {"<experiment><setStimulusCodeResponse groupId=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><setStimulusCodeResponse groupId=\"length('&lt;metadataField_educationOther&gt;')\"/><setStimulusCodeResponse groupId=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            },
            {"<experiment><sendGroupTokenMessage dataLogFormat=\"length(&quot;&lt;metadataField_educationOther&gt;&quot;)\"/><sendGroupTokenMessage dataLogFormat=\"length('&lt;metadataField_educationOther&gt;')\"/><sendGroupTokenMessage dataLogFormat=\"length(&quot;::metadataField_educationOther::&quot;)\"/></experiment>",
                "Issues with token text found.\n"
                + "The first token usage is:\n"
                + "length(\"<metadataField_educationOther>\")\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length('<metadataField_educationOther>')\n"
                + "The following token usage is inconsistent with the first:\n"
                + "length(\"::metadataField_educationOther::\")\n"
            }
        };
        for (String currentFault[] : commonFaultTests) {
            Document loadStimulusMenuFalutDocument = getDocument(currentFault[0]);
            assertEquals(currentFault[1], instance.validateEvaluateTokens(loadStimulusMenuFalutDocument));
        }
    }

    /**
     * Test of validateEvaluateTokens method, of class XpathExperimentValidator.
     */
    @Test
    public void testValidateProductionVersion() throws Exception {
        System.out.println("validateProductionVersion");
        XpathExperimentValidator instance = new XpathExperimentValidator();
        String commonFaultTests[][] = {
            {"<experiment><deployment state=\"staging\" frinexVersion=\"stable\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"staging\" frinexVersion=\"beta\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"staging\" frinexVersion=\"snapshot\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"staging\" frinexVersion=\"1.6.3481-snapshot\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"stable\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"1.6.3481-stable\"/></experiment>",
                ""
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"1.6.3481-snapshot\"/></experiment>",
                "The snapshot versions cannot be used for production deployments. Please specify a Frinex stable version for example frinexVersion=\"1.6.XXXX-stable\". You can find the version number on the initial page of the staging version of your experiment FRINEX Version: 1.6.XXXX-stable.\n"
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"snapshot\"/></experiment>",
                "The snapshot versions cannot be used for production deployments. Please specify a Frinex stable version for example frinexVersion=\"1.6.XXXX-stable\". You can find the version number on the initial page of the staging version of your experiment FRINEX Version: 1.6.XXXX-stable.\n"
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"alpha\"/></experiment>",
                "The frinexVersion=\"alpha\" is too ambiguous for production deployments. Please specify the Frinex version for example frinexVersion=\"1.6.XXXX-stable\". You can find the version number on the initial page of the staging version of your experiment FRINEX Version: 1.6.XXXX-stable.\n"
            },
            {"<experiment><deployment state=\"production\" frinexVersion=\"beta\"/></experiment>",
                "The frinexVersion=\"beta\" is too ambiguous for production deployments. Please specify the Frinex version for example frinexVersion=\"1.6.XXXX-stable\". You can find the version number on the initial page of the staging version of your experiment FRINEX Version: 1.6.XXXX-stable.\n"
            }
        };
        for (String currentFault[] : commonFaultTests) {
            Document loadStimulusMenuFalutDocument = getDocument(currentFault[0]);
            assertEquals(currentFault[1], instance.validateProductionVersion(loadStimulusMenuFalutDocument));
        }
    }
}
