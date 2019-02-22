package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day04.Aoc2015Day04;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day04Test {

    @Test
    public void test1() {
        final Aoc2015Day04 day = new Aoc2015Day04();
        assertEquals("609043", test("abcdef", day::part1));
        assertEquals("1048970", test("pqrstuv", day::part1));
        assertEquals("254575", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day04 day = new Aoc2015Day04();
        assertEquals("6742839", test("abcdef", day::part2));
        assertEquals("5714438", test("pqrstuv", day::part2));
        assertEquals("1038736", test(day.getInput2(), day::part2));
    }
}