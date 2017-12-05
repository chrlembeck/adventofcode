package de.chrlembeck.aoc2017.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day4Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day4 day4 = new Day4();
        assertEquals("1", test("aa bb cc dd ee", day4::part1));
        assertEquals("0", test("aa bb cc dd aa", day4::part1));
        assertEquals("1", test("aa bb cc dd aaa", day4::part1));
        assertEquals("2", test("aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa", day4::part1));
        assertEquals("325", test(day4.getInput1(), day4::part1));
    }

    @Test
    @Ignore
    public void test2() {
        final Day4 day4 = new Day4();
        assertEquals("1", test("abcde fghij", day4::part2));
        assertEquals("0", test("abcde xyz ecdab", day4::part2));
        assertEquals("1", test("a ab abc abd abf abj", day4::part2));
        assertEquals("1", test("iiii oiii ooii oooi oooo", day4::part2));
        assertEquals("0", test("oiii ioii iioi iiio", day4::part2));
        assertEquals("119", test(day4.getInput2(), day4::part2));
    }
}