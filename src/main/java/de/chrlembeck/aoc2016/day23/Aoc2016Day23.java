package de.chrlembeck.aoc2016.day23;

import de.chrlembeck.aoc2016.day12.Environment;
import de.chrlembeck.aoc2016.day12.Operation;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2016Day23 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day23().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        Environment env = new Environment(Operation.readOperations(input));
        env.setRegister(Environment.Register.A, 7);
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public Integer part2(final Scanner input) {
        Environment env = new Environment(Operation.readOperations(input));
        env.setRegister(Environment.Register.A, 12);
        env.run();
        return env.readRegister(Environment.Register.A);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day23.txt";
    }
}