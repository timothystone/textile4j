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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Textile
 * Java implementation of Textism's Textile Humane Web Text Generator
 * <p/>
 * For Origional PHP see http://www.textism.com/tools/textile
 * <p/>
 * JTextile is another java implementation compatible with older (ie 1.3) versions of
 * the JDK, and uses GNU-REGEX for the expressions
 * See http://pipthepixie.tripod.com/code/jtextile.html
 *
 * @author Mark Lussier
 * @version $Id: Textile.java,v 1.9 2004/05/26 19:14:35 intabulas Exp $
 */
public class Textile implements TextileConstants {

    /**
     * Public Constructor
     */
    public Textile() {
    }


    /**
     * Process a textile formatted string
     *
     * @param origionalContent Textile formatted content
     * @return Content converted to HTML
     */
    public final String process(final String origionalContent) {

        String content = origionalContent;
        /**
         * Turn any incoming ampersands into a dummy character for now.
         * This uses a negative lookahead for alphanumerics followed by a semicolon,
         * implying an incoming html entity, to be skipped
         */
        //text = preg_replace("&(?![#a-zA-Z0-9]+;)","x%x%",text);
        content = content.replaceAll(EXP_AMPERSAND, EXP_AMPERSAND_REPLACE);

        /**
         * unentify angle brackets and ampersands
         */
        content = replace(content, GREATER_THAN, ">");
        content = replace(content, LESS_THAN, "<");
        content = replace(content, "&amp;", "&");

        /**
         *  zap carriage returns
         */
        content = replace(content, "\r\n", "\n");

        /**
         * zap tabs
         */
        content = replace(content, "\t", "");

        /**
         * preserve double line breaks
         */
        content = replace(content, "\n\n", "\n \n");

        /**
         * trim each line.. no it is not faster to String.split() here since we are just trimming fat
         */
        StringBuffer splitBuffer = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(content, "\n", false);
        while (tokenizer.hasMoreTokens()) {
            splitBuffer.append(tokenizer.nextToken().trim());
            splitBuffer.append("\n");
        }
        content = splitBuffer.toString();

        //### Find and replace quick tags

        /**
         * double equal signs mean <notextile>
         */
        content = content.replaceAll(EXP_DOUBLEQUOTE_MATCH, EXP_DOUBLEQUOTE_REPLACE);

        /**
         * image qtag
         */
        content = content.replaceAll(EXP_IMAGE_QTAG_MATCH, EXP_IMAGE_QTAG_REPLACE);

        /**
         * image with hyperlink
         */
        content = content.replaceAll(EXP_IMAGE_WITH_HREF_QTAG_MATCH, EXP_IMAGE_WITH_HREF_QTAG_REPLACE);

        /**
         *  hyperlink qtag
         */
        content = content.replaceAll(EXP_HREF_QTAG_MATCH, EXP_HREF_QTAG_REPLACE);

        /**
         * loop through the array, replacing qtags with html
         */

        for (int x = 0; x < EXP_PHRASE_MODIFIER_SOURCETAGS.length; x++) {
            String prefix = "(^|\\s|>)" + EXP_PHRASE_MODIFIER_SOURCETAGS[x]
                    + "(.+?)([^\\w\\s]*?)"
                    + EXP_PHRASE_MODIFIER_SOURCETAGS[x];
            //+ "([^\\w\\s]{0,2})(\\s|$)?";


            //String suffix = "$1<" + EXP_PHRASE_MODIFIER_REPLACETAGS[x] + ">$2$3</" + EXP_PHRASE_MODIFIER_REPLACETAGS[x] + ">$4";
            String suffix = "$1<" + EXP_PHRASE_MODIFIER_REPLACETAGS[x] + ">$2$3</" + EXP_PHRASE_MODIFIER_REPLACETAGS[x] + ">";
            content = content.replaceAll(prefix, suffix);
        }

        /**
         * From the Origional Docs:
         * "some weird bs with underscores and \b word boundaries,
         * so we'll do those on their own"
         */
        content = content.replaceAll(EXP_ITALICS_MATCH, EXP_ITALICS_REPLACE);
        content = content.replaceAll(EXP_EMPHASIS_MATCH, EXP_EMPHASIS_REPLACE);
        content = content.replaceAll(EXP_SUPERSCRIPT_MATCH, EXP_SUPERSCRIPT_REPLACE);


        /**
         * small problem with double quotes at the end of a string
         */
        content = content.replaceAll(EXP_EOL_DBL_QUOTES, " ");
        content = content.replaceAll(EXP_EOL_FULL_STOP, " .");


        String[] glyphMatches = {EXP_SINGLE_CLOSING,
                                 EXP_SINGLE_OPENING,
                                 EXP_DOUBLE_CLOSING,
                                 EXP_DOUBLE_OPENING,
                                 EXP_ELLIPSES,
                                 EXP_3UPPER_ACCRONYM,
                                 EXP_3UPPERCASE_CAPS,
                                 EXP_EM_DASH,
                                 EXP_EN_DASH,
                                 EXP_EN_DECIMAL_DASH,
                                 EXP_DIMENSION_SIGN,
                                 EXP_TRADEMARK,
                                 EXP_REGISTERED,
                                 EXP_COPYRIGHT};


        String[] glyphReplacement = {REPLACE_SINGLE_CLOSING,
                                     REPLACE_SINGLE_OPENING,
                                     REPLACE_DOUBLE_CLOSING,
                                     REPLACE_DOUBLE_OPENING,
                                     REPLACE_ELLIPSES,
                                     REPLACE_3UPPER_ACCRONYM,
                                     REPLACE_3UPPERCASE_CAPS,
                                     REPLACE_EM_DASH,
                                     REPLACE_EN_DASH,
                                     REPLACE_EN_DECIMAL_DASH,
                                     REPLACE_DIMENSION_SIGN,
                                     REPLACE_TRADEMARK,
                                     REPLACE_REGISTERED,
                                     REPLACE_COPYRIGHT};


        boolean ishtml = Pattern.compile(EXP_ISHTML, Pattern.MULTILINE).matcher(content).find();
        boolean inpreservation = false;

        if (!ishtml) {
            content = arrayReplaceAll(content, glyphMatches, glyphReplacement);
        } else {
            String[] segments = splitContent(EXP_ISHTML, content);

            StringBuffer segmentBuffer = new StringBuffer();
            for (int x = 0; x < segments.length; x++) {
                //  # matches are off if we're between <code>, <pre> etc.
                if (segments[x].toLowerCase().matches(EXP_STARTPRESERVE)) {
                    inpreservation = true;
                } else if (segments[x].toLowerCase().matches(EXP_ENDPRESERVE)) {
                    inpreservation = false;
                }

                if (!Pattern.compile(EXP_ISHTML, Pattern.MULTILINE).matcher(segments[x]).find() && !inpreservation) {
                    segments[x] = arrayReplaceAll(segments[x], glyphMatches, glyphReplacement);
                }

                //# convert htmlspecial if between <code>
                if (inpreservation) {
                    segments[x] = htmlSpecialChars(segments[x], MODE_ENT_NOQUOTES);
                    segments[x] = replace(segments[x], "&lt;pre&gt;", "<pre>");
                    segments[x] = replace(segments[x], "&lt;code&gt;", "<code>");
                    segments[x] = replace(segments[x], "&lt;notextile&gt;", "<notextile>");
                }

                segmentBuffer.append(segments[x]);

            }

            content = segmentBuffer.toString();

        }


        //### Block level formatting

        //# deal with forced breaks; this is going to be a problem between
        //#  <pre> tags, but we'll clean them later

        content = content.replaceAll(EXP_FORCESLINEBREAKS, REPLACE_FORCESLINEBREAK);

        //# might be a problem with lists
        content = replace(content, "l><br />", "l>\n");


        String[] blockMatches = {EXP_BULLETED_LIST,
                                 EXP_NUMERIC_LIST,
                                 EXP_BLOCKQUOTE,
                                 EXP_HEADER_WITHCLASS,
                                 EXP_HEADER,
                                 EXP_PARA_WITHCLASS,
                                 EXP_PARA,
                                 EXP_REMAINING_PARA};

        String[] blockReplace = {REPLACE_BULLETED_LIST,
                                 REPLACE_NUMERIC_LIST,
                                 REPLACE_BLOCKQUOTE,
                                 REPLACE_HEADER_WITHCLASS,
                                 REPLACE_HEADER,
                                 REPLACE_PARA_WITHCLASS,
                                 REPLACE_PARA,
                                 REPLACE_REMAINING_PARA};


        StringBuffer blockBuffer = new StringBuffer();
        String list = "";
        content += "\n";

        boolean inpre = false;
        //# split the text into an array by newlines
        String[] tokens = content.split("\n");
        int tokenCount = tokens.length;

        for (int x = 0; x < tokenCount; x++) {
            String line = tokens[x];

            //#make sure the line isn't blank
            if (!line.matches("^$")) {

                //# matches are off if we're between <pre> or <code> tags
                if (line.toLowerCase().indexOf("<pre>") > -1) {
                    inpre = true;
                }

                //# deal with block replacements first, then see if we're in a list
                if (!inpre) {
                    line = arrayReplaceAll(line, blockMatches, blockReplace);
                }

                //# kill any br tags that slipped in earlier
                if (inpre) {
                    line = replace(line, "<br />", "\n");
                    line = replace(line, "<br/>", "\n");
                }
                //# matches back on after </pre>
                if (line.toLowerCase().indexOf("</pre>") > -1) {
                    inpre = false;
                }

                //# at the beginning of a list, $line switches to a value
                boolean islist = Pattern.compile(EXP_LISTSTART, Pattern.MULTILINE).matcher(line).find();
                boolean islistline = Pattern.compile(EXP_LISTSTART + list, Pattern.MULTILINE).matcher(line).find();
                if (list.length() == 0 && islist) {
                    line = line.replaceAll(EXP_MATCHLIST, REPLACE_MATCHLIST);
                    list = line.substring(2, 3);

                    //# at the end of a list, $line switches to empty
                } else if (list.length() > 0 && !islistline) {
                    line = line.replaceAll(EXP_ENDMATCHLIST, "</" + list + REPLACE_ENDMATCHLIST);
                    list = "";
                }
            }
            // push each line to a new array once it's processed
            blockBuffer.append(line);
            blockBuffer.append("\n");
        }

        if (!list.equals("")) {
            blockBuffer.append("</" + list + "l>\n");
            list = "";
        }

        content = blockBuffer.toString();

        // Trim trailing EOL
        if (content.endsWith("\n")) {
            content = content.substring(0, content.length() - 1);
        }
//        // Trim starting EOL
//        if (content.startsWith("\n") || content.startsWith("\t")) {
//            content = content.substring(1, content.length());
//        }



        // Clean Up <notextile>
        content = content.replaceAll("<\\/?notextile>", "");

        // Clean up liu and lio
        content = content.replaceAll("<(\\/?)li(u|o)>", "<$1li>");

        // Turn the temp char back to an ampersand entity
        content = replace(content, "x%x%", "&#38;");

        //# Newline linebreaks, just for markup tidiness
        content = replace(content, "<br />", "<br />\n");


        return content;
    }

    /**
     * An implementation of the PHP htmlspecialchars()
     *
     * @param origContent Source string
     * @param mode        Mode to select replacement string for quotes
     * @return String with replace occurrences
     */
    private String htmlSpecialChars(final String origContent, final int mode) {
        String content = origContent;
        content = replace(content, "&", "&amp;");

        if (mode != MODE_ENT_NOQUOTES) {
            content = replace(content, "\"", "&quot;");
        }
        if (mode == MODE_ENT_QUOTES) {
            content = replace(content, "'", "&#039;");
        }
        content = replace(content, "<", LESS_THAN);
        content = replace(content, ">", GREATER_THAN);
        return content;
    }


    /**
     * Splits a string into a string array based on a matching regex
     *
     * @param matchexp Expression to match
     * @param content  Content to split
     * @return String array of split content
     */
    private String[] splitContent(final String matchexp, final String content) {
        int startAt = 0;
        List tempList = new ArrayList();
        Pattern pattern = Pattern.compile(matchexp, Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            tempList.add(content.substring(startAt, matcher.start()));
            tempList.add(matcher.group());
            startAt = matcher.end();
        }

        tempList.add(content.substring(startAt));

        String[] result = new String[tempList.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = (String) tempList.get(i);
        }

        return result;
    }


    /**
     * Replace an array of match patterns in a string
     *
     * @param content  Source string
     * @param matches  Match patterns
     * @param replaces Replacement patterns
     * @return String with replaced occurrences
     */
    private String arrayReplaceAll(final String content, final String[] matches, final String[] replaces) {
        String result = content;

        for (int x = 0; x < matches.length; x++) {
            String match = matches[x];
            String replace = replaces[x];
            result = result.replaceAll(match, replace);
        }

        return result;
    }


    /**
     * Replace any occurances of a string pattern within a string with a different string.
     *
     * @param str     The source string.  This is the string that will be searched and have the replacements
     * @param pattern The pattern to look for in str
     * @param replace The string to insert in the place of <i>pattern</i>
     * @return String with replaced occurences
     */
    private static String replace(final String str, final String pattern, final String replace) {
        if (str == null || "".equals(str)) {
            return str;
        }

        if (replace == null) {
            return str;
        }

        if ("".equals(pattern)) {
            return str;
        }

        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }


}
