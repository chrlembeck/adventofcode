package de.chrlembeck.aoc2016.day10;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.function.Function;

public class Aoc2016Day10 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day10().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        final Map<Integer, Bot> bots = new TreeMap<>();
        final Map<Integer, List<Integer>> outputs = new TreeMap<>();
        return input.useDelimiter("\n").tokens().map(line -> consume(line, bots, outputs)).reduce(OptionalResult::or).get().getValue();
    }

    @Override
    public Integer part2(final Scanner input) {
        final Map<Integer, Bot> bots = new TreeMap<>();
        final Map<Integer, List<Integer>> outputs = new TreeMap<>();
        input.useDelimiter("\n").tokens().forEach(line -> consume(line, bots, outputs));
        return outputs.get(0).get(0) * outputs.get(1).get(0) * outputs.get(2).get(0);
    }

    public OptionalResult consume(final String line, final Map<Integer, Bot> bots, final Map<Integer, List<Integer>> outputs) {
        final String[] parts = line.split(" ");
        OptionalResult result = OptionalResult.EMPTY;
        if ("value".equals(parts[0])) {
            result = result.or(bots.computeIfAbsent(Integer.valueOf(parts[5]), Bot::new).apply(Integer.valueOf(parts[1])));
        } else if ("bot".equals(parts[0])) {
            final Integer lowNr = Integer.valueOf(parts[6]);
            Bot bot = bots.computeIfAbsent(Integer.valueOf(parts[1]), Bot::new);
            result = "bot".equals(parts[5]) ?
                    result.or(bot.setLowReceiver(bots.computeIfAbsent(lowNr, Bot::new))) :
                    result.or(bot.setLowReceiver(new OutputReceiver(outputs.computeIfAbsent(lowNr, ArrayList::new))));
            Integer highNr = Integer.valueOf(parts[11]);
            result = "bot".equals(parts[10]) ?
                    result.or(bot.setHighReceiver(bots.computeIfAbsent(highNr, Bot::new))) :
                    result.or(bot.setHighReceiver(new OutputReceiver(outputs.computeIfAbsent(highNr, ArrayList::new))));
        } else {
            throw new IllegalArgumentException(parts[0]);
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day10.txt";
    }

    static class OutputReceiver implements Function<Integer, OptionalResult> {

        private final List<Integer> list;

        public OutputReceiver(final List<Integer> list) {
            this.list = list;
        }

        @Override
        public OptionalResult apply(final Integer value) {
            list.add(value);
            return OptionalResult.EMPTY;
        }

        public Integer getFirst() {
            return list.get(0);
        }
    }
}