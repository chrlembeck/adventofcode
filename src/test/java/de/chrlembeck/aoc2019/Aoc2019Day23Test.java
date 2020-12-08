package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day23.Aoc2019Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day23Test {

    @Test
    public void test1() {
        final Aoc2019Day23 day = new Aoc2019Day23();
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day23 day = new Aoc2019Day23();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}