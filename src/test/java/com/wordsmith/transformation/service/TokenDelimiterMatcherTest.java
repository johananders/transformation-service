package com.wordsmith.transformation.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableSet;
import com.wordsmith.transformation.service.ReverseService.TokenDelimiterMatcher;
import java.util.Set;
import org.junit.Test;

public class TokenDelimiterMatcherTest {

    private TokenDelimiterMatcher tokenDelimiterMatcher = new TokenDelimiterMatcher();
    private static final Set<Character> DELIMITERS = ImmutableSet.of('!', '.', ',', '?', ' ', '\n', '\t', '\r');

    @Test
    public void shouldMatchDelimiters() {
        DELIMITERS.forEach(c -> assertThat(tokenDelimiterMatcher.matches(c)).isTrue());
    }

    @Test
    public void shoulNotMatchDelimiters() {
        for (char c = 0; c < Character.MAX_VALUE; c++) {
            if (!DELIMITERS.contains(c) && !CharMatcher.whitespace().matches(c)) {
                assertThat(tokenDelimiterMatcher.matches(c)).isFalse();
            }
        }
    }
}