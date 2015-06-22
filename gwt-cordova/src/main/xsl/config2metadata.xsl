<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2metadata.xsl
    Created on : June 22, 2015, 11:30 AM
    Author     : petwit
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:template match="/">
        <xsl:result-document href="target/generated-sources/gwt/nl/mpi/tg/eg/experiment/client/MetadataFields.properties" method="text">
            <xsl:for-each select="experiment/metadata/field">
                   <xsl:text>postName_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@menuLabel" /><xsl:text>
</xsl:text>
                   <xsl:text>registrationField_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@registrationField" /><xsl:text>
</xsl:text>
                   <xsl:text>fieldValues_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@fieldValues" /><xsl:text>
</xsl:text>
                   <xsl:text>controlledMessage_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@controlledMessage" /><xsl:text>
</xsl:text>
                   <xsl:text>controlledRegex_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@controlledRegex" /><xsl:text>
</xsl:text>
            </xsl:for-each>
            </xsl:result-document>
    </xsl:template>
                    <!--<xsl:for-each select="*[@fieldNapostName=-->
<!--            <xsl:result-document href="target/generated-sources/gwt/nl/mpi/tg/eg/experiment/client/MetadataFields.properties" method="text">
            <xsl:text>package nl.mpi.tg.eg.experiment.client.service;

import com.google.gwt.core.client.GWT;

public class MetadataFieldProvider {

    private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
    public final MetadataField ageMetadataField = new MetadataField(mateadataFields.postName_age(), mateadataFields.registrationField_age(), mateadataFields.fieldValues_age(), null, null);
    public final MetadataField shareMetadataField = new MetadataField(mateadataFields.postName_share(), mateadataFields.registrationField_share(), mateadataFields.fieldValues_share(), mateadataFields.controlledRegex_share(), mateadataFields.controlledMessage_share());
    public final MetadataField firstNameMetadataField = new MetadataField(mateadataFields.postName_playername(), mateadataFields.registrationField_playername(), null, mateadataFields.controlledRegex_playername(), mateadataFields.controlledMessage_playername());
    public final MetadataField[] metadataFieldArray = new MetadataField[]{
        firstNameMetadataField,
        ageMetadataField,
        new MetadataField(mateadataFields.postName_language(), mateadataFields.registrationField_language(), null, null, null),
        shareMetadataField
    };
}</xsl:text>
        </xsl:result-document>
    </xsl:template>-->
</xsl:stylesheet>
