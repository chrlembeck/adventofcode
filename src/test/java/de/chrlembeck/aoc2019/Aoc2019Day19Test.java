package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day19.Aoc2019Day19;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day19Test {

    @Test
    public void test1() {
        final Aoc2019Day19 day = new Aoc2019Day19();
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day19 day = new Aoc2019Day19();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}