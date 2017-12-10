package de.chrlembeck.aoc2017.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day8Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day8 day8 = new Day8();
        assertEquals("1", test("b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10", day8::part1));
        assertEquals("4567", test(day8.getInput1(), day8::part1));
    }

    @Test
    public void test2() {
        final Day8 day8 = new Day8();
        assertEquals("10", test("b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10", day8::part2));
        assertEquals("5636", test(day8.getInput2(), day8::part2));
    }
}