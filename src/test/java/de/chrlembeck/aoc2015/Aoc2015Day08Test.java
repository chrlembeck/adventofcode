package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day08.Aoc2015Day08;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day08Test {

    @Test
    public void test1() {
        final Aoc2015Day08 day = new Aoc2015Day08();
        assertEquals("2", test("\"\"", day::part1));
        assertEquals("2", test("\"abc\"", day::part1));
        assertEquals("3", test("\"aaa\\\"aaa\"", day::part1));
        assertEquals("3", test("\"aaa\\\\aaa\"", day::part1));
        assertEquals("5", test("\"\\x27\"", day::part1));
        assertEquals("1342", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day08 day = new Aoc2015Day08();
        assertEquals("4", test("\"\"", day::part2));
        assertEquals("4", test("\"abc\"", day::part2));
        assertEquals("6", test("\"aaa\\\"aaa\"", day::part2));
        assertEquals("5", test("\"\\x27\"", day::part2));
        assertEquals("2074", test(day.getInput2(), day::part2));
    }
}