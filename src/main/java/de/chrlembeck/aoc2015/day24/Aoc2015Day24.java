package de.chrlembeck.aoc2015.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.function.BiConsumer;
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

    public static final long quantumEntanglement(List<Integer> list) {
        return list.stream().mapToLong(Integer::longValue).reduce(1, (a,b)->a*b);
    }

    private long findSolutions(List<Integer> weights, int groupWeight) {
        LengthSortedResult pairs = findGroup1(weights, groupWeight);
        for (Pair pair: pairs) {
            LengthSortedResult p2s = findGroup1(pair.list2, groupWeight);
            if (p2s.size() > 0) {
                return quantumEntanglement(pair.list1);
            }
        }
        return -1;
    }

    private static LengthSortedResult findGroup1(List<Integer> weights, int groupWeight) {
        LengthSortedResult res = new LengthSortedResult(weights.size()+1);
        BiConsumer<List<Integer>, List<Integer>> collector = (l1, l2) -> res.add(new Pair(new ArrayList<>(l1), new ArrayList<>(l2)));
        findGroup1(weights, groupWeight, new ArrayList<>(), collector, 0, new ArrayList<>());
        return res;
    }

    private static void findGroup1(List<Integer> weights, int remaining, List<Integer> result, BiConsumer<List<Integer>, List<Integer>> resultConsumer, int startAt, List<Integer> unused) {
        for (int i = startAt; i < weights.size(); i++) {
            Integer current = weights.get(i);
            if (current > remaining) {
                break;
            }
            result.add(current);
            if (remaining == current) {
                List<Integer> test = new ArrayList<>(unused.size() + weights.size() - i + 1); // check length
                test.addAll(unused);
                test.addAll(weights.subList(i + 1, weights.size()));
                resultConsumer.accept(result, test);
            } else if (remaining > current) {
                List<Integer> unusedCopy = new ArrayList<>(weights.size());
                unusedCopy.addAll(unused);
                findGroup1(weights, remaining - current, result, resultConsumer, i + 1, unusedCopy);
            }
            Integer removed = result.remove(result.size() - 1);
            if (!(removed.intValue() == current.intValue())) {
                throw new IllegalStateException();
            }
            unused.add(current);
        }
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day24.txt";
    }

    static class Pair {
        private List<Integer> list1;
        private List<Integer> list2;

        public Pair(List<Integer> list1, List<Integer> list2) {
            this.list1 = list1;
            this.list2 = list2;
        }

        @Override
        public String toString() {
            return list1 + " -> "+ list2;
        }
    }

    static class LengthSortedResult implements Iterable<Pair>{

        private final List<Pair>[] data;

        private int size;

        public LengthSortedResult(int maxlength) {
            data = new List[maxlength];
            for (int i = 0; i < data.length; i++) {
                data[i] = new ArrayList<>();
            }

        }

        public void add(Pair pair) {
            data[pair.list1.size()].add(pair);
            size++;
        }

        @Override
        public Iterator<Pair> iterator() {
            return Arrays.stream(data).flatMap(l -> l.stream()).iterator();
        }

        public int size() {
            return size;
        }
    }
}