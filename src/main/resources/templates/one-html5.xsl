<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/1999/xhtml">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    <xsl:strip-space elements="tasks participants"/>
    <xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <meta charset="utf-8"/>
                <title>XHTML5 + SVG example</title>
            </head>
            <body>
                <p>XHTML5 contents below are generated from XML with XSLT.
                    Contents of and age elements are display in both text and with SVG as a green bar.
                    Look at the source of this page for the XML and at the <a href="http://tecfa.unige.ch/guides/svg/ex/html5-xslt/one-html5.xsl">one-html5.xsl</a>
                    file. Read the <a href="http://edutechwiki.unige.ch/en/XSLT_to_generate_SVG_tutorial">
                        XSLT to generate SVG tutorial</a>
                </p>
                <hr/>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="people">
        <h1>People</h1>
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="person">
        <div style="border-style:dotted;margin:10px;padding:5px;width:200px;height:100px;">
            <xsl:apply-templates/>
        </div>
    </xsl:template>
    <xsl:template match="FirstName">
        <xsl:apply-templates/>,
    </xsl:template>
    <xsl:template match="age">
        Age: <xsl:apply-templates/>
        <xsl:variable name="years" select="."/>
        <svg style="background-color:yellow;float:right" width="20" height="100" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg">
            <rect x="5px" y="5px" height="{$years*1.2}" width="10px" fill="green"/>
        </svg>
    </xsl:template>
    <xsl:template match="description">
        <p>
            <xsl:apply-templates/>
        </p>
    </xsl:template>
    <xsl:template match="title">
        <h1>
            <xsl:apply-templates/>
        </h1>
    </xsl:template>
</xsl:stylesheet>
