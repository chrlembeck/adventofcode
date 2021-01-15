package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day05.Aoc2016Day05;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day05Test {

    @Test
    public void test1() {
        final Aoc2016Day05 day = new Aoc2016Day05();
        assertEquals("c6697b55", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day05 day = new Aoc2016Day05();
        assertEquals("8c35d1ab", test(day.getInput2(), day::part2));
    }
}