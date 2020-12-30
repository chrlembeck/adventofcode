package de.chrlembeck.aoc2020.day08;

import java.util.ArrayList;
import java.util.List;

public class Program {

    private final List<AbstractInstruction> instructions;

    private long accumulator;

    private int progCount;

    public Program(final List<AbstractInstruction> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    public long getAccumulator() {
        return accumulator;
    }

    public void incAccumulator(final long value) {
        accumulator += value;
    }

    public int getProgCount() {
        return progCount;
    }

    public AbstractInstruction getInstruction(final int idx) {
        return instructions.get(idx);
    }

    protected void incProgramCounter(final int argument) {
        progCount += argument;
    }

    public int getInstructionCount() {
        return instructions.size();
    }

    public void replace(final int index, final AbstractInstruction instruction) {
        instructions.set(index, instruction);
    }

    public void reset() {
        accumulator = 0;
        progCount = 0;
    }
}