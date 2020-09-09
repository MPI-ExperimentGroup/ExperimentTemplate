<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : map2properties.xsl
    Created on : December 10, 2014, 12:27 PM
    Author     : Peter Withers <p.withers@psych.ru.nl>
    Description:
        Converts the SVG map from inkscape into a properties file with all the regions from the input SVG.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:svg="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape"
                version="2.0">
    <xsl:output method="text" encoding="UTF-8" />
    <xsl:param name="configurationName" select="configurationName"/>
    <xsl:variable name="filename" select="(tokenize(base-uri(), '/'))[last()]"/>
    <xsl:variable name="classpath" select="substring-before(substring-after(base-uri(), $configurationName), concat('/', $filename))"/>
    <!--<xsl:variable name="classpath" select="substring-after(base-uri(), $configurationName)"/>-->
    <xsl:variable name="classname" select="substring-before($filename, '.svg')"/>
    <xsl:template match="/">
        <xsl:text>package nl.mpi.tg.eg.experiment.client.svg</xsl:text>
        <xsl:value-of select="replace($classpath, '/', '.')" />
        <xsl:text>;
            // base-uri </xsl:text>
        <xsl:value-of select="base-uri()" />
        <xsl:text>;
            // configurationName </xsl:text>
        <xsl:value-of select="$configurationName" />
        <xsl:text>;
            // filename </xsl:text>
        <xsl:value-of select="$filename" />
        <xsl:text>;
            // classpath </xsl:text>
        <xsl:value-of select="$classpath" />
        <xsl:text>;            
            import com.google.gwt.core.client.GWT;
            import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
            import nl.mpi.tg.eg.experiment.client.util.SvgTemplate;
            import com.google.gwt.user.client.DOM;
            import com.google.gwt.dom.client.Element;
            import com.google.gwt.event.dom.client.ClickEvent;
            import com.google.gwt.event.dom.client.ClickHandler;
            import com.google.gwt.event.dom.client.TouchEndEvent;
            import com.google.gwt.event.dom.client.TouchEndHandler;
            import com.google.gwt.safehtml.shared.SafeHtmlUtils;
            import com.google.gwt.user.client.ui.HTML;
            import java.util.HashMap;
            import java.util.Map;
            import nl.mpi.tg.eg.frinex.common.listener.TimedStimulusListener;

            // generated with svg2java.xsl
            public class </xsl:text>
        <xsl:value-of select="$classname" />
        <xsl:text>Builder {

            private static final SvgTemplate SVG_TEMPLATE = GWT.create(SvgTemplate.class);
            private static final </xsl:text>
        <xsl:value-of select="$classname" />
        <xsl:text> SVG_DATA = new </xsl:text>
        <xsl:value-of select="$classname" />
        <xsl:text>();
            private SafeHtmlBuilder safeHtmlBuilder = new SafeHtmlBuilder();
            private Map&lt;SvgGroupStates, TimedStimulusListener&gt; groupActions = new HashMap&lt;&gt;();
            public enum SvgGroupStates {
        </xsl:text>
        <xsl:for-each select="svg:svg//svg:g">
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>,
            </xsl:text>
        </xsl:for-each>
        <xsl:text>svgDiagram // the diagram entry is used to identify the svg root element
            }

            public enum SvgTextElements {
        </xsl:text>
        <xsl:for-each select="svg:svg//svg:tspan">
            <xsl:value-of select="translate(@id, ' -', '__')"/>
            <xsl:text>,
            </xsl:text>
        </xsl:for-each>
        <xsl:text>end // this final element is not used
            }
            public HTML getHtml() {
            SafeHtmlBuilder builder = new SafeHtmlBuilder();
            builder.append(SafeHtmlUtils.fromTrustedString("&lt;style&gt;.overlay {pointer-events: none;}&lt;/style&gt;"));
            builder.append(SafeHtmlUtils.fromTrustedString("&lt;svg class='svgDiagram' id='</xsl:text>
        <xsl:value-of select="$classname" />
        <xsl:text>' width=\"100%\" height=\"100%\" viewBox='0 0 568 320' &gt;"));
            getSvg(builder);
            builder.append(SafeHtmlUtils.fromTrustedString("&lt;/svg&gt;"));
            final HTML html = new HTML(builder.toSafeHtml());
            html.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
            event.preventDefault();
            eventTriggered(Element.as(event.getNativeEvent().getEventTarget()));
            }
            });
            html.addTouchEndHandler(new TouchEndHandler() {

            @Override
            public void onTouchEnd(TouchEndEvent event) {
            event.preventDefault();
            eventTriggered(Element.as(event.getNativeEvent().getEventTarget()));
            }
            });
            html.setStylePrimaryName("svgPanel");
            return html;
            }
            private void eventTriggered(Element targetElement) {
            boolean consumed = false;
            while (!consumed) {
            while (targetElement.getParentElement() != null &amp;&amp; targetElement.getId().isEmpty()) {
            targetElement = targetElement.getParentElement();
            }
            final String elementId = targetElement.getId();
            if (elementId.equals(&quot;</xsl:text>
        <xsl:value-of select="$classname" />
        <xsl:text>&quot;)) {
            // we have navigated to the root node of the SVG
            return;
            }
            if (!elementId.isEmpty()) {
            try {
            consumed = performClick(elementId);
            } catch (IllegalArgumentException exception) {
            // id values can exist outside of group tags, only group tags are put into the enum and so this exception is not exceptional
            }
            }
            targetElement = targetElement.getParentElement();
            }
            }
            public void svgSetLabel(SvgTextElements textElement, String label) {
            final Element elementById = DOM.getElementById(textElement.name());
            if (elementById != null) {
            elementById.setInnerText(label);
            }
            }

            public void svgGroupAdd(SvgGroupStates group, boolean isVisible) {
            switch(group) {
        </xsl:text>
        <xsl:for-each select="svg:svg//svg:g">
            <xsl:text>case </xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>:
            </xsl:text>
            <xsl:text>getSvg</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>(safeHtmlBuilder, (isVisible)? SvgTemplate.Visibility.visible : SvgTemplate.Visibility.hidden);
                break;
            </xsl:text>
        </xsl:for-each>
        <xsl:text>
            }
            }
            
            public void svgGroupMatching(SvgGroupStates group, String childRegex, boolean isVisible) {
            final Element elementById = DOM.getElementById(group.name());
            final String styleString = elementById.getAttribute("style").replaceAll("visibility:[^;]*(;|$)", "");
            elementById.setAttribute("style", "visibility:" + SvgTemplate.Visibility.visible + ";" + styleString);
            Element childElement = elementById.getFirstChildElement();
            while (childElement != null) {
            if (childElement.getId() != null) {
            if (childElement.getId().matches(childRegex)) {
            final String updatedStyle = childElement.getAttribute("style").replaceAll("visibility:[^;]*(;|$)", "");
            childElement.setAttribute("style", "visibility:" + ((isVisible) ? SvgTemplate.Visibility.visible : SvgTemplate.Visibility.hidden) + ";" + updatedStyle);
            }
            }
            childElement = childElement.getNextSiblingElement();
            }
            }

            public void svgGroupShow(SvgGroupStates group, boolean isVisible) {
            if (isVisible) {
            showGroup(group);
            } else {
            hideGroup(group);
            }
            }
            
            public void svgGroupAction(SvgGroupStates group, TimedStimulusListener listener) {
            groupActions.put(group, listener);
            }          

            protected boolean performClick(final String svgGroupStateString) {
            if (!svgGroupStateString.isEmpty()) {
            final SvgGroupStates group = SvgGroupStates.valueOf(svgGroupStateString);
            final TimedStimulusListener listener = groupActions.get(group);
            if (listener != null) {
            listener.postLoadTimerFired();
            return true;
            } else {
            return false;
            }
            } else {
            return false;
            }
            }
            
            public void showGroup(SvgGroupStates group) {
            final Element elementById = DOM.getElementById(group.name());
            final String styleString = elementById.getAttribute("style").replaceAll("visibility:[^;]*(;|$)", "");
            elementById.setAttribute("style", "visibility:" + SvgTemplate.Visibility.visible + ";" + styleString);
            }

            public void hideGroup(SvgGroupStates group) {
            final Element elementById = DOM.getElementById(group.name());
            final String styleString = elementById.getAttribute("style").replaceAll("visibility:[^;]*(;|$)", "");
            elementById.setAttribute("style", "visibility:" + SvgTemplate.Visibility.hidden + ";" + styleString);
            }
        </xsl:text>
        <!--<xsl:for-each select="svg:svg/svg:g[svg:path]">
            <xsl:text>
            @DefaultMessage("</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/>"<xsl:text>)
            @Key("id</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>")
            abstract public String id</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>;
                
            @DefaultMessage("</xsl:text><xsl:value-of select="@transform"/>"<xsl:text>)
            @Key("data</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>")
            abstract public String transform</xsl:text><xsl:value-of select="replace(@inkscape:label, ' ', '_')"/><xsl:text>;
        </xsl:text>
            <xsl:for-each select="svg:path">    
            <xsl:text>
            @DefaultMessage("</xsl:text><xsl:value-of select="@style"/>"<xsl:text>)
            @Key("style</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>")
            abstract public String style</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>;
                
            @DefaultMessage("</xsl:text><xsl:value-of select="@data"/>"<xsl:text>)
            @Key("data</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>")
            abstract public String data</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>;
                
            @DefaultMessage("</xsl:text><xsl:value-of select="@transform"/>"<xsl:text>)
            @Key("data</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>")
            abstract public String transform</xsl:text><xsl:value-of select="generate-id(.)"/><xsl:text>;
        </xsl:text>
            </xsl:for-each>
        </xsl:for-each>-->
        <xsl:text>
            public void getSvg(SafeHtmlBuilder builder) {
            getDefsTag(builder);
        </xsl:text>
        <xsl:for-each select="svg:svg/svg:g">
            <xsl:text>        getSvg</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>(builder, SvgTemplate.Visibility.visible);
            </xsl:text>
        </xsl:for-each>
        <xsl:text>    }

        </xsl:text>
        <xsl:apply-templates select="svg:svg/svg:defs"/>
        <!--<xsl:apply-templates select="svg:svg/svg:g"/>-->
        <xsl:for-each select="svg:svg//svg:g">
            <xsl:text>
                public void getSvg</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>(SafeHtmlBuilder builder, SvgTemplate.Visibility visibility) {
                builder.append(SVG_TEMPLATE.groupTag(SVG_DATA.id</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>,SVG_DATA.transform</xsl:text>
            <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
            <xsl:text>, visibility));</xsl:text>
            <xsl:apply-templates/>
            <xsl:text>      builder.append(SVG_TEMPLATE.groupTagEnd());
                }
            </xsl:text>  
        </xsl:for-each>
        <xsl:text>}</xsl:text>
    </xsl:template>
    
    <!--    <xsl:template match="svg:g">
<xsl:text>
    public void getSvg</xsl:text><xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/><xsl:text>(SafeHtmlBuilder builder, SvgTemplate.Visibility visibility) {
        builder.append(SVG_TEMPLATE.groupTag(SVG_DATA.id</xsl:text>
<xsl:value-of select="translate(@inkscape:label, ' -', '__')"/>
<xsl:text>,SVG_DATA.transform</xsl:text>
<xsl:value-of select="translate(@inkscape:label, ' -', '__')"/>
<xsl:text>, visibility));
</xsl:text>
<xsl:apply-templates/>
<xsl:text>      builder.append(SVG_TEMPLATE.groupTagEnd());
    }
</xsl:text>
    </xsl:template>-->
    <xsl:template match="svg:g">
        <xsl:text>
            getSvg</xsl:text>
        <xsl:value-of select="translate(if (@inkscape:label) then @inkscape:label else @id, ' -', '__')"/>
        <xsl:text>(builder, SvgTemplate.Visibility.inherit);</xsl:text>
    </xsl:template>
    <xsl:template match="svg:path">
        <xsl:text>    builder.append(SVG_TEMPLATE.pathTag(SVG_DATA.transform</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.data</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.id</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));</xsl:text>
    </xsl:template>
    <xsl:template match="svg:defs">
        <xsl:text>public void getDefsTag(SafeHtmlBuilder builder) {
            builder.append(SVG_TEMPLATE.defsTag());
        </xsl:text>     
        <xsl:apply-templates select="svg:linearGradient"/>
        <xsl:text>      builder.append(SVG_TEMPLATE.defsTagEnd());
            }
        </xsl:text>         
    </xsl:template>
    <xsl:template match="svg:linearGradient">
        <xsl:text>      builder.append(SVG_TEMPLATE.linearGradientTag(SVG_DATA.id</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.xlinkHref</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.x1</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.y1</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.x2</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.y2</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.gradientTransform</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.gradientUnits</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>     
        <xsl:apply-templates select="svg:stop"/>
        <xsl:text>      builder.append(SVG_TEMPLATE.linearGradientTagEnd());
        </xsl:text>         
    </xsl:template>
    <xsl:template match="svg:stop">
        <xsl:text>      builder.append(SVG_TEMPLATE.stopTag(SVG_DATA.offset</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>         
    </xsl:template>
    <xsl:template match="svg:text">
        <xsl:text>    builder.append(SVG_TEMPLATE.textTag(SVG_DATA.x</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.y</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.transform</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>     
        <xsl:apply-templates select="svg:tspan"/>
        <xsl:text>        builder.append(SVG_TEMPLATE.textTagEnd());</xsl:text>         
    </xsl:template>
    <xsl:template match="svg:tspan">
        <xsl:text>        builder.append(SVG_TEMPLATE.tspanTag(SVG_DATA.id</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.x</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.y</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.text</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>         
    </xsl:template>
    <xsl:template match="svg:rect">
        <xsl:text>    builder.append(SVG_TEMPLATE.rectTag(SVG_DATA.x</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.y</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.rx</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.ry</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.width</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.height</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>
    </xsl:template>
    <xsl:template match="svg:circle">
        <xsl:text>    builder.append(SVG_TEMPLATE.circleTag(SVG_DATA.cx</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.cy</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.r</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>
    </xsl:template>
    <xsl:template match="svg:ellipse">
        <xsl:text>    builder.append(SVG_TEMPLATE.ellipseTag(SVG_DATA.cx</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.cy</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.rx</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.ry</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.style</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>, SVG_DATA.transform</xsl:text>
        <xsl:value-of select="generate-id(.)"/>
        <xsl:text>));
        </xsl:text>
    </xsl:template>
    <xsl:template match="svg:flowRoot">
    </xsl:template>
</xsl:stylesheet>
