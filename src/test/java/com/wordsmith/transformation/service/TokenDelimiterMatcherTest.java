package com.wordsmith.transformation.service;

import static com.wordsmith.transformation.service.ReverseService.TokenDelimiterMatcher;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.junit.Test;

public class TokenDelimiterMatcherTest {

    private TokenDelimiterMatcher tokenDelimiterMatcher = new TokenDelimiterMatcher();
    private static final Set<Character> DELIMITERS = ImmutableSet.of('!', '.', ',', '?', ' ', '\n', '\t', '\r');

    @Test
    public void shouldMatchDelimiters() {
        DELIMITERS.forEach(c ->
            assertTrue(c + " should be delimiter character", tokenDelimiterMatcher.isDelimiter(c)));
    }

    @Test
    public void shoulNotMatchDelimiters() {
        for (char c = 0; c < 256; c++) {
            if (!DELIMITERS.contains(c) && CharMatcher.whitespace().negate().matches(c)) {
                assertFalse((int) c + " should not be delimiter character", tokenDelimiterMatcher.isDelimiter(c));
            }
        }
    }
}