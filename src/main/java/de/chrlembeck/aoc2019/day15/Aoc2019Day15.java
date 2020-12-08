package de.chrlembeck.aoc2019.day15;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2019Day15 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day15().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final TargetFinder finder = new TargetFinder(computer.getInputConsumer());
        computer.setOutputConsumer(finder);
        computer.startCalculation();
        finder.readFully();
        return finder.getStepsToOxygenModule();
    }

    @Override
    public Object part2(final Scanner input) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final TargetFinder finder = new TargetFinder(computer.getInputConsumer());
        computer.setOutputConsumer(finder);
        computer.startCalculation();
        finder.readFully();
        return finder.getOxygenFillTime();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day15.txt";
    }
}