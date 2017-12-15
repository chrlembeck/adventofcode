package de.chrlembeck.aoc2015.day02;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day02 extends AbstractAocBase {

    Pattern regex = Pattern.compile("(\\d+)x(\\d+)x(\\d+)");

    public static void main(final String[] args) {
        new Aoc2015Day02().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input)[0];
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input)[1];
    }

    public int[] calc(final Scanner input) {
        int paper = 0;
        int ribbon = 0;
        while (input.hasNextLine()) {
            final Matcher matcher = matchRegex(regex, input.nextLine());
            final int[] lengths = new int[3];
            for (int i = 0; i < 3; i++) {
                lengths[i] = Integer.parseInt(matcher.group(i + 1));
            }
            Arrays.sort(lengths);
            paper += 2 * (lengths[0] * lengths[1] + lengths[0] * lengths[2] + lengths[1] * lengths[2])
                    + lengths[0] * lengths[1];
            ribbon += 2 * (lengths[0] + lengths[1]) + lengths[0] * lengths[1] * lengths[2];
        }
        return new int[] { paper, ribbon };
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day02.txt";
    }
}