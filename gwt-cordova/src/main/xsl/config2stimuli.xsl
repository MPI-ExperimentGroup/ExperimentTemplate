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

import java.util.List;
import java.util.Objects;

public class Stimulus {

    public enum Tags {

        </xsl:text>
        <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/tag/text())">
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                    <xsl:text>, </xsl:text>
                </xsl:if>
        </xsl:for-each>
        <xsl:text>
    }

    public static final void fillPictureList(List&lt;String&gt; pictureList) {</xsl:text>
        <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@image)">
            <xsl:text>
        pictureList.add("</xsl:text>
            <xsl:value-of select="." />
            <xsl:text>");</xsl:text>
        </xsl:for-each>
        <xsl:text>
    }
</xsl:text>
    <xsl:for-each select="experiment/stimuli/stimulus"><xsl:text>
    private static final Stimulus </xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text> = new Stimulus("</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>", new Tags[]{</xsl:text>
        <xsl:for-each select="distinct-values(tag/text())">
            <xsl:text>Tags.</xsl:text>
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                    <xsl:text>, </xsl:text>
                </xsl:if>
        </xsl:for-each>
        <xsl:text>}, "</xsl:text>
            <xsl:value-of select="@label" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@audio" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@video" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@picture" />
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
    final private Tags tags[];
    final private String label;
    final private String audioFile;
    final private String videoFile;
    final private String picture;

    public Stimulus(String uniqueId, Tags tags[], String label, String audioFile, String videoFile, String picture) {
        this.uniqueId = uniqueId;
        this.tags = tags;
        this.label = label;
        this.audioFile = audioFile;
        this.videoFile = videoFile;
        this.picture = picture;
    }
    
    public String getUniqueId() {
        return uniqueId;
    }

    public Tags[] getTags() {
        return tags;
    }

    public String getLabel() {
        return label;
    }

    public String getOgg() {
        return audioFile.concat(".ogg");
    }

    public String getMp3() {
        return audioFile.concat(".mp3");
    }

    public String getAudioTag() {
        return audioFile;
    }

    public String getJpg() {
        return picture;
    }
            
    public String getVideoFile() {
        return videoFile;
    }

    public String getPicture() {
        return picture;
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
