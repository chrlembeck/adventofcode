package de.chrlembeck.aoc2017.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day14Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day14 day14 = new Day14();
        assertEquals("8108", test("flqrgnkx", day14::part1));
        assertEquals("8214", test(day14.getInput1(), day14::part1));
    }

    @Test
    public void test2() {
        final Day14 day14 = new Day14();
        assertEquals("1242", test("flqrgnkx", day14::part2));
        assertEquals("1093", test(day14.getInput2(), day14::part2));
    }
}