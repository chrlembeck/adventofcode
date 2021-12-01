package de.chrlembeck.aoc2016.day10;

import java.util.TreeSet;
import java.util.function.Function;

public class Bot implements Function<Integer, OptionalResult> {

    private final Integer botNr;

    private Function<Integer, OptionalResult> lowReceiver;

    private Function<Integer, OptionalResult> highReceiver;

    @SuppressWarnings("PMD.LooseCoupling")
    private final TreeSet<Integer> values = new TreeSet<>();

    public Bot(final Integer botNr) {
        this.botNr = botNr;
    }

    @Override
    public OptionalResult apply(final Integer value) {
        values.add(value);
        return execute();
    }

    public OptionalResult execute() {
        if (values.size() < 2) {
            return OptionalResult.EMPTY;
        } else if (lowReceiver == null || highReceiver == null) {
            return OptionalResult.EMPTY;
        } else {
            final Integer low = values.pollFirst();
            final Integer high = values.pollFirst();
            OptionalResult result = lowReceiver.apply(low);
            result = result.orElse(highReceiver.apply(high));
            lowReceiver = null;
            highReceiver = null;
            if (low == 17 && high == 61) {
                return new OptionalResult(botNr);
            }
            return result;
        }
    }

    public OptionalResult registerLowReceiver(final Function<Integer, OptionalResult> lowBot) {
        lowReceiver = lowBot;
        return execute();
    }

    public OptionalResult registerHighReceiver(final Function<Integer, OptionalResult> highBot) {
        highReceiver = highBot;
        return execute();
    }
}