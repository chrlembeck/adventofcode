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
        final Aoc2017Day17 day17 = new Aoc2017Day17();
        assertEquals(null, test("", day17::part1));
        assertEquals(null, test(day17.getInput1(), day17::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day17 day17 = new Aoc2017Day17();
        assertEquals(null, test("", day17::part2));
        assertEquals(null, test(day17.getInput2(), day17::part2));
    }
}