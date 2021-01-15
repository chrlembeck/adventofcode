package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day09.Aoc2016Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day09Test {

    @Test
    public void test1() {
        final Aoc2016Day09 day = new Aoc2016Day09();
        assertEquals("6", test("ADVENT", day::part1));
        assertEquals("74532", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day09 day = new Aoc2016Day09();
        assertEquals("11558231665", test(day.getInput2(), day::part2));
    }
}