<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="invitation">
        <fo:root >
            <fo:layout-master-set>
                <fo:simple-page-master master-name="one" page-height="15cm" page-width="21cm" margin-left="2cm" margin-right="2cm">
                    <fo:region-body margin-top="1cm" margin-bottom="1cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="one">
                <fo:flow flow-name="xsl-region-body" font-size="14pt" line-height="18pt">
                    <xsl:apply-templates select="event"/>
                    <fo:block text-align="justify" padding="2em" margin-left="0cm" margin-right="0cm" border-left="solid red 2em" border-right="solid red 2em">
                        I'd like to invite you to come to
                        <xslt:apply-templates xmlns:xslt="http://www.w3.org/1999/XSL/Transform" select="location"/>
                        . The event takes place on
                        <xslt:apply-templates xmlns:xslt="http://www.w3.org/1999/XSL/Transform" select="date"/>
                        at
                        <xslt:apply-templates xmlns:xslt="http://www.w3.org/1999/XSL/Transform" select="time"/>
                        .
                    </fo:block>
                    <xslt:apply-templates xmlns:xslt="http://www.w3.org/1999/XSL/Transform" select="description|dresscode"/>
                    <fo:block text-align="justify" background-color="#cccccc" color="#000000" padding="10pt" font-size="8pt" space-before.optimum="30pt">
                        Generated from XML with invitation2html.xslt. Copyright © Stefan Mintert, Linkwerk.com.
                        <fo:inline font-style="italic">
                            http://www.linkwerk.com/pub/xml/invitation/
                        </fo:inline>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>