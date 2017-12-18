package de.chrlembeck.aoc2017.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day06 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day06().run();
    }

    @Override
    public String part1(final Scanner input) {
        final Set<List<Integer>> history = new HashSet<>();
        List<Integer> bank = loadBank(input);
        int count = 0;
        while (history.add(bank)) {
            count++;
            bank = allocate(bank);
        }

        return Integer.toString(count);
    }

    @Override
    public String part2(final Scanner input) {
        List<Integer> bank = loadBank(input);
        final Map<List<Integer>, Integer> history = new HashMap<>();
        Integer firstOccurence;
        int count = 0;
        while ((firstOccurence = history.put(bank, count)) == null) {
            count++;
            bank = allocate(bank);
        }
        return Integer.toString(count - firstOccurence);
    }

    private List<Integer> loadBank(final Scanner input) {
        final List<Integer> bank = new ArrayList<>();
        while (input.hasNextInt()) {
            final int value = input.nextInt();
            bank.add(value);
        }
        return bank;
    }

    private List<Integer> allocate(final List<Integer> bank) {
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < bank.size(); i++) {
            final int value = bank.get(i);
            if (value > max) {
                max = value;
                maxIdx = i;
            }
        }
        final List<Integer> newBank = new ArrayList<>(bank);
        newBank.set(maxIdx, 0);
        for (int i = 1; i <= max; i++) {
            final int idx = (maxIdx + i) % newBank.size();
            newBank.set(idx, newBank.get(idx) + 1);
        }

        return newBank;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day06.txt";
    }
}