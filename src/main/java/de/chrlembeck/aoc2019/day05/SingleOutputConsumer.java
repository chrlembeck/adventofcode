package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.function.Consumer;

public class SingleOutputConsumer implements Consumer<BigInteger> {

    private BigInteger output;

    @Override
    public void accept(final BigInteger value) {
        synchronized (this) {
            this.output = value;
            this.notifyAll();
        }
    }

    public BigInteger getOutput() {
        synchronized (this) {
            while (output == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return output;
        }
    }
}