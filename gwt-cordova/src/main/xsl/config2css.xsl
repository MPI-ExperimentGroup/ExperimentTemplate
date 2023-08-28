<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2css.xsl
    Created on : October 9, 2015, 10:36 AM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        This transform creates the properties file that is used by maven to filter the CSS with the colours from the configuration file.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="outputDirectory" select="outputDirectory"/>
    <xsl:template match="/">
        <xsl:result-document href="{$outputDirectory}/_customisationsFirst.scss" method="text">
            <xsl:text>
                $primaryColour0: </xsl:text>
            <xsl:value-of select="if(experiment/@primaryColour0) then experiment/@primaryColour0 else '#628D8D'" />
            <xsl:text>;
                $primaryColour1: </xsl:text>
            <xsl:value-of select="if(experiment/@primaryColour1) then experiment/@primaryColour1 else '#385E5E'" />
            <xsl:text>;
                $primaryColour2: </xsl:text>
            <xsl:value-of select="if(experiment/@primaryColour2) then experiment/@primaryColour2 else '#4A7777'" />
            <xsl:text>;
                $primaryColour3: </xsl:text>
            <xsl:value-of select="if(experiment/@primaryColour3) then experiment/@primaryColour3 else '#96ADAD'" />
            <xsl:text>;
                $primaryColour4: </xsl:text>
            <xsl:value-of select="if(experiment/@primaryColour4) then experiment/@primaryColour4 else '#D5D8D8'" />
            <xsl:text>;
                $complementColour0: </xsl:text>
            <xsl:value-of select="if(experiment/@complementColour0) then experiment/@complementColour0 else '#EAC3A3'" />
            <xsl:text>;
                $complementColour1: </xsl:text>
            <xsl:value-of select="if(experiment/@complementColour1) then experiment/@complementColour1 else '#9D7B5E'" />
            <xsl:text>;
                $complementColour2: </xsl:text>
            <xsl:value-of select="if(experiment/@complementColour2) then experiment/@complementColour2 else '#C69E7C'" />
            <xsl:text>;
                $complementColour3: </xsl:text>
            <xsl:value-of select="if(experiment/@complementColour3) then experiment/@complementColour3 else '#FFEDDE'" />
            <xsl:text>;
                $complementColour4: </xsl:text>
            <xsl:value-of select="if(experiment/@complementColour4) then experiment/@complementColour4 else '#FFFDFB'" />
            <xsl:text>;
                $backgroundColour: </xsl:text>
            <xsl:value-of select="if(experiment/@backgroundColour) then experiment/@backgroundColour else '#FFFFFF'" />
            <xsl:text>;
                $textFontSize: </xsl:text>
            <xsl:value-of select="if(experiment/@textFontSize) then experiment/@textFontSize else '17'" />
            <xsl:text>pt;
                $gridCellZoom: </xsl:text>
            <xsl:value-of select="if(experiment/@defaultScale) then experiment/@defaultScale else '1.0'" />
            <xsl:text>;
            </xsl:text>
        </xsl:result-document>
        <xsl:result-document href="{$outputDirectory}/_customisationsLast.scss" method="text">
            <xsl:value-of select="experiment/scss" />
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
