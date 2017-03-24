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
    <xsl:param name="targetClientDirectory" select="targetClientDirectory"/>
    <xsl:template match="/">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.model;
            
            import com.google.gwt.core.client.GWT;
            import java.util.Arrays;
            import java.util.List;
            import java.util.Objects;
            import nl.mpi.tg.eg.experiment.client.ServiceLocations;
            import nl.mpi.tg.eg.experiment.client.util.GeneratedStimulusProvider;

            public class GeneratedStimulus implements Stimulus {
            protected final ServiceLocations serviceLocations = GWT.create(ServiceLocations.class);
        </xsl:text>    
            
        <xsl:result-document href="{$targetClientDirectory}/model/GeneratedStimulusStrings.java" method="text">
            package nl.mpi.tg.eg.experiment.client.model;
            public class GeneratedStimulusStrings {
            <xsl:for-each select="experiment/stimuli/stimulus">
                <xsl:text>
                    public static final String label_</xsl:text>
                <xsl:value-of select="generate-id(.)" />
                <xsl:text> = "</xsl:text>
                <xsl:value-of select="@label" />
                <xsl:text>";
                    public static final String code_</xsl:text>
                <xsl:value-of select="generate-id(.)" />
                <xsl:text> = "</xsl:text>
                <xsl:value-of select="@code" />
                <xsl:text>";
                </xsl:text>
            </xsl:for-each>
            <xsl:text>}</xsl:text>
        </xsl:result-document>
        <xsl:result-document href="{$targetClientDirectory}/util/GeneratedStimulusProvider.java" method="text">
            <xsl:text>
                package nl.mpi.tg.eg.experiment.client.util;
                import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus;
                import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus.Tag;
                import static nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus.Tag.*;
                import static nl.mpi.tg.eg.experiment.client.model.GeneratedStimulusStrings.*;
                public class GeneratedStimulusProvider {
                public static final GeneratedStimulus[] values = new GeneratedStimulus[]{
            </xsl:text>
            <xsl:for-each select="experiment/stimuli/stimulus">
                <xsl:text>new GeneratedStimulus(</xsl:text>
                <!--<xsl:value-of select="generate-id(.)" />
                generate-id(.) caused issues with the node ID changing and pointing to the wrong file. It might be better at some point to use an explicit identifier value but for now we are using the 'code'.
                -->
                <xsl:value-of select="if(@identifier) then concat('&quot;', @identifier, '&quot;, ') else concat('&quot;', generate-id(.), '&quot;, ')" />
                <xsl:text>new Tag[]{</xsl:text>
                <xsl:for-each select="distinct-values(tag/text())">
                    <xsl:text>tag_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}, label_</xsl:text>
                <xsl:value-of select="generate-id(.)" />
                <!--<xsl:value-of select="@label" />-->
                <xsl:text>, code_</xsl:text>
                <xsl:value-of select="generate-id(.)" />
                <xsl:text>, </xsl:text>
                <xsl:value-of select="@pauseMs" />
                <xsl:if test="@audioPath or @videoPath or @ogg or @imagePath">
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@audioPath) then concat('&quot;', @audioPath, '&quot;') else 'null'" />
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@videoPath) then concat('&quot;', @videoPath, '&quot;') else 'null'" />
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@imagePath) then concat('&quot;', @imagePath, '&quot;') else 'null'" />
                </xsl:if>
                <xsl:value-of select="if(@ratingLabels) then concat(',&quot;', @ratingLabels, '&quot;') else ',null'" />
                <xsl:value-of select="if(@correctResponses) then concat(',&quot;', @correctResponses, '&quot;') else ',null'" />
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
                }
            </xsl:text>
        </xsl:result-document>
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
            stimulusArray.addAll(Arrays.asList(GeneratedStimulusProvider.values));</xsl:text>
        <xsl:text>
            }
            final private String uniqueId;
            final private List&lt;Tag&gt; tags;
            final private String label;
            final private String code;
            final private int pauseMs;
            final private String audioPath;
            final private String videoPath;
            final private String imagePath;
            final private String ratingLabels;
            final private String correctResponses;

            public GeneratedStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String audioPath, String videoPath, String imagePath, String ratingLabels, String correctResponses) {
            this.uniqueId = uniqueId;
            this.tags = Arrays.asList(tags);
            this.label = label;
            this.code = code;
            this.pauseMs = pauseMs;
            this.audioPath = audioPath;
            this.videoPath = videoPath;
            this.imagePath = imagePath;
            this.ratingLabels = ratingLabels;
            this.correctResponses = correctResponses;
            }
            
            public GeneratedStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs, String ratingLabels, String correctResponses) {
            this.uniqueId = (uniqueId != null) ? uniqueId : code;
            this.tags = Arrays.asList(tags);
            this.label = label;
            this.code = code;
            this.pauseMs = pauseMs;
            this.audioPath = null;
            this.videoPath = null;
            this.imagePath = null;
            this.ratingLabels = ratingLabels;
            this.correctResponses = correctResponses;
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
            
            public String getRatingLabels() {
            return ratingLabels;
            }
            
            public String getCorrectResponses() {
            return correctResponses;
            }
            
            public int getPauseMs() {
            return pauseMs;
            }
            
            public String getAudio() {
            return serviceLocations.staticFilesUrl() + audioPath;
            }

            public String getImage() {
            return serviceLocations.staticFilesUrl() + imagePath;
            }

            public String getVideo() {
            return serviceLocations.staticFilesUrl() + videoPath;
            }
            
            public boolean hasAudio() {
            return audioPath != null;
            }

            public boolean hasVideo() {
            return videoPath != null;
            }

            public boolean hasImage() {
            return imagePath != null;
            }
            
            public boolean hasRatingLabels() {
            return ratingLabels != null;
            }
            
            public boolean hasCorrectResponses() {
            return correctResponses != null;
            }
            
            @Override
            public int compareTo(Stimulus o) {
            return this.uniqueId.compareTo(o.getUniqueId());
            }
            
            @Override
            public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.uniqueId);
            return hash;
            }

            @Override
            public boolean equals(Object obj) {
            if (this == obj) {
            return true;
            }
            if (obj == null) {
            return false;
            }
            if (getClass() != obj.getClass()) {
            return false;
            }
            final Stimulus other = (Stimulus) obj;
            if (!Objects.equals(this.uniqueId, other.getUniqueId())) {
            return false;
            }
            return true;
            }
            }  
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
