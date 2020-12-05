package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.ArrayList;

public class IntcodeProgram {

    private final ArrayList<BigInteger> memory;

    public IntcodeProgram(final ArrayList<BigInteger> list) {
        memory = new ArrayList<>(list);
    }

    public void set(final int index, final BigInteger value) {
        if (index >= memory.size()) {
            memory.ensureCapacity(index + 1);
            while(memory.size() <= index) {
                memory.add(BigInteger.ZERO);
            }
        }
        memory.set(index, value);
    }

    public BigInteger get(final int index) {
        if (index >= memory.size()) {
            return BigInteger.ZERO;
        } else {
            return memory.get(index);
        }
    }

    @Override
    public IntcodeProgram clone() {
        return new IntcodeProgram(memory);
    }
}