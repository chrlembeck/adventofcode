package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day10.Aoc2015Day10;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day10Test {

    @Test
    public void test1() {
        final Aoc2015Day10 day = new Aoc2015Day10();
        assertEquals("11", day.step("1"));
        assertEquals("21", day.step("11"));
        assertEquals("1211", day.step("21"));
        assertEquals("111221", day.step("1211"));
        assertEquals("312211", day.step("111221"));
        // assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day10 day = new Aoc2015Day10();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}