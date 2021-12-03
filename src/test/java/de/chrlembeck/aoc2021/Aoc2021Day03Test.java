package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day03.Aoc2021Day03;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day03Test {

    @Test
    public void test1() {
        final Aoc2021Day03 day = new Aoc2021Day03();
        assertEquals("198", test("""
                              00100
                              11110
                              10110
                              10111
                              10101
                              01111
                              00111
                              11100
                              10000
                              11001
                              00010
                              01010""", day::part1));
        assertEquals("4147524", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day03 day = new Aoc2021Day03();
        assertEquals("230", test("""
                              00100
                              11110
                              10110
                              10111
                              10101
                              01111
                              00111
                              11100
                              10000
                              11001
                              00010
                              01010""", day::part2));
        assertEquals("3570354", test(day.getInput2(), day::part2));
    }
}