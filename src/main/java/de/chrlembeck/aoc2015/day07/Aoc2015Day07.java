package de.chrlembeck.aoc2015.day07;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day07 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day07().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final Map<String, Gate> program = createProgram(input);
        return program.get("a").execute(program);
    }

    private Map<String, Gate> createProgram(final Scanner input) {
        final Map<String, Gate> program = new TreeMap<>();
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            if (DefineGate.accepts(program, line)) {
                continue;
            }
            if (LogicGate.accepts(program, line)) {
                continue;
            }
            if (NotGate.accepts(program, line)) {
                continue;
            }
            if (ShiftGate.accepts(program, line)) {
                continue;
            }
            throw new IllegalArgumentException("unkown line " + line);
        }
        return program;
    }

    @Override
    public Integer part2(final Scanner input) {
        final Map<String, Gate> program = createProgram(input);
        final Map<String, Gate> prog2 = new TreeMap<>(program);
        final int aValue = prog2.get("a").execute(prog2);
        program.put("b", new Constant(aValue));

        return program.get("a").execute(program);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day07.txt";
    }

    public static Gate parse(final String value) {
        try {
            return new Constant(Integer.parseInt(value));
        } catch (final NumberFormatException nfe) {
            return new DefineGate(value);
        }
    }
}