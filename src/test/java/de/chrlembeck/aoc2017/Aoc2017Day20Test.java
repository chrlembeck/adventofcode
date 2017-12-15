package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day20.Aoc2017Day20;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day20Test {

    @Test
    public void test1() {
        final Aoc2017Day20 day20 = new Aoc2017Day20();
        assertEquals(null, test("", day20::part1));
        assertEquals(null, test(day20.getInput1(), day20::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day20 day20 = new Aoc2017Day20();
        assertEquals(null, test("", day20::part2));
        assertEquals(null, test(day20.getInput2(), day20::part2));
    }
}