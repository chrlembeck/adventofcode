package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day06.Aoc2015Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day06Test {

    @Test
    public void test1() {
        final Aoc2015Day06 day = new Aoc2015Day06();
        assertEquals("1000000", test("turn on 0,0 through 999,999", day::part1));
        assertEquals("0", test("turn off 0,0 through 999,999", day::part1));
        assertEquals("500000", test("turn on 0,0 through 999,999\ntoggle 0,0 through 499,999", day::part1));
        assertEquals("377891", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day06 day = new Aoc2015Day06();
        assertEquals("1", test("turn on 0,0 through 0,0", day::part2));
        assertEquals("0", test("turn off 0,0 through 0,0", day::part2));
        assertEquals("2000000", test("toggle 0,0 through 999,999", day::part2));
        assertEquals("14110788", test(day.getInput2(), day::part2));
    }
}