package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day18.Aoc2017Day18;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day18Test {

    @Test
    public void test1() {
        final Aoc2017Day18 day18 = new Aoc2017Day18();
        assertEquals(null, test("", day18::part1));
        assertEquals(null, test(day18.getInput1(), day18::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day18 day13 = new Aoc2017Day18();
        assertEquals(null, test("", day13::part2));
        assertEquals(null, test(day13.getInput2(), day13::part2));
    }
}