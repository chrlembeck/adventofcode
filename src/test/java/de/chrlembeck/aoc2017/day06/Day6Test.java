package de.chrlembeck.aoc2017.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day6Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day6 day6 = new Day6();
        assertEquals("5", test("0 2 7 0", day6::part1));
        assertEquals("4074", test(day6.getInput1(), day6::part1));
    }

    @Test
    public void test2() {
        final Day6 day6 = new Day6();
        assertEquals("4", test("0  2   7   0", day6::part2));
        assertEquals("2793", test(day6.getInput2(), day6::part2));
    }
}