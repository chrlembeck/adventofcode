package de.chrlembeck.aoc2017.day15;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day15 extends AbstractAocBase {

    Pattern regex = Pattern.compile("Generator [AB] starts with (\\d+)");

    public static void main(final String[] args) {
        new Aoc2017Day15().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        Matcher matcher = regex.matcher(input.nextLine());
        matcher.matches();
        final int startA = Integer.parseInt(matcher.group(1));
        matcher = regex.matcher(input.nextLine());
        matcher.matches();
        final int startB = Integer.parseInt(matcher.group(1));
        long aValue = startA;
        long bValue = startB;
        int result = 0;
        for (int i = 0; i < 40000000; i++) {
            aValue = (aValue * 16807) % 2147483647;
            bValue = (bValue * 48271) % 2147483647;

            if (((short) aValue) == ((short) bValue)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Integer part2(final Scanner input) {
        Matcher matcher = regex.matcher(input.nextLine());
        matcher.matches();
        final int startA = Integer.parseInt(matcher.group(1));
        matcher = regex.matcher(input.nextLine());
        matcher.matches();
        final int startB = Integer.parseInt(matcher.group(1));
        long aValue = startA;
        long bValue = startB;
        int result = 0;
        for (int i = 0; i < 5000000; i++) {
            do {
                aValue = (aValue * 16807) % 2147483647;
            } while (aValue % 4 != 0);
            do {
                bValue = (bValue * 48271) % 2147483647;
            } while (bValue % 8 != 0);

            if (((short) aValue) == ((short) bValue)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/day15.txt";
    }
}