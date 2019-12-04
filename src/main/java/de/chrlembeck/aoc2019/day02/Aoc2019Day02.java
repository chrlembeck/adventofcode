package de.chrlembeck.aoc2019.day02;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc2019Day02 extends AbstractAocBase {

    public static void main(String[] args) {
        new Aoc2019Day02().run();
    }

    private ArrayList<BigInteger> readProgram(Scanner input) {
        input.useDelimiter(",");
        ArrayList<BigInteger> list = new ArrayList<>();
        while (input.hasNextBigInteger()) {
            list.add(input.nextBigInteger());
        }
        return list;
    }

    @Override
    public Object part1(Scanner input) {
        List<BigInteger> list = readProgram(input);
        return execute(list, 12,2);
    }

    public BigInteger test1(Scanner scanner) {
        return execute(readProgram(scanner),null,null);
    }

    public BigInteger execute(List<BigInteger> list, Integer noun, Integer verb) {
        if (noun != null) {
            list.set(1, BigInteger.valueOf(noun));
            list.set(2, BigInteger.valueOf(verb));
        }

        int pc = 0;
        int opcode = list.get(0).intValueExact();
        while (opcode != 99) {
            if (opcode == 1) {
                // add
                BigInteger a = list.get(list.get(pc+1).intValueExact());
                BigInteger b = list.get(list.get(pc+2).intValueExact());
                int storePos = list.get(pc+3).intValueExact();
                list.set(storePos, a.add(b));
                pc+=4;
            } else if (opcode == 2) {
                // mult
                BigInteger a = list.get(list.get(pc+1).intValueExact());
                BigInteger b = list.get(list.get(pc+2).intValueExact());
                int storePos = list.get(pc+3).intValueExact();
                list.set(storePos, a.multiply(b));
                pc+=4;
            } else {
                throw new IllegalStateException();
            }
            opcode = list.get(pc).intValueExact();
        }
        return list.get(0);
    }

    @Override
    public Object part2(Scanner input) {
        ArrayList<BigInteger> list = readProgram(input);

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
    public String getInputLocation(int part) {
        return "/input/aoc2019/aoc2019day02.txt";
    }
}
