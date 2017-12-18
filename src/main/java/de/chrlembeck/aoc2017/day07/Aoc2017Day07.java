package de.chrlembeck.aoc2017.day07;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day07 extends AbstractAocBase {

    final Pattern regex = Pattern.compile("([a-z]+)\\s*\\((\\d+)\\)(\\s*\\-\\>(([a-z,\\s]*)))?");

    Map<String, Program> programs;

    public static void main(final String[] args) {
        new Aoc2017Day07().run();
    }

    @Override
    public String part1(final Scanner input) {
        prepareTree(input);
        return programs.values().stream().filter(prog -> prog.parent == null).findAny().get().name;
    }

    @Override
    public String part2(final Scanner input) {
        prepareTree(input);
        final Program root = programs.values().stream().filter(prog -> prog.parent == null).findAny().get();
        return Integer.toString(root.findMisbalance());
    }

    private void prepareTree(final Scanner input) {
        programs = new TreeMap<>();
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = regex.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(line);
            }
            final String name = matcher.group(1);
            final int weight = Integer.parseInt(matcher.group(2));
            final String rest = matcher.group(4);
            final String[] children = rest == null ? new String[0] : rest.trim().split("\\s*,\\s*");
            programs.put(name, new Program(name, weight, children));
        }
        programs.values().forEach(prog -> Stream.of(prog.children).map(child -> programs.get(child))
                .forEach(cprog -> cprog.parent = prog));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day07.txt";
    }

    class Program {

        final int weight;

        final String name;

        Program parent;

        final String[] children;

        public Program(final String name, final int weight, final String... children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
        }

        public int findMisbalance() {
            if (children.length == 0) {
                return 0;
            }
            final Optional<Integer> misbalance = Stream.of(children).map(child -> programs.get(child))
                    .map(Program::findMisbalance).filter(value -> value > 0).findAny();
            if (misbalance.isPresent()) {
                return misbalance.get().intValue();
            }
            final Map<Integer, Integer> histogram = new HashMap<>();
            Stream.of(children).map(child -> programs.get(child).getTotalWeight()).forEach(weight -> {
                final Integer count = histogram.get(weight);
                histogram.put(weight, count == null ? 1 : count + 1);
            });

            if (histogram.size() > 1) {
                final Function<BiFunction<Entry<Integer, Integer>, Entry<Integer, Integer>, Boolean>, Entry<Integer, Integer>> finder = operator -> histogram
                        .entrySet().stream().reduce((ent1, ent2) -> operator.apply(ent1, ent2) ? ent1 : ent2).get();
                final Entry<Integer, Integer> max = finder
                        .apply((ent1, ent2) -> ent1.getValue() > ent2.getValue());
                final Entry<Integer, Integer> min = finder
                        .apply((ent1, ent2) -> ent1.getValue() < ent2.getValue());
                final int delta = max.getKey() - min.getKey();
                final Optional<Program> misbalanced = Stream.of(children).map(child -> programs.get(child))
                        .filter(candidate -> candidate.getTotalWeight() == min.getKey()).findAny();
                if (misbalanced.isPresent()) {
                    return misbalanced.get().weight + delta;
                }
            }
            return 0;
        }

        public int getTotalWeight() {
            int sum = weight;
            for (final String child : children) {
                sum += programs.get(child).getTotalWeight();
            }
            return sum;
        }
    }
}