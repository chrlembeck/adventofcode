package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day24.Aoc2015Day24;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day24Test {

    @Test
    public void test1() {
        final Aoc2015Day24 day = new Aoc2015Day24();
        assertEquals("99", test("1\n2\n3\n4\n5\n7\n8\n9\n10\n11", day::part1));
        assertEquals("11846773891", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day24 day = new Aoc2015Day24();
        assertEquals("", test("", day::part2));
        assertEquals("", test("", day::part2));
        assertEquals("", test(day.getInput2(), day::part2));
    }
}