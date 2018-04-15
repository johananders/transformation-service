package com.wordsmith.transformation.service;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ReverseServiceTest {

    private final ReverseService reverseService = new ReverseService();

    @Test
    public void reversesLargeInput() {
        final String input = StringUtils.repeat("The red fox crosses the ice, intent on none of my business.", 10000);
        final String expected =
            StringUtils.repeat("ehT der xof sessorc eht eci, tnetni no enon fo ym ssenisub.", 10000);

        assertEquals(expected, reverseService.reverse(input));
    }

    @Test
    public void reversesOneLargeWord() {
        final String input = StringUtils.repeat("Theredfoxcrossestheiceintentonnoneofmybusiness", 10000);
        final String expected =
            StringUtils.repeat("ssenisubymfoenonnotnetnieciehtsessorcxofderehT", 10000);

        assertEquals(expected, reverseService.reverse(input));
    }

    @Test
    public void reversesInputContainingAlphaCharacters() {
        final String input = "The red fox crosses the ice, intent on none of my business.";
        final String expected = "ehT der xof sessorc eht eci, tnetni no enon fo ym ssenisub.";

        assertEquals(expected, reverseService.reverse(input));
    }

    @Test
    public void reversesInputContainingNonAlphaCharacters() {
        final String input = "The r@ed fox cr'osses the #ice, 0intent on none of my business.";
        final String expected = "ehT de@r xof sesso'rc eht eci#, tnetni0 no enon fo ym ssenisub.";

        assertEquals(expected, reverseService.reverse(input));
    }

    @Test
    public void reverseSingleCharacter() {
        assertEquals("A", reverseService.reverse("A"));
        assertEquals("@", reverseService.reverse("@"));
    }

    @Test
    public void reverseOnlyWhitespaceCharacters() {
        assertEquals(" ", reverseService.reverse(" "));
        assertEquals("\t ", reverseService.reverse("\t "));
    }

    @Test
    public void shouldHandleEmptyInput() {
        assertEquals("", reverseService.reverse(""));
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptNullInput() {
        reverseService.reverse(null);
    }
}