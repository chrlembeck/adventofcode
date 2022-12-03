package de.chrlembeck.aoc2022.day03;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Aoc2022Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day03().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return input.useDelimiter("\n").tokens().mapToLong(this::calcPriority).sum();
    }

    @Override
    public Long part2(final Scanner input) {
        long sum = 0;
        while (input.hasNextLine()) {
            Set<Integer> set = rucksackToSet(input.nextLine());
            set.retainAll(rucksackToSet( input.nextLine()));
            set.retainAll(rucksackToSet(input.nextLine()));
            sum += calcPriority((char)set.iterator().next().intValue());
        }
        return sum;
    }

    private long calcPriority(final String line) {
        boolean[] alreadyContained = new boolean[52];
        for (int i = 0; i < line.length() / 2; i++) {
            alreadyContained[calcPriority(line.charAt(i)) - 1] = true;
        }
        for (int i = line.length() / 2; i < line.length(); i++) {
            int prio = calcPriority(line.charAt(i));
            if (alreadyContained[prio - 1]) {
                return prio;
            }
        }
        throw new IllegalArgumentException();
    }

    private int calcPriority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        }
        if (item >= 'A' && item <= 'Z') {
            return item - 'A' + 27;
        }
        throw new IllegalArgumentException();
    }

    private Set<Integer> rucksackToSet(String rucksack) {
        return rucksack.chars().boxed().collect(Collectors.toSet());
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day03.txt";
    }
}