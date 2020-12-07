package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day13.Aoc2019Day13;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day13Test {

    @Test
    public void test1() {
        final Aoc2019Day13 day = new Aoc2019Day13();
        assertEquals("304", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day13 day = new Aoc2019Day13();
        assertEquals("14747", test(day.getInput2(), day::part2));
    }
}