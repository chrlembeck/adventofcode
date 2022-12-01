package de.chrlembeck.aoc2015.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.stream.Collectors;

public class Aoc2015Day24 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day24().run();
    }

    @Override
    public Long part1(final Scanner input) {
        List<Integer> weights = input.useDelimiter("\n").tokens().map(Integer::valueOf).collect(Collectors.toList());
        int weight = weights.stream().mapToInt(Integer::intValue).sum();
        return findSolution(weights, weight / 3, false);
    }

    @Override
    public Long part2(final Scanner input) {
        List<Integer> weights = input.useDelimiter("\n").tokens().map(Integer::valueOf).collect(Collectors.toList());
        int weight = weights.stream().mapToInt(Integer::intValue).sum();
        return findSolution(weights, weight / 4, true);
    }

    public static final long quantumEntanglement(final Integer[] list) {
        long result = list[0];
        for (int i = list.length - 1; i > 0; i--) {
            result *= list[i];
        }
        return result;
    }

    private long findSolution(final List<Integer> weights, final int groupWeight, final boolean part1) {
        final LengthSortedResult combinations = calcCombinations(weights, groupWeight);
        for (Integer[] firstCombination : combinations) {
            List<Integer> remaining = difference(weights, firstCombination);
            final LengthSortedResult combinationsForSecondGroup = calcCombinations(remaining, groupWeight);
            if (part1) {
                if (combinationsForSecondGroup.size() > 0) {
                    return quantumEntanglement(firstCombination);
                }
            } else if (combinationsForSecondGroup.size() > 0) {
                for (final Integer[] secondCombination : combinationsForSecondGroup) {
                    final LengthSortedResult combinationsForThirdGroup = calcCombinations(difference(remaining, secondCombination), groupWeight);
                    if (combinationsForThirdGroup.size() > 0) {
                        return quantumEntanglement(firstCombination);
                    }
                }
            }
        }
        return -1;
    }

    private List<Integer> difference(final List<Integer> elements, final Integer[] elementsToRemove) {
        final List<Integer> remaining = new ArrayList<>(elements);
        for (final Integer elementToRemove : elementsToRemove) {
            remaining.remove(elementToRemove);
        }
        return remaining;
    }

    private static LengthSortedResult calcCombinations(List<Integer> weights, int groupWeight) {
        LengthSortedResult res = new LengthSortedResult(weights.size() + 1);
        calcCombinations(weights, groupWeight, new ArrayList<>(), 0, res);
        return res;
    }

    private static void calcCombinations(final List<Integer> weights, final int remaining, final List<Integer> combination, final int startAt, final LengthSortedResult result) {
        for (int i = startAt; i < weights.size(); i++) {
            final Integer current = weights.get(i);
            if (current > remaining) {
                break;
            }
            combination.add(current);
            if (remaining == current) {
                result.add(combination.toArray(Integer[]::new));
            } else if (remaining > current) {
                calcCombinations(weights, remaining - current, combination, i + 1, result);
            }
            combination.remove(combination.size() - 1);
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