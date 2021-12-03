package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day02.Aoc2021Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day02Test {

    @Test
    public void test1() {
        final Aoc2021Day02 day = new Aoc2021Day02();
        assertEquals("150", test("""
                               forward 5
                               down 5
                               forward 8
                               up 3
                               down 8
                               forward 2""", day::part1));
        assertEquals("1762050", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day02 day = new Aoc2021Day02();
        assertEquals("900", test("""
                               forward 5
                               down 5
                               forward 8
                               up 3
                               down 8
                               forward 2""", day::part2));
        assertEquals("1855892637", test(day.getInput2(), day::part2));
    }
}