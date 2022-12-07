package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day22.Aoc2015Day22;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day22Test {

    @Test
    public void test1() {
        final Aoc2015Day22 day = new Aoc2015Day22();
        assertEquals("1824", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day22 day = new Aoc2015Day22();
        assertEquals("1937", test(day.getInput2(), day::part2));
    }
}