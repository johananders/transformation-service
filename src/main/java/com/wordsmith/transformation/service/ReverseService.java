package com.wordsmith.transformation.service;

import com.google.common.base.CharMatcher;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
class ReverseService {

    private static final TokenDelimiterMatcher DELIMITER_MATCHER = new TokenDelimiterMatcher();

    /**
     * Reverses all tokens in a String, keeping the order of the tokens.
     * Token delimiters are [.,?!] and any whitespace
     *
     * Examples:
     * "The red fox crosses the ice." => "ehT der xof sessorc eht eci."
     * " " => " "
     * "" => ""
     * " a" => " a"
     *
     * @param input String to be reversed
     * @return the "reversed" String
     * @throws NullPointerException if input is null
     */
    String reverse(final String input) {
        Objects.requireNonNull(input, "Null input");

        final StringBuilder result = new StringBuilder(input.length());
        final Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i <= input.length(); i++) {
            if (i == input.length() || DELIMITER_MATCHER.matches(input.charAt(i))) {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
                if (i < input.length()) {
                    result.append(input.charAt(i));
                }
            } else {
                stack.push(input.charAt(i));
            }
        }

        return result.toString();
    }

    static final class TokenDelimiterMatcher {

        private static final CharMatcher MATCHER = CharMatcher.anyOf(",.?!").or(CharMatcher.whitespace());

        /**
         * Tests if a character is considered a token delimiter
         *
         * @param c character to test
         * @return true if character is a token delimiter, otherwise false
         */
        boolean matches(final char c) {
            return MATCHER.matches(c);
        }

    }

}
