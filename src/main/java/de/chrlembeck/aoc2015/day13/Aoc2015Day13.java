package de.chrlembeck.aoc2015.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Permutation;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Aoc2015Day13 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("(\\w+) would (gain|lose) (\\d+) happiness units by sitting next to (\\w+).");

    public static void main(final String[] args) {
        new Aoc2015Day13().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, true);
    }

    public int calc(Scanner input, boolean includingMe) {
        List<Constraint> constraints = input.findAll(REGEX).map(Constraint::new).collect(Collectors.toList());
        Set<String> names = constraints.stream().map(Constraint::getName1).collect(Collectors.toSet());
        Permutation<String> orderGenerator = new Permutation<>(names, String[]::new);
        int max = Integer.MIN_VALUE;
        for (String[] order : orderGenerator) {
            int happiness = 0;
            for (int i = 0; i < order.length - (includingMe?1:0); i++) {
                String name1 = order[i];
                String name2 = order[(i + 1) % order.length];
                happiness += constraints.stream().filter(c -> c.fits(name1, name2)).mapToInt(Constraint::getValue).sum();
            }
            max = Math.max(max, happiness);
        }
        return max;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day13.txt";
    }
}