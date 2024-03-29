package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day24.Aoc2016Day24;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day24Test {

    @Test
    public void test1() {
        final Aoc2016Day24 day = new Aoc2016Day24();
        assertEquals("14", test("""
                              ###########
                              #0.1.....2#
                              #.#######.#
                              #4.......3#
                              ###########""", day::part1));
        assertEquals("442", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day24 day = new Aoc2016Day24();
//        assertEquals("", test("", day::part2));
//        assertEquals("", test("", day::part2));
        assertEquals("660", test(day.getInput2(), day::part2));
    }
}