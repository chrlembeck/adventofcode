package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IntcodeProgram implements Cloneable {

    @SuppressWarnings("PMD.LooseCoupling")
    private ArrayList<BigInteger> memory;

    public IntcodeProgram(final List<BigInteger> list) {
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
        try {
            final IntcodeProgram clone = (IntcodeProgram) super.clone();
            clone.memory = new ArrayList<>(this.memory);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}