package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day17.Aoc2020Day17;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day17Test {

    @Test
    public void test1() {
        final Aoc2020Day17 day = new Aoc2020Day17();
        assertEquals("112", test("""
                                .#.
                                ..#
                                ###""", day::part1));
        assertEquals("324", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day17 day = new Aoc2020Day17();
        assertEquals("848", test("""
                                 .#.
                                 ..#
                                 ###""", day::part2));
        assertEquals("1836", test(day.getInput1(), day::part2));
    }
}