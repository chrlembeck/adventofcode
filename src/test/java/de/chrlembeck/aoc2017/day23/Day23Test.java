package de.chrlembeck.aoc2017.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day23Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day23 day23 = new Day23();
        assertEquals(null, test("", day23::part1));
        assertEquals(null, test(day23.getInput1(), day23::part1));
    }

    @Test
    public void test2() {
        final Day23 day23 = new Day23();
        assertEquals(null, test("", day23::part2));
        assertEquals(null, test(day23.getInput2(), day23::part2));
    }
}