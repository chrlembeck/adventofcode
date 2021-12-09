package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day05.Aoc2021Day05;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day05Test {

    @Test
    public void test1() {
        final Aoc2021Day05 day = new Aoc2021Day05();
        assertEquals("5", test("""
                              0,9 -> 5,9
                              8,0 -> 0,8
                              9,4 -> 3,4
                              2,2 -> 2,1
                              7,0 -> 7,4
                              6,4 -> 2,0
                              0,9 -> 2,9
                              3,4 -> 1,4
                              0,0 -> 8,8
                              5,5 -> 8,2""", day::part1));
        assertEquals("6007", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day05 day = new Aoc2021Day05();
        assertEquals("12", test("""
                              0,9 -> 5,9
                              8,0 -> 0,8
                              9,4 -> 3,4
                              2,2 -> 2,1
                              7,0 -> 7,4
                              6,4 -> 2,0
                              0,9 -> 2,9
                              3,4 -> 1,4
                              0,0 -> 8,8
                              5,5 -> 8,2""", day::part2));
        assertEquals("19349", test(day.getInput2(), day::part2));
    }
}