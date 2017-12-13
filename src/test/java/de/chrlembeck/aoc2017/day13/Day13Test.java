package de.chrlembeck.aoc2017.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day13Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day13 day13 = new Day13();
        assertEquals("24", test("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4", day13::part1));
        assertEquals("1476", test(day13.getInput1(), day13::part1));
    }

    @Test
    public void test2() {
        final Day13 day13 = new Day13();
        assertEquals("10", test("0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4", day13::part2));
        assertEquals("3937334", test(day13.getInput2(), day13::part2));
    }
}