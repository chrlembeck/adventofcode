package de.chrlembeck.aoc2015.day05;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day05 extends AbstractAocBase {

    final Pattern AT_LEAST_THREE_VOWELS = Pattern.compile(".*([aeiou].*){3}.*");

    final Pattern REPEAT_ONE_BETWEEN = Pattern.compile(".*(\\w)\\w\\1.*");

    final Pattern REPEATING_CHARS = Pattern.compile(".*(\\w)\\1.*");

    final Pattern REPEATING_PAIR = Pattern.compile(".*([a-z]{2}).*\\1.*");

    final Pattern FORBIDDEN_WORDS = Pattern.compile(".*(ab|cd|pq|xy).*");

    public static void main(final String[] args) {
        new Aoc2015Day05().run();
    }

    public int findNiceTests(final Scanner input, final Predicate<String> isNice) {
        int result = 0;
        while (input.hasNextLine()) {
            if (isNice.test(input.nextLine())) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Integer part1(final Scanner input) {
        return findNiceTests(input, this::nice1);
    }

    @Override
    public Integer part2(final Scanner input) {
        return findNiceTests(input, this::nice2);
    }

    public boolean nice1(final String text) {
        return AT_LEAST_THREE_VOWELS.matcher(text).matches() && REPEATING_CHARS.matcher(text).matches()
                && !FORBIDDEN_WORDS.matcher(text).matches();
    }

    public boolean nice2(final String text) {
        return REPEAT_ONE_BETWEEN.matcher(text).matches() && REPEATING_PAIR.matcher(text).matches();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day05.txt";
    }
}