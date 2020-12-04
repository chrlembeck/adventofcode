package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

public class State {

    private final BlockingQueue<BigInteger> inputQueue = new LinkedBlockingDeque();

    private int progCount;

    private Consumer<BigInteger> outputConsumer;

    private BigInteger lastOutput;

    public State(final BigInteger... inputValues) {
        Arrays.stream(inputValues).forEach(inputQueue::add);
    }

    public void setOutputConsumer(final Consumer<BigInteger> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    public void inc(final int steps) {
        progCount += steps;
    }

    public int getProgCount() {
        return progCount;
    }

    public BigInteger getInput() {
        try {
            return inputQueue.take();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public void output(final BigInteger output) {
        if (outputConsumer == null) {
            throw new IllegalStateException("no output consumer");
        }
        lastOutput = output;
        outputConsumer.accept(output);
    }

    public void setProgCount(final int newPos) {
        this.progCount = newPos;
    }

    public Consumer<BigInteger> getInputConsumer() {
        return inputQueue::add;
    }

    public BigInteger getLastOutput() {
        return lastOutput;
    }
}