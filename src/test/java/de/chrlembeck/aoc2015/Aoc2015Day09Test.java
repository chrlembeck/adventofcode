package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day09.Aoc2015Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day09Test {

    @Test
    public void test1() {
        final Aoc2015Day09 day = new Aoc2015Day09();
        assertEquals("605",
                test("London to Dublin = 464\nLondon to Belfast = 518\nDublin to Belfast = 141", day::part1));
        assertEquals("117", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day09 day = new Aoc2015Day09();
        assertEquals("982",
                test("London to Dublin = 464\nLondon to Belfast = 518\nDublin to Belfast = 141", day::part2));
        assertEquals("909", test(day.getInput2(), day::part2));
    }
}