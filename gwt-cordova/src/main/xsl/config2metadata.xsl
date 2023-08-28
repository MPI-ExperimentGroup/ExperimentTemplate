<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2metadata.xsl
    Created on : June 22, 2015, 11:30 AM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        This transformation generates the MetadataFieldProvider class from the experiment configuration file.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="targetClientDirectory" select="targetClientDirectory"/>
    <xsl:template match="/">
        <xsl:result-document href="{$targetClientDirectory}/MetadataFields.properties" method="text">
            <xsl:text>placeholder_to_make_sure_that_this_file_gets_created=true&#xa;</xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>postName_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>registrationField_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="replace(@registrationField,'''','''''')" />
                <xsl:text>&#xa;</xsl:text>
                <!--<xsl:if test="@fieldValues">-->
                <xsl:text>fieldValues_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="@fieldValues" />
                <xsl:text>&#xa;</xsl:text>
                <!--</xsl:if>-->
                <!--<xsl:if test="@controlledMessage">-->
                <xsl:text>controlledMessage_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="@controlledMessage" />
                <xsl:text>&#xa;</xsl:text>
                <!--</xsl:if>-->
                <!--<xsl:if test="@controlledRegex">-->
                <xsl:text>controlledRegex_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="@controlledRegex" />
                <xsl:text>&#xa;</xsl:text>
                <!--                   <xsl:text>preventServerDuplicates_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@preventServerDuplicates" /><xsl:text>
                </xsl:text>
                                   <xsl:text>duplicatesControlledMessage_</xsl:text><xsl:value-of select="@postName" /><xsl:text>=</xsl:text><xsl:value-of select="@duplicatesControlledMessage" /><xsl:text>
                </xsl:text>-->
                <!--</xsl:if>-->
            </xsl:for-each>
            <!-- <xsl:for-each select="distinct-values(//@storageField[not(.=experiment/metadata/field/@postName)])">
                take the list of nodes from the storageField attribute that do not exist in the experiment/metadata/field list and add the result as metadata fields
                <xsl:text>postName_</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>=</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each> -->
        </xsl:result-document>
        <!--make separate properties files for each locale-->
        <xsl:variable name="translationNodes" select="experiment/metadata/field/translation" />
        <xsl:for-each select="distinct-values($translationNodes/@locale)">
            <xsl:variable name="translationLocale" select="." />
            <xsl:result-document href="{$targetClientDirectory}/MetadataFields_{$translationLocale}.properties" method="text" encoding="UTF-8">
                <xsl:for-each select="$translationNodes[@locale eq $translationLocale]">
                    <xsl:if test="@controlledMessage">
                        <xsl:text>controlledMessage_</xsl:text>
                        <xsl:value-of select="../@postName" />
                        <xsl:text>=</xsl:text>
                        <xsl:value-of select="replace(@controlledMessage,'''','''''')"/>
                        <xsl:text>&#xa;</xsl:text>
                    </xsl:if>
                    <xsl:if test="@registrationField">
                        <xsl:text>registrationField_</xsl:text>
                        <xsl:value-of select="../@postName" />
                        <xsl:text>=</xsl:text>
                        <xsl:value-of select="replace(@registrationField,'''','''''')"/>
                        <xsl:text>&#xa;</xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </xsl:result-document>
        </xsl:for-each>   
        <xsl:result-document href="{$targetClientDirectory}/model/ExperimentMetadataFieldProvider.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.experiment.client.model;

                import com.google.gwt.core.client.GWT;
                import nl.mpi.tg.eg.experiment.client.MetadataFields;
                
                public class ExperimentMetadataFieldProvider implements MetadataFieldProvider {

                private final MetadataFields mateadataFields = GWT.create(MetadataFields.class);
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>
                    public final MetadataField </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>MetadataField = new MetadataField(mateadataFields.postName_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>(), mateadataFields.registrationField_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>(), mateadataFields.fieldValues_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>(), mateadataFields.controlledRegex_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>(), mateadataFields.controlledMessage_</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>());</xsl:text>
            </xsl:for-each>
            <!-- <xsl:for-each select="distinct-values(//@storageField[not(.=experiment/metadata/field/@postName)])">
                take the list of nodes from the storageField attribute that do not exist in the experiment/metadata/field list and add the result as metadata fields
                but we don't do this because it is problematic in the admin pages
                <xsl:text>
                    public final MetadataField </xsl:text>
                <xsl:value-of select="." />
                <xsl:text>MetadataField = new MetadataField(mateadataFields.postName_</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>(), mateadataFields.postName_</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>(), "", "", "");</xsl:text>
            </xsl:for-each> -->
            <xsl:text>
                public final MetadataField[] getMetadataFieldArray(){
                return new MetadataField[]{
            </xsl:text>
            <xsl:value-of select="experiment/metadata/field/@postName" separator="MetadataField, " />
            <xsl:if test="experiment/metadata/field">
                <xsl:text>MetadataField</xsl:text>
            </xsl:if>
            <xsl:text>
                };
                }
                
                public final String getDataAgreementFieldName() {
                return </xsl:text>
            <xsl:value-of select="if(experiment/administration/dataAgreementField/@fieldName) then concat('&quot;', experiment/administration/dataAgreementField/@fieldName, '&quot;') else 'null'" />
            <xsl:text>;
                }
                
                public final String getDataAgreementMatch() {
                return </xsl:text>
            <xsl:value-of select="if(experiment/administration/dataAgreementField/@matchingRegex) then concat('&quot;', experiment/administration/dataAgreementField/@matchingRegex, '&quot;') else 'null'" />
            <xsl:text>;
                }
                }</xsl:text>
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
