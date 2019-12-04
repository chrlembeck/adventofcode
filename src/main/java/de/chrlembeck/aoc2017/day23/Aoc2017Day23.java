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
        regB = regB * 1000 + 100000;
        final long regC = regB + 17000;
        long regH = 0;
        for (; regB <= regC; regB += 17) {
            // f = 1; // 8 set f 1 f=1
            // d = 2; // 9 set d 2 d=2

            // do {
            // e = 2; // 10 set e 2 e=2
            // do {
            // if (d * e == b) {// 11 set g d // 12 mul g e // 13 sub g b // 14 jnz g 2 --> if g != 0 GOTO 16
            // f = 0; // 15 set f 0
            //
            // System.out.println(d + " * " + e + " = " + b);
            // }
            // e++;// 16 sub e -1
            // } while (e < b && f == 1);// 17 set g e // 18 sub g b // 19 jnz g -8 --> if g != 0 GOTO 11
            // d++;// 20 sub d -1
            // } while (d < b && f == 1); // 21 set g d // 22 sub g b // 23 jnz g -13 --> if g != 0 GOTO 10
            if (!isPrime(regB)) {
                regH++;
            }
            // if (f == 0) { // 24 jnz f 2 --> if f != 0 GOTO 26
            // h++; // 25 sub h -1
            // }
            // if (b == c) { // 26 set g b// 27 sub g c // 28 jnz g 2 --> if g != 0 GOTO 30
            // break; // 29 jnz 1 3 --> EXIT
            // }
            // b = b + 17; // 30 sub b -17
        } // 31 jnz 1 -23 --> GOTO 8
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