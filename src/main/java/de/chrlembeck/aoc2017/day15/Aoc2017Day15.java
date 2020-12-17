package de.chrlembeck.aoc2017.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2017Day15 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("Generator [AB] starts with (\\d+)");

    public static void main(final String[] args) {
        new Aoc2017Day15().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        Matcher matcher = REGEX.matcher(input.nextLine());
        matcher.matches();
        final int startA = Integer.parseInt(matcher.group(1));
        matcher = REGEX.matcher(input.nextLine());
        matcher.matches();
        final int startB = Integer.parseInt(matcher.group(1));
        long aValue = startA;
        long bValue = startB;
        int result = 0;
        for (int i = 0; i < 40_000_000; i++) {
            aValue = (aValue * 16_807) % 2_147_483_647;
            bValue = (bValue * 48_271) % 2_147_483_647;

            if (((short) aValue) == ((short) bValue)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Integer part2(final Scanner input) {
        Matcher matcher = REGEX.matcher(input.nextLine());
        matcher.matches();
        final int startA = Integer.parseInt(matcher.group(1));
        matcher = REGEX.matcher(input.nextLine());
        matcher.matches();
        final int startB = Integer.parseInt(matcher.group(1));
        long aValue = startA;
        long bValue = startB;
        int result = 0;
        for (int i = 0; i < 5_000_000; i++) {
            do {
                aValue = (aValue * 16_807) % 2_147_483_647;
            } while (aValue % 4 != 0);
            do {
                bValue = (bValue * 48_271) % 2_147_483_647;
            } while (bValue % 8 != 0);

            if (((short) aValue) == ((short) bValue)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day15.txt";
    }
}