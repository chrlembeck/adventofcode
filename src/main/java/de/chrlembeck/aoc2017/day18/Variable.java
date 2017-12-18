package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class Variable implements Token {

    private String name;

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public BigInteger intValue(final State state) {
        return state.getValue(name);
    }

    @Override
    public String toString() {
        return name;
    }
}