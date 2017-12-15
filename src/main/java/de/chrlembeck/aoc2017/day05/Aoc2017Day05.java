package de.chrlembeck.aoc2017.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntFunction;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day05 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day05().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input, offset -> offset + 1);
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input, offset -> offset >= 3 ? offset - 1 : offset + 1);
    }

    public String calc(final Scanner input, final IntFunction<Integer> nextOffsetCalculator) {
        final List<Integer> offsets = new ArrayList<>();
        while (input.hasNextInt()) {
            offsets.add(input.nextInt());
        }
        int pos = 0;
        int count = 0;
        while (pos >= 0 && pos < offsets.size()) {
            final int offset = offsets.get(pos);
            final int newOffset = nextOffsetCalculator.apply(offset);
            offsets.set(pos, newOffset);
            pos += offset;
            count++;
        }
        return Integer.toString(count);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/day05.txt";
    }
}