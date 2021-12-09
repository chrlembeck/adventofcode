package de.chrlembeck.aoc2021.day07;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

public class Aoc2021Day07 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day07().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final int[] numbers = input.useDelimiter(",").tokens().mapToInt(Integer::parseInt).toArray();
        final IntSummaryStatistics statistics = Arrays.stream(numbers).summaryStatistics();
        int best = Integer.MAX_VALUE;
        for (int i = statistics.getMin(); i <= statistics.getMax(); i++) {
            int fuel = 0;
            for (final int number:numbers) {
                fuel += Math.abs(number - i);
            }
            if (fuel < best) {
                best = fuel;
            }
        }

        return best;
    }

    @Override
    public Long part2(final Scanner input) {
        final int[] numbers = input.useDelimiter(",").tokens().mapToInt(Integer::parseInt).toArray();
        final IntSummaryStatistics statistics = Arrays.stream(numbers).summaryStatistics();
        long best = Long.MAX_VALUE;
        for (int i = statistics.getMin(); i <= statistics.getMax(); i++) {
            int fuel = 0;
            for (final int number:numbers) {
                fuel += (Math.abs(number - i) * (Math.abs(number - i) + 1)) /2;
            }
            if (fuel < best) {
                best = fuel;
            }
        }
        return best;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day07.txt";
    }
}