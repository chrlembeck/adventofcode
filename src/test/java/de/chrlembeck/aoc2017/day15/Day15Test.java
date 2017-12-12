package de.chrlembeck.aoc2017.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day15Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day15 day15 = new Day15();
        assertEquals(null, test("", day15::part1));
        assertEquals(null, test(day15.getInput1(), day15::part1));
    }

    @Test
    public void test2() {
        final Day15 day16 = new Day15();
        assertEquals(null, test("", day16::part2));
        assertEquals(null, test(day16.getInput2(), day16::part2));
    }
}