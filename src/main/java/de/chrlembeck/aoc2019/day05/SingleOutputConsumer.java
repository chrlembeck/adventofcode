package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.function.Consumer;

public class SingleOutputConsumer implements Consumer<BigInteger> {

    private BigInteger output;

    @Override
    public synchronized void accept(BigInteger value) {
        this.output = value;
        this.notify();
    }

    public synchronized BigInteger getOutput() {
        if (output == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return output;
    }
}