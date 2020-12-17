package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class State {

    private final int programId;

    private State otherState;

    private boolean running = true;

    private BigInteger sentCount = BigInteger.ZERO;

    private final Map<String, BigInteger> register = new TreeMap<>();

    private BigInteger currentSound;

    private int position;

    private BigInteger recoveredSound;

    private final Queue<BigInteger> queue = new LinkedList<>();

    private int mulInstructionCount;

    public State(final int programId) {
        this.programId = programId;
        register.put("p", BigInteger.valueOf(programId));
    }

    public void setOtherState(final State otherState) {
        this.otherState = otherState;
        otherState.otherState = this;
    }

    public State getOtherState() {
        return otherState;
    }

    public void playSound(final BigInteger value) {
        this.currentSound = value;
    }

    public BigInteger getValue(final String varName) {
        final BigInteger value = register.get(varName);
        return value == null ? BigInteger.ZERO : value;
    }

    public void setValue(final String varName, final BigInteger newValue) {
        register.put(varName, newValue);
    }

    public BigInteger getCurrentSound() {
        return currentSound;
    }

    public void jump(final int intValue) {
        this.position += intValue - 1;
    }

    public int getPosition() {
        return position;
    }

    public void incPos() {
        position++;
    }

    public BigInteger getRecoveredSound() {
        return recoveredSound;
    }

    public void recoverSound(final BigInteger sound) {
        recoveredSound = sound;
    }

    public int getProgramId() {
        return programId;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }

    public BigInteger getSentCount() {
        return sentCount;
    }

    public void incSentCount() {
        sentCount = sentCount.add(BigInteger.ONE);
    }

    public void send(final BigInteger value) {
        otherState.queue.add(value);
        otherState.running = true;
        incSentCount();
    }

    public BigInteger receiveValue() {
        return queue.isEmpty() ? null : queue.poll();
    }

    public void countMulInstruction() {
        mulInstructionCount++;
    }

    public int getMulInstructionCount() {
        return mulInstructionCount;
    }
}