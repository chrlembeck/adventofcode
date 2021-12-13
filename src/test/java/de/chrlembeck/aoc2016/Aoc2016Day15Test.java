package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day15.Aoc2016Day15;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day15Test {

    @Test
    public void test1() {
        final Aoc2016Day15 day = new Aoc2016Day15();
        assertEquals("203660", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day15 day = new Aoc2016Day15();
        assertEquals("2408135", test(day.getInput2(), day::part2));
    }
}