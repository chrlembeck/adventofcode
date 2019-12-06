package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day11.Aoc2015Day11;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day11Test {

    @Test
    public void test1() {
        final Aoc2015Day11 day = new Aoc2015Day11();
        assertEquals("abcdffaa", test("abcdefgh", day::part1));
        assertEquals("ghjaabcc", test("ghijklmn", day::part1));
        assertEquals("vzbxxyzz", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day11 day = new Aoc2015Day11();
        assertEquals("vzcaabcc", test(day.getInput2(), day::part2));
    }
}