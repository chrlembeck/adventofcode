package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day01.Aoc2020Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day01Test {

    @Test
    public void test1() {
        final Aoc2020Day01 day = new Aoc2020Day01();
        assertEquals(Long.toString(1010*1010), test("1010\n1010", day::part1));
        assertEquals(Long.toString(2000*20), test("2000\n19\n20", day::part1));
        assertEquals("1019571", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day01 day = new Aoc2020Day01();
        assertEquals(Long.toString(999*1001*20), test("999\n1001\n20", day::part2));
        assertEquals(Long.toString(2000*5*15), test("1\n2000\n5\n15", day::part2));
        assertEquals("100655544", test(day.getInput2(), day::part2));
    }
}