package de.chrlembeck.aoc2020.day25;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;

public class Aoc2020Day25 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day25().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return BigInteger.valueOf(input.nextInt())
                .modPow(BigInteger.valueOf(loop(input.nextInt())), BigInteger.valueOf(20_201_227));
    }

    public int loop(final int key) {
        int value = 1;
        int loop;
        for (loop = 0; value != key; loop++) {
            value = (value * 7) % 20_201_227;
        }
        return loop;
    }

    @Override
    public Object part2(final Scanner input) {
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day25.txt";
    }
}