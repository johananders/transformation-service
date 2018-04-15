package com.wordsmith.transformation.service;

import com.google.common.base.CharMatcher;
import java.util.ArrayDeque;
import java.util.Deque;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ReverseService {

    private static final TokenDelimiterMatcher DELIMITER_MATCHER = new TokenDelimiterMatcher();

    /**
     * Reverses all tokens in a String, keeping the order of the words.
     * Token delimiters are [.,?!] and any whitespace
     *
     * Example:
     * Input: The red fox crosses the ice, intent on none of my business.
     * Output: ehT der xof sessorc eht eci, tnetni no enon fo ym ssenisub.
     *
     * @param input String to be transformed
     * @return the transformed String
     * @throws NullPointerException if input is null
     */
    public String reverse(@NonNull final String input) {
        final StringBuilder result = new StringBuilder(input.length());
        final Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);

            if (DELIMITER_MATCHER.isDelimiter(c)) {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }

                result.append(c);
            } else {
                stack.push(c);
            }
        }

        if (!stack.isEmpty()) {
            stack.iterator().forEachRemaining(result::append);
        }

        return result.toString();
    }

    static class TokenDelimiterMatcher {

        private static final CharMatcher MATCHER = CharMatcher
            .anyOf(",.?!")
            .or(CharMatcher.whitespace());

        /**
         * Returns true if the character is considered a token delimiter
         * @param c character to test
         * @return true if character is a token delimiter, otherwise false
         */
        boolean isDelimiter(final char c) {
            return MATCHER.matches(c);
        }

    }

}
