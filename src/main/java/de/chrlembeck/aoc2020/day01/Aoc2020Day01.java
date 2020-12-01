package de.chrlembeck.aoc2020.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2020Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2020Day01().run();
    }

    @Override
    public Object part1(final Scanner input) {
        Set<Long> values = new TreeSet<>();
        while (input.hasNextLong()) {
            long next = input.nextLong();
            if (values.contains(2020 - next)) {
                return next * (2020 - next);
            }
            values.add(next);
        }
        return null;
    }

    @Override
    public Object part2(final Scanner input) {
        List<Long> values = new ArrayList<>();
        while (input.hasNextLong()) {
            long next = input.nextLong();
            for (int a = 0; a < values.size()-1; a++) {
                for (int b = a + 1; b < values.size(); b++) {
                    if (next + values.get(a) + values.get(b) == 2020) {
                        return next * values.get(a) * values.get(b);
                    }
                }
            }
            values.add(next);
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day01.txt";
    }
}