package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;

public class State {

    private final BigInteger input;

    private int progCount = 0;

    private BigInteger lastOutput;

    public State(BigInteger inputValue) {
        this.input = inputValue;
    }

    public void inc(int steps) {
        progCount += steps;
    }

    public int getProgCount() {
        return progCount;
    }

    public BigInteger getInput() {
        return input;
    }

    public void output(BigInteger operand1) {
        lastOutput = operand1;
    }

    public BigInteger getLastOutput() {
        return lastOutput;
    }

    public void setProgCount(int newPos) {
        this.progCount = newPos;
    }
}