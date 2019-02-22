package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day02.Aoc2015Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day02Test {

    @Test
    public void test1() {
        final Aoc2015Day02 day = new Aoc2015Day02();
        assertEquals("58", test("2x3x4", day::part1));
        assertEquals("43", test("1x1x10", day::part1));
        assertEquals("101", test("2x3x4\n1x1x10", day::part1));
        assertEquals("1598415", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day02 day = new Aoc2015Day02();
        assertEquals("34", test("2x3x4", day::part2));
        assertEquals("14", test("1x1x10", day::part2));
        assertEquals("48", test("2x3x4\n1x1x10", day::part2));
        assertEquals("3812909", test(day.getInput2(), day::part2));
    }
}