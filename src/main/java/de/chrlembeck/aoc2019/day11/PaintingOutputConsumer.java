package de.chrlembeck.aoc2019.day11;

import java.math.BigInteger;
import java.util.function.Consumer;

public class PaintingOutputConsumer implements Consumer<BigInteger> {

    private final Consumer<BigInteger> inputConsumer;

    private boolean colorMode = true;

    private final Area area;

    public PaintingOutputConsumer(final Area area, final Consumer<BigInteger> inputConsumer) {
        this.area = area;
        this.inputConsumer = inputConsumer;
    }

    @Override
    public void accept(final BigInteger out) {
        if (colorMode) {
            area.paint(out.intValueExact());
        } else {
            if (out.intValueExact() == 0) {
                area.left();
            } else {
                area.right();
            }
            inputConsumer.accept(BigInteger.valueOf(area.getCurrentColor()));
        }
        colorMode = !colorMode;
    }
}