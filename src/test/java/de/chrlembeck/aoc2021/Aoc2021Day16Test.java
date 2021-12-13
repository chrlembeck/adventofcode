package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day16.Aoc2021Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day16Test {

    @Test
    public void test1() {
        final Aoc2021Day16 day = new Aoc2021Day16();
        assertEquals("", test("", day::part1));
        assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day16 day = new Aoc2021Day16();
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}