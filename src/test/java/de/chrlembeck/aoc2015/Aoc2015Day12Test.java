package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day12.Aoc2015Day12;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day12Test {

    @Test
    public void test1() {
        final Aoc2015Day12 day = new Aoc2015Day12();
        assertEquals("6", test("[1,2,3]", day::part1));
        assertEquals("6", test("{\"a\":2,\"b\":4}", day::part1));
        assertEquals("3", test("[[[3]]]", day::part1));
        assertEquals("3", test("{\"a\":{\"b\":4},\"c\":-1}", day::part1));
        assertEquals("0", test("{\"a\":[-1,1]}", day::part1));
        assertEquals("0", test("[-1,{\"a\":1}]", day::part1));
        assertEquals("0", test("{}", day::part1));
        assertEquals("0", test("[]", day::part1));
        assertEquals("119433", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day12 day = new Aoc2015Day12();
        assertEquals("68466", test(day.getInput2(), day::part2));
    }
}