package de.chrlembeck.aoc2017.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Day11Test {

    static String test(final String input, final Function<Scanner, String> function) {
        try (Scanner scanner = new Scanner(input)) {
            return function.apply(scanner);
        }
    }

    @Test
    public void test1() {
        final Day11 day11 = new Day11();
        assertEquals("3", test("ne,ne,ne", day11::part1));
        assertEquals("0", test("ne,ne,sw,sw", day11::part1));
        assertEquals("2", test("ne,ne,s,s", day11::part1));
        assertEquals("3", test("se,sw,se,sw,sw", day11::part1));
        assertEquals("877", test(day11.getInput1(), day11::part1));
    }

    @Test
    public void test2() {
        final Day11 day11 = new Day11();
        assertEquals("3", test("ne,ne,ne", day11::part2));
        assertEquals("2", test("ne,ne,sw,sw", day11::part2));
        assertEquals("2", test("ne,ne,s,s", day11::part2));
        assertEquals("3", test("se,sw,se,sw,sw", day11::part2));
        assertEquals("1622", test(day11.getInput2(), day11::part2));
    }
}