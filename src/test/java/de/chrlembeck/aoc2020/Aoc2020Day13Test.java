package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day13.Aoc2020Day13;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day13Test {

    @Test
    public void test1() {
        final Aoc2020Day13 day = new Aoc2020Day13();
        assertEquals("295", test("939\n7,13,x,x,59,x,31,19", day::part1));
        assertEquals("3385", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day13 day = new Aoc2020Day13();
//        assertEquals(null, test("", day::part2));
//        assertEquals(null, test("", day::part2));
//        assertEquals(null, test(day.getInput1(), day::part2));
    }
}