package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day20.Aoc2016Day20;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day20Test {

    @Test
    public void test1() {
        final Aoc2016Day20 day = new Aoc2016Day20();
        assertEquals("19449262", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day20 day = new Aoc2016Day20();
        assertEquals("119", test(day.getInput2(), day::part2));
    }
}