package de.chrlembeck.aoc2017.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day9Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day9 day9 = new Day9();
        assertEquals("1", test("{}", day9::part1));
        assertEquals("6", test("{{{}}}", day9::part1));
        assertEquals("5", test("{{},{}}", day9::part1));
        assertEquals("16", test("{{{},{},{{}}}}", day9::part1));
        assertEquals("1", test("{<a>,<a>,<a>,<a>}", day9::part1));
        assertEquals("9", test("{{<ab>},{<ab>},{<ab>},{<ab>}}", day9::part1));
        assertEquals("9", test("{{<!!>},{<!!>},{<!!>},{<!!>}}", day9::part1));
        assertEquals("3", test("{{<a!>},{<a!>},{<a!>},{<ab>}}", day9::part1));
        assertEquals("14190", test(day9.getInput1(), day9::part1));
    }

    @Test
    public void test2() {
        final Day9 day9 = new Day9();
        assertEquals("0", test("<>", day9::part2));
        assertEquals("17", test("<random characters", day9::part2));
        assertEquals("3", test("<<<<>", day9::part2));
        assertEquals("2", test("<{!>}>", day9::part2));
        assertEquals("0", test("<!!>", day9::part2));
        assertEquals("0", test("<!!!>>", day9::part2));
        assertEquals("10", test("<{o\"i!a,<{i<a>", day9::part2));
        assertEquals("7053", test(day9.getInput2(), day9::part2));
    }
}