package de.chrlembeck.aoc2016.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day12 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day12().run();
    }


    @Override
    public Integer part1(final Scanner input) {
        Environment env = new Environment(Operation.readOperations(input));
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public Integer part2(final Scanner input) {
        Environment env = new Environment(Operation.readOperations(input));
        env.setRegister(Environment.Register.C, 1);
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day12.txt";
    }
}