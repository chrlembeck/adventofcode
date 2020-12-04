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

    public BigInteger run(final Scanner program, final BigInteger... inputValues) {
        return run(Aoc2019Day02.readProgram(program), inputValues);
    }

    public BigInteger run(final List<BigInteger> program, final BigInteger... initialInput) {
        final SingleOutputConsumer outputConsumer = new SingleOutputConsumer();
        final IntcodeComputer computer = new IntcodeComputer(program, initialInput);
        computer.setOutputConsumer(outputConsumer);
        computer.startCalculation();
        try {
            computer.waitForExit();
            return outputConsumer.getOutput();
        } catch(InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day05.txt";
    }
}