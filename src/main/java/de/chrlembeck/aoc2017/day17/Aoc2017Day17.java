package de.chrlembeck.aoc2017.day17;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2017Day17 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day17().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final int steps = Integer.parseInt(input.nextLine());
        final int[] array = new int[2019];
        int pos = 0;
        int size = 1;
        for (int i = 1; i <= 2017; i++) {
            final int insertAt = 1 + (pos + steps) % size;
            System.arraycopy(array, insertAt, array, insertAt + 1, size - insertAt + 1);
            array[insertAt] = i;
            pos = insertAt;
            size++;
        }
        return array[(pos + 1) % size];
    }

    @Override
    public Integer part2(final Scanner input) {
        final int steps = Integer.parseInt(input.nextLine());
        int pos = 0;
        int result = 0;
        for (int i = 1; i <= 50_000_000; i++) {
            pos = (pos + steps) % i;
            if (pos == 0) {
                result = i;
            }
            pos++;
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day17.txt";
    }
}