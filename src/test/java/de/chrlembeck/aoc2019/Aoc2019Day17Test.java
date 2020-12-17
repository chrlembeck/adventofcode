package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day17.Aoc2019Day17;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day17Test {

    @Test
    public void test1() {
        final Aoc2019Day17 day = new Aoc2019Day17();
        assertEquals("4044", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day17 day = new Aoc2019Day17();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}