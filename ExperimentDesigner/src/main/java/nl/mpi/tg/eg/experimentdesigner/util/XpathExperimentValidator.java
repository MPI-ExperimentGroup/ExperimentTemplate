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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import nl.mpi.tg.eg.experimentdesigner.model.FeatureType;
import nl.mpi.tg.eg.experimentdesigner.model.PresenterType;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @since Jun 7, 2018 4:21:45 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class XpathExperimentValidator {

    public String extractFrinexVersion(Reader xmlFileSource, String defaultSchemaName) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        String frinexVersion = (String) validationXPath.compile("/experiment/deployment/@frinexVersion").evaluate(new InputSource(xmlFileSource), XPathConstants.STRING);
        if (frinexVersion != null && !frinexVersion.isEmpty()) {
            return frinexVersion;
        } else {
            return defaultSchemaName;
        }
    }

    public void validateDocument(File xmlFile) throws IllegalArgumentException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, XpathExperimentException {
        try {
            System.out.println("validateDocument: " + xmlFile.getName());
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(fileInputStream);
            String fileName = xmlFile.getName();
            String result = "";
            result += validateInternalName(fileName, xmlDocument);
            result += validatePresenterNames(xmlDocument);
            result += validatePresenterLinks(xmlDocument);
            result += validateStimuliTags(xmlDocument);
            result += validateStimuliIds(xmlDocument); // it has been observed that duplicate stimulus identifiers do cause issues, so while there might be use cases where this could be done intentionally, so we no longer allow it here
            result += validateMetadataFieldPostNames(xmlDocument);
            result += validateMetadataFields(xmlDocument);
            result += validateRegexStrings(xmlDocument);
            result += validatePresenterTypes(xmlDocument);
            if (!result.isEmpty()) {
                throw new XpathExperimentException(result);
            }
        } catch (SAXException saxe) {
            if (saxe.getMessage().contains("[xX][mM][lL]")) {
                // an empty line at the start of the file gives an unhelpful message "The processing instruction target matching "[xX][mM][lL]" is not allowed." so we give a hint here
                throw new XpathExperimentException(saxe.getMessage() + " There must be no white space and no empty lines at the start of the XML file.");
            } else if (saxe.getMessage().contains("root element")) {
                throw new XpathExperimentException(saxe.getMessage() + " There can only be one root element and it must be <experiment>...</experiment>.");
            } else {
                throw new XpathExperimentException(saxe.getMessage());
            }
        }
    }

    protected String validateInternalName(String fileName, Document xmlDocument) throws XPathExpressionException {
        // check that the name is lower case and validate that the file name matches the internal name of the experiment
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        String appNameInternal = (String) validationXPath.compile("/experiment/@appNameInternal").evaluate(xmlDocument, XPathConstants.STRING);
        if (!fileName.equals(fileName.toLowerCase())) {
            return "The experiment file name must be lowercase: '" + fileName + "'.\n";
        }
        if (!appNameInternal.equals(appNameInternal.toLowerCase())) {
            return "The experiment appNameInternal must be lowercase: '" + appNameInternal + "'.\n";
        }
        if (!appNameInternal.equals(fileName.replaceFirst("\\.xml$", ""))) {
            return "The experiment appNameInternal must match the XML file name: '" + appNameInternal + "'.\n";
        }
        return "";
    }

    protected String validatePresenterNames(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> presenterNames = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) validationXPath.compile("/experiment/presenter/@self").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList.getLength(); index++) {
            final String presenterName = nodeList.item(index).getTextContent();
            if (presenterNames.contains(presenterName.toLowerCase())) {
                returnMessage += "Each presenter name must be unique, but '" + presenterName + "' is used on another presenter.\n";
                System.out.println(returnMessage);
            } else {
                presenterNames.add(presenterName.toLowerCase());
            }
        }
        return returnMessage;
    }

    protected String validatePresenterLinks(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> presenterNames = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/presenter/@self").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList1.getLength(); index++) {
            final String presenterName = nodeList1.item(index).getTextContent();
            presenterNames.add(presenterName);
//            System.out.println("presenterName: " + presenterName);
        }
        for (String testType : new String[]{"target", "back", "next"}) {
            NodeList nodeList2 = (NodeList) validationXPath.compile("//@" + testType).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList2.getLength(); index++) {
                final String targetName = nodeList2.item(index).getTextContent();
//                System.out.println("targetName: " + targetName);
                if (!presenterNames.contains(targetName)) {
                    returnMessage += "Each '" + testType + "' attribute must reference a valid presenter, but '" + targetName + "' is not the self name of any presenter.\n";
                    System.out.println(returnMessage);
                }
            }
        }
        return returnMessage;
    }

    protected String validateStimuliIds(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> stimuliIds = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/stimuli/stimulus/@identifier").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList1.getLength(); index++) {
            final String stimuliId = nodeList1.item(index).getTextContent();
            if (!stimuliIds.contains(stimuliId)) {
                stimuliIds.add(stimuliId);
            } else {
                returnMessage += "The stimulus identifier '" + stimuliId + "' has been used more than once. Each stimulus identifier must be unique.";
            }
        }
        return returnMessage;
    }

    protected String validateStimuliTags(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> tagNames = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/stimuli/stimulus/@tags").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList1.getLength(); index++) {
            final String tagsString = nodeList1.item(index).getTextContent();
            for (String tagString : tagsString.split(" ")) {
                if (!tagNames.contains(tagString)) {
                    tagNames.add(tagString);
//                    System.out.println("tag: " + tagString);
                }
            }
        }
        for (String testType : new String[]{"tag", "@tags"}) {
            NodeList nodeList2 = (NodeList) validationXPath.compile("/experiment/presenter//" + testType).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList2.getLength(); index++) {
                final String targetName = nodeList2.item(index).getTextContent();
                if (targetName.contains(" ") && !testType.endsWith("s")) {
                    returnMessage += "The attribute '" + testType + "' cannot contain more than one tag, but '" + targetName + "' was found.\n";
                }
                for (String targetTag : targetName.split(" ")) {
//                    System.out.println("targetTag: " + targetTag);
                    if (!tagNames.contains(targetTag)) {
                        returnMessage += "Each '" + testType + "' attribute must reference a valid stimuli tag, but '" + targetTag + "' is not specified any stimuli.\n";
                        //System.out.println(returnMessage);
                    }
                }
            }
        }
        return returnMessage;
    }

    protected String validateMetadataFieldPostNames(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> fieldNames = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/metadata/field/@postName").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList1.getLength(); index++) {
            final String fieldNamesString = nodeList1.item(index).getTextContent();
            if (!fieldNames.contains(fieldNamesString)) {
                fieldNames.add(fieldNamesString);
            } else {
                returnMessage += "The metadata field postName '" + fieldNamesString + "' has been used more than once. Each postName must be unique.";
            }
        }
        return returnMessage;
    }

    protected String validateMetadataFields(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        final ArrayList<String> fieldNames = new ArrayList<>();
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList1 = (NodeList) validationXPath.compile("/experiment/metadata/field/@postName").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList1.getLength(); index++) {
            final String fieldNamesString = nodeList1.item(index).getTextContent();
            if (!fieldNames.contains(fieldNamesString)) {
                fieldNames.add(fieldNamesString);
            }
        }
        for (String testType : new String[]{"fieldName", "linkedFieldName", "storageField", "errorField"}) {
            NodeList nodeList2 = (NodeList) validationXPath.compile("/experiment//@" + testType).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < nodeList2.getLength(); index++) {
                final String targetName = nodeList2.item(index).getTextContent();
                if (!fieldNames.contains(targetName)) {
                    returnMessage += "Each '" + testType + "' attribute must reference a valid metadata field, but '" + targetName + "' is not specified the postName attribute of any metadata field.\n";
                    //System.out.println(returnMessage);
                }
            }
        }
        return returnMessage;
    }

    protected String validateRegexStrings(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) validationXPath.compile("/experiment//@*[contains(name(), \"Regex\")]").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList.getLength(); index++) {
            // the regex strings are later stored in properties files that need to have { and } escaped with 'swhich need to be removed for this test
            final String attributeValue = nodeList.item(index).getTextContent().replaceAll("'\\{'", "{").replaceAll("'\\}'", "}");
            try {
                Pattern.compile(attributeValue);
            } catch (PatternSyntaxException pse) {
                final Node parentNode = nodeList.item(index).getParentNode();
                final String elementName = (parentNode != null) ? parentNode.getLocalName() : "";
                final String attributeName = nodeList.item(index).getNodeName();
                returnMessage += "Invalid REGEX \"" + attributeValue + "\" found in attribute " + elementName + " " + attributeName + "\n";
                //System.out.println(returnMessage);
            }
        }
        return returnMessage;
    }

    /* TODO: add flexible validation of the SCSS section of the XML to detect and prevent compilation errors
    protected String validateScssStrings(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) validationXPath.compile("/experiment//scss").evaluate(xmlDocument, XPathConstants.NODESET);
        for (int index = 0; index < nodeList.getLength(); index++) {
            final String scssValue = nodeList.item(index).getTextContent();
//                ([#.@]?[\w.:> ]+)[\s]{[\r\n]?([A-Za-z\- \r\n\t]+[:][\s]*[\w .\/()\-!]+;[\r\n]*(?:[A-Za-z\- \r\n\t]+[:][\s]*[\w .\/()\-!]+;[\r\n]*(?2)*)*)}
//                 .gwt-Button.optionButton.hiddenButton{ background-color: Transparent; } .testBorder { padding-top: 300px; opacity: 0.2 position: fixed: left: 100px; } 
            if (!scssValue.matches("([^:;\\{]+\\{[^:;\\{]+:[^:;\\{]+;[^:;\\{]+\\}[^:;\\{]*)*")) {
                returnMessage += "Invalid SCSS \"" + scssValue + "\"\n";
                //System.out.println(returnMessage);
            }
        }
        return returnMessage;
    } */

    protected String validatePresenterTypes(Document xmlDocument) throws XPathExpressionException {
        String returnMessage = "";
        XPath validationXPath = XPathFactory.newInstance().newXPath();
        for (PresenterType presenterType : PresenterType.values()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FeatureType featureType : presenterType.getExcludedFeatureTypes()) {
                stringBuilder.append("local-name() = '").append(featureType.name()).append("' or ");
            }
            stringBuilder.append("false");
            NodeList faultList = (NodeList) validationXPath.compile("/experiment/presenter[@type='" + presenterType.name() + "']/descendant::*[" + stringBuilder.toString() + "]").evaluate(xmlDocument, XPathConstants.NODESET);
            for (int index = 0; index < faultList.getLength(); index++) {
                String presenterName = "unkown";
                Node parentNode = faultList.item(index).getParentNode();
                while (parentNode != null && !parentNode.getNodeName().equals("presenter")) {
                    parentNode = parentNode.getParentNode();
                }
                if (parentNode != null && parentNode.getNodeName().equals("presenter")) {
                    final NamedNodeMap attributes = parentNode.getAttributes();
                    if (attributes != null) {
                        presenterName = attributes.getNamedItem("self").getTextContent();
                    }
                }
                final String featureName = faultList.item(index).getNodeName();
                returnMessage += "The Presenter " + presenterName + " is of the type " + presenterType.name() + " and cannot be used with " + featureName + ". ";
            }
        }
//        String commonFaults[][] = {{"menu", "loadStimulus"}};
//        for (String currentFault[] : commonFaults) {
//            NodeList faultList = (NodeList) validationXPath.compile("/experiment/presenter[@type='" + currentFault[0] + "'][descendant::" + currentFault[1] + "]/@self").evaluate(xmlDocument, XPathConstants.NODESET);
//            for (int index = 0; index < faultList.getLength(); index++) {
//                final String presenterName = faultList.item(index).getTextContent();
//                returnMessage += "The Presenter " + presenterName + " is of the type " + currentFault[0] + " and cannot be used with " + currentFault[1] + ". ";
//            }
//        }
        return returnMessage;
    }

    // todo: validate that stimulusButton etc at in a load stimulus tag
    // todo: validate token text such that formating with <stimulus.. is always in a loadStim or withStim element
    // todo: htmlTokenText in the endOfStimulus element cannot have <stimulus in the token text
    // todo: validate that stimulus label does not include unescaped &quot;
    // todo: validate that featureText has contents because it will fail if empty<htmlText featureText=""/>
}
