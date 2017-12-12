package de.chrlembeck.aoc2017.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day18Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day18 day18 = new Day18();
        assertEquals(null, test("", day18::part1));
        assertEquals(null, test(day18.getInput1(), day18::part1));
    }

    @Test
    public void test2() {
        final Day18 day13 = new Day18();
        assertEquals(null, test("", day13::part2));
        assertEquals(null, test(day13.getInput2(), day13::part2));
    }
}