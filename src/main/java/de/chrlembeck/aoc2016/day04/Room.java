package de.chrlembeck.aoc2016.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Room {

    private final String checksum;

    private final List<String> names = new ArrayList<>();

    private int identifier;

    public Room(final String input) {
        final int pos = input.indexOf('[');
        checksum = input.substring(pos + 1, pos + 6);
        if (checksum.length() < 5) {
            throw new IllegalArgumentException();
        }
        final String prefix = input.substring(0, pos);
        final StringTokenizer tokenizer = new StringTokenizer(prefix, "-", false);
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                names.add(token);
            } else {
                identifier = Integer.parseInt(token);
            }
        }
    }

    public boolean isValid() {
        final int[] occurrences = new int['z' - 'a' + 1];
        for (final String name : names) {
            for (int i = 0; i < name.length(); i++) {
                final char character = name.charAt(i);
                occurrences[character - 'a']++;
            }
        }
        for (int i = 0; i < checksum.length(); i++) {
            final char character = checksum.charAt(i);
            final int count = occurrences[character - 'a'];
            if (!isMax(count, occurrences)) {
                return false;
            }
            occurrences[character - 'a'] = 0;
        }
        return true;
    }

    private boolean isMax(final int max, final int... occurrences) {
        for (final int occurrence: occurrences) {
            if (max < occurrence) {
                return false;
            }
        }
        return true;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String decrypt() {
        final StringBuilder result = new StringBuilder();
        for (final String name : names) {
            for (int i = 0; i < name.length(); i++) {
                char character = name.charAt(i);
                character = (char) ('a' + (character - 'a' + identifier) % ('z' - 'a' + 1));
                result.append(character);
            }
            result.append(' ');
        }
        return result.toString().trim();
    }
}