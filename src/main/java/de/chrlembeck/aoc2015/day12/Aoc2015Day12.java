package de.chrlembeck.aoc2015.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2015Day12 extends AbstractAocBase {

    enum State {
        unkown,
        identifier,
        number
    }

    public static void main(final String[] args) {
        new Aoc2015Day12().run();
    }

    @Override
    public Long part1(final Scanner input) {
        String line = input.nextLine();
        State state = State.unkown;
        long sum = 0;
        for (int i = 0; i < line.length(); i++) {
            char next = line.charAt(i);
            if (Character.isDigit(next) || next == '-') {
                // number
                if (next == '-') {
                    i++;
                }
                long number = line.charAt(i) - '0';

                while (i < line.length() - 1 && Character.isDigit(line.charAt(i + 1))) {
                    number = number * 10 + (line.charAt(i + 1) - '0');
                    i++;
                }
                sum += (next == '-') ? -number : number;
            } else if (next == '\"') {
                // string
                i++;
                while (i < line.length() && line.charAt(i) != '\"') {
                    i++;
                }
            }
        }

        return sum;
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day12.txt";
    }
}