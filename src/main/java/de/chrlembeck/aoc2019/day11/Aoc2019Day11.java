package de.chrlembeck.aoc2019.day11;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import de.chrlembeck.aoc2019.day05.IntcodeComputer;
import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.Function;

public class Aoc2019Day11 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day11().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return run(input, Area.BLACK, Area::getPaintedFieldCount);
    }

    @Override
    public Object part2(final Scanner input) {
        return run(input, Area.WHITE, Area::getImage);
    }

    private Object run(final Scanner input, final int initialColor, final Function<Area, Object> resultFunction) {
        final IntcodeProgram program = Aoc2019Day02.readProgram(input);
        final Area area = new Area();
        area.paint(initialColor);
        final IntcodeComputer computer = new IntcodeComputer(program, BigInteger.valueOf(area.getCurrentColor()));
        computer.setOutputConsumer(new PaintingOutputConsumer(area, computer.getInputConsumer()));
        computer.startCalculation();
        computer.waitForExit();
        return resultFunction.apply(area);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day11.txt";
    }
}