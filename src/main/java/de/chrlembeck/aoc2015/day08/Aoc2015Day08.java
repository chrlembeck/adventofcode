package de.chrlembeck.aoc2015.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.Scanner;

public class Aoc2015Day08 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day08().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int counter = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            boolean escaping = false;
            counter += 2;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '\\' && !escaping) {
                    counter++;
                    escaping = true;
                } else {
                    if (escaping) {
                        if (line.charAt(i) == 'x') {
                            counter += 2;
                        }
                        escaping = false;
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public Integer part2(final Scanner input) {
        int counter = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            counter += 2;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '\\' || line.charAt(i) == '\"') {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day08.txt";
    }
}