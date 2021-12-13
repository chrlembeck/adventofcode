package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day14.Aoc2016Day14;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day14Test {

    @Test
    public void test1() {
        final Aoc2016Day14 day = new Aoc2016Day14();
        assertEquals("15035", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day14 day = new Aoc2016Day14();
        assertEquals("19968", test(day.getInput2(), day::part2));
    }
}