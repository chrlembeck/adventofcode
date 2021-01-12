package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day25.Aoc2020Day25;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day25Test {

    @Test
    public void test1() {
        final Aoc2020Day25 day = new Aoc2020Day25();
        assertEquals("14897079", test("5764801\n17807724", day::part1));
        assertEquals("12181021", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day25 day = new Aoc2020Day25();
        assertEquals(null, test("", day::part2));
        assertEquals(null, test("", day::part2));
        assertEquals(null, test(day.getInput1(), day::part2));
    }
}