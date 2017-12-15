package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day24.Aoc2017Day24;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day24Test {

    @Test
    public void test1() {
        final Aoc2017Day24 day24 = new Aoc2017Day24();
        assertEquals(null, test("", day24::part1));
        assertEquals(null, test(day24.getInput1(), day24::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day24 day24 = new Aoc2017Day24();
        assertEquals(null, test("", day24::part2));
        assertEquals(null, test(day24.getInput2(), day24::part2));
    }
}