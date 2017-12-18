package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day19.Aoc2017Day19;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day19Test {

    @Test
    public void test1() {
        final Aoc2017Day19 day19 = new Aoc2017Day19();
        assertEquals(null, test("", day19::part1));
        assertEquals(null, test(day19.getInput1(), day19::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day19 day19 = new Aoc2017Day19();
        assertEquals(null, test("", day19::part2));
        assertEquals(null, test(day19.getInput2(), day19::part2));
    }
}