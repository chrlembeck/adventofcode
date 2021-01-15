package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day03.Aoc2016Day03;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day03Test {

    @Test
    public void test1() {
        final Aoc2016Day03 day = new Aoc2016Day03();
        assertEquals("1050", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day03 day = new Aoc2016Day03();
        assertEquals("1921", test(day.getInput2(), day::part2));
    }
}