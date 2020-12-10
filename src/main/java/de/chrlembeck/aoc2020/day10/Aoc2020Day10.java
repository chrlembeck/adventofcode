package de.chrlembeck.aoc2020.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Aoc2020Day10 extends AbstractAocBase {

    private BigInteger[] cache = new BigInteger[100];

    public static void main(final String[] args) {
        new Aoc2020Day10().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final long[] adapters = LongStream.concat(LongStream.of(0), input.tokens().mapToLong(Long::parseLong)).sorted().toArray();
        int oneStepCounter = 0;
        int threeeStepCounter = 1;
        for (int i = 1; i < adapters.length; i++) {
            if (adapters[i] - adapters[i - 1] == 1) {
                oneStepCounter++;
            } else if (adapters[i] - adapters[i - 1] == 3) {
                threeeStepCounter++;
            }
        }
        return oneStepCounter * threeeStepCounter;
    }

    @Override
    public Object part2(final Scanner input) {
        final long[] adapters = LongStream.concat(LongStream.of(0), input.tokens().mapToLong(Long::parseLong)).sorted().toArray();
        BigInteger mutations = BigInteger.ONE;
        int oneStepCounter = 0;
        for (int i = 1; i < adapters.length; i++) {
            if (adapters[i] - adapters[i - 1] == 1) {
                oneStepCounter++;
            } else if (adapters[i] - adapters[i - 1] == 3) {
                mutations = mutations.multiply(solutions(oneStepCounter + 1));
                oneStepCounter = 0;
            }
        }
        mutations = mutations.multiply(solutions(oneStepCounter + 1));
        return mutations;
    }

    public BigInteger solutions(final int length) {
        if (length <= 2) {
            return BigInteger.ONE;
        }
        if (length == 3) {
            return BigInteger.TWO;
        }
        BigInteger cached = cache[length];
        if (cached == null) {
            cached = solutions(length - 3).add(solutions(length - 2)).add(solutions(length - 1));
            cache[length] = cached;
        }
        return cached;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day10.txt";
    }
}