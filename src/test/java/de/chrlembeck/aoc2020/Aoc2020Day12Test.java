package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day12.Aoc2020Day12;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day12Test {

    @Test
    public void test1() {
        final Aoc2020Day12 day = new Aoc2020Day12();
        assertEquals("25", test("F10\nN3\nF7\nR90\nF11", day::part1));
        assertEquals("1177", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day12 day = new Aoc2020Day12();
        assertEquals("286", test("F10\nN3\nF7\nR90\nF11", day::part2));
        assertEquals("46530", test(day.getInput2(), day::part2));
    }
}