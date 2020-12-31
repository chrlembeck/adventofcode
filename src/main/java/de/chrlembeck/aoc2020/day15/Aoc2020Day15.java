package de.chrlembeck.aoc2020.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2020Day15 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day15().run();
    }

    @Override
    public Object part1(final Scanner input) {
        int[] startNums = input.useDelimiter(",").tokens().mapToInt(Integer::parseInt).toArray();
        return playGame(2020, startNums);
    }

    @Override
    public Object part2(final Scanner input) {
        int[] startNums = input.useDelimiter(",").tokens().mapToInt(Integer::parseInt).toArray();
        return playGame(30_000_000, startNums);
    }

    public int playGame(final int rounds, final int[] startingNumbers) {
        int lastNumber = startingNumbers[startingNumbers.length - 1];
        final int[] lastUsages = new int[rounds];
        for (int i = 0; i < startingNumbers.length; i++) {
            lastUsages[startingNumbers[i]] = i+1;
        }
        for (int round = startingNumbers.length; round < rounds; round++) {
            final int lastUsage = lastUsages[lastNumber];
            lastUsages[lastNumber] = round;
            lastNumber = lastUsage == 0 ? 0 : round - lastUsage;
        }
        return lastNumber;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day15.txt";
    }
}