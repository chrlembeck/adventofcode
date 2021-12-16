package de.chrlembeck.aoc2015.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Aoc2015Day24 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day24().run();
    }

    @Override
    public Long part1(final Scanner input) {
        List<Integer> weights = input.useDelimiter("\n").tokens().map(Integer::valueOf).collect(Collectors.toList());
        int weight = weights.stream().mapToInt(Integer::intValue).sum();
        int groupWeight = weight / 3;

        var result = findSolutions(weights, groupWeight);
        return result;
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    public static final long quantumEntanglement(final Integer[] list) {
        long result = list[0];
        for (int i = list.length - 1; i > 0; i--) {
            result *= list[i];
        }
        return result;
    }

    private long findSolutions(List<Integer> weights, int groupWeight) {
        LengthSortedResult pairs = findGroup1(weights, groupWeight);
        for (Integer[] pair : pairs) {
            List<Integer> other = new ArrayList<>(weights);
            for (Integer i: pair) {
                other.remove(i);
            }
            LengthSortedResult p2s = findGroup1(other, groupWeight);
            if (p2s.size() > 0) {
                return quantumEntanglement(pair);
            }
        }
        return -1;
    }

    private static LengthSortedResult findGroup1(List<Integer> weights, int groupWeight) {
        LengthSortedResult res = new LengthSortedResult(weights.size() + 1);
        Consumer<Integer[]> collector = l1 -> res.add(l1);
        findGroup1(weights, groupWeight, new ArrayList<>(), collector, 0);
        return res;
    }

    private static void findGroup1(List<Integer> weights, int remaining, List<Integer> result, Consumer<Integer[]> resultConsumer, int startAt) {
        for (int i = startAt; i < weights.size(); i++) {
            Integer current = weights.get(i);
            if (current > remaining) {
                break;
            }
            result.add(current);
            if (remaining == current) {
                resultConsumer.accept(result.toArray(Integer[]::new));
            } else if (remaining > current) {
                findGroup1(weights, remaining - current, result, resultConsumer, i + 1);
            }
            Integer removed = result.remove(result.size() - 1);
            if (!(removed.intValue() == current.intValue())) {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day24.txt";
    }

    static class LengthSortedResult implements Iterable<Integer[]> {

        private final List<Integer[]>[] data;

        private int size;

        public LengthSortedResult(int maxlength) {
            data = new List[maxlength];
            for (int i = 0; i < data.length; i++) {
                data[i] = new ArrayList<>();
            }

        }

        public void add(Integer[] pair) {
            data[pair.length].add(pair);
            size++;
        }

        @Override
        public Iterator<Integer[]> iterator() {
            return Arrays.stream(data).flatMap(l -> l.stream()).iterator();
        }

        public int size() {
            return size;
        }
    }
}