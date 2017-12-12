package de.chrlembeck.aoc2017.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day10Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day10 day10 = new Day10();
        day10.setLength(5);
        assertEquals("12", test("3,4,1,5", day10::part1));
        day10.setLength(256);
        assertEquals("52070", test(day10.getInput1(), day10::part1));
    }

    @Test
    public void test2() {
        final Day10 day10 = new Day10();
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", test("", day10::part2));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", test("AoC 2017", day10::part2));
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", test("1,2,3", day10::part2));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", test("1,2,4", day10::part2));
        assertEquals("7f94112db4e32e19cf6502073c66f9bb", test(day10.getInput2(), day10::part2));
    }
}