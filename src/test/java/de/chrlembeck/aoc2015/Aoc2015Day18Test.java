package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day18.Aoc2015Day18;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day18Test {

    @Test
    public void test1() {
        final Aoc2015Day18 day = new Aoc2015Day18();
        assertEquals("4", test("""
                              .#.#.#
                              ...##.
                              #....#
                              ..#...
                              #.#..#
                              ####..""", day::part1));
        assertEquals("1061", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day18 day = new Aoc2015Day18();
        assertEquals("7", test("""
                              ##.#.#
                              ...##.
                              #....#
                              ..#...
                              #.#..#
                              ####.#""", day::part2));
        assertEquals("1006", test(day.getInput2(), day::part2));
    }
}