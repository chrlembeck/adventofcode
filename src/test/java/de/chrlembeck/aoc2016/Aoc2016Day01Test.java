package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day01.Aoc2016Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day01Test {

    @Test
    public void test1() {
        final Aoc2016Day01 day = new Aoc2016Day01();
        assertEquals("279", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day01 day = new Aoc2016Day01();
        assertEquals("163", test(day.getInput2(), day::part2));
    }
}