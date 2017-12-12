package de.chrlembeck.aoc2017.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day17Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day17 day17 = new Day17();
        assertEquals(null, test("", day17::part1));
        assertEquals(null, test(day17.getInput1(), day17::part1));
    }

    @Test
    public void test2() {
        final Day17 day17 = new Day17();
        assertEquals(null, test("", day17::part2));
        assertEquals(null, test(day17.getInput2(), day17::part2));
    }
}