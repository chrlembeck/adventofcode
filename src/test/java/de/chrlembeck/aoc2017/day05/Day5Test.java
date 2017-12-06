package de.chrlembeck.aoc2017.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day5Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day5 day5 = new Day5();
        assertEquals("5", test("0\n3\n0\n1\n-3", day5::part1));
        assertEquals("355965", test(day5.getInput1(), day5::part1));
    }

    @Test
    public void test2() {
        final Day5 day5 = new Day5();
        assertEquals("10", test("0\n3\n0\n1\n-3", day5::part2));
        assertEquals("26948068", test(day5.getInput2(), day5::part2));
    }
}