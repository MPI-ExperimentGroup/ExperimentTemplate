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
                <xsl:if test="@mp3 or @mp4 or @ogg or @image">
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@mp3) then 'true' else 'false'" />
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@mp4) then 'true' else 'false'" />
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@ogg) then 'true' else 'false'" />
                    <xsl:text>, </xsl:text>
                    <xsl:value-of select="if(@image) then 'true' else 'false'" />
                </xsl:if>
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
            
            public GeneratedStimulus(String uniqueId, Tag tags[], String label, String code, int pauseMs) {
            this.uniqueId = (uniqueId != null) ? uniqueId : code;
            this.tags = Arrays.asList(tags);
            this.label = label;
            this.code = code;
            this.pauseMs = pauseMs;
            this.mp3 = false;
            this.mp4 = false;
            this.ogg = false;
            this.image = false;
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
            return serviceLocations.staticFilesUrl() + "</xsl:text>
        <xsl:text>" + uniqueId + ".mp3";
            }

            public String getImage() {
            return serviceLocations.staticFilesUrl() + "</xsl:text>
        <xsl:text>" + uniqueId + ".jpg";
            }

            public String getMp4() {
            return serviceLocations.staticFilesUrl() + "</xsl:text>
        <xsl:text>" + uniqueId + ".mp4";
            }
            
            public String getOgg() {
            return serviceLocations.staticFilesUrl() + "</xsl:text>
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
