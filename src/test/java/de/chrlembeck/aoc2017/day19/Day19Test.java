package de.chrlembeck.aoc2017.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day19Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day19 day19 = new Day19();
        assertEquals(null, test("", day19::part1));
        assertEquals(null, test(day19.getInput1(), day19::part1));
    }

    @Test
    public void test2() {
        final Day19 day19 = new Day19();
        assertEquals(null, test("", day19::part2));
        assertEquals(null, test(day19.getInput2(), day19::part2));
    }
}