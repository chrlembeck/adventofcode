package de.chrlembeck.aoc2019.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc2019Day02 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2019Day02().run();
    }

    private ArrayList<BigInteger> readProgram(final Scanner input) {
        input.useDelimiter(",");
        final ArrayList<BigInteger> list = new ArrayList<>();
        while (input.hasNextBigInteger()) {
            list.add(input.nextBigInteger());
        }
        return list;
    }

    @Override
    public Object part1(final Scanner input) {
        final List<BigInteger> list = readProgram(input);
        return execute(list, 12,2);
    }

    public BigInteger test1(final Scanner scanner) {
        return execute(readProgram(scanner),null,null);
    }

    public BigInteger execute(final List<BigInteger> list, final Integer noun, final Integer verb) {
        if (noun != null) {
            list.set(1, BigInteger.valueOf(noun));
            list.set(2, BigInteger.valueOf(verb));
        }

        int programCounter = 0;
        int opcode = list.get(0).intValueExact();
        while (opcode != 99) {
            if (opcode == 1) {
                // add
                final BigInteger addend1 = list.get(list.get(programCounter+1).intValueExact());
                final BigInteger addend2 = list.get(list.get(programCounter+2).intValueExact());
                final int storePos = list.get(programCounter + 3).intValueExact();
                list.set(storePos, addend1.add(addend2));
                programCounter += 4;
            } else if (opcode == 2) {
                // mult
                final BigInteger factor1 = list.get(list.get(programCounter+1).intValueExact());
                final BigInteger factor2 = list.get(list.get(programCounter+2).intValueExact());
                final int storePos = list.get(programCounter + 3).intValueExact();
                list.set(storePos, factor1.multiply(factor2));
                programCounter += 4;
            } else {
                throw new IllegalStateException();
            }
            opcode = list.get(programCounter).intValueExact();
        }
        return list.get(0);
    }

    @Override
    public Object part2(final Scanner input) {
        final ArrayList<BigInteger> list = readProgram(input);

        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (execute((ArrayList<BigInteger>)list.clone(), noun, verb).compareTo(BigInteger.valueOf(19690720)) == 0) {
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
