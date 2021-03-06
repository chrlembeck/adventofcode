package de.chrlembeck.aoc2018;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import de.chrlembeck.aoc2018.day18.Aoc2018Day18;

public class Aoc2018Day18Test {

    @Test
    public void test1() {
        final Aoc2018Day18 day = new Aoc2018Day18();
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test("", day::part1));
        assertEquals("", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2018Day18 day = new Aoc2018Day18();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}