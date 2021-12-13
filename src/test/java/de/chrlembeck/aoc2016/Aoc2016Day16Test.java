package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day16.Aoc2016Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day16Test {

    @Test
    public void test1() {
        final Aoc2016Day16 day = new Aoc2016Day16();
        assertEquals("10010010110011010", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day16 day = new Aoc2016Day16();
        assertEquals("01010100101011100", test(day.getInput2(), day::part2));
    }
}