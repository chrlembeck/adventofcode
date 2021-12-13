package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day11.Aoc2021Day11;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day11Test {

    public static final String INPUT = """
                                       5483143223
                                       2745854711
                                       5264556173
                                       6141336146
                                       6357385478
                                       4167524645
                                       2176841721
                                       6882881134
                                       4846848554
                                       5283751526""";

    @Test
    public void test1() {
        final Aoc2021Day11 day = new Aoc2021Day11();
        assertEquals("1656", test(INPUT, day::part1));
        assertEquals("1601", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day11 day = new Aoc2021Day11();
        assertEquals("195", test(INPUT, day::part2));
        assertEquals("368", test(day.getInput2(), day::part2));
    }
}