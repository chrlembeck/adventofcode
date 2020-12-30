package de.chrlembeck.aoc2019.day17;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;

public class Aoc2019Day17 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day17().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final ScaffoldScanner scanner = new ScaffoldScanner();
        computer.setOutputConsumer(scanner);
        computer.startCalculation();
        computer.waitForExit();

        return scanner.getAlignmentParameters();
    }

    @Override
    public Object part2(final Scanner input) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final IntcodeComputer computer = new IntcodeComputer(program);
        final ScaffoldScanner scanner = new ScaffoldScanner();
        computer.setOutputConsumer(scanner);
        computer.startCalculation();
        computer.waitForExit();

        System.out.println(scanner.getScaffold());
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day17.txt";
    }
}