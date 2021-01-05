package de.chrlembeck.aoc2020.day17;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2020Day17 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day17().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, 3);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, 4);
    }

    private int calc(final Scanner input, final int dimension) {
        NCube cube = new NCube(dimension, input);
        for (int i = 1; i <= 6; i++) {
            cube = cube.next();
        }
        return cube.countActive();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day17.txt";
    }
}