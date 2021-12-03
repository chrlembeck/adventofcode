package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day09.Aoc2021Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day09Test {

    @Test
    public void test1() {
        final Aoc2021Day09 day = new Aoc2021Day09();
        assertEquals("", test("", day::part1));
        assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day09 day = new Aoc2021Day09();
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}