/**
 * Textile4J
 * Java implementation of Textism's Textile Humane Web Text Generator
 * Portions  Copyright (c) 2003 Mark Lussier, All Rights Reserved
 *
 * --------------------------------------------------------------------------------
 *
 * Textile is Copyright (c) 2003, Dean Allen, www.textism.com, All rights reserved
 * The  origional Textile can be found at http://www.textism.com/tools/textile
 *
 * _______________
 * TEXTILE LICENSE
 *
 * Redistribution and use in source and binary forms, with or without
 * modifcation, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name Textile nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sf.textile4j;

/**
 * Textile Constants
 *
 * @author Mark Lussier
 * @version $Id: TextileConstants.java,v 1.9 2004/05/18 23:05:53 intabulas Exp $
 */
public interface TextileConstants {

    public static final int MODE_ENT_COMPAT = 0;
    public static final int MODE_ENT_NOQUOTES = 2;
    public static final int MODE_ENT_QUOTES = 3;


    public static final String LESS_THAN = "&lt;";
    public static final String GREATER_THAN = "&gt;";


    public static final String EXP_ISHTML = "<.*>";

    public static final String EXP_AMPERSAND = "&(?![#a-zA-Z0-9]+;)";
    public static final String EXP_AMPERSAND_REPLACE = "x%x%";


    public static final String EXP_DOUBLEQUOTE_MATCH = "(^|\\s)==(.*)==([^[:alnum:]]{0,2})(\\s|$)";
    public static final String EXP_DOUBLEQUOTE_REPLACE = "$1<notextile>$2</notextile>$3$4";

    public static final String EXP_IMAGE_QTAG_MATCH = "!([^\\s\\(=^!]+?)\\s?(\\(([^\\)]+?)\\))?!";
    public static final String EXP_IMAGE_QTAG_REPLACE = "<img src=\"$1\" alt=\"$3\" />";

    public static final String EXP_IMAGE_WITH_HREF_QTAG_MATCH = "(<img.+ \\/>):(\\S+)";
    public static final String EXP_IMAGE_WITH_HREF_QTAG_REPLACE = "<a href=\"$2\">$1</a>";

    public static final String EXP_HREF_QTAG_MATCH = "\"([^\"\\(]+)\\s?(\\(([^\\)]+)\\))?\":(\\S+)(\\/?)(\\.)?([^\\w\\s\\/;]|[1-9]*?)(\\s|$)";
    public static final String EXP_HREF_QTAG_REPLACE = "<a href=\\\"$4\\\" title=\\\"$3\\\">$1</a>";


    public static final String[] EXP_PHRASE_MODIFIER_SOURCETAGS = {"\\*\\*", "\\*", "\\?\\?", "\\-", "\\+", "~", "@"};
    public static final String[] EXP_PHRASE_MODIFIER_REPLACETAGS = {"b", "strong", "cite", "del", "ins", "sub", "code"};
    public static final String EXP_PHRASE_MODIFIER = "";

    public static final String EXP_ITALICS_MATCH = "(^|\\s)__(.*?)__([^\\w\\s]{0,2})(\\s|$)?";
    public static final String EXP_ITALICS_REPLACE = "$1<i>$2</i>$4";

    public static final String EXP_EMPHASIS_MATCH = "(^|\\s)_(.*?)_([^\\w\\s]{0,2})(\\s|$)?";
    public static final String EXP_EMPHASIS_REPLACE = "$1<em>$2</em>$4";

    public static final String EXP_SUPERSCRIPT_MATCH = "(^|\\s)\\^(.*?)\\^(\\s|$)?";
    public static final String EXP_SUPERSCRIPT_REPLACE = "$1<sup>$2</sup>$3";

    public static final String EXP_EOL_DBL_QUOTES = "\"$";
    public static final String EXP_EOL_FULL_STOP = "\\.$";


    public static final String EXP_SINGLE_CLOSING = "\"([^\\\\']*)\\\\'([^\\\\']*)\"";
    public static final String EXP_SINGLE_OPENING = "\\'";
    public static final String EXP_DOUBLE_CLOSING = "([^\\']*)\\\"([^\\\"]*)";
    public static final String EXP_DOUBLE_OPENING = "\"";
    public static final String EXP_ELLIPSES = "\\b( )?\\.{3}";
    public static final String EXP_3UPPER_ACCRONYM = "\\b([A-Z][A-Z0-9]{2,})\\b(\\(([^\\)]+)\\))";
    public static final String EXP_3UPPERCASE_CAPS = "(^|[^\"][>\\s])([A-Z][A-Z0-9 ]{2,})([^<a-z0-9]|$)";
    public static final String EXP_EM_DASH = "\\s?--\\s?";
    public static final String EXP_EN_DASH = "\\s-\\s";
    public static final String EXP_EN_DECIMAL_DASH = "(\\d+)-(\\d+)";
    public static final String EXP_DIMENSION_SIGN = "(\\d+) ?x ?(\\d+)";
    public static final String EXP_TRADEMARK = "\\b ?(\\((tm|TM)\\))";
    public static final String EXP_REGISTERED = "\\b ?(\\([rR]\\))";
    public static final String EXP_COPYRIGHT = "\\b ?(\\([cC]\\))";


    public static final String REPLACE_SINGLE_CLOSING = "$1&#8217;$2";
    public static final String REPLACE_SINGLE_OPENING = "&#8216;";
    public static final String REPLACE_DOUBLE_CLOSING = "$1&#8221;$2";
    public static final String REPLACE_DOUBLE_OPENING = "&#8220;";
    public static final String REPLACE_ELLIPSES = "$1&#8230;";
    public static final String REPLACE_3UPPER_ACCRONYM = "<acronym title=\"$3\">$1</acronym>";
    public static final String REPLACE_3UPPERCASE_CAPS = "$1<span class=\"caps\">$2</span>$3";

    public static final String REPLACE_EM_DASH = " &#8212; ";
    public static final String REPLACE_EN_DASH = " &#8211; ";
    public static final String REPLACE_EN_DECIMAL_DASH = "$1&#8211;$2";
    public static final String REPLACE_DIMENSION_SIGN = "$1&#215;$2";
    public static final String REPLACE_TRADEMARK = "&#8482;";
    public static final String REPLACE_REGISTERED = "&#174;";
    public static final String REPLACE_COPYRIGHT = "&#169;";


    public static final String EXP_STARTPRESERVE = "<(code|pre|kbd|notextile)>";
    public static final String EXP_ENDPRESERVE = "</(code|pre|kbd|notextile)>";

    public static final String EXP_FORCESLINEBREAKS = "(\\S)(_*)([:punct:]*) *\\n([^#*\\s])";
    public static final String REPLACE_FORCESLINEBREAK = "$1$2$3<br />$4";


    public static final String EXP_BULLETED_LIST = "^\\s?\\*\\s(.*)$";
    public static final String EXP_NUMERIC_LIST = "^\\s?#\\s(.*)$";
    public static final String EXP_BLOCKQUOTE = "^bq\\. (.*)";
    public static final String EXP_HEADER_WITHCLASS = "^h(\\d)\\(([\\w]+)\\)\\.\\s(.*)";
    public static final String EXP_HEADER = "^h(\\d)\\. (.*)";
    public static final String EXP_PARA_WITHCLASS = "^p\\(([\\w]+)\\)\\.\\s(.*)";
    public static final String EXP_PARA = "^p\\. (.*)";
    public static final String EXP_REMAINING_PARA = "^([^\\t ]+.*)";


    public static final String REPLACE_BULLETED_LIST = "\t<liu>$1</liu>";
    public static final String REPLACE_NUMERIC_LIST = "\t<lio>$1</lio>";
    public static final String REPLACE_BLOCKQUOTE = "\t<blockquote>$1</blockquote>";
    public static final String REPLACE_HEADER_WITHCLASS = "\t<h$1 class=\"$2\">$3</h$1>";
    public static final String REPLACE_HEADER = "\t<h$1>$2</h$1>";
    public static final String REPLACE_PARA_WITHCLASS = "\t<p class=\"$1\">$2</p>";
    public static final String REPLACE_PARA = "\t<p>$1</p>";
    public static final String REPLACE_REMAINING_PARA = "\t<p>$1</p>";

    public static final String EXP_LISTSTART = "\\t<li";
    public static final String EXP_MATCHLIST = "^(\\t<li)(o|u)";
    public static final String REPLACE_MATCHLIST = "\n<$2l>\n$1$2";
    public static final String EXP_ENDMATCHLIST = "^(.*)$";
    public static final String REPLACE_ENDMATCHLIST = "l>\n$1";

}
