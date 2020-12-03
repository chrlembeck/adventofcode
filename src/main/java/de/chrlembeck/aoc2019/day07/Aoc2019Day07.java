package de.chrlembeck.aoc2019.day07;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.SingleOutputConsumer;
import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Permutation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Aoc2019Day07 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day07().run();
    }

    @Override
    public Object part1(final Scanner input) {
        Permutation<BigInteger> perm = new Permutation<>(
                new BigInteger[] { BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3), BigInteger.valueOf(4) }, BigInteger[]::new);
        List<BigInteger> program = Collections.unmodifiableList(Aoc2019Day02.readProgram(input));

        BigInteger maxResult = null;
        while (perm.hasNext()) {
            BigInteger[] phaseSettings = perm.next();

            IntcodeComputer a = new IntcodeComputer(new ArrayList<>(program), phaseSettings[0], BigInteger.ZERO);
            IntcodeComputer b = new IntcodeComputer(new ArrayList<>(program), phaseSettings[1]);
            IntcodeComputer c = new IntcodeComputer(new ArrayList<>(program), phaseSettings[2]);
            IntcodeComputer d = new IntcodeComputer(new ArrayList<>(program), phaseSettings[3]);
            IntcodeComputer e = new IntcodeComputer(new ArrayList<>(program) ,phaseSettings[4]);
            a.setOutputConsumer(b.getInputConsumer());
            b.setOutputConsumer(c.getInputConsumer());
            c.setOutputConsumer(d.getInputConsumer());
            d.setOutputConsumer(e.getInputConsumer());
            SingleOutputConsumer cons = new SingleOutputConsumer();
            e.setOutputConsumer(cons);

            a.startCalculation();
            b.startCalculation();
            c.startCalculation();
            d.startCalculation();
            e.startCalculation();

            BigInteger value = cons.getOutput();

            maxResult = maxResult == null ? value : maxResult.max(value);
        }
        return maxResult;
    }

    @Override
    public Object part2(final Scanner input) {
        Permutation<BigInteger> perm = new Permutation<>(
                new BigInteger[] { BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(7), BigInteger.valueOf(8), BigInteger.valueOf(9) }, BigInteger[]::new);
        List<BigInteger> program = Collections.unmodifiableList(Aoc2019Day02.readProgram(input));

        BigInteger maxResult = null;
        while (perm.hasNext()) {
            BigInteger[] phaseSettings = perm.next();

            IntcodeComputer a = new IntcodeComputer(new ArrayList<>(program), phaseSettings[0], BigInteger.ZERO);
            IntcodeComputer b = new IntcodeComputer(new ArrayList<>(program), phaseSettings[1]);
            IntcodeComputer c = new IntcodeComputer(new ArrayList<>(program), phaseSettings[2]);
            IntcodeComputer d = new IntcodeComputer(new ArrayList<>(program), phaseSettings[3]);
            IntcodeComputer e = new IntcodeComputer(new ArrayList<>(program) ,phaseSettings[4]);
            a.setOutputConsumer(b.getInputConsumer());
            b.setOutputConsumer(c.getInputConsumer());
            c.setOutputConsumer(d.getInputConsumer());
            d.setOutputConsumer(e.getInputConsumer());
            e.setOutputConsumer(a.getInputConsumer());


            a.startCalculation();
            b.startCalculation();
            c.startCalculation();
            d.startCalculation();
            e.startCalculation();
            try {
                e.waitForExit();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }

            BigInteger value = e.getLastOutput();

            maxResult = maxResult == null ? value : maxResult.max(value);
        }
        return maxResult;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day07.txt";
    }
}