package de.chrlembeck.aoc2017.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day1Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day1 day1 = new Day1();
        assertEquals("3", test("1122", day1::part1));
        assertEquals("4", test("1111", day1::part1));
        assertEquals("0", test("1234", day1::part1));
        assertEquals("9", test("91212129", day1::part1));
        assertEquals("1228", test(day1.getInput1(), day1::part1));
    }

    @Test
    public void test2() {
        final Day1 day1 = new Day1();
        assertEquals("6", test("1212", day1::part2));
        assertEquals("0", test("1221", day1::part2));
        assertEquals("4", test("123425", day1::part2));
        assertEquals("12", test("123123", day1::part2));
        assertEquals("4", test("12131415", day1::part2));
        assertEquals("1238", test(day1.getInput2(), day1::part2));
    }
}