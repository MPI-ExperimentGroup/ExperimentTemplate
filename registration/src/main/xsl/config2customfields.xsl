<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : config2customfelds.xsl
    Created on : September 23, 2015, 10:15 AM
    Author     : Peter Withers <peter.withers@mpi.nl>
    Description:
        Converts the metadata segment of the configuration file into a class that can be stored when matching data is received from the remote app.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="targetClientDirectory" select="targetClientDirectory"/>
    <xsl:param name="targetTemplateDirectory" select="targetTemplateDirectory"/>
    <xsl:template match="/">
        <xsl:result-document href="{$targetClientDirectory}/model/Participant.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.frinex.model;

                import java.io.Serializable;
                import java.util.Date;
                import javax.persistence.Entity;
                import javax.persistence.GeneratedValue;
                import javax.persistence.GenerationType;
                import javax.persistence.Id;
                import javax.persistence.Temporal;

                @Entity                     
                public class Participant implements Serializable {

                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                private long id;

                @Temporal(javax.persistence.TemporalType.TIMESTAMP)
                private Date submitDate;
                private String userId;
                private String remoteAddr;
                private String acceptLang;
                private String userAgent;
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>
                    private String </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField;</xsl:text>
            </xsl:for-each>
            <xsl:text>
    
                public long getId() {
                return id;
                }

                public String getUserId() {
                return userId;
                }
                
                public Date getSubmitDate() {
                return submitDate;
                }

                public void setSubmitDate(Date submitDate) {
                this.submitDate = submitDate;
                }

                public String getRemoteAddr() {
                return remoteAddr;
                }
                
                public void setRemoteAddr(String remoteAddr) {
                this.remoteAddr = remoteAddr;
                }

                public String getAcceptLang() {
                return acceptLang;
                }

                public void setAcceptLang(String acceptLang) {
                this.acceptLang = acceptLang;
                }

                public String getUserAgent() {
                return userAgent;
                }

                public void setUserAgent(String userAgent) {
                this.userAgent = userAgent;
                }
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>
                    public String get</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField() {
                    return </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField;
                    }
                    public void set</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField(String </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField) {
                    this.</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField = </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField;
                    }
                </xsl:text>
            </xsl:for-each>
            <xsl:text>              
                }    </xsl:text>
        </xsl:result-document>
        <xsl:result-document href="{$targetClientDirectory}/util/ParticipantCsvExporter.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.frinex.util;
                
                import java.io.IOException;
                import nl.mpi.tg.eg.frinex.model.Participant;
                import org.apache.commons.csv.CSVPrinter;
                
                public class ParticipantCsvExporter {
                public void appendCsvHeader(CSVPrinter printer) throws IOException {
                printer.printRecord(</xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>"</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>"</xsl:text>
                <xsl:if test="position() != last()">
                    <xsl:text>,</xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>);
                }
                public void appendCsvRow(CSVPrinter printer, Participant participant) throws IOException {
                printer.printRecord(</xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>participant.get</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField()</xsl:text>
                <xsl:if test="position() != last()">
                    <xsl:text>,</xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>);
                }
                }    </xsl:text>
        </xsl:result-document>
        <xsl:result-document href="{$targetTemplateDirectory}/participanttable.html" method="text">
            <xsl:text>&lt;!DOCTYPE html&gt;
                &lt;html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"&gt;
                    &lt;head&gt;&lt;title&gt;&lt;/title&gt;&lt;/head&gt;
    &lt;body&gt;
        &lt;table&gt;
                    &lt;tr th:fragment="participantheader"&gt;
                    &lt;th&gt;&lt;a th:attr="href='?sort=id'"&gt;ID&lt;/a&gt;&lt;/th&gt;
                    &lt;th&gt;&lt;a th:attr="href='?sort=userId'"&gt;UUID&lt;/a&gt;&lt;/th&gt;
                    &lt;th&gt;&lt;a th:attr="href='?sort=submitDate'"&gt;submitDate&lt;/a&gt;&lt;/th&gt;
            </xsl:text>
            <!--&amp;${(sortOrder='a')? 'd' : 'a'}-->
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>&lt;th&gt;&lt;a th:attr="href='?sort=</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField'"&gt;</xsl:text>
                <xsl:value-of select="@registrationField" />
                <xsl:text>&lt;/a&gt;&lt;/th&gt;</xsl:text>
            </xsl:for-each>
            <xsl:text>    
                &lt;/tr&gt;
                    &lt;tr th:fragment="participantrows"&gt;
                    &lt;td th:text="${participant.id}"&gt;id&lt;/td&gt;
                    &lt;td th:text="${participant.userId}"&gt;userId&lt;/td&gt;
                    &lt;td th:text="${participant.submitDate}"&gt;submitDate&lt;/td&gt;
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>
                    &lt;td  th:text="${participant.</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField}"&gt;&lt;/td&gt;</xsl:text>
            </xsl:for-each>
            <xsl:text>    
                &lt;/tr&gt;
                &lt;tr th:fragment="participantinputfields"&gt; 
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>&lt;td&gt;&lt;input id="</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>CustomField" /&gt;&lt;/td&gt;</xsl:text>
            </xsl:for-each>
            <xsl:text>    
                &lt;td&gt;&lt;button type="submit" name="add"&gt;Add Participant&lt;/button&gt;&lt;/td&gt;&lt;/tr&gt;
                &lt;/table&gt;
            &lt;/body&gt;
        &lt;/html&gt;
            </xsl:text>
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
