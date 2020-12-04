package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public class Exit extends AbstractInstruction {

    Exit(final List<BigInteger> program, final State state) {
        super(program, state);
    }

    @Override
    public void exec(final List<BigInteger> program, final State state) {
        throw new RuntimeException("programm already halted.");
    }
}