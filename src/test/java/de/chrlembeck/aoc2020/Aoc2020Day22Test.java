package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day22.Aoc2020Day22;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day22Test {

    @Test
    public void test1() {
        final Aoc2020Day22 day = new Aoc2020Day22();
        assertEquals(null, test("", day::part1));
        assertEquals(null, test("", day::part1));
        assertEquals(null, test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day22 day = new Aoc2020Day22();
        assertEquals(null, test("", day::part2));
        assertEquals(null, test("", day::part2));
        assertEquals(null, test(day.getInput1(), day::part2));
    }
}