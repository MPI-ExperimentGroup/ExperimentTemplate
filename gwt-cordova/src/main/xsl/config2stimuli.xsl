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

            public class GeneratedStimulus implements Stimulus {

            private static final GeneratedStimulus[] values = new GeneratedStimulus[]{
        </xsl:text>
        <xsl:for-each select="experiment/stimuli/stimulus">
            <xsl:text>new GeneratedStimulus("</xsl:text>
            <!--<xsl:value-of select="generate-id(.)" />
            generate-id(.) caused issues with the node ID changing and pointing to the wrong file. It might be better at some point to use an explicit identifier value but for now we are using the 'code'.
            -->
            <xsl:value-of select="@baseFilePath" />
            <xsl:text>", new Tag[]{</xsl:text>
            <xsl:for-each select="distinct-values(tag/text())">
                <xsl:text>Tag.tag_</xsl:text>
                <xsl:value-of select="." />
                <xsl:if test="position() != last()">
                    <xsl:text>, </xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>}, "</xsl:text>
            <xsl:value-of select="@label" />
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@code" />
            <xsl:text>", </xsl:text>
            <xsl:value-of select="@pauseMs" />
            <xsl:text>, </xsl:text>
            <xsl:value-of select="if(@mp3) then 'true' else 'false'" />
            <xsl:text>, </xsl:text>
            <xsl:value-of select="if(@mp4) then 'true' else 'false'" />
            <xsl:text>, </xsl:text>
            <xsl:value-of select="if(@ogg) then 'true' else 'false'" />
            <xsl:text>, </xsl:text>
            <xsl:value-of select="if(@image) then 'true' else 'false'" />
            <xsl:text>)</xsl:text>
            <xsl:if test="position() != last()">
                <xsl:text>,
                </xsl:text>
            </xsl:if>
            <xsl:if test="position() = last()">
                <xsl:text>};</xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:text>
     
            public enum Tag {

        </xsl:text>
        <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/tag/text())">
            <xsl:text>tag_</xsl:text>
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                <xsl:text>, </xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:text>
            }
                   
            public static final void fillStimulusList(List&lt;Stimulus&gt; stimulusArray) {
            stimulusArray.addAll(Arrays.asList(values));</xsl:text>
        <xsl:text>
            }
            final private String uniqueId;
            final private List&lt;Tag&gt; tags;
            final private String label;
            final private String code;
            final private int pauseMs;
            final private boolean mp3;
            final private boolean mp4;
            final private boolean ogg;
            final private boolean image;

            public GeneratedStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, boolean mp3, boolean mp4, boolean ogg, boolean image) {
            this.uniqueId = uniqueId;
            this.tags = Arrays.asList(tags);
            this.label = label;
            this.code = code;
            this.pauseMs = pauseMs;
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
            
            public String getCode() {
            return code;
            }
            
            public int getPauseMs() {
            return pauseMs;
            }
            
            public String getMp3() {
            return "</xsl:text>
        <xsl:value-of select="experiment/@staticFilesUrl" />
        <xsl:text>" + uniqueId + ".mp3";
            }

            public String getImage() {
            return "</xsl:text>
        <xsl:value-of select="experiment/@staticFilesUrl" />
        <xsl:text>" + uniqueId + ".jpg";
            }

            public String getMp4() {
            return "</xsl:text>
        <xsl:value-of select="experiment/@staticFilesUrl" />
        <xsl:text>" + uniqueId + ".mp4";
            }
            
            public String getOgg() {
            return "</xsl:text>
        <xsl:value-of select="experiment/@staticFilesUrl" />
        <xsl:text>" + uniqueId + ".ogg";
            }
            
            public boolean isMp3() {
            return mp3;
            }

            public boolean isMp4() {
            return mp4;
            }

            public boolean isOgg() {
            return ogg;
            }

            public boolean isImage() {
            return image;
            }
            }  
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
