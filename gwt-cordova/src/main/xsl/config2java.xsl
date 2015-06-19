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
    <xsl:template match="/">
        <xsl:text>package nl.mpi.tg.eg.experiment.client;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.mpi.tg.eg.experiment.client.presenter.*;
import nl.ru.languageininteraction.language.client.AppController;
import nl.ru.languageininteraction.language.client.exception.AudioException;
import nl.ru.languageininteraction.language.client.presenter.ErrorPresenter;

public class ApplicationController extends AppController {

    public enum ApplicationState {
        
        start(messages.startButton()),
</xsl:text>
        <xsl:for-each select="experiment/presenter">
            <xsl:text>        </xsl:text><xsl:value-of select="@self" /><xsl:text>("</xsl:text><xsl:value-of select="@menuLabel" /><xsl:text>"),
</xsl:text>
        </xsl:for-each>
        <xsl:text>        highscoresubmitted(""),
        highscoresfailednon202(""),
        highscoresfailedbuildererror(""),
        highscoresfailedconnectionerror(""),
        end(messages.exitButton());

        final public String label;

        ApplicationState(String label) {
            this.label = label;
        }
    } 

    public ApplicationController(RootLayoutPanel widgetTag) {
        super(widgetTag);
    }
            
    public void requestApplicationState(ApplicationState applicationState) {
try {
            trackView(applicationState.name());
            History.newItem(applicationState.name(), false);
            // todo:
            // on each state change check if there is an completed game data, if the share is true then upload or store if offline
            // when any stored data is uploaded then delete the store 
            // on new game play erase any in memory game data regardless of its shared or not shared state
            switch (applicationState) {
        </xsl:text>
        <xsl:for-each select="experiment/presenter">
            <xsl:text>
                case </xsl:text><xsl:value-of select="@self" /><xsl:text>:
                    this.presenter = new </xsl:text><xsl:value-of select="@self" /><xsl:text>Presenter(widgetTag);
                    presenter.setState(this, ApplicationState.</xsl:text><xsl:value-of select="@back" /><xsl:text>, ApplicationState.</xsl:text><xsl:value-of select="@next" /><xsl:text>);
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
        } catch (AudioException error) {
            logger.warning(error.getMessage());
            this.presenter = new ErrorPresenter(widgetTag, error.getMessage());
            presenter.setState(this, ApplicationState.start, applicationState);
        }
    }
}</xsl:text>
        <xsl:apply-templates select="experiment"/>
    </xsl:template>
    <xsl:template match="presenter">        
           <!--<xsl:value-of select="concat(@self, 'Presenter.java')" />-->                                                                                                                                  
      <xsl:result-document href="target/generated-sources/gwt/nl/mpi/tg/eg/experiment/client/presenter/{@self}Presenter.java" method="text">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.presenter;
    
import com.google.gwt.user.client.ui.Button;        
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.Version;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.language.client.presenter.Presenter;
import nl.ru.languageininteraction.language.client.view.ComplexView;
import nl.ru.languageininteraction.language.client.view.MenuView;
                        
// generated with config2java.xsl
public class </xsl:text><xsl:value-of select="@self" /><xsl:text>Presenter extends AbstractPresenter implements Presenter {
</xsl:text> 
<xsl:if test="versionData">
    <xsl:text>
    private final Version version = GWT.create(Version.class);
</xsl:text> 
</xsl:if>
<xsl:text>    
    public </xsl:text><xsl:value-of select="@self" /><xsl:text>Presenter(RootLayoutPanel widgetTag) {
</xsl:text>  
<xsl:choose>
  <xsl:when test="@type = 'menu'"><xsl:text>
        super(widgetTag, new MenuView());
</xsl:text>
    </xsl:when>
    <xsl:when  test="@type = 'text'"><xsl:text>
        super(widgetTag, new ComplexView());
</xsl:text>
    </xsl:when>
  <xsl:otherwise>
      no type attribute
  </xsl:otherwise>
</xsl:choose>
<xsl:text>    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.</xsl:text><xsl:value-of select="//title//@field" /><xsl:text>(), titleBarListner);
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
</xsl:text><xsl:apply-templates select="htmlText|padding|image|menuItem|text|versionData|optionButton"/><xsl:text>    }
}</xsl:text>
        </xsl:result-document>
    </xsl:template>
<xsl:template match="htmlText">
<xsl:text>    ((ComplexView) simpleView).addHtmlText(messages.</xsl:text><xsl:value-of select="@field" /><xsl:text>());
</xsl:text>
    </xsl:template>
<xsl:template match="text">
<xsl:text>    ((ComplexView) simpleView).addText(messages.</xsl:text><xsl:value-of select="@field" /><xsl:text>());
</xsl:text>
    </xsl:template>
<xsl:template match="image">
    <!--<xsl:choose>-->
        <!--<xsl:when test="@link">-->
<xsl:text>    ((ComplexView) simpleView).addImage(UriUtils.fromString("</xsl:text><xsl:value-of select="@image" /><xsl:text>"), messages.</xsl:text><xsl:value-of select="@link" /><xsl:text>(), </xsl:text><xsl:value-of select="@width" /><xsl:text>);
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
                    public void eventFired(Button button) {
                        appEventListner.requestApplicationState(AppEventListner.ApplicationState.</xsl:text><xsl:value-of select="@target" /><xsl:text>);
                    }

                    @Override
                    public String getLabel() {
                        return messages.</xsl:text><xsl:value-of select="@label" /><xsl:text>();
                    }
                }, true);
</xsl:text>
    </xsl:template>
<xsl:template match="optionButton">
<xsl:text>    ((ComplexView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return messages.</xsl:text><xsl:value-of select="@label" /><xsl:text>();
            }

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(AppEventListner.ApplicationState.</xsl:text><xsl:value-of select="@target" /><xsl:text>);
            }
        });
</xsl:text>
    </xsl:template>
<xsl:template match="padding">
<xsl:text>    ((ComplexView) simpleView).addPadding();
</xsl:text>
    </xsl:template>
<xsl:template match="versionData">
<xsl:text>    ((ComplexView) simpleView).addText("Version: " + version.majorVersion() + "."
                + version.minorVersion() + "."
                + version.buildVersion() + "-"
                + version.projectVersion() + "\n"
                + "Compile Date: " + version.compileDate() + "\n"
                + "Last Commit Date: " + version.lastCommitDate());
</xsl:text>
    </xsl:template>
</xsl:stylesheet>
