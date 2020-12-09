package de.chrlembeck.aoc2020.day09;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;

public class Aoc2020Day09 extends AbstractAocBase {

    public static final int PREAMBLE = 25;

    public static void main(final String[] args) {
        new Aoc2020Day09().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final BigInteger[] numbers = input.tokens().map(BigInteger::new).toArray(BigInteger[]::new);
        return findInvalid(numbers);
    }

    private BigInteger findInvalid(final BigInteger[] numbers) {
        nextNumber:
        for (int candidateIdx = PREAMBLE; candidateIdx < numbers.length; candidateIdx++) {
            BigInteger candidate = numbers[candidateIdx];
            for (int leftIdx = candidateIdx - PREAMBLE; candidate != null && leftIdx < candidateIdx - 1; leftIdx++) {
                for (int rightIdx = leftIdx + 1; candidate != null && rightIdx < candidateIdx; rightIdx++) {
                    if (numbers[leftIdx].add(numbers[rightIdx]).compareTo(candidate) == 0) {
                        candidate = null;
                    }
                }
            }
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    @Override
    public Object part2(final Scanner input) {
        final BigInteger[] numbers = input.tokens().map(BigInteger::new).toArray(BigInteger[]::new);
        final BigInteger invalid = findInvalid(numbers);
        for (int start = 0; start < numbers.length - 1; start++) {
            BigInteger sum = numbers[start];
            BigInteger min = sum;
            BigInteger max = sum;
            int idx = start + 1;
            while (idx < numbers.length && (sum = sum.add(numbers[idx])).compareTo(invalid) < 0) {
                min = min.min(numbers[idx]);
                max = max.max(numbers[idx]);
                idx++;
            }
            if (sum.compareTo(invalid) == 0) {
                return min.add(max);
            }
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day09.txt";
    }
}