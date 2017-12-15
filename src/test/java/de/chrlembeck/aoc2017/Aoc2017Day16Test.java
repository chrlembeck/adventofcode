package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day16.Aoc2017Day16;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day16Test {

    @Test
    public void test1() {
        final Aoc2017Day16 day13 = new Aoc2017Day16();
        assertEquals(null, test("", day13::part1));
        assertEquals(null, test(day13.getInput1(), day13::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day16 day16 = new Aoc2017Day16();
        assertEquals(null, test("", day16::part2));
        assertEquals(null, test(day16.getInput2(), day16::part2));
    }
}