package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day12.Aoc2017Day12;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day12Test {

    @Test
    public void test1() {
        final Aoc2017Day12 day12 = new Aoc2017Day12();
        assertEquals("6", test("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5", day12::part1));
        assertEquals("283", test(day12.getInput1(), day12::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day12 day12 = new Aoc2017Day12();
        assertEquals("2", test("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5", day12::part2));
        assertEquals("195", test(day12.getInput2(), day12::part2));
    }
}