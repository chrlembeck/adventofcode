package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day17.Aoc2017Day17;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day17Test {

    @Test
    public void test1() {
        final Aoc2017Day17 day = new Aoc2017Day17();
        assertEquals("638", test("3", day::part1));
        assertEquals("2000", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day17 day = new Aoc2017Day17();
        assertEquals("1222153", test("3", day::part2));
        assertEquals("10242889", test(day.getInput2(), day::part2));
    }
}