package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day02.Aoc2016Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day02Test {

    @Test
    public void test1() {
        final Aoc2016Day02 day = new Aoc2016Day02();
        assertEquals("98575", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day02 day = new Aoc2016Day02();
        assertEquals("CD8D4", test(day.getInput2(), day::part2));
    }
}