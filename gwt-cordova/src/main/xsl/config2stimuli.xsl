<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2stimuli.xsl
    Created on : September 24, 2015, 5:21 PM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        Purpose of transformation is to create the stimuli class in the model package for the current experiment.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:template match="/">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Stimulus {

    public enum Tag {

        </xsl:text>
        <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/tag/text())">
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                    <xsl:text>, </xsl:text>
                </xsl:if>
        </xsl:for-each>
        <xsl:text>
    }

</xsl:text>
    <xsl:for-each select="experiment/stimuli/stimulus"><xsl:text>
    private static final Stimulus </xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text> = new Stimulus("</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>", new Tag[]{</xsl:text>
        <xsl:for-each select="distinct-values(tag/text())">
            <xsl:text>Tag.</xsl:text>
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                    <xsl:text>, </xsl:text>
                </xsl:if>
        </xsl:for-each>
        <xsl:text>}, "</xsl:text>
            <xsl:value-of select="@label" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@mp3" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@mp4" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@ogg" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@image" />
            <xsl:text>");</xsl:text>
                </xsl:for-each>
        <xsl:text>
            
    public static final void fillStimulusList(List&lt;Stimulus&gt; stimulusArray) {</xsl:text>
    <xsl:for-each select="experiment/stimuli/stimulus"><xsl:text>
        stimulusArray.add(</xsl:text>
        <xsl:value-of select="generate-id(.)" /><xsl:text>);</xsl:text>
                </xsl:for-each>
        <xsl:text>
    }
    final private String uniqueId;
    final private List&lt;Tag&gt; tags;
    final private String label;
    final private String mp3;
    final private String mp4;
    final private String ogg;
    final private String image;

    public Stimulus(String uniqueId, Tag tags[], String label, String mp3, String mp4, String ogg, String image) {
        this.uniqueId = uniqueId;
        this.tags = Arrays.asList(tags);
        this.label = label;
        this.mp3 = mp3;
        this.mp4 = mp4;
        this.ogg = ogg;
        this.image = image;
    }
    
    public String getUniqueId() {
        return uniqueId;
    }

    public List&lt;Tag&gt; getTags() {
        return tags;
    }

    public String getLabel() {
        return label;
    }

    public String getMp3() {
        return mp3;
    }

    public String getImage() {
        return image;
    }
            
    public String getMp4() {
        return mp4;
    }
    public String getOgg() {
        return ogg;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.uniqueId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stimulus other = (Stimulus) obj;
        if (!Objects.equals(this.uniqueId, other.uniqueId)) {
            return false;
        }
        return true;
    }
}  
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
