package de.chrlembeck.aoc2023.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2023Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2023Day01().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return input.tokens().mapToLong(line -> calibrate(line, false)).sum();
    }

    @Override
    public Long part2(final Scanner input) {
        return input.tokens().mapToLong(line -> calibrate(line, true)).sum();
    }

    private long calibrate(final String line, boolean allowWords) {
        int idx = 0;
        long number;
        while ((number = checkNumber(line, idx, allowWords)) == -1) {
            idx++;
        }
        final long first = number;
        idx = line.length() - 1;
        while ((number = checkNumber(line, idx, allowWords)) == -1) {
            idx--;
        }
        final long last = number;

        return 10 * first + last;
    }

    private long checkNumber(String line, int pos, boolean allowWords) {
        if (Character.isDigit(line.charAt(pos))) {
            return line.charAt(pos) - '0';
        }
        if (!allowWords) {
            return -1;
        }
        final String value = line.substring(pos);
        if (value.startsWith("one")) {
            return 1;
        }
        if (value.startsWith("two")) {
            return 2;
        }
        if (value.startsWith("three")) {
            return 3;
        }
        if (value.startsWith("four")) {
            return 4;
        }
        if (value.startsWith("five")) {
            return 5;
        }
        if (value.startsWith("six")) {
            return 6;
        }
        if (value.startsWith("seven")) {
            return 7;
        }
        if (value.startsWith("eight")) {
            return 8;
        }
        if (value.startsWith("nine")) {
            return 9;
        }
        return -1;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2023/aoc2023day01.txt";
    }
}