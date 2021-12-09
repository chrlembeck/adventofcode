package de.chrlembeck.aoc2021.day06;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2021Day06 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day06().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calcFishCount(input, 80);
    }

    @Override
    public Object part2(final Scanner input) {
        return calcFishCount(input, 256);
    }

    public long calcFishCount(final Scanner input, final int days) {
        input.useDelimiter(",");
        final long[] lanternfishs = new long[9];
        while (input.hasNextInt()) {
            lanternfishs[input.nextInt()]++;
        }
        for (int dayIndex = 0; dayIndex < days; dayIndex++) {
            final long newFishes = lanternfishs[0];
            System.arraycopy(lanternfishs, 1, lanternfishs, 0, 8);
            lanternfishs[8] = newFishes;
            lanternfishs[6] += newFishes;
        }
        long sum = 0;
        for (final long fishcount : lanternfishs) {
            sum += fishcount;
        }
        return sum;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day06.txt";
    }
}