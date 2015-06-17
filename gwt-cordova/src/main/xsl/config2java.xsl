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
    <xsl:variable name="filename" select="replace(base-uri(), 'Presenter', @name)"/>
    <xsl:variable name="classname" select="@name"/>
<!--        <xsl:value-of select="@name"/>-->
      <xsl:result-document href="{$filename}" method="text">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.presenter;
            
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import nl.ru.languageininteraction.language.client.util.SvgTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;
                        
// generated with config2java.xsl
public class </xsl:text><xsl:value-of select="$classname" /><xsl:text>Presenter implements abstractPresenter {
    }
</xsl:text>  
        <xsl:text>}</xsl:text>
        </xsl:result-document>
    </xsl:template>
    </xsl:stylesheet>
