package de.chrlembeck.aoc2017.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day3Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day3 day3 = new Day3();
        assertEquals("0", test("1", day3::part1));
        assertEquals("3", test("12", day3::part1));
        assertEquals("2", test("23", day3::part1));
        assertEquals("31", test("1024", day3::part1));
        assertEquals("480", test(day3.getInput1(), day3::part1));
    }

    @Test
    public void test2() {
        final Day3 day3 = new Day3();
        assertEquals("5", test("4", day3::part2));
        assertEquals("23", test("11", day3::part2));
        assertEquals("806", test("747", day3::part2));
        assertEquals("349975", test(day3.getInput2(), day3::part2));
    }
}