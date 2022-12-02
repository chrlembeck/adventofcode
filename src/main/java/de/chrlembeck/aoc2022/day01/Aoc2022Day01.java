package de.chrlembeck.aoc2022.day01;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Aoc2022Day01 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day01().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return calc(input, 0L, Math::max);
    }

    @Override
    public Long part2(final Scanner input) {
        List<Long> calories = calc(input, new ArrayList<>(), (l, v) -> {
            l.add(v);
            return l;
        });
        calories.sort(Collections.reverseOrder());
        return calories.get(0) + calories.get(1) + calories.get(2);
    }

    public <T> T calc(final Scanner input, T initial, BiFunction<T, Long, T> valueConsumer) {
        T result = initial;
        long current = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            if (line.isEmpty()) {
                result = valueConsumer.apply(result, current);
                current = 0;
            } else {
                current += Long.parseLong(line);
            }
        }
        return valueConsumer.apply(result, current);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day01.txt";
    }
}