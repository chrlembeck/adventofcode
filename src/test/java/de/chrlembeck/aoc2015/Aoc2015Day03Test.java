package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day03.Aoc2015Day03;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day03Test {

    @Test
    public void test1() {
        final Aoc2015Day03 day = new Aoc2015Day03();
        assertEquals("2", test(">", day::part1));
        assertEquals("4", test("^>v<", day::part1));
        assertEquals("2", test("^v^v^v^v^v", day::part1));
        assertEquals("2081", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day03 day = new Aoc2015Day03();
        assertEquals("3", test("^v", day::part2));
        assertEquals("3", test("^>v<", day::part2));
        assertEquals("11", test("^v^v^v^v^v", day::part2));
        assertEquals("2341", test(day.getInput2(), day::part2));
    }
}