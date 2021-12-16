package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day21.Aoc2015Day21;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day21Test {

    @Test
    public void test1() {
        final Aoc2015Day21 day = new Aoc2015Day21();
        assertEquals("111", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day21 day = new Aoc2015Day21();
        assertEquals("188", test(day.getInput2(), day::part2));
    }
}