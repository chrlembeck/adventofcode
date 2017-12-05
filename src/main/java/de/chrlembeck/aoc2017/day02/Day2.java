package de.chrlembeck.aoc2017.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import de.chrlembeck.aoc2017.common.AbstractAocBase;

public class Day2 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Day2().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input, this::calcValue1);
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input, this::calcValue2);
    }

    private String calc(final Scanner input, final Function<Scanner, Integer> lineFunction) {
        int sum = 0;
        while (input.hasNextLine()) {
            try (final Scanner lineScanner = new Scanner(input.nextLine())) {
                sum += lineFunction.apply(lineScanner);
            }
        }
        return Integer.toString(sum);
    }

    public int calcValue1(final Scanner lineScanner) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        while (lineScanner.hasNextInt()) {
            final int value = lineScanner.nextInt();
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        return max - min;
    }

    public int calcValue2(final Scanner lineScanner) {
        final List<Integer> values = new ArrayList<>();
        while (lineScanner.hasNextInt()) {
            final int value = lineScanner.nextInt();
            final int divisionResult = divisionResult(values, value);
            if (divisionResult > 0) {
                return divisionResult;
            } else {
                values.add(value);
            }
        }
        return 0;
    }

    private int divisionResult(final List<Integer> values, final int value) {
        for (final int v2 : values) {
            if (v2 % value == 0) {
                return v2 / value;
            }
            if (value % v2 == 0) {
                return value / v2;
            }
        }
        return 0;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/day02.txt";
    }
}