package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day23.Aoc2015Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day23Test {

    @Test
    public void test1() {
        final Aoc2015Day23 day = new Aoc2015Day23();
        assertEquals("2", test("""
                inc b
                jio b, +2
                tpl b
                inc b""", day::part1));
        assertEquals("184", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day23 day = new Aoc2015Day23();
        assertEquals("231", test(day.getInput2(), day::part2));
    }
}