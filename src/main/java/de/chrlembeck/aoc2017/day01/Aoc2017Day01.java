package de.chrlembeck.aoc2017.day01;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day01().run();
    }

    public static long calculate(final String input, final int distance) {
        long sum = 0;
        final int length = input.length();
        for (int charIndex = 0; charIndex < length; charIndex++) {
            final char currentChar = input.charAt(charIndex);
            final char otherChar = input.charAt((charIndex + distance) % length);
            if (currentChar == otherChar) {
                sum += currentChar - '0';
            }
        }
        return sum;
    }

    @Override
    public String part1(final Scanner input) {
        return Long.toString(calculate(input.nextLine(), 1));
    }

    @Override
    public String part2(final Scanner input) {
        final String line = input.nextLine();
        return Long.toString(calculate(line, line.length() / 2));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day01.txt";
    }
}