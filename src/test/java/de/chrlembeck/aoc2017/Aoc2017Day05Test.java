package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day05.Aoc2017Day05;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day05Test {

    @Test
    public void test1() {
        final Aoc2017Day05 day5 = new Aoc2017Day05();
        assertEquals("5", test("0\n3\n0\n1\n-3", day5::part1));
        assertEquals("372671", test(day5.getInput1(), day5::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day05 day5 = new Aoc2017Day05();
        assertEquals("10", test("0\n3\n0\n1\n-3", day5::part2));
        assertEquals("25608480", test(day5.getInput2(), day5::part2));
    }
}