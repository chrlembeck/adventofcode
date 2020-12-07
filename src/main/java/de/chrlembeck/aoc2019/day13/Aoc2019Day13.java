package de.chrlembeck.aoc2019.day13;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoc2019.day11.Position;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;

public class Aoc2019Day13 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day13().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return run(input, BigInteger.ONE, ArcadeOutputConsumer::countBlocks);
    }

    @Override
    public Object part2(final Scanner input) {
        return run(input, BigInteger.TWO, ArcadeOutputConsumer::getScore);
    }

    private Number run(final Scanner input, final BigInteger mode, final Function<ArcadeOutputConsumer, Number> result) {
        final Map<Position, Tile> map = new TreeMap<>();
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        program.set(0, mode);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final ArcadeOutputConsumer consumer = new ArcadeOutputConsumer(map, computer.getInputConsumer());
        computer.setOutputConsumer(consumer);
        computer.startCalculation();
        computer.waitForExit();
        return result.apply(consumer);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day13.txt";
    }
}