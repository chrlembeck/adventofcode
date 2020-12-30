package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.function.Consumer;

public class IntcodeComputer {

    private final IntcodeProgram program;

    private final State state;

    private Thread calculator;

    public IntcodeComputer(final IntcodeProgram program, final BigInteger... initialInput) {
        this.state = new State(initialInput);
        this.program = program;
    }

    public void setOutputConsumer(final Consumer<BigInteger> outputConsumer) {
        state.setOutputConsumer(outputConsumer);
    }

    public Consumer<BigInteger> getInputConsumer() {
        return state.getInputConsumer();
    }

    public void startCalculation() {
        final Runnable runnable = () -> {
            AbstractInstruction instruction = AbstractInstruction.readNextInstruction(program, state);
            while (instruction.getOpcode() != 99) {
                instruction.exec(program, state);
                instruction = AbstractInstruction.readNextInstruction(program, state);
            }
        };
        calculator = new Thread(runnable);
        calculator.start();
    }

    public void waitForExit() {
        try {
            calculator.join();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public BigInteger getLastOutput() {
        return state.getLastOutput();
    }
}