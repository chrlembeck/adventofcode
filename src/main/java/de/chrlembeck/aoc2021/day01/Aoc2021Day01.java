package de.chrlembeck.aoc2021.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2021Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day01().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int incCounter = 0;
        long prev = input.nextLong();
        while (input.hasNextLong()) {
            final long current = input.nextLong();
            if (current > prev) {
                incCounter++;
            }
            prev = current;
        }
        return incCounter;
    }

    @Override
    public Integer part2(final Scanner input) {
        int incCounter = 0;
        final long[] buffer = new long[4];
        int idx;
        for (idx = 0; idx < 3; idx++) {
            buffer[idx] = input.nextLong();
        }
        while (input.hasNextLong()) {
            buffer[idx] = input.nextLong();
            if (buffer[idx] > buffer[(idx + 1) % 4]) {
                incCounter++;
            }
            idx = (idx + 1) % 4;
        }
        return incCounter;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day01.txt";
    }
}