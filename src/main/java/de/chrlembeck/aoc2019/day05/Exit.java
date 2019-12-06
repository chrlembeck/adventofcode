package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Exit extends Instruction {

    Exit(List<BigInteger> program, State state) {
        super(program, state);
    }

    @Override
    public void exec(List<BigInteger> program, State state) {
        throw new RuntimeException("programm already halted.");
    }
}
