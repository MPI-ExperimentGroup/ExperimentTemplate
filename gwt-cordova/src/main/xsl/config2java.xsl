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
        <xsl:apply-templates select="experiment"/>
    </xsl:template>
    <xsl:template match="presenter">        
           <xsl:value-of select="concat(@name, 'Presenter.java')" />                                                                                                                                  
      <xsl:result-document href="target/generated-sources/gwt/nl/mpi/tg/eg/experiment/client/presenter/{@name}Presenter.java" method="text">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.presenter;
            
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.language.client.presenter.Presenter;
import nl.ru.languageininteraction.language.client.view.ComplexView;
import nl.ru.languageininteraction.language.client.view.MenuView;
                        
// generated with config2java.xsl
public class </xsl:text><xsl:value-of select="@name" /><xsl:text>Presenter extends AbstractPresenter implements Presenter {
    
    public </xsl:text><xsl:value-of select="@name" /><xsl:text>Presenter(RootLayoutPanel widgetTag) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}</xsl:text>
        </xsl:result-document>
    </xsl:template>
    </xsl:stylesheet>
