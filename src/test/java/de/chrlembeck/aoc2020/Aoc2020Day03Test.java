package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day03.Aoc2020Day03;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day03Test {

    private static final String testInput = """
                                ..##.......
                                #...#...#..
                                .#....#..#.
                                ..#.#...#.#
                                .#...##..#.
                                ..#.##.....
                                .#.#.#....#
                                .#........#
                                #.##...#...
                                #...##....#
                                .#..#...#.#""";

    @Test
    public void test1() {
        final Aoc2020Day03 day = new Aoc2020Day03();
        assertEquals("7", test(testInput, day::part1));
        assertEquals("286", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day03 day = new Aoc2020Day03();
        assertEquals("336", test(testInput, day::part2));
        assertEquals("3638606400", test(day.getInput1(), day::part2));
    }
}