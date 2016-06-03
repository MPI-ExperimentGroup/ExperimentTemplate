<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2properties.xsl
    Created on : June 25, 2015, 3:06 PM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        Converts the textual components of the XML config file into a properties file.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:template match="/">
        <xsl:text>stimulusscreenselectbutton=Submit
            stimulusscreenQuitButton=Quit
            stimulusscreenrejectbutton=No colour
            stimulusscreenprogresstext={0} of {1}    
            appNameInternal=</xsl:text>
        <xsl:value-of select="experiment/@appNameInternal" />
        <xsl:text>
            appNameDisplay=</xsl:text>
        <xsl:value-of select="experiment/@appNameDisplay" />
        <xsl:text>
            popupOkButtonLabel=OK
            popupCancelButtonLabel=Cancel
            errorScreenTitle=An error occurred
            appNameDisplay=</xsl:text>
        <xsl:value-of select="experiment/@appNameDisplay" />
        <xsl:text>
            jsonDateFormat=yyyy-MM-dd''T''HH:mm:ss.SSSZ
        </xsl:text>
        <xsl:apply-templates/>
        <xsl:text>errorScreenText={0}
            stopSharingDetailsExplanation=({0}) and unique id ({1})
        </xsl:text>
    </xsl:template>
    <xsl:template match="text()" /><!--prevent text nodes slipping into the output-->
    <xsl:template match="experiment/presenter">
        <xsl:text>menuLabel</xsl:text>
        <xsl:value-of select="@self" />
        <xsl:text>=</xsl:text>
        <xsl:value-of select="@menuLabel" />
        <xsl:text>
        </xsl:text>      
        <xsl:text>title</xsl:text>
        <xsl:value-of select="@self" />
        <xsl:text>Presenter=</xsl:text>
        <xsl:value-of select="@title" />
        <xsl:text>
        </xsl:text>      
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="helpDialogue[@featureText != '']|eraseUsersDataButton[@featureText != '']|saveMetadataButton[@featureText != '']|saveMetadataButton[@networkErrorMessage != '']|createUserButton[@featureText != '']|targetButton[@featureText != '']|actionButton[@featureText != '']|targetFooterButton[@featureText != '']|actionFooterButton[@featureText != '']|plainText[@featureText != '']|popupMessage[@featureText != '']|menuItem[@featureText != '']|featureText[@featureText != '']|nextStimulusButton[@featureText != '']|htmlText[@featureText != '']|userInfo[@featureText != '']">
        <xsl:if test="@featureText != ''">       
            <xsl:value-of select="generate-id(.)" />
            <xsl:text>=</xsl:text>
            <xsl:value-of select="replace(@featureText,'''','''''')"/>
            <xsl:text>
            </xsl:text>
        </xsl:if>
        <xsl:if test="@networkErrorMessage != ''">   
            <xsl:text>errorMessage</xsl:text>
            <xsl:value-of select="generate-id(.)" />
            <xsl:text>=</xsl:text>
            <xsl:value-of select="replace(@networkErrorMessage,'''','''''')"/>
            <xsl:text>
            </xsl:text>
        </xsl:if>
        <xsl:if test="@closeButtonLabel != ''">   
            <xsl:text>closeButtonLabel</xsl:text>
            <xsl:value-of select="generate-id(.)" />
            <xsl:text>=</xsl:text>
            <xsl:value-of select="replace(@closeButtonLabel,'''','''''')"/>
            <xsl:text>
            </xsl:text>
        </xsl:if>
        <xsl:apply-templates/>
    </xsl:template>
</xsl:stylesheet>
