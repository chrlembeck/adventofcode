package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;

public class IntcodeComputer {

    private List<BigInteger> program;

    private State state;

    private Thread calculator;

    public IntcodeComputer(List<BigInteger> program, BigInteger... initialInput) {
        this.state = new State(initialInput);;
        this.program = program;
    }

    public void setOutputConsumer(Consumer<BigInteger> outputConsumer) {
        state.setOutputConsumer(outputConsumer);
    }

    public Consumer<BigInteger> getInputConsumer() {
        return state.getInputConsumer();
    }

    public void startCalculation() {
        Runnable runnable = () -> {

            Instruction instruction = Instruction.of(program, state);
            while (instruction.getOpcode() != 99) {
                instruction.exec(program, state);
                instruction = Instruction.of(program, state);
            }
        };
        calculator = new Thread(runnable);
        calculator.start();
    }

    public void waitForExit() throws InterruptedException{
        calculator.join();
    }

    public BigInteger getLastOutput() {
        return state.getLastOutput();
    }
}