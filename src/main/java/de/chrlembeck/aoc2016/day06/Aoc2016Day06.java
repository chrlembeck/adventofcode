package de.chrlembeck.aoc2016.day06;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiPredicate;

public class Aoc2016Day06 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day06().run();
    }

    @Override
    public String part1(final Scanner input) {
        return decode(input, (max, value) -> max < value);
    }

    @Override
    public String part2(final Scanner input) {
        return decode(input, (min, value) -> min == 0 ||(value > 0 && min > value));
    }


    public String decode(final Scanner input, final BiPredicate<Integer, Integer> predicate) {
        int[][] histogram = null;
        while (input.hasNext()) {
            final String line = input.next();
            if (histogram == null) {
                histogram = new int[line.length()]['z' - 'a' + 1];
            }
            for (int i = 0; i < line.length(); i++) {
                histogram[i][line.charAt(i) - 'a']++;
            }
        }
        return Arrays.stream(histogram).map(hist -> findMax(hist, predicate)).reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private char findMax(final int[] histogram, final BiPredicate<Integer, Integer> predicate) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < histogram.length; i++) {
            if (predicate.test(max, histogram[i])) {
                max = histogram[i];
                maxIndex = i;
            }
        }
        return (char) (maxIndex + 'a');
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day06.txt";
    }
}