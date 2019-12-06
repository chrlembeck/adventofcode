package de.chrlembeck.aoc2019.day05;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class Aoc2019Day05 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day05().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return run(input, BigInteger.ONE);
    }

    @Override
    public Object part2(final Scanner input) {
        return run(input, BigInteger.valueOf(5));
    }

    public BigInteger run(Scanner input, BigInteger value) {
        List<BigInteger> program = Aoc2019Day02.readProgram(input);
        State state = new State(value);
        Instruction instruction = Instruction.of(program, state);
        while (instruction.getOpcode() != 99) {
            instruction.exec(program, state);
            instruction = Instruction.of(program, state);
        }
        return state.getLastOutput();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day05.txt";
    }
}