package de.chrlembeck.aoc2017.day23;

import de.chrlembeck.aoc2017.day18.Aoc2017Day18;
import de.chrlembeck.aoc2017.day18.Instruction;
import de.chrlembeck.aoc2017.day18.SetInstruction;
import de.chrlembeck.aoc2017.day18.State;
import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.List;
import java.util.Scanner;

public class Aoc2017Day23 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day23().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final List<Instruction> program = Aoc2017Day18.loadProgram(input, 1);
        final State state = new State(0);
        while (state.getPosition() < program.size()) {
            final int pos = state.getPosition();
            final Instruction instruction = program.get(pos);
            instruction.execute(state);
            state.incPos();
        }
        return state.getMulInstructionCount();
    }

    @Override
    public Long part2(final Scanner input) {
        final List<Instruction> program = Aoc2017Day18.loadProgram(input, 1);
        long regB = ((SetInstruction) program.get(0)).getValue().intValue(null).longValueExact();
        regB = regB * 1_000 + 100_000;
        final long regC = regB + 17_000;
        long regH = 0;
        for (; regB <= regC; regB += 17) {
            if (!isPrime(regB)) {
                regH++;
            }
        }
        return regH;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day23.txt";
    }

    public static boolean isPrime(final long value) {
        if (value % 2 == 0) {
            return false;
        }
        long factor = 3;
        while (factor * factor <= value) {
            if (value % factor == 0) {
                return false;
            }
            factor += 2;
        }
        return true;
    }
}