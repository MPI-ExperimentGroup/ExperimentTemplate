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
    <xsl:param name="targetJavaDirectory" select="targetJavaDirectory" />
    <xsl:param name="targetTemplateDirectory" select="targetTemplateDirectory" />
    <xsl:param name="separateClassNames" select="separateClassNames" />
    <xsl:param name="outputPrefix" select="outputPrefix" />
    <xsl:param name="usingMockXml" select="usingMockXml" />
    <xsl:param name="targetTestDirectory" select="targetTestDirectory" />
    <xsl:template match="/">
        <xsl:if test="$usingMockXml eq 'false'">
            <xsl:result-document href="{$targetJavaDirectory}/{$outputPrefix}AdminUserList.java" method="text">
                <xsl:text>package nl.mpi.tg.eg.frinex;
                    public class </xsl:text>
                <xsl:value-of select="$outputPrefix" />
                <xsl:text>AdminUserList {
                    public String[][] getAdminUserList(){
                    return new String[][] {                
                </xsl:text>
                <xsl:for-each select="experiment/administration/adminUser">
                    <xsl:text>{"</xsl:text>
                    <xsl:value-of select="@name" />
                    <xsl:text>","</xsl:text>
                    <xsl:value-of select="@password" />
                    <xsl:text>"}</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>
                    };
                    }
                    }    </xsl:text>
            </xsl:result-document>
        </xsl:if>
        <xsl:result-document href="{$targetJavaDirectory}/model/Participant.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.frinex.model;

                import java.io.Serializable;
                import java.util.Date;
                import javax.persistence.Column;
                import javax.persistence.Entity;
                import javax.persistence.GeneratedValue;
                import javax.persistence.GenerationType;
                import javax.persistence.Id;
                import javax.persistence.Temporal;

                @Entity                     
                public class <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant implements Serializable, Comparable&lt;<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant&gt; {

                public <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant(){}
                public <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant(final String userId){this.userId = userId;}

                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                private long id;

                @Temporal(javax.persistence.TemporalType.TIMESTAMP)
                private Date submitDate;
                private String userId;
                private String remoteAddr;
                private String acceptLang;
                private String userAgent;
                private Boolean staleCopy = false;
            </xsl:text>
            <xsl:for-each select="experiment/metadata/field">
                <xsl:text>
                    @Column(length = 1024)
                    private String </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>;
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
            
                @Override
                public int compareTo(<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant o) {
                return (this.userId != null) ? this.userId.compareTo(o.getUserId()) : 1;
                }
            
                public long getId() {
                return id;
                }

                public boolean getStaleCopy() {
                return (staleCopy == null) ? false : staleCopy;
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
                <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                <xsl:text>() {
                    return </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>;
                    }
                    public void set</xsl:text>
                <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                <xsl:text>(String </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>) {
                    this.</xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text> = </xsl:text>
                <xsl:value-of select="@postName" />
                <xsl:text>;
                    }
                </xsl:text>
            </xsl:for-each>
            <xsl:text>              
                }    </xsl:text>
        </xsl:result-document>
        <xsl:if test="$usingMockXml eq 'false'">
            <xsl:result-document href="{$targetJavaDirectory}/rest/{$outputPrefix}ParticipantColumnsRepository.java" method="text">
                <xsl:text>package nl.mpi.tg.eg.frinex.rest;
            
                    import java.util.List;
                    import javax.persistence.QueryHint;
                    import nl.mpi.tg.eg.frinex.model.<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant;
                    import org.springframework.data.jpa.repository.Query;
                    import org.springframework.data.jpa.repository.QueryHints;
                    import org.springframework.data.repository.NoRepositoryBean;
                    import org.springframework.data.repository.query.Param;

                    @NoRepositoryBean
                    public interface <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->ParticipantColumnsRepository {
                </xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                <!--xsl:if test="not(contains(@postName, '_'))"-->
                    <xsl:text>
                        <!-- the use of the field name from the XML into the query here is not without risk, so it is important that any characters not matching 0-9a-zA-Z_ are removed from the postName -->
                        @Query("select p from Participant p where staleCopy = :staleCopy and </xsl:text><xsl:value-of select="replace(@postName,'[^0-9a-zA-Z_]+','')" /><xsl:text> = :</xsl:text><xsl:value-of select="replace(@postName,'[^0-9a-zA-Z_]+','')" /><xsl:text>")
                        public List&lt;<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant&gt; findByStaleCopyAnd</xsl:text>
                        <xsl:for-each select="tokenize(@postName,'_')">
                            <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))" />
                        </xsl:for-each>
                    <!--xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" /--><!-- replace(@postName,'_','__') -->
                    <xsl:text>(@Param("staleCopy") boolean staleCopy, @Param("</xsl:text>
                    <xsl:value-of select="@postName" />
                    <xsl:text>") String </xsl:text>
                    <xsl:value-of select="@postName" />
                    <xsl:text>);
                    </xsl:text>
                <!--/xsl:if-->
                    <xsl:text>
                        @QueryHints({@QueryHint(name="org.hibernate.cacheable", value="true")})
                        @Query("select count(p.id) from Participant p where staleCopy = :staleCopy and </xsl:text><xsl:value-of select="replace(@postName,'[^0-9a-zA-Z_]+','')" /><xsl:text> like :matchingLike")
                        <!-- @Query("select count p from Participant p where staleCopy = :staleCopy and </xsl:text><xsl:value-of select="replace(@postName,'[^0-9a-zA-Z_]+','')" /><xsl:text> regexp :matchingRegex") -->
                        public int countByStaleCopyAnd</xsl:text>
                        <xsl:for-each select="tokenize(@postName,'_')">
                            <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))" />
                        </xsl:for-each>
                    <xsl:text>Like(@Param("staleCopy") boolean staleCopy, @Param("matchingLike") String matchingLike);
                    <!-- <xsl:text>Regex(@Param("staleCopy") boolean staleCopy, @Param("matchingRegex") String matchingRegex); -->
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                    }
                </xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetTestDirectory}/rest/MockParticipantColumnsRepository.java" method="text">
                <xsl:text>package nl.mpi.tg.eg.frinex.rest;

                    import java.util.List;
                    import nl.mpi.tg.eg.frinex.model.<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant;

                    public abstract class MockParticipantColumnsRepository implements ParticipantRepository {
                </xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                <!--xsl:if test="not(contains(@postName, '_'))"-->
                    <xsl:text>
                        @Override                    
                        public List&lt;<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant&gt; findByStaleCopyAnd</xsl:text>
                        <xsl:for-each select="tokenize(@postName,'_')">
                            <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))" />
                        </xsl:for-each>
                    <!--xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" /--> <!-- replace(@postName,'_','__') -->
                    <xsl:text>(boolean staleCopy, String </xsl:text>
                    <xsl:value-of select="@postName" /> <!-- replace(@postName,'_','__') -->
                    <xsl:text>) {
                        throw new UnsupportedOperationException("This is a test only class.");
                        }
                    </xsl:text>
                    <xsl:text>
                        @Override
                        public int countByStaleCopyAnd</xsl:text>
                        <xsl:for-each select="tokenize(@postName,'_')">
                            <xsl:value-of select="concat(upper-case(substring(.,1,1)), substring(., 2))" />
                        </xsl:for-each>
                    <xsl:text>Like(boolean staleCopy, String matchingLike) {
                    <!-- <xsl:text>Regex(boolean staleCopy, String matchingRegex) { -->
                        throw new UnsupportedOperationException("This is a test only class.");
                        }
                    </xsl:text>
                <!--/xsl:if-->
                </xsl:for-each>
                <xsl:text>
                    }
                </xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetJavaDirectory}/model/{$outputPrefix}ReportTypes.java" method="text">
                <xsl:text>package nl.mpi.tg.eg.frinex.model;

                    public class </xsl:text>
                <xsl:value-of select="$outputPrefix" />
                <xsl:text>ReportTypes {
            
                    enum CsvReportTypes {
                </xsl:text>
                <xsl:for-each select="experiment//sendStimuliReport">
                    <xsl:value-of select="generate-id(.)" />
                    <xsl:text>("</xsl:text>
                    <xsl:value-of select="@type" />
                    <xsl:text>","</xsl:text>
                    <xsl:value-of select="@headerKey" />
                    <xsl:text>","</xsl:text>
                    <xsl:value-of select="@separator" />
                    <xsl:text>")
                    </xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                    <xsl:if test="position() = last()">
                        <xsl:text>;
                        </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:if test="experiment//sendStimuliReport">
                    <xsl:text>
                        public final String type;
                        public final String separator;
                        public final String headerKey;

                        private CsvReportTypes(final String type, final String separator, final String headerKey) {
                        this.type = type;
                        this.separator = separator;
                        this.headerKey = headerKey;
                        }
                        static public CsvReportTypes getCsvReportType(final String eventTag) {
                        for (CsvReportTypes crt : values()) {
                        if (eventTag.equals(crt.type)) {
                        return crt;
                        }
                        }
                        return null;
                        }

                        public boolean isHeader(final String eventTag, final String tagValue1) {
                        return eventTag.equals(type) &amp;&amp; tagValue1.equals(headerKey);
                        }

                        public boolean isRow(final String eventTag, final String tagValue1) {
                        return eventTag.equals(type) &amp;&amp; !tagValue1.equals(headerKey);
                        }
                    </xsl:text>
                </xsl:if>
                <xsl:text>
                    }
                    enum ScreenReports {
                </xsl:text>
                <xsl:for-each select="experiment/presenter">
                    <xsl:value-of select="@self" />
                    <xsl:text>,
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                    }
                    enum LoadStimulusReports {
                </xsl:text>
                <xsl:for-each select="experiment/presenter[loadStimulus]">
                    <xsl:value-of select="@self" />
                    <xsl:text>,
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                    }
                    public enum DataChannels {
                </xsl:text>
                <!--Convert the administration datachannels segment of the configuration file into a data structure consumable by the administration web interface.-->
                <xsl:for-each select="experiment/administration/dataChannel">
                    <xsl:value-of select="generate-id(.)" />
                    <xsl:text>(</xsl:text>
                    <xsl:value-of select="@channel" />
                    <xsl:text>,"</xsl:text>
                    <xsl:value-of select="@label" />
                    <xsl:text>",</xsl:text>
                    <xsl:value-of select="if(@logToSdCard) then @logToSdCard else 'false'" />
                    <xsl:text>)</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,
                        </xsl:text>
                    </xsl:if>
                    <xsl:if test="position() = last()">
                        <xsl:text></xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>;
                    public final int channel; public final String label; public final boolean logToSdCard;
                    DataChannels(int channel, String label, boolean logToSdCard){
                    this.channel=channel;
                    this.label = label;
                    this.logToSdCard = logToSdCard;
                    }

                    }
                    }
                </xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetJavaDirectory}/util/{$outputPrefix}ParticipantCsvExporter.java" method="text">
                <xsl:text>package nl.mpi.tg.eg.frinex.util;
            
                    import java.io.IOException;
                    import java.text.SimpleDateFormat;
                    import java.util.List;
                    import nl.mpi.tg.eg.frinex.model.<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant;
                    import nl.mpi.tg.eg.frinex.model.TagData;
                    import org.apache.commons.csv.CSVPrinter;
            
                    public class </xsl:text>
                <xsl:value-of select="$outputPrefix" />
                <xsl:text>ParticipantCsvExporter {
                </xsl:text>
                <!--            <xsl:if test="count(experiment/stimuli/stimulus) gt 200">
                    <xsl:text>
                        public void appendAggregateCsvHeader(CSVPrinter printer) throws IOException {
                        printer.printRecord("Too many stimuli items (</xsl:text>
                    <xsl:value-of select="count(experiment/stimuli/stimulus)"/>
                    <xsl:text>)for this CSV export type to be produced. Please refer to the other CSV files provided.");
                        }
                        public void appendAggregateCsvRow(CSVPrinter printer, Participant participant, List&lt;TagData&gt; participantTagData) {
                        }</xsl:text>
                </xsl:if>-->
                <!--<xsl:if test="count(experiment/stimuli/stimulus) le 200">-->
                <!--<xsl:value-of select="count(experiment/stimuli/stimulus)"/>-->
                <xsl:text>
                    public void appendAggregateCsvHeader(CSVPrinter printer) throws IOException {
                    printer.printRecord("UserId"</xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>,"</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                    <xsl:text>"</xsl:text>
                </xsl:for-each>
                <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@id)">
                    <xsl:text>,"</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>","</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>_datetime",</xsl:text>
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>_ms"</xsl:text>
                    <!--                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>-->
                </xsl:for-each>
                <xsl:text>);
                    }
                    public void appendAggregateCsvRow(CSVPrinter printer, <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant participant, List&lt;TagData&gt; participantTagData) throws IOException, CsvExportException {
                    SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss");
                </xsl:text>
                <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@id)">
                    <xsl:text>String stimulus_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> = "";&#xa;</xsl:text>
                    <xsl:text>String datetime_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> = "";&#xa;</xsl:text>
                    <xsl:text>String ms_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> = "";&#xa;</xsl:text>
                </xsl:for-each>
                <xsl:text>
                    TagData startData=null;
                    for (TagData currentData : participantTagData) {
                    if ("NextStimulus".equals(currentData.getEventTag())) {
                    <!--                if(startData!=null){
                    ms_</xsl:text>
                <xsl:value-of select="@code" />
                <xsl:text> += "no end event ";
                    throw new CsvExportException("no end tag for for: " + startData.getEventTag() + " " + startData.getTagValue() + " " + startData.getUserId() + " " + startData.getTagDate());
                    }-->
                    startData=currentData;
                    switch(startData.getTagValue()){
                </xsl:text>
                <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@id)">
                    <xsl:text>case "</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>":
                        datetime_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> += format.format(startData.getTagDate()) + " ";
                        break;
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                    default:
                    throw new CsvExportException("no case for: " + startData.getEventTag() + " " + startData.getTagValue() + " " + startData.getUserId());
                    }}
                    if ("RatingButton".equals(currentData.getEventTag()) || "volgende [ spatiebalk ]".equals(currentData.getTagValue())) {
                    TagData endData=currentData;
                    String msString = (startData==null)?"no start event ":Integer.toString(endData.getEventMs()-startData.getEventMs());   
                    if(startData!=null) //throw new CsvExportException("no start for: " + endData.getEventTag() + " " + endData.getTagValue() + " " + endData.getUserId() + " " + endData.getTagDate());
                    switch(startData.getTagValue()){
                </xsl:text>
                <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@id)">
                    <xsl:text>case "</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>":
                        stimulus_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> += endData.getTagValue() + " ";
                        ms_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text> += msString + " ";
                        break;
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                    default:
                    throw new CsvExportException("no case for: " + endData.getEventTag() + " " + endData.getTagValue() + " " + endData.getUserId());
                    }
                    startData=null;
                    }                
                    }
                    printer.printRecord(participant.getUserId()</xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>,&#xa;participant.get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                    <xsl:text>()</xsl:text>
                </xsl:for-each>
                <xsl:for-each select="distinct-values(experiment/stimuli/stimulus/@id)">
                    <xsl:text>,&#xa;stimulus_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>, datetime_</xsl:text>
                    <xsl:value-of select="." />
                    <xsl:text>, ms_</xsl:text>
                    <xsl:value-of select="." />
                    <!--                    <xsl:if test="position() != last()">
                        <xsl:text>,&#xa;</xsl:text>
                    </xsl:if>-->
                </xsl:for-each>
                <xsl:text>);
                    }
                </xsl:text>
                <!--</xsl:if>-->
                <xsl:text>
                    public void appendCsvHeader(CSVPrinter printer) throws IOException {
                    printer.printRecord("UserId"</xsl:text>
                    <xsl:if test="experiment/metadata/field">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                    <xsl:text>"</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>);
                    }
                    public void appendCsvRow(CSVPrinter printer, <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant participant) throws IOException {
                    printer.printRecord(participant.getUserId()</xsl:text>
                    <xsl:if test="experiment/metadata/field">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>participant.get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@postName,1,1)), substring(@postName, 2))" />
                    <xsl:text>()</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>);
                    }
                    }
                </xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetTemplateDirectory}/{$outputPrefix}charts.html" method="text">
                <xsl:text>&lt;div th:fragment="charts"&gt;</xsl:text>
                <xsl:if test="experiment/administration/chart">
                    <xsl:text>&lt;script src="webjars/chartjs/dist/chart.min.js"&gt;&lt;/script&gt;</xsl:text>
                    <xsl:text>&lt;script src="js/ExperimentCharts.js"&gt;&lt;/script&gt;</xsl:text>
                </xsl:if>
                    <xsl:for-each select="experiment/administration/chart">
                        <xsl:text>&lt;span class="chart-outer"&gt;</xsl:text>
                        <xsl:text>&lt;div class="chart-container chart-</xsl:text>
                        <xsl:value-of select="@type" />
                        <xsl:text>"&gt;</xsl:text>
                        <xsl:text>&lt;canvas id="</xsl:text>
                        <xsl:value-of select="generate-id(.)" />
                        <xsl:text>"&gt;&lt;/canvas&gt;</xsl:text>
                        <xsl:text>&lt;/div&gt;&lt;/span&gt;</xsl:text>
                        <xsl:text>
                            &lt;script&gt;</xsl:text>
                        <xsl:text>
                            <!-- $(document).on('loaded', -->
                            generateChart({canvas:"</xsl:text>
                        <xsl:value-of select="generate-id(.)" />
                        <xsl:text>", label: "</xsl:text>
                        <xsl:value-of select="@label" />
                        <xsl:text>", type: "</xsl:text>
                        <xsl:value-of select="@type" />
                        <xsl:text>", metadata: [</xsl:text>
                        <xsl:for-each select="metadata">
                            <xsl:text>
                                {label: "</xsl:text>
                            <xsl:value-of select="@label" />
                            <xsl:text>", fieldname: "</xsl:text>
                            <xsl:value-of select="concat(upper-case(substring(@fieldName,1,1)), substring(@fieldName, 2))" />
                            <xsl:text>", matching: "</xsl:text>
                            <xsl:value-of select="@matching" />
                            <xsl:text>", colour: "</xsl:text>
                            <xsl:value-of select="@colour" />
                            <xsl:text>"}</xsl:text>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:for-each>
                        <xsl:text>], stimulusResponse: [</xsl:text>
                        <xsl:for-each select="stimulusResponse">
                            <xsl:text>
                                {label: "</xsl:text>
                            <xsl:value-of select="@label" />
                            <!-- <xsl:text>", columnName: "</xsl:text>
                            <xsl:value-of select="concat(upper-case(substring(@columnName,1,1)), substring(@columnName, 2))" />
                            <xsl:text>", matching: "</xsl:text>
                            <xsl:value-of select="@matching" /> -->
                            <xsl:text>", screenName: "</xsl:text>
                            <xsl:value-of select="if (@screenName) then @screenName else '%'" />
                            <xsl:text>", eventTag: "</xsl:text>
                            <xsl:value-of select="if (@eventTag) then @eventTag else '%'" />
                            <xsl:text>", tagValue1: "</xsl:text>
                            <xsl:value-of select="if (@tagValue1) then @tagValue1 else '%'" />
                            <xsl:text>", tagValue2: "</xsl:text>
                            <xsl:value-of select="if (@tagValue2) then @tagValue2 else '%'" />
                            <xsl:text>", screenName: "</xsl:text>
                            <xsl:value-of select="if (@screenName) then @screenName else '%'" />
                            <xsl:text>", dataChannel: "</xsl:text>
                            <xsl:value-of select="if (@dataChannel) then @dataChannel else '%'" />
                            <xsl:text>", responseGroup: "</xsl:text>
                            <xsl:value-of select="if (@responseGroup) then @responseGroup else '%'" />
                            <xsl:text>", scoreGroup: "</xsl:text>
                            <xsl:value-of select="if (@scoreGroup) then @scoreGroup else '%'" />
                            <xsl:text>", stimulusId: "</xsl:text>
                            <xsl:value-of select="if (@stimulusId) then @stimulusId else '%'" />
                            <xsl:text>", response: "</xsl:text>
                            <xsl:value-of select="if (@response) then @response else '%'" />
                            <xsl:value-of select="if (@isCorrect) then concat('&quot;, isCorrect: &quot;', @isCorrect) else ''" />
                            <xsl:text>", colour: "</xsl:text>
                            <xsl:value-of select="@colour" />
                            <xsl:text>"}</xsl:text>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:for-each>
                        <xsl:text>]});
                            &lt;/script&gt;
                        </xsl:text>
                    </xsl:for-each>
                <xsl:text>&lt;/div&gt;</xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetTemplateDirectory}/{$outputPrefix}tables.html" method="text">
                <xsl:text>&lt;div th:fragment="tables"&gt;</xsl:text>
                    <xsl:for-each select="experiment/administration/dataTable">
                        <xsl:text>&lt;span class="table-outer"&gt;</xsl:text>
                        <xsl:text>&lt;div class="table-container" id="</xsl:text>
                        <xsl:value-of select="generate-id(.)" />
                        <xsl:text>"&gt;&lt;/div&gt;&lt;/span&gt;</xsl:text>
                        <xsl:text>
                            &lt;script&gt;</xsl:text>
                        <xsl:text>
                            <!-- $(document).on('loaded', -->
                            generateTable({divId:"</xsl:text>
                        <xsl:value-of select="generate-id(.)" />
                        <xsl:text>", label: "</xsl:text>
                        <xsl:value-of select="if (@label) then @label else '%'" />
                        <xsl:text>", source: "</xsl:text>
                        <xsl:value-of select="if (@source) then @source else '%'" />
                        <xsl:text>", columnNames: "</xsl:text>
                        <xsl:value-of select="if (@columnNames) then @columnNames else '%'" />
                        <xsl:text>", screenName: "</xsl:text>
                        <xsl:value-of select="if (@screenName) then @screenName else '%'" />
                        <xsl:text>", eventTag: "</xsl:text>
                        <xsl:value-of select="if (@eventTag) then @eventTag else '%'" />
                        <xsl:text>", tagValue1: "</xsl:text>
                        <xsl:value-of select="if (@tagValue1) then @tagValue1 else '%'" />
                        <xsl:text>", tagValue2: "</xsl:text>
                        <xsl:value-of select="if (@tagValue2) then @tagValue2 else '%'" />
                        <xsl:text>", screenName: "</xsl:text>
                        <xsl:value-of select="if (@screenName) then @screenName else '%'" />
                        <xsl:text>", dataChannel: "</xsl:text>
                        <xsl:value-of select="if (@dataChannel) then @dataChannel else '%'" />
                        <xsl:text>", responseGroup: "</xsl:text>
                        <xsl:value-of select="if (@responseGroup) then @responseGroup else '%'" />
                        <xsl:text>", scoreGroup: "</xsl:text>
                        <xsl:value-of select="if (@scoreGroup) then @scoreGroup else '%'" />
                        <xsl:text>", stimulusId: "</xsl:text>
                        <xsl:value-of select="if (@stimulusId) then @stimulusId else '%'" />
                        <xsl:text>", response: "</xsl:text>
                        <xsl:value-of select="if (@response) then @response else '%'" />
                        <xsl:value-of select="if (@isCorrect) then concat('&quot;, isCorrect: &quot;', @isCorrect) else ''" />
                        <xsl:text>"});
                            &lt;/script&gt;
                        </xsl:text>
                    </xsl:for-each>
                <xsl:text>&lt;/div&gt;</xsl:text>
            </xsl:result-document>
            <xsl:result-document href="{$targetTemplateDirectory}/{$outputPrefix}participanttable.html" method="text">
                <xsl:text>
                &lt;!DOCTYPE html&gt;
                &lt;html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"&gt;
                    &lt;head&gt;&lt;title&gt;&lt;/title&gt;&lt;/head&gt;
    &lt;body&gt;
        &lt;table&gt;
                    &lt;tr th:fragment="participantheader"&gt;
                    &lt;th th:if="${!simpleMode}"&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=id'"&gt;ID&lt;/a&gt;&lt;/th&gt;
                    &lt;th th:if="${!simpleMode}"&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=staleCopy'"&gt;Stale&lt;/a&gt;&lt;/th&gt;
                    &lt;th th:if="${!simpleMode}"&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=userId'"&gt;Participant ID&lt;/a&gt;&lt;/th&gt;
                    &lt;th th:if="${!simpleMode}"&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=userAgent'"&gt;User Agent&lt;/a&gt;&lt;/th&gt;
                    &lt;th th:if="${!simpleMode}"&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=acceptLang'"&gt;Browser Language&lt;/a&gt;&lt;/th&gt;
                    <!--&lt;th&gt;&lt;a th:attr="href='?sort=remoteAddr'"&gt;remoteAddr&lt;/a&gt;&lt;/th&gt;-->
                </xsl:text>
                <!--&amp;${(sortOrder='a')? 'd' : 'a'}-->
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>&lt;th&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=</xsl:text>
                    <xsl:value-of select="replace(@postName,'_','\\_')" />
                    <xsl:text>'"&gt;</xsl:text>
                    <xsl:value-of select="@registrationField" />
                    <xsl:text>&lt;/a&gt;&lt;/th&gt;
                    </xsl:text>
                </xsl:for-each>
                <xsl:text>
                &lt;th&gt;&lt;a th:attr="href='?simple=' + ${simpleMode} + ${(paramId != null)? '&amp;id='+paramId : ''} + '&amp;amp;sort=submitDate'"&gt;Date&lt;/a&gt;&lt;/th&gt;
                &lt;/tr&gt;
                    &lt;tr th:fragment="participantrows"&gt;
                    &lt;td th:if="${!simpleMode}" th:text="${participant.id}"&gt;id&lt;/td&gt;
                    &lt;td th:if="${!simpleMode}" th:text="${participant.staleCopy}"&gt;staleCopy&lt;/td&gt;
                    &lt;td th:if="${!simpleMode}" th:text="${participant.userId}"&gt;userId&lt;/td&gt;
                    &lt;td th:if="${!simpleMode}" th:text="${participant.userAgent}"&gt;userAgent&lt;/td&gt;
                    &lt;td th:if="${!simpleMode}" th:text="${participant.acceptLang}"&gt;acceptLang&lt;/td&gt;
                    <!--&lt;td th:text="${participant.remoteAddr}"&gt;remoteAddr&lt;/td&gt;-->
                </xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>
                    &lt;td th:text="${participant.</xsl:text>
                    <xsl:value-of select="replace(@postName,'__','\\_\\_')" />
                    <xsl:text>}"&gt;&lt;/td&gt;</xsl:text>
                </xsl:for-each>
                <xsl:text>    
                &lt;td th:text="${participant.submitDate}"&gt;submitDate&lt;/td&gt;
                &lt;/tr&gt;
                &lt;tr th:fragment="participantinputfields(buttonLabel)"&gt;
                &lt;td th:if="${!simpleMode}"&gt;&lt;/td&gt;
                &lt;td th:if="${!simpleMode}"&gt;&lt;/td&gt;
                &lt;td th:if="${!simpleMode}"&gt;&lt;input id="userIdInput" th:value="${insertUserData.userId}" /&gt;&lt;/td&gt;
                &lt;td th:if="${!simpleMode}"&gt;&lt;/td&gt;
                &lt;td th:if="${!simpleMode}"&gt;&lt;/td&gt;
                </xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>&lt;td&gt;&lt;input id="</xsl:text>
                    <xsl:value-of select="@postName" />
                    <xsl:text>Input" th:value="${insertUserData.</xsl:text>
                    <xsl:value-of select="replace(@postName,'__','\\_\\_')" />
                    <xsl:text>}" /&gt;&lt;/td&gt;</xsl:text>
                </xsl:for-each>
                <xsl:text>    
                &lt;td&gt;
                &lt;input th:if="${simpleMode}" id="userIdInput" th:value="${insertUserData.userId}" type="hidden" /&gt;
                &lt;button id="addParticipantButton" class="tableButton" th:text="${buttonLabel}" &gt;Add Record&lt;/button&gt;&lt;/td&gt;&lt;/tr&gt;
                &lt;/table&gt;
            &lt;/body&gt;
        &lt;/html&gt;
                </xsl:text>
                <xsl:text>    
                    &lt;script th:fragment="addparticipantscript"&gt;
                    $(document).ready(function () {
                    $("#addParticipantButton").on('click', function () {
                    $.ajax({
                    url: 'metadata',
                    type: "POST",
                    dataType : "json",
                    contentType: "application/json; charset=utf-8",
                    data: "[{\"userId\": \"" + $("#userIdInput").val() + "\",</xsl:text>
                <xsl:for-each select="experiment/metadata/field">
                    <xsl:text>\"</xsl:text>
                    <xsl:value-of select="@postName" />
                    <xsl:text>\": \"" + $("#</xsl:text>
                    <xsl:value-of select="@postName" />
                    <xsl:text>Input").val() + "\"</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}]",
                    success: function (result) {
                    location.reload();
                    },
                    error: function (xhr, resp, text) {
                    console.log(xhr, resp, text);
                    }
                    })
                    });
                    });

                    &lt;/script&gt;
                </xsl:text>
            </xsl:result-document>
        </xsl:if>
        <xsl:variable name="filename" select="(tokenize(base-uri(), '/'))[last()]" />
        <xsl:variable name="classname" select="if ($separateClassNames ne 'true') then concat($outputPrefix, 'Participant') else substring-before($filename, '.xml')" />
        <xsl:variable name="targetOutputDirectory" select="if ($usingMockXml ne 'true') then $targetJavaDirectory else $targetTestDirectory" />
        <xsl:result-document href="{$targetOutputDirectory}/rest/{$classname}ValidationController.java" method="text">
            <xsl:text>package nl.mpi.tg.eg.frinex.rest;

                import java.io.IOException;
                import java.util.ArrayList;
                import java.util.Date;
                import nl.mpi.tg.eg.frinex.model.StimulusResponse;
                import java.util.List;
                import javax.servlet.http.HttpServletRequest;
                import nl.mpi.tg.eg.frinex.model.<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant;
                import nl.mpi.tg.eg.frinex.model.TagData;
                import org.springframework.beans.factory.annotation.Autowired;
                import org.springframework.http.HttpStatus;
                import org.springframework.http.MediaType;
                import org.springframework.http.ResponseEntity;
                import org.springframework.web.bind.annotation.RequestHeader;
                import org.springframework.web.bind.annotation.RequestMapping;
                import org.springframework.web.bind.annotation.RequestMethod;
                import org.springframework.web.bind.annotation.RequestParam;
                import org.springframework.web.bind.annotation.ResponseBody;
                import org.springframework.web.bind.annotation.RestController;

                @RestController                  
                public class </xsl:text>
            <xsl:value-of select="$classname" />
            <xsl:text>ValidationController {

            </xsl:text>
            <xsl:if test="$separateClassNames eq 'true'">
                <xsl:text>
                    public </xsl:text>
                <xsl:value-of select="$classname" />
                <xsl:text>ValidationController(ScreenDataRepository screenDataRepository, TimeStampRepository timeStampRepository, <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->ParticipantRepository participantRepository, TagRepository tagRepository, StimulusResponseRepository stimulusResponseRepository) {
                    this.screenDataRepository = screenDataRepository;
                    this.timeStampRepository = timeStampRepository;
                    this.participantRepository = participantRepository;
                    this.tagRepository = tagRepository;
                    this.stimulusResponseRepository = stimulusResponseRepository;
                    }
                </xsl:text>
            </xsl:if>
            <xsl:text>
                @Autowired
                ScreenDataRepository screenDataRepository;
                @Autowired
                TimeStampRepository timeStampRepository;
                @Autowired
                <!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->ParticipantRepository participantRepository;
                @Autowired
                TagRepository tagRepository;
                @Autowired
                StimulusResponseRepository stimulusResponseRepository;

                @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                @ResponseBody
                public ResponseEntity&lt;String&gt; validate(
                @RequestParam("requestingUserId") String requestingUserId,
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/*[@postField]">
                <xsl:text>@RequestParam(value = "</xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>", required = false, defaultValue = "") String </xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>,</xsl:text>
            </xsl:for-each>
            <xsl:text>
                @RequestParam("applicationversion") String applicationversion,
                @RequestParam(value = "datalog", required = false) String datalog,
                @RequestHeader("Accept-Language") String acceptLang,
                @RequestHeader("User-Agent") String userAgent,
                HttpServletRequest request) throws IOException {
                final Date tagDate = new java.util.Date();
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/*[@postField]">
                <xsl:text>tagRepository.save(new TagData(requestingUserId, "validate", "</xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>", (</xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>.length() > 254) ? </xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>.substring(0, 254) : </xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>, 0, tagDate));
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                final String remoteAddr = (request == null)? "unknown" : request.getRemoteAddr();
                <!--final int lastIndexOfIPv4 = remoteAddr.lastIndexOf(".");-->
                <!--final int lastIndexOfIPv6 = remoteAddr.length() / 2;-->
                final int lastIndexOf = remoteAddr.lastIndexOf(".");
                if (lastIndexOf > 0) {
                tagRepository.save(new TagData(requestingUserId, "validate", "remoteAddr", remoteAddr.substring(0, lastIndexOf) + ".0", 0, tagDate));
                }
                tagRepository.save(new TagData(requestingUserId, "validate", "acceptLang", acceptLang, 0, tagDate));
                tagRepository.save(new TagData(requestingUserId, "validate", "userAgent", userAgent, 0, tagDate));
                tagRepository.save(new TagData(requestingUserId, "validate", "applicationversion", (applicationversion.length() > 254) ? applicationversion.substring(0, 254) : applicationversion, 0, tagDate));
                if (datalog!=null &amp;&amp; !datalog.isEmpty()) {tagRepository.save(new TagData(requestingUserId, "validate", "datalog", (datalog.length() > 254) ? datalog.substring(0, 254) : datalog, 0, tagDate));}
                final List&lt;<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant&gt; foundRecords = new ArrayList();
                boolean foundError = false;
            </xsl:text>
            <xsl:for-each select="distinct-values((experiment/validationService/validation/@errorField, experiment/validationService/validation/*/@errorField))">
                <xsl:text>
                    String errorMessage</xsl:text>
                <xsl:value-of select="." />
                <xsl:text> = "";
                </xsl:text>
            </xsl:for-each>
            <xsl:text>final boolean allowValidationOnMissing = </xsl:text>
            <xsl:value-of select="if (experiment/validationService/validation/@allowValidationOnMissing eq 'true') then 'true' else 'false'" />
            <xsl:text>;
                for(<!--/xsl:text><xsl:value-of select="$outputPrefix" /><xsl:text-->Participant participantRecord : participantRepository.findByUserId(requestingUserId)) {
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/recordMatch">
                <xsl:if test="@postField">
                    <xsl:text>if (!</xsl:text>
                    <xsl:value-of select="@postField" />
                    <xsl:text>.equals(participantRecord.get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@adminField,1,1)), substring(@adminField, 2))" />
                    <xsl:text>())) {
                        if(!allowValidationOnMissing &amp;&amp; foundRecords.isEmpty()){
                        foundError = true;
                        errorMessage</xsl:text>
                    <xsl:value-of select="@errorField" />
                    <!--only one initial error message should be shown, so this clears any previous messages-->
                    <xsl:text> = "</xsl:text>
                    <xsl:value-of select="@errorMessage" />
                    <xsl:text> ";
                        }
                        } else </xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text> {
                foundRecords.add(participantRecord);
                foundError = false;
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/recordMatch">
                <xsl:text>errorMessage</xsl:text>
                <xsl:value-of select="@errorField" />
                <!--only one initial error message should be shown, so this clears any previous messages-->
                <xsl:text> = "";</xsl:text>
            </xsl:for-each>
            <xsl:text>
                }
                }
            </xsl:text>
            <xsl:text>
                if(!allowValidationOnMissing &amp;&amp; !foundError &amp;&amp; foundRecords.isEmpty()){
                foundError = true;
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation">
                <xsl:text>errorMessage</xsl:text>
                <xsl:value-of select="@errorField" />
                <!--only one initial error message should be shown, so this clears any previous messages-->
                <xsl:text> = "</xsl:text>
                <xsl:value-of select="@errorMessage" />
                <xsl:text> ";
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                }
            </xsl:text>
            <xsl:text>
                while (foundRecords.size() > 1) {
                if (foundRecords.get(0).getSubmitDate().before(foundRecords.get(1).getSubmitDate())) {
                foundRecords.remove(0);
                } else {
                foundRecords.remove(1);
                }
                }
                final StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{\n");
                if(foundRecords.size()&lt;1 &amp;&amp; !foundError){
                stringBuilder.append("\"metadata\": {\n");</xsl:text>
            <xsl:for-each select="experiment/validationService/validation/recordMatch[@responseField]">
                <xsl:text>stringBuilder.append("\"</xsl:text>
                <xsl:value-of select="@responseField" />
                <xsl:text>\":\"");
                    stringBuilder.append(</xsl:text>
                <xsl:value-of select="@postField" />
                <xsl:text>);
                    <!-- TODO: investigate if all @responseField should return the requestingUserId as its return value? -->
                    stringBuilder.append("\",\n");
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                if (','==(stringBuilder.charAt(stringBuilder.length() - 2))) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 2);
                }
                stringBuilder.append("},\n");
                stringBuilder.append("\"information\": \"validated user data not found so no existing fields can be returned but this validation is allowed due to allowValidationOnMissing\",\n");
                } else if (!foundError) {
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/*[@adminField]">
                <!--match the value to the regex-->
                <xsl:if test="@validationRegex">
                    <xsl:text>if (null == foundRecords.get(0).get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@adminField,1,1)), substring(@adminField, 2))" />
                    <xsl:text>() || !foundRecords.get(0).get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@adminField,1,1)), substring(@adminField, 2))" />
                    <xsl:text>().matches("</xsl:text>
                    <xsl:value-of select="@validationRegex" />
                    <xsl:text>")){
                        errorMessage</xsl:text>
                    <xsl:value-of select="@errorField" />
                    <xsl:text>+="</xsl:text>
                    <xsl:value-of select="@errorMessage" />
                    <xsl:text> ";
                        foundError = true;
                        stringBuilder.append("\"information\": \"validated user data found but the field </xsl:text>
                    <xsl:value-of select="@adminField" />
                    <xsl:text> does not match the validation regex\",\n");
                        }
                    </xsl:text>
                </xsl:if>
                <!--compare the provided value to the DB counterpart-->
                <xsl:if test="@postField and @adminField">
                    <xsl:text>if (!</xsl:text>
                    <xsl:value-of select="@postField" />
                    <xsl:text>.equals(foundRecords.get(0).get</xsl:text>
                    <xsl:value-of select="concat(upper-case(substring(@adminField,1,1)), substring(@adminField, 2))" />
                    <xsl:text>())){
                        errorMessage</xsl:text>
                    <xsl:value-of select="@errorField" />
                    <xsl:text>+="</xsl:text>
                    <xsl:value-of select="@errorMessage" />
                    <xsl:text> "; 
                        foundError = true;
                        stringBuilder.append("\"information\": \"validated user data found but the field </xsl:text>
                    <xsl:value-of select="@adminField" />
                    <xsl:text> does not match the latest validated record in the admin system\",\n");
                        }
                    </xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>stringBuilder.append("\"metadata\": {\n");
                if (!foundError) {
            </xsl:text>
            <xsl:for-each select="experiment/validationService/validation/*[@responseField]">
                <xsl:text>stringBuilder.append("\"</xsl:text>
                <xsl:value-of select="@responseField" />
                <xsl:text>\":\"");
                </xsl:text>
                <xsl:text>stringBuilder.append(foundRecords.get(0).get</xsl:text>
                <xsl:value-of select="concat(upper-case(substring(@adminField,1,1)), substring(@adminField, 2))" />
                <xsl:text>());
                    stringBuilder.append("\",\n");
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                }
            </xsl:text>
            <xsl:for-each select="distinct-values((experiment/validationService/validation/@errorField, experiment/validationService/validation/*/@errorField))">
                <xsl:text>
                    stringBuilder.append("\"</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>\":\"");
                    stringBuilder.append(errorMessage</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>);
                    stringBuilder.append("\",\n");
                    if(!errorMessage</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>.isEmpty()){
                    tagRepository.save(new TagData(requestingUserId, "validate", "</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>", errorMessage</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>, 0, tagDate));
                    }
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                if (','==(stringBuilder.charAt(stringBuilder.length() - 2))) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 2);
                }
                stringBuilder.append("},\n");
                if (!foundError) {
                stringBuilder.append("\"information\": \"validated user data found so existing fields are enclosed in this response\",\n");
                for (StimulusResponse stimulusResponse : stimulusResponseRepository.findTop1ByUserIdOrderByTotalPotentialScoreDesc(requestingUserId)) {
                stringBuilder.append("\"scoredata\": {\n");
                stringBuilder.append("\"gamesPlayed\": \"").append(stimulusResponse.getGamesPlayed()).append("\",\n");
                stringBuilder.append("\"totalScore\": \"").append(stimulusResponse.getTotalScore()).append("\",\n");
                stringBuilder.append("\"totalPotentialScore\": \"").append(stimulusResponse.getTotalPotentialScore()).append("\",\n");
                stringBuilder.append("\"currentScore\": \"").append(stimulusResponse.getCurrentScore()).append("\",\n");
                stringBuilder.append("\"correctStreak\": \"").append(stimulusResponse.getCorrectStreak()).append("\",\n");
                stringBuilder.append("\"errorStreak\": \"").append(stimulusResponse.getErrorStreak()).append("\",\n");
                stringBuilder.append("\"potentialScore\": \"").append(stimulusResponse.getPotentialScore()).append("\",\n");
                stringBuilder.append("\"maxScore\": \"").append(stimulusResponse.getMaxScore()).append("\",\n");
                stringBuilder.append("\"maxErrors\": \"").append(stimulusResponse.getMaxErrors()).append("\",\n");
                stringBuilder.append("\"maxCorrectStreak\": \"").append(stimulusResponse.getMaxCorrectStreak()).append("\",\n");
                stringBuilder.append("\"maxErrorStreak\": \"").append(stimulusResponse.getMaxErrorStreak()).append("\",\n");
                stringBuilder.append("\"maxPotentialScore\": \"").append(stimulusResponse.getMaxPotentialScore()).append("\"\n");
                stringBuilder.append("},\n");
                }
                }
                if (','==(stringBuilder.charAt(stringBuilder.length() - 2))) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 2);
                }
                } else if (foundError){</xsl:text>
            <xsl:for-each select="distinct-values((experiment/validationService/validation/@errorField))">
                <xsl:text>
                    stringBuilder.append("\"</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>\":\"");
                    stringBuilder.append(errorMessage</xsl:text>
                <xsl:value-of select="." />
                <xsl:text>);
                    stringBuilder.append("\",\n");
                </xsl:text>
            </xsl:for-each>
            <xsl:text>
                }
                if (','==(stringBuilder.charAt(stringBuilder.length() - 2))) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 2);
                }
                stringBuilder.append("}");
                return new ResponseEntity(stringBuilder.toString(), HttpStatus.OK);
                }            
                }
            </xsl:text>
        </xsl:result-document>
    </xsl:template>
</xsl:stylesheet>
