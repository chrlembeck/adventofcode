package de.chrlembeck.aoc2015.day01;

import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day01().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final String line = input.nextLine();
        int floor = 0;
        for (int i = 0; i < line.length(); i++) {
            floor = floor + (line.charAt(i) == '(' ? 1 : -1);
        }
        return floor;
    }

    @Override
    public Integer part2(final Scanner input) {
        final String line = input.nextLine();
        int floor = 0;
        for (int i = 0; i < line.length(); i++) {
            floor = floor + (line.charAt(i) == '(' ? 1 : -1);
            if (floor == -1) {
                return i + 1;
            }
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day01.txt";
    }
}