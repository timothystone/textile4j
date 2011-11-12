package net.sf.textile4j;

import junit.framework.TestCase;

/**
 * @author Mark Lussier
 */

public class TextileTest extends TestCase {
    Textile textile = new Textile();


    public void testLarryRoller1() throws Exception {
        String in = "*strong* plain _emphasis_ * _emphaticStrong_ * ";
        String out = "\t<p><strong>strong</strong> plain <em>emphasis</em> <strong> <em>emphaticStrong</em> </strong></p>";
        String content = textile.process(in);
        assertEquals(out, content);
    }

    public void testCodeSyntax1() {
        String in = "This is an empty dictionary: @{}@";
        String out = "\t<p>This is an empty dictionary: <code>{}</code></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax2() {
        String in = "This is an empty dictionary: *{}*";
        String out = "\t<p>This is an empty dictionary: <strong>{}</strong></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax3() {
        String in = "This is an empty dictionary: **{}**";
        String out = "\t<p>This is an empty dictionary: <b>{}</b></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax4() {
        String in = "This is an empty dictionary: ??{}??";
        String out = "\t<p>This is an empty dictionary: <cite>{}</cite></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax5() {
        String in = "This is an empty dictionary: -{}-";
        String out = "\t<p>This is an empty dictionary: <del>{}</del></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax6() {
        String in = "This is an empty dictionary: +{}+";
        String out = "\t<p>This is an empty dictionary: <ins>{}</ins></p>";
        assertEquals(out, textile.process(in));
    }

    public void testCodeSyntax7() {
        String in = "This is an empty dictionary: ~{}~";
        String out = "\t<p>This is an empty dictionary: <sub>{}</sub></p>";
        assertEquals(out, textile.process(in));
    }


    public void testStyleSyntax1() {
        String in = "Welcome to Mark's Test";
        String out = "\t<p>Welcome to Mark&#8216;s Test</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax2() {
        String in = "Welcome to Mark\"s Test";
        String out = "\t<p>Welcome to Mark&#8221;s Test</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax3() {
        String in = "Welcome to 'Marks' Test";
        String out = "\t<p>Welcome to &#8216;Marks&#8216; Test</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax4() {
        String in = "Welcome to \"Marks\" Test";
        String out = "\t<p>Welcome to &#8220;Marks&#8221; Test</p>";
        assertEquals(out, textile.process(in));
    }

    /**
     * Acronym
     */
    public void testStyleSyntax5() {
        String in = "Welcome to WTF(What the Heck) Dave!";
        String out = "\t<p>Welcome to <acronym title=\"What the Heck\">WTF</acronym> Dave!</p>";
        assertEquals(out, textile.process(in));
    }

    /**
     * Capitals
     */
    public void testStyleSyntax6() {
        String in = "Welcome to WTF baby!";
        String out = "\t<p>Welcome to <span class=\"caps\">WTF</span> baby!</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax7() {
        String in = "Welcome to -- Welcome";
        String out = "\t<p>Welcome to &#8212; Welcome</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax8() {
        String in = "Welcome to - Welcome";
        String out = "\t<p>Welcome to &#8211; Welcome</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax9() {
        String in = "Welcome to 123-456";
        String out = "\t<p>Welcome to 123&#8211;456</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax10() {
        String in = "Welcome to 23 x 45";
        String out = "\t<p>Welcome to 23&#215;45</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax11() {
        String in = "This is textile4j(tm)";
        String out = "\t<p>This is textile4j&#8482;</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax12() {
        String in = "This is textile4j(r)";
        String out = "\t<p>This is textile4j&#174;</p>";
        assertEquals(out, textile.process(in));
    }

    public void testStyleSyntax13() {
        String in = "Copyright (c) 2002 Mark Lussier";
        String out = "\t<p>Copyright&#169; 2002 Mark Lussier</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax1() {
        String in = "\"link text\":#1";
        String out = "\t<p><a href=\"#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax2() {
        String in = "\"link text\":#a";
        String out = "\t<p><a href=\"#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax3() {
        String in = "\"link text\":#a1";
        String out = "\t<p><a href=\"#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax4() {
        String in = "\"link text\":#a10";
        String out = "\t<p><a href=\"#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax5() {
        String in = "\"link text\":index.html";
        String out = "\t<p><a href=\"index.html\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax6() {
        String in = "\"link text\":index.html#1";
        String out = "\t<p><a href=\"index.html#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax7() {
        String in = "\"link text\":index.html#a";
        String out = "\t<p><a href=\"index.html#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax8() {
        String in = "\"link text\":index.html#a1";
        String out = "\t<p><a href=\"index.html#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax9() {
        String in = "\"link text\":index.html#a10";
        String out = "\t<p><a href=\"index.html#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax10() {
        String in = "\"link text\":http://example.com/";
        String out = "\t<p><a href=\"http://example.com/\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax11() {
        String in = "\"link text\":http://example.com/#1";
        String out = "\t<p><a href=\"http://example.com/#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax12() {
        String in = "\"link text\":http://example.com/#a";
        String out = "\t<p><a href=\"http://example.com/#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax13() {
        String in = "\"link text\":http://example.com/#a1";
        String out = "\t<p><a href=\"http://example.com/#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax14() {
        String in = "\"link text\":http://example.com/#a10";
        String out = "\t<p><a href=\"http://example.com/#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax15() {
        String in = "\"link text\":http://example.com/index.html";
        String out = "\t<p><a href=\"http://example.com/index.html\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax16() {
        String in = "\"link text\":http://example.com/index.html#a";
        String out = "\t<p><a href=\"http://example.com/index.html#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax17() {
        String in = "\"link text\":http://example.com/index.html#1";
        String out = "\t<p><a href=\"http://example.com/index.html#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax18() {
        String in = "\"link text\":http://example.com/index.html#a1";
        String out = "\t<p><a href=\"http://example.com/index.html#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax19() {
        String in = "\"link text\":http://example.com/index.html#a10";
        String out = "\t<p><a href=\"http://example.com/index.html#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax20() {
        String in = "\"link text\":http://example.com/?foo=bar";
        String out = "\t<p><a href=\"http://example.com/?foo=bar\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax21() {
        String in = "\"link text\":http://example.com/?foo=bar#a";
        String out = "\t<p><a href=\"http://example.com/?foo=bar#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax22() {
        String in = "\"link text\":http://example.com/?foo=bar#1";
        String out = "\t<p><a href=\"http://example.com/?foo=bar#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax23() {
        String in = "\"link text\":http://example.com/?foo=bar#a1";
        String out = "\t<p><a href=\"http://example.com/?foo=bar#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax24() {
        String in = "\"link text\":http://example.com/?foo=bar#a10";
        String out = "\t<p><a href=\"http://example.com/?foo=bar#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax25() {
        String in = "\"link text\":http://example.com/?foo=bar&a=b";
        String out = "\t<p><a href=\"http://example.com/?foo=bar&#38;a=b\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax26() {
        String in = "\"link text\":http://example.com/?foo=bar&a=b#1";
        String out = "\t<p><a href=\"http://example.com/?foo=bar&#38;a=b#1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax27() {
        String in = "\"link text\":http://example.com/?foo=bar&a=b#a";
        String out = "\t<p><a href=\"http://example.com/?foo=bar&#38;a=b#a\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax28() {
        String in = "\"link text\":http://example.com/?foo=bar&a=b#a1";
        String out = "\t<p><a href=\"http://example.com/?foo=bar&#38;a=b#a1\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax29() {
        String in = "\"link text\":http://example.com/?foo=bar&a=b#a10";
        String out = "\t<p><a href=\"http://example.com/?foo=bar&#38;a=b#a10\" title=\"\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax30() {
        String in = "This is a \"link\":http://example.com/";
        String out = "\t<p>This is a <a href=\"http://example.com/\" title=\"\">link</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax31() {
        String in = "This is a \"link\":http://example.com/.";
        String out = "\t<p>This is a <a href=\"http://example.com/\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax32() {
        String in = "This is a \"_link_\":http://example.com/index.html.";
        String out = "\t<p>This is a <a href=\"http://example.com/index.html\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax33() {
        String in = "This is a \"link\":http://example.com/index.html#a.";
        String out = "\t<p>This is a <a href=\"http://example.com/index.html#a\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax34() {
        String in = "This is a \"link\":http://example.com/index.html#1.";
        String out = "\t<p>This is a <a href=\"http://example.com/index.html#1\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax35() {
        String in = "This is a \"link\":http://example.com/index.html#a1.";
        String out = "\t<p>This is a <a href=\"http://example.com/index.html#a1\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax36() {
        String in = "This is a \"link\":http://example.com/index.html#a10.";
        String out = "\t<p>This is a <a href=\"http://example.com/index.html#a10\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax37() {
        String in = "This is a \"link\":http://example.com/?foo=bar.";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax38() {
        String in = "This is a \"link\":http://example.com/?foo=bar#1.";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar#1\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax39() {
        String in = "This is a \"link\":http://example.com/?foo=bar#a.";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar#a\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax40() {
        String in = "This is a \"link\":http://example.com/?foo=bar#a1 .";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar#a1\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax41() {
        String in = "This is a \"link\":http://example.com/?foo=bar#a10.";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar#a10\" title=\"\">link</a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax42() {
        String in = "This is a \"link\":http://example.com/?foo=bar#a10, but this is not.";
        String out = "\t<p>This is a <a href=\"http://example.com/?foo=bar#a10\" title=\"\">link</a>, but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax43() {
        String in = "(This is a \"link\":http://example.com/?foo=bar#a10) but this is not.";
        String out = "\t<p>(This is a <a href=\"http://example.com/?foo=bar#a10\" title=\"\">link</a>) but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax44() {
        String in = "\"link text(link title)\":http://example.com/";
        String out = "\t<p><a href=\"http://example.com/\" title=\"link title\">link text</a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax46() {
        String in = "\"(link) text(link title)\":http://example.com/";
        String out = "\t<p>&#8220;(link) text(link title)&#8221;:http://example.com/</p>";
        assertEquals(out, textile.process(in));
    }

    public void testLinksSyntax47() {
        String in = "\"Dive Into XML\":http://www.xml.com/pub/au/164";
        String out = "\t<p><a href=\"http://www.xml.com/pub/au/164\" title=\"\">Dive Into <span class=\"caps\">XML</span></a></p>";
        assertEquals(out, textile.process(in));
    }


    public void testLinksSyntax48() {
        String in = "-test \"test\":http://www.test-test-test.com";
        String out = "\t<p>-test <a href=\"http://www.test-test-test.com\" title=\"\">test</a></p>";
        assertEquals(out, textile.process(in));
    }


    public void testImagesSyntax1() {
        String in = "This is an !image.jpg!";
        String out = "\t<p>This is an <img src=\"image.jpg\" alt=\"\" /></p>";
        String s = textile.process(in);
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax2() {
        String in = "This is an !image.jpg(with alt text)!";
        String out = "\t<p>This is an <img src=\"image.jpg\" alt=\"with alt text\" /></p>";
        String s = textile.process(in);
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax3() {
        String in = "This is an !http://example.com/i/image.jpg!";
        String out = "\t<p>This is an <img src=\"http://example.com/i/image.jpg\" alt=\"\" /></p>";
        String s = textile.process(in);
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax4() {
        String in = "This is an !http://example.com/i/image.jpg#a1!";
        String out = "\t<p>This is an <img src=\"http://example.com/i/image.jpg#a1\" alt=\"\" /></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax5() {
        String in = "This is an !image.jpg!.";
        String out = "\t<p>This is an <img src=\"image.jpg\" alt=\"\" />.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax6() {
        String in = "This is an !image.jpg(with alt text)!.";
        String out = "\t<p>This is an <img src=\"image.jpg\" alt=\"with alt text\" />.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax7() {
        String in = "This is an !http://example.com/i/image.jpg!.";
        String out = "\t<p>This is an <img src=\"http://example.com/i/image.jpg\" alt=\"\" />.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax8() {
        String in = "This is an !http://example.com/i/image.jpg#a1!.";
        String out = "\t<p>This is an <img src=\"http://example.com/i/image.jpg#a1\" alt=\"\" />.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax9() {
        String in = "This is not an image!!!";
        String out = "\t<p>This is not an image!!!</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax10() {
        String in = "This is an !http://example.com/i/image.jpg!:#1";
        String out = "\t<p>This is an <a href=\"#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax11() {
        String in = "This is an !http://example.com/i/image.jpg!:#a";
        String out = "\t<p>This is an <a href=\"#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax12() {
        String in = "This is an !http://example.com/i/image.jpg!:#a1";
        String out = "\t<p>This is an <a href=\"#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax13() {
        String in = "This is an !http://example.com/i/image.jpg!:#a10";
        String out = "\t<p>This is an <a href=\"#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax14() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html";
        String out = "\t<p>This is an <a href=\"index.html\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax15() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html#1";
        String out = "\t<p>This is an <a href=\"index.html#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax16() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html#a1";
        String out = "\t<p>This is an <a href=\"index.html#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax17() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html#a10";
        String out = "\t<p>This is an <a href=\"index.html#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax18() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html?foo=bar";
        String out = "\t<p>This is an <a href=\"index.html?foo=bar\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax19() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html?foo=bar#1";
        String out = "\t<p>This is an <a href=\"index.html?foo=bar#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax20() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html?foo=bar#a";
        String out = "\t<p>This is an <a href=\"index.html?foo=bar#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax21() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html?foo=bar#a1";
        String out = "\t<p>This is an <a href=\"index.html?foo=bar#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax22() {
        String in = "This is an !http://example.com/i/image.jpg!:index.html?foo=bar#a10";
        String out = "\t<p>This is an <a href=\"index.html?foo=bar#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax23() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/";
        String out = "\t<p>This is an <a href=\"http://example.com/\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax24() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/#1";
        String out = "\t<p>This is an <a href=\"http://example.com/#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax25() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/#a";
        String out = "\t<p>This is an <a href=\"http://example.com/#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax26() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/#a1";
        String out = "\t<p>This is an <a href=\"http://example.com/#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax27() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/#a10";
        String out = "\t<p>This is an <a href=\"http://example.com/#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax28() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax29() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html#1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax30() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html#a";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax31() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html#a1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax32() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html#a10";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax33() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax34() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar#1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax35() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar#a";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax36() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar#a1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax37() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar#a10";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax38() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax39() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax40() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax41() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a1";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax42() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a10";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a></p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax43() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax44() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#1.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax45() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax46() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a1.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax47() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a10.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax48() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b, but this is not.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>, but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax49() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#1, but this is not.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>, but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax50() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a, but this is not.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>, but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax51() {
        String in = "This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a1, but this is not.";
        String out = "\t<p>This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>, but this is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax52() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a10)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax53() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax54() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#1)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax55() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax56() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a1)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a1\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    public void testImagesSyntax57() {
        String in = "(This is an !http://example.com/i/image.jpg!:http://example.com/index.html?foo=bar&a=b#a10)  This is not.";
        String out = "\t<p>(This is an <a href=\"http://example.com/index.html?foo=bar&#38;a=b#a10\"><img src=\"http://example.com/i/image.jpg\" alt=\"\" /></a>)  This is not.</p>";
        assertEquals(out, textile.process(in));
    }

    /**
     * Test of process method, of class Textile.
     */
    public void testProcess() {
        System.out.println("process");
        String origionalContent = "";
        Textile instance = new Textile();
        String expResult = "";
        String result = instance.process(origionalContent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

