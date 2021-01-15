package de.chrlembeck.aoc2016.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Room {

    private String checksum;

    private List<String> names = new ArrayList<>();

    private int id;

    public Room(final String input) {
        final int pos = input.indexOf('[');
        checksum = input.substring(pos + 1, pos + 6);
        final String prefix = input.substring(0, pos);
        if (checksum.length() < 5) {
            throw new IllegalArgumentException();
        }
        StringTokenizer tokenizer = new StringTokenizer(prefix, "-", false);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) {
                names.add(token);
            } else {
                id = Integer.parseInt(token);
            }
        }
    }

    public boolean isValid() {
        int[] occurrences = new int['z' - 'a' + 1];
        for (String name : names) {
            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                occurrences[ch - 'a']++;
            }
        }
        for (int i = 0; i < checksum.length(); i++) {
            char ch = checksum.charAt(i);
            int count = occurrences[ch - 'a'];
            if (!isMax(count, occurrences)) {
                return false;
            }
            occurrences[ch - 'a'] = 0;
        }
        return true;
    }

    private boolean isMax(final int max, final int[] occurrences) {
        for (int i = 0; i < occurrences.length; i++) {
            if (max < occurrences[i]) {
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public String decrypt() {
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                ch = (char) ('a' + (ch - 'a' + id) % ('z' - 'a' + 1));
                sb.append(ch);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}