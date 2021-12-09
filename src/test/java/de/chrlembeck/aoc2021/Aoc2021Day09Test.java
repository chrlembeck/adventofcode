package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day09.Aoc2021Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day09Test {

    @Test
    public void test1() {
        final Aoc2021Day09 day = new Aoc2021Day09();
        assertEquals("15", test("""
                                2199943210
                                3987894921
                                9856789892
                                8767896789
                                9899965678""", day::part1));
        assertEquals("545", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day09 day = new Aoc2021Day09();
        assertEquals("1134", test("""
                                2199943210
                                3987894921
                                9856789892
                                8767896789
                                9899965678""", day::part2));
        assertEquals("950600", test(day.getInput2(), day::part2));
    }
}