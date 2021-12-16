package de.chrlembeck.aoc2015.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Arrays;
import java.util.Scanner;

public class Aoc2015Day20 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day20().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int minPresents = input.nextInt();
        final int[] houses = new int[minPresents / 10];
        Arrays.fill(houses, 1);
        for (int elve = 2; elve < houses.length; elve++) {
            for (int idx = elve; idx < houses.length; idx += elve) {
                houses[idx] += elve * 10;
            }
        }
        int idx = 0;
        while (houses[idx] < minPresents) {
            idx++;
        }
        return idx;
    }

    @Override
    public Integer part2(final Scanner input) {
        int minPresents = input.nextInt();
        int[] houses = new int[minPresents / 11];
        Arrays.fill(houses, 1);
        for (int elve = 2; elve < minPresents; elve++) {
            for (int idx = elve, co = 0; co < 50 && idx < houses.length; co++, idx += elve) {
                houses[idx] += elve * 11;
            }
        }
        int idx = 0;
        while (houses[idx] < minPresents) {
            idx++;
        }
        return idx;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day20.txt";
    }
}