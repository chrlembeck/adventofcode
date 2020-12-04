package de.chrlembeck.aoc2019.day07;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoc2019.day05.SingleOutputConsumer;
import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Permutation;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Aoc2019Day07 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day07().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Permutation<BigInteger> perm = new Permutation<>(
                new BigInteger[] { BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3), BigInteger.valueOf(4) }, BigInteger[]::new);
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);

        BigInteger maxResult = null;
        while (perm.hasNext()) {
            final BigInteger[] phaseSettings = perm.next();
            final IntcodeComputer[] computers = createComputers(program, phaseSettings, BigInteger.ZERO);
            final SingleOutputConsumer cons = new SingleOutputConsumer();
            computers[4].setOutputConsumer(cons);
            Arrays.stream(computers).forEach(IntcodeComputer::startCalculation);

            final BigInteger value = cons.getOutput();
            maxResult = maxResult == null ? value : maxResult.max(value);
        }
        return maxResult;
    }

    @Override
    public Object part2(final Scanner input) {
        final Permutation<BigInteger> perm = new Permutation<>(
                new BigInteger[] { BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(7), BigInteger.valueOf(8), BigInteger.valueOf(9) },
                BigInteger[]::new);
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);

        BigInteger maxResult = null;
        while (perm.hasNext()) {
            final BigInteger[] phaseSettings = perm.next();
            final IntcodeComputer[] computers = createComputers(program, phaseSettings, BigInteger.ZERO);
            computers[4].setOutputConsumer(computers[0].getInputConsumer());
            Arrays.stream(computers).forEach(IntcodeComputer::startCalculation);

            computers[4].waitForExit();

            final BigInteger value = computers[4].getLastOutput();
            maxResult = maxResult == null ? value : maxResult.max(value);
        }
        return maxResult;
    }

    private IntcodeComputer[] createComputers(final IntcodeProgram program, final BigInteger[] phaseSettings, final BigInteger initialInput) {
        final IntcodeComputer[] computers = new IntcodeComputer[5];
        for (int computerIdx = 0; computerIdx < computers.length; computerIdx++) {
            computers[computerIdx] = new IntcodeComputer(program.clone(), phaseSettings[computerIdx]);
        }
        for (int computerIdx = 0; computerIdx < computers.length - 1; computerIdx++) {
            computers[computerIdx].setOutputConsumer(computers[computerIdx + 1].getInputConsumer());
        }
        computers[0].getInputConsumer().accept(initialInput);
        return computers;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day07.txt";
    }
}