<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2java.xsl
    Created on : June 17, 2015, 17:27 PM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        Converts the XML config file into concrete presenter classes.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="targetClientDirectory" select="targetClientDirectory"/>
    <xsl:template match="/">
        <xsl:text>package nl.mpi.tg.eg.experiment.client;

            import com.google.gwt.user.client.History;
            import com.google.gwt.user.client.ui.RootLayoutPanel;
            import nl.mpi.tg.eg.experiment.client.exception.AudioException;
            import nl.mpi.tg.eg.experiment.client.exception.CanvasError;
            import nl.mpi.tg.eg.experiment.client.presenter.*;
            import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;

            public class ApplicationController extends AppController {

            public enum ApplicationState {
        
            start(null),
        </xsl:text>
        <xsl:for-each select="experiment/presenter">
            <xsl:text>        </xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>("</xsl:text>
            <xsl:value-of select="@menuLabel" />
            <xsl:text>"),
            </xsl:text>
        </xsl:for-each>
        <xsl:text>        highscoresubmitted(null),
            highscoresfailednon202(null),
            highscoresfailedbuildererror(null),
            highscoresfailedconnectionerror(null),
            end(null),
            menu(null),
            playerdetails(null),
            locale(null),
            tutorial(null),
            stopSharing(null),
            tutorialorguessround(null),
            chooseplayer(null),
            guessround(null),
            metadata(null),
            registration(null),
            infoscreen(null),
            explaindatasharing(null),
            moreinfo(null),
            alien(null),
            scores(null),
            map(null),
            setuser(null),
            matchlanguage(null),
            autotyp_regions(null),
            startscreen(null),
            version(null);
        
            final public String label;

            ApplicationState(String label) {
            this.label = label;
            }
            } 

            public ApplicationController(RootLayoutPanel widgetTag) {
            super(widgetTag);
        </xsl:text>
        <!--todo: does this even work?-->
        <xsl:value-of select="if(experiment/preventWindowClose) then concat('preventWindowClose(&quot;', experiment/preventWindowClose, '&quot;);') else ''" />
        <xsl:text>        
            }
            
            @Override
            public void requestApplicationState(ApplicationState applicationState) {
            localStorage.saveAppState(applicationState.name());
            if (presenter != null) {
            presenter.savePresenterState();
            }
        </xsl:text>
        <xsl:if test="experiment/presenter/@type = 'colourPicker' or experiment/presenter/@type = 'preload' or experiment/presenter/@type = 'stimulus' or experiment/presenter/@type = 'kindiagram' or experiment/presenter/@type = 'timeline'">
            <xsl:text>try {</xsl:text>
        </xsl:if>
        <xsl:text>
            submissionService.submitScreenChange(userResults.getUserData().getUserId(), applicationState.name());
            History.newItem(applicationState.name(), false);
            // todo:
            // on each state change check if there is an completed game data, if the share is true then upload or store if offline
            // when any stored data is uploaded then delete the store 
            // on new game play erase any in memory game data regardless of its shared or not shared state
            switch (applicationState) {
            case start:
        </xsl:text>
        <xsl:for-each select="experiment/presenter">
            <xsl:text>
                case </xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>:
                this.presenter = new </xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>Presenter(widgetTag</xsl:text>
            <xsl:value-of select="
if(@type = 'transmission' or @type = 'metadata') then ', submissionService, userResults' else
if(@type = 'preload') then ', new AudioPlayer(this), submissionService, userResults' else
if(@type = 'stimulus' or @type = 'kindiagram' or @type = 'timeline' or @type = 'colourPicker') then ', new AudioPlayer(this), submissionService, userResults, localStorage' else ''" />
            <xsl:text>);
                presenter.setState(this, </xsl:text>
            <xsl:choose>
                <xsl:when test="@back">
                    <xsl:text>ApplicationState.</xsl:text>
                    <xsl:value-of select="@back" />
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>null</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text>, </xsl:text>
            <xsl:choose>
                <xsl:when test="@next">
                    <xsl:text>ApplicationState.</xsl:text>
                    <xsl:value-of select="@next" />
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>null</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text>);
                break;                                                                                                                                                  
            </xsl:text>
        </xsl:for-each>
        <xsl:text>
            case end:
            exitApplication();
            break;
            case highscoresubmitted:
            case highscoresfailedbuildererror:
            case highscoresfailedconnectionerror:
            case highscoresfailednon202:
            break;
            default:
            this.presenter = new ErrorPresenter(widgetTag, "No state for: " + applicationState);
            presenter.setState(this, ApplicationState.start, applicationState);
            break;
            }
        </xsl:text>
        <xsl:if test="experiment/presenter/@type = 'preload' or experiment/presenter/@type = 'stimulus' or experiment/presenter/@type = 'kindiagram' or experiment/presenter/@type = 'timeline'">
            <xsl:text>
                } catch (AudioException error) {
                logger.warning(error.getMessage());
                this.presenter = new ErrorPresenter(widgetTag, error.getMessage());
                presenter.setState(this, ApplicationState.start, applicationState);
                }</xsl:text>
        </xsl:if>
        <xsl:if test="experiment/presenter/@type = 'colourPicker'">
            <xsl:text>
                } catch (AudioException|CanvasError error) {
                logger.warning(error.getMessage());
                this.presenter = new ErrorPresenter(widgetTag, error.getMessage());
                presenter.setState(this, ApplicationState.start, applicationState);
                }</xsl:text>
        </xsl:if>
        <xsl:text>
            }
            }</xsl:text>

        <xsl:apply-templates select="experiment"/>
    </xsl:template>
    <xsl:template match="presenter">        
        <!--<xsl:value-of select="concat(@self, 'Presenter.java')" />-->                                                                                                                                  
        <xsl:result-document href="{$targetClientDirectory}/presenter/{@self}Presenter.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.experiment.client.presenter;
    
                import com.google.gwt.core.client.GWT;        
                import com.google.gwt.safehtml.shared.UriUtils;
                import com.google.gwt.user.client.ui.ButtonBase;
                import com.google.gwt.user.client.ui.RootLayoutPanel;
                import java.util.Arrays;
                import nl.mpi.tg.eg.experiment.client.Version;
                import nl.mpi.tg.eg.experiment.client.ApplicationController.ApplicationState;
                import nl.mpi.tg.eg.experiment.client.exception.CanvasError;
                import nl.mpi.tg.eg.experiment.client.listener.AppEventListner;
                import nl.mpi.tg.eg.experiment.client.listener.PresenterEventListner;
                import nl.mpi.tg.eg.experiment.client.listener.SingleShotEventListner;
                import nl.mpi.tg.eg.experiment.client.view.VideoPanel;
                import nl.mpi.tg.eg.experiment.client.view.AnnotationTimelinePanel;
                import nl.mpi.tg.eg.experiment.client.view.ComplexView;
                import nl.mpi.tg.eg.experiment.client.view.MenuView;     
                import nl.mpi.tg.eg.experiment.client.listener.TimedStimulusListener;  
                import nl.mpi.tg.eg.experiment.client.model.GeneratedStimulus.Tag;  
                import nl.mpi.tg.eg.experiment.client.model.UserId;    
                import nl.mpi.tg.eg.experiment.client.service.AudioPlayer;
                import nl.mpi.tg.eg.experiment.client.model.UserResults;    
                import nl.mpi.tg.eg.experiment.client.view.MetadataView; 
                import nl.mpi.tg.eg.experiment.client.service.DataSubmissionService; 
                import nl.mpi.tg.eg.experiment.client.service.LocalStorage;
                        
                // generated with config2java.xsl
                public class </xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>Presenter extends </xsl:text>
            <xsl:value-of select="if(@type = 'colourPicker') then 'AbstractColourPicker' else if(@type = 'timeline') then 'AbstractTimeline' else if(@type = 'transmission') then 'AbstractDataSubmission' else if(@type = 'menu') then 'AbstractMenu' else if(@type = 'stimulus') then 'AbstractStimulus' else if(@type = 'preload') then 'AbstractPreloadStimulus' else if(@type = 'debug') then 'LocalStorage' else if(@type = 'metadata') then 'AbstractMetadata' else if(@type = 'kindiagram') then 'AbstractKinDiagram' else 'Abstract'" />
            <xsl:text>Presenter implements Presenter {
                private final ApplicationState selfApplicationState = ApplicationState.</xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>;</xsl:text> 
            <xsl:if test="versionData">
                <xsl:text>
                    private final Version version = GWT.create(Version.class);
                </xsl:text> 
            </xsl:if>
            <xsl:text>    
                public </xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>Presenter(RootLayoutPanel widgetTag</xsl:text>
            <xsl:value-of select="
if(@type = 'transmission' or @type = 'metadata') then ', DataSubmissionService submissionService, UserResults userResults' else 
if(@type = 'preload') then ', AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults' else 
if(@type = 'stimulus' or @type = 'kindiagram' or @type = 'timeline' or @type = 'colourPicker') then ', AudioPlayer audioPlayer, DataSubmissionService submissionService, UserResults userResults, LocalStorage localStorage' else ''" />
            <xsl:value-of select="if(@type = 'colourPicker') then ') throws CanvasError {' else ') {'"/>
            <xsl:choose>
                <xsl:when test="@type = 'menu'">
                    <xsl:text>
                        super(widgetTag, new MenuView());
                    </xsl:text>
                </xsl:when>
                <xsl:when test="@type = 'text'">
                    <xsl:text>
                        super(widgetTag, new ComplexView());
                    </xsl:text>
                </xsl:when>
                <xsl:when test="@type = 'debug'">
                    <xsl:text>
                        super(widgetTag);
                    </xsl:text>
                </xsl:when>
                <xsl:when test="@type = 'preload'">
                    <xsl:text>
                        super(widgetTag, audioPlayer, submissionService, userResults);
                    </xsl:text>                    
                </xsl:when>
                <xsl:when test="@type = 'kindiagram' or @type = 'stimulus' or @type = 'timeline' or @type = 'colourPicker'">
                    <xsl:text>
                        super(widgetTag, audioPlayer, submissionService, userResults, localStorage);
                    </xsl:text>                    
                </xsl:when>
                <xsl:when test="@type = 'metadata' or @type = 'transmission'">
                    <xsl:text>
                        super(widgetTag, submissionService, userResults);
                    </xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>
                        no type attribute configured for "</xsl:text>
                    <xsl:value-of select="@type" />
                    <xsl:text>" in config2java.xsl
                    </xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text>    }

                @Override
                protected String getTitle() {
                return messages.title</xsl:text>
            <xsl:value-of select="@self" />
            <xsl:text>Presenter();
                }

                @Override
                protected void setContent(final AppEventListner appEventListner) {
            </xsl:text>
            <xsl:apply-templates/> <!--select="htmlText|padding|image|menuItem|text|versionData|optionButton|userInfo|localStorageData|stimulusImage|stimulusAudio"-->
            <xsl:text>    }
                }</xsl:text>
        </xsl:result-document>
    </xsl:template>
    <xsl:template match="text()" /><!--prevent text nodes slipping into the output-->
    <xsl:template match="htmlText">
        <xsl:text>    ((ComplexView) simpleView).addHtmlText(messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>());
        </xsl:text>
    </xsl:template>
    <xsl:template match="text">
        <xsl:text>    ((ComplexView) simpleView).addText(messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>());
        </xsl:text>
    </xsl:template>
    <xsl:template match="image">
        <!--<xsl:choose>-->
        <!--<xsl:when test="@link">-->
        <xsl:text>    ((ComplexView) simpleView).addImage(UriUtils.fromString("</xsl:text>
        <xsl:value-of select="@image" />
        <xsl:text>"), UriUtils.fromString("</xsl:text>
        <xsl:value-of select="@link" />
        <xsl:text>"), </xsl:text>
        <xsl:value-of select="@maxHeight" />
        <xsl:text>, </xsl:text>
        <xsl:value-of select="@maxWidth" />
        <xsl:text>, "</xsl:text>
        <xsl:value-of select="@align" />
        <xsl:text>");
        </xsl:text>
        <!--</xsl:when>
 <xsl:otherwise><xsl:text>    ((ComplexView) simpleView).addImage(UriUtils.fromString("</xsl:text><xsl:value-of select="@image" /><xsl:text>"), "",</xsl:text><xsl:value-of select="@width" /><xsl:text>);
 </xsl:text>
 </xsl:otherwise>
        </xsl:choose>-->
    </xsl:template>
    <xsl:template match="menuItem">
        <xsl:text>    ((MenuView) simpleView).addMenuItem(new PresenterEventListner() {

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
            appEventListner.requestApplicationState(ApplicationState.</xsl:text>
        <xsl:value-of select="@target" />
        <xsl:text>);
            }

            @Override
            public String getLabel() {
            return messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>();
            }
            }, true);
        </xsl:text>
    </xsl:template>
    <xsl:template match="popupMessage">           
        <xsl:value-of select ="local-name()"/>
        <xsl:text>(null, messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>());
        </xsl:text>
    </xsl:template>
    <xsl:template match="targetButton|actionButton">
        <xsl:text>    ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
            return messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>();
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
        </xsl:text>
        <xsl:choose>
            <xsl:when test="@target">
                <xsl:text>appEventListner.requestApplicationState(ApplicationState.</xsl:text>
                <xsl:value-of select="@target" />
                <xsl:text>);</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates/>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text>
            }
            });
        </xsl:text>
    </xsl:template>
    <xsl:template match="endOfStimulusButton">
        <xsl:value-of select ="local-name()"/>
        <xsl:text>(new PresenterEventListner() {

            @Override
            public String getLabel() {
            return messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>();
            }

            @Override
            public void eventFired(ButtonBase button, SingleShotEventListner singleShotEventListner) {
            appEventListner.requestApplicationState(ApplicationState.</xsl:text>
        <xsl:value-of select="@target" />
        <xsl:text>);
            }
            }</xsl:text>
        <xsl:value-of select="if(@eventTag) then concat(', &quot;', @eventTag, '&quot;') else ''" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="saveMetadataButton|localStorageData|allMetadataFields|eraseLocalStorageButton|showCurrentMs|enableStimulusButtons|disableStimulusButtons|showStimulus|showStimulusProgress|hideStimulusButtons|showStimulusButtons|generateCompletionCode|sendAllData|eraseLocalStorageOnWindowClosing|clearStimulus|removeStimulus|keepStimulus|stimulusLabel">
        <xsl:text>    </xsl:text>    
        <xsl:value-of select ="local-name()"/>
        <xsl:text>(</xsl:text>            
        <xsl:value-of select="if(@featureText) then concat('messages.', generate-id(.), '()') else ''" />    
        <xsl:value-of select="if(@sendData) then concat(', ', @sendData eq 'true') else ''" />    
        <xsl:apply-templates select="onError" />
        <xsl:apply-templates select="onSuccess" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="centrePage|clearPage|addPadding">
        <xsl:text>    ((ComplexView) simpleView).</xsl:text>    
        <xsl:value-of select ="local-name()"/>
        <xsl:text>();
        </xsl:text>
    </xsl:template>
    <xsl:template match="createUserButton|selectUserMenu|allMenuItems|addKinTypeGui|autoNextPresenter">    
        <xsl:text>    </xsl:text>
        <xsl:value-of select ="local-name()"/>
        <xsl:text>(appEventListner</xsl:text>
        <xsl:value-of select="if(@diagramName) then concat(', &quot;', @diagramName, '&quot;') else ''" />
        <xsl:value-of select="if(@eventTag) then concat(', &quot;', @eventTag, '&quot;') else ''" />
        <xsl:value-of select="if(@featureText) then concat(', messages.', generate-id(.), '()') else ''" />
        <xsl:value-of select="if(@target) then concat(', ApplicationState.', @target) else ''" />
        <xsl:value-of select="if(local-name() eq 'allMenuItems') then ', selfApplicationState' else ''" />        
        <xsl:value-of select="if(@norepeat) then concat(', ', @norepeat eq 'true') else ''" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="logTimeStamp|audioButton|nextStimulusButton|nextStimulus">            
        <xsl:text>    </xsl:text>    
        <xsl:value-of select ="local-name()"/>
        <xsl:text>(</xsl:text>
        <xsl:value-of select="if(@eventTag) then concat('&quot;', @eventTag, '&quot;') else ''" />
        <xsl:value-of select="if(@featureText) then concat(', messages.', generate-id(.), '()') else ''" />
        <xsl:value-of select="if(@mp3) then concat(', &quot;', @mp3, '&quot;') else ''" />
        <xsl:value-of select="if(@ogg) then concat(', &quot;', @ogg, '&quot;') else ''" />
        <xsl:value-of select="if(@poster) then concat(', &quot;', @poster, '&quot;') else ''" />        
        <xsl:value-of select="if(@norepeat) then concat(', ', @norepeat eq 'true') else ''" />
        <xsl:value-of select="if(@hotKey) then concat(', ', @hotKey) else ''" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="preloadAllStimuli|kinTypeStringDiagram|loadKinTypeStringDiagram">
        <xsl:text>    </xsl:text>
        <xsl:value-of select="local-name()" />
        <xsl:text>(appEventListner</xsl:text>
        <xsl:value-of select="if(@timeToNext) then concat(', ', @timeToNext) else ''" />
        <xsl:text>, new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
        </xsl:text>
        <xsl:apply-templates/>
        <xsl:text>
            }
            }</xsl:text>
        <xsl:value-of select="if(@kintypestring) then concat(', &quot;', @kintypestring, '&quot;') else ''" />
        <xsl:value-of select="if(@diagramName) then concat(', &quot;', @diagramName, '&quot;') else ''" />
        <xsl:value-of select="if(@imageWidth) then concat(', &quot;', @imageWidth, '&quot;') else ''" />
        <xsl:value-of select="if(@eventTag) then concat(', &quot;', @eventTag, '&quot;') else ''" />
        <xsl:apply-templates select="stimuli" mode="stimuliTags" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="onError|onSuccess|responseCorrect|responseIncorrect|hasMoreStimulus|endOfStimulus|hasTag|withoutTag">
        <xsl:value-of select="if(@timeToNext) then concat(', ', @timeToNext) else ''" />
        <xsl:text>, new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
        </xsl:text>
        <xsl:apply-templates />
        <xsl:text>
            }
            }</xsl:text>
    </xsl:template>
    <xsl:template match="showStimulusGrid">
        <xsl:text>    </xsl:text>
        <xsl:value-of select="local-name()" />
        <xsl:text>(appEventListner</xsl:text>
        <xsl:apply-templates select="responseCorrect" />
        <xsl:apply-templates select="responseIncorrect" />
        <xsl:apply-templates select="hasMoreStimulus" />
        <xsl:apply-templates select="endOfStimulus" />
        <xsl:value-of select="if(@columnCount) then concat(', ', @columnCount) else ''" />
        <xsl:value-of select="if(@imageWidth) then concat(', &quot;', @imageWidth, '&quot;') else ''" />
        <xsl:value-of select="if(@eventTag) then concat(', &quot;', @eventTag, '&quot;') else ''" />
        <xsl:value-of select="if(@alternativeChoice) then concat(', &quot;', @alternativeChoice, '&quot;') else ''" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
    <xsl:template match="pause|stimulusImage|stimulusCodeImage|stimulusAudio|stimulusPause">
        <xsl:text>    </xsl:text>
        <xsl:value-of select="local-name()" />
        <xsl:text>(</xsl:text>
        <xsl:value-of select="if(@maxHeight) then concat(@maxHeight, ', ') else ''" />
        <xsl:value-of select="if(@maxWidth) then concat(@maxWidth, ', ') else ''" />
        <xsl:value-of select="if(@timeToNext) then concat(@timeToNext, ', ') else ''" />
        <xsl:value-of select="if(@codeFormat) then concat('&quot;', @codeFormat, '&quot;, ') else ''" />
        <xsl:text>new TimedStimulusListener() {

            @Override
            public void postLoadTimerFired() {
        </xsl:text>
        <xsl:apply-templates/>
        <xsl:text>
            }
            });
        </xsl:text>
    </xsl:template>
    <xsl:template match="userInfo">
        <xsl:text>    ((ComplexView) simpleView).addHtmlText(messages.</xsl:text>
        <xsl:value-of select="generate-id(.)" />
        <xsl:text>(userNameValue, userResults.getUserData().getUserId().toString()));
        </xsl:text>
    </xsl:template>
    <xsl:template match="versionData">
        <xsl:text>    ((ComplexView) simpleView).addText("Framework For Interactive Experiments\n" + "Version: " + version.majorVersion() + "."
            + version.minorVersion() + "."
            + version.buildVersion() + "-"
            + version.projectVersion() + "\n"
            + "Compile Date: " + version.compileDate() + "\n"
            + "Last Commit Date: " + version.lastCommitDate());
        </xsl:text>
    </xsl:template>
    <xsl:template match="stimuli" mode="stimuliTags">
        <xsl:text>, Arrays.asList(new Tag[]{</xsl:text>
        <xsl:for-each select="distinct-values(tag/text())">
            <xsl:text>Tag.tag_</xsl:text>
            <xsl:value-of select="." />
            <xsl:if test="position() != last()">
                <xsl:text>, </xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:text>})</xsl:text>
    </xsl:template>
    <xsl:template match="VideoPanel|startAudioRecorder|stopAudioRecorder|tagAudioRecorder|AnnotationTimelinePanel|loadStimulus|loadSdCardStimulus|loadAllStimulus|loadSubsetStimulus|currentStimulusHasTag">
        <xsl:value-of select="if(ends-with(local-name(), 'Panel')) then '    set' else '    '" />
        <xsl:value-of select="local-name()" />
        <!--        <xsl:text>(new </xsl:text>
        <xsl:value-of select="local-name()" />-->
        <xsl:text>(</xsl:text>
        <xsl:value-of select="if(@timeToNext) then @timeToNext else ''" />
        <xsl:value-of select="if(@src) then concat('&quot;', @src, '&quot;') else ''" />        
        <xsl:value-of select="if(@wavFormat) then concat(@wavFormat eq 'true', ', ') else ''" />
        <xsl:value-of select="if(@maxHeight) then @maxHeight else ''" />
        <xsl:value-of select="if(@maxWidth) then concat(', ', @maxWidth) else ''" />
        <xsl:value-of select="if(@eventTag) then concat('&quot;', @eventTag, '&quot;') else ''" />
        <xsl:if test="@poster|@mp4|@ogg|@webm">
            <xsl:value-of select="if(@poster) then concat(', &quot;', @poster, '&quot;') else ',&quot;&quot;'" />
            <xsl:value-of select="if(@mp4) then concat(', &quot;', @mp4, '&quot;') else ',&quot;&quot;'" />
            <xsl:value-of select="if(@ogg) then concat(', &quot;', @ogg, '&quot;') else ',&quot;&quot;'" />
            <xsl:value-of select="if(@webm) then concat(', &quot;', @webm, '&quot;') else ',&quot;&quot;'" />
        </xsl:if>
        <xsl:apply-templates select="stimuli" mode="stimuliTags" />
        <xsl:value-of select="if(@condition0Tag) then concat(', Tag.tag_', @condition0Tag, '') else ''" />
        <xsl:value-of select="if(@condition1Tag) then concat(', Tag.tag_', @condition1Tag, '') else ''" />
        <xsl:value-of select="if(@condition2Tag) then concat(', Tag.tag_', @condition2Tag, '') else ''" />
        <xsl:value-of select="if(@maxStimuli) then concat(', ', @maxStimuli, '') else ''" />
        <xsl:value-of select="if(@columnCount) then concat(', ', @columnCount, '') else ''" />
        <xsl:value-of select="if(@imageWidth) then concat(', &quot;', @imageWidth, '&quot;') else ''" />
        <xsl:value-of select="if(@randomise) then concat(', ', @randomise eq 'true') else ''" />
        <xsl:apply-templates select="hasMoreStimulus" />
        <xsl:apply-templates select="endOfStimulus" />
        <xsl:apply-templates select="hasTag" />
        <xsl:apply-templates select="withoutTag" />
        <xsl:text>);
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>
