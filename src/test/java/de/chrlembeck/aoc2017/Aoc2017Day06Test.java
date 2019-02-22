package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day06.Aoc2017Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day06Test {

    @Test
    public void test1() {
        final Aoc2017Day06 day6 = new Aoc2017Day06();
        assertEquals("5", test("0 2 7 0", day6::part1));
        assertEquals("5042", test(day6.getInput1(), day6::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day06 day6 = new Aoc2017Day06();
        assertEquals("4", test("0  2   7   0", day6::part2));
        assertEquals("1086", test(day6.getInput2(), day6::part2));
    }
}