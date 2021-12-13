package de.chrlembeck.aoc2016.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day16 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day16().run();
    }

    @Override
    public String part1(final Scanner scanner) {
        return new Bits(scanner.nextLine(), 272).checksum();
    }

    @Override
    public String part2(final Scanner scanner) {
        return new Bits(scanner.nextLine(), 35651584).checksum();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day16.txt";
    }
}