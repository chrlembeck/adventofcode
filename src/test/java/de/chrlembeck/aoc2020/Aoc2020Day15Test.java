package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day15.Aoc2020Day15;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day15Test {

    @Test
    public void test1() {
        final Aoc2020Day15 day = new Aoc2020Day15();
        assertEquals("436", test("0,3,6", day::part1));
        assertEquals("959", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day15 day = new Aoc2020Day15();
        assertEquals("175594", test("0,3,6", day::part2));
        assertEquals("116590", test(day.getInput1(), day::part2));
    }
}