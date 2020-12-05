package de.chrlembeck.aoc2019.day09;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoc2019.day05.SingleOutputConsumer;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;

public class Aoc2019Day09 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day09().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return runProgramm(input, BigInteger.ONE);
    }

    @Override
    public Object part2(final Scanner input) {
        return runProgramm(input, BigInteger.TWO);
    }

    private BigInteger runProgramm(final Scanner input, final BigInteger value) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final SingleOutputConsumer outputConsumer = new SingleOutputConsumer();
        final IntcodeComputer computer = new IntcodeComputer(program, value);
        computer.setOutputConsumer(outputConsumer);
        computer.startCalculation();
        computer.waitForExit();
        return outputConsumer.getOutput();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day09.txt";
    }
}