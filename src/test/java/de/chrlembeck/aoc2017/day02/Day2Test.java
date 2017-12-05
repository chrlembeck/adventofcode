package de.chrlembeck.aoc2017.day02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day2Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day2 day2 = new Day2();
        assertEquals("18", test("5 1 9 5\n7 5 3\n2 4 6 8", day2::part1));
        assertEquals("48357", test(day2.getInput1(), day2::part1));
    }

    @Test
    public void test2() {
        final Day2 day2 = new Day2();
        assertEquals("9", test("5 9 2 8\n9 4 7 3\n3 8 6 5", day2::part2));
        assertEquals("351", test(day2.getInput2(), day2::part2));
    }
}