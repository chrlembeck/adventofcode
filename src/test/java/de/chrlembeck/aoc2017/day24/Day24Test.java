package de.chrlembeck.aoc2017.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day24Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day24 day24 = new Day24();
        assertEquals(null, test("", day24::part1));
        assertEquals(null, test(day24.getInput1(), day24::part1));
    }

    @Test
    public void test2() {
        final Day24 day24 = new Day24();
        assertEquals(null, test("", day24::part2));
        assertEquals(null, test(day24.getInput2(), day24::part2));
    }
}