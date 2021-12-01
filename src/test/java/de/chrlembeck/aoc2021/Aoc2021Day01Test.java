package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day01.Aoc2021Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day01Test {

    @Test
    public void test1() {
        final Aoc2021Day01 day = new Aoc2021Day01();
        assertEquals("7", test("199\n200\n208\n210\n200\n207\n240\n269\n260\n263", day::part1));
        assertEquals("1711", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day01 day = new Aoc2021Day01();
        assertEquals("5", test("199\n200\n208\n210\n200\n207\n240\n269\n260\n263", day::part2));
        assertEquals("1743", test(day.getInput2(), day::part2));
    }
}