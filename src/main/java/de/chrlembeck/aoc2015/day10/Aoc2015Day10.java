package de.chrlembeck.aoc2015.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2015Day10 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day10().run();
    }

    @Override
    public String part1(final Scanner input) {
        String line = input.nextLine();
        for (int i = 0; i < 40; i++) {
            line = step(line);
        }
        return line;
    }

    public String step(final String input) {
        int counter = 0;
        char last = 0;
        final StringBuilder result = new StringBuilder();
        char next = 0;
        for (int i = 0; i < input.length(); i++) {
            next = input.charAt(i);
            if (next == last || i == 0) {
                counter++;
            } else {
                result.append(counter);
                result.append(last);
                counter = 1;
            }
            last = next;
        }
        if (next == last) {
            // counter++;
            result.append(counter);
            result.append(last);
        }
        return result.toString();
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day10.txt";
    }
}