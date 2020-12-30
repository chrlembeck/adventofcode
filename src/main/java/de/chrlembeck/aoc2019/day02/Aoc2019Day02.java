package de.chrlembeck.aoc2019.day02;

import de.chrlembeck.aoc2019.day05.IntcodeProgram;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Aoc2019Day02 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day02().run();
    }

    public static IntcodeProgram readProgram(final Scanner input) {
        input.useDelimiter(",");
        final ArrayList<BigInteger> list = new ArrayList<>();
        while (input.hasNextBigInteger()) {
            list.add(input.nextBigInteger());
        }
        return new IntcodeProgram(list);
    }

    @Override
    public Object part1(final Scanner input) {
        final IntcodeProgram program = readProgram(input);
        return execute(program, 12,2);
    }

    public BigInteger test1(final Scanner scanner) {
        return execute(readProgram(scanner),null,null);
    }

    public BigInteger execute(final IntcodeProgram program, final Integer noun, final Integer verb) {
        if (noun != null) {
            program.set(1, BigInteger.valueOf(noun));
            program.set(2, BigInteger.valueOf(verb));
        }

        int programCounter = 0;
        int opcode = program.get(0).intValueExact();
        while (opcode != 99) {
            if (opcode == 1) {
                // add
                final BigInteger addend1 = program.get(program.get(programCounter+1).intValueExact());
                final BigInteger addend2 = program.get(program.get(programCounter+2).intValueExact());
                final int storePos = program.get(programCounter + 3).intValueExact();
                program.set(storePos, addend1.add(addend2));
                programCounter += 4;
            } else if (opcode == 2) {
                // mult
                final BigInteger factor1 = program.get(program.get(programCounter+1).intValueExact());
                final BigInteger factor2 = program.get(program.get(programCounter+2).intValueExact());
                final int storePos = program.get(programCounter + 3).intValueExact();
                program.set(storePos, factor1.multiply(factor2));
                programCounter += 4;
            } else {
                throw new IllegalStateException();
            }
            opcode = program.get(programCounter).intValueExact();
        }
        return program.get(0);
    }

    @Override
    public Object part2(final Scanner input) {
        final IntcodeProgram program = readProgram(input);
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (execute(program.clone(), noun, verb).compareTo(BigInteger.valueOf(19_690_720)) == 0) {
                    return noun * 100 + verb;
                }
            }
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2019/aoc2019day02.txt";
    }
}
