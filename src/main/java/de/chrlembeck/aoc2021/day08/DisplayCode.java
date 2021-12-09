package de.chrlembeck.aoc2021.day08;

import java.util.Arrays;
import java.util.regex.Matcher;

public class DisplayCode {

    private final String[] orderedCodes = new String[10];

    private final String[] output = new String[4];

    public DisplayCode(final Matcher matcher) {
        final String[] samples = new String[10];

        for (int i = 0; i < 10; i++) {
            samples[i] = matcher.group(i+1);
        }
        for (int i = 0; i < 4; i++) {
            output[i] = matcher.group(i+11);
        }

        orderedCodes[1] = Arrays.stream(samples).filter(sa -> sa.length() == 2).findAny().orElseThrow();
        orderedCodes[4] = Arrays.stream(samples).filter(sa -> sa.length() == 4).findAny().orElseThrow();
        orderedCodes[7] = Arrays.stream(samples).filter(sa -> sa.length() == 3).findAny().orElseThrow();
        orderedCodes[8] = Arrays.stream(samples).filter(sa -> sa.length() == 7).findAny().orElseThrow();

        orderedCodes[3] = Arrays.stream(samples).filter(sa -> sa.length() == 5 && containsAll(sa, orderedCodes[1])).findAny().orElseThrow();
        orderedCodes[9] = Arrays.stream(samples).filter(sa -> sa.length() == 6 && containsAll(sa, orderedCodes[3])).findAny().orElseThrow();
        orderedCodes[0] = Arrays.stream(samples).filter(sa -> sa.length() == 6 && containsAll(sa, orderedCodes[7]) && !containsAll(sa, orderedCodes[9])).findAny().orElseThrow();
        orderedCodes[6] = Arrays.stream(samples).filter(sa -> sa.length() == 6 && !containsAll(sa, orderedCodes[0]) && !containsAll(sa, orderedCodes[9])).findAny().orElseThrow();

        final char bReplacemnt = (char)orderedCodes[9].chars().filter(i -> orderedCodes[3].indexOf(i) == -1).findAny().orElseThrow();
        final char eReplacement = (char)orderedCodes[8].chars().filter(i -> orderedCodes[9].indexOf(i) == -1).findAny().orElseThrow();

        orderedCodes[2] = Arrays.stream(samples).filter(sa -> sa.length() == 5 && !containsAll(sa, orderedCodes[3]) && sa.indexOf(eReplacement) != -1).findAny().orElseThrow();
        orderedCodes[5] = Arrays.stream(samples).filter(sa -> sa.length() == 5 && !containsAll(sa, orderedCodes[3]) && sa.indexOf(bReplacemnt) != -1).findAny().orElseThrow();
    }

    public int getOutputAsInt() {
        int sum = 0;
        for (final String out: output) {
            sum = sum * 10 + decode(out);
        }
        return sum;
    }

    private int decode(final String code) {
        for (int i = 0; i < 10; i++) {
            if (sameCharacters(code, orderedCodes[i])) {
                return i;
            }
        }
        throw new RuntimeException("code not found: " + code);
    }

    public static boolean sameCharacters(final String source, final String destination) {
        return source.length() == destination.length() && containsAll(source, destination);
    }

    public static boolean containsAll(final String text, final String characters) {
        for (int i = 0; i < characters.length(); i++) {
            if (text.indexOf(characters.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}
