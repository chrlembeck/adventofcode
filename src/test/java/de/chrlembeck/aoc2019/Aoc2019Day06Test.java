package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day06.Aoc2019Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day06Test {

    @Test
    public void test1() {
        final Aoc2019Day06 day = new Aoc2019Day06();
        assertEquals("42", test("COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L", day::part1));
        assertEquals("147807", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day06 day = new Aoc2019Day06();
        assertEquals("4", test("COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L\nK)YOU\nI)SAN", day::part2));
        assertEquals("229", test(day.getInput2(), day::part2));
    }
}