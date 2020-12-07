package de.chrlembeck.aoc2020.day05;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2020Day05 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day05().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return input.tokens()
                .mapToInt(this::convertRow)
                .max()
                .getAsInt();
    }

    private int convertRow(final String row) {
        return Integer.parseInt(row
                .replaceAll("F", "0")
                .replaceAll("B", "1")
                .replaceAll("L", "0")
                .replaceAll("R", "1"), 2);
    }

    @Override
    public Object part2(final Scanner input) {
        final boolean[] used = new boolean[1024];
        input.tokens()
                .mapToInt(this::convertRow)
                .forEach(seatId -> used[seatId] = true);

        for (int seatId = 1; seatId < used.length; seatId++) {
            if (used[seatId - 1] && !used[seatId]) {
                return seatId;
            }
        }
        return -1;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day05.txt";
    }
}