package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day05.Aoc2020Day05;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day05Test {

    @Test
    public void test1() {
        final Aoc2020Day05 day = new Aoc2020Day05();
        assertEquals("567", test("BFFFBBFRRR", day::part1));
        assertEquals("119", test("FFFBBBFRRR", day::part1));
        assertEquals("820", test("BBFFBBFRLL", day::part1));
        assertEquals("951", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day05 day = new Aoc2020Day05();
        assertEquals("653", test(day.getInput1(), day::part2));
    }
}