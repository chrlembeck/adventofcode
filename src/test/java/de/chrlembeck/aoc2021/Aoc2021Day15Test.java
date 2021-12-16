package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day15.Aoc2021Day15;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day15Test {

    @Test
    public void test1() {
        final Aoc2021Day15 day = new Aoc2021Day15();
        assertEquals("40", test("""
                              1163751742
                              1381373672
                              2136511328
                              3694931569
                              7463417111
                              1319128137
                              1359912421
                              3125421639
                              1293138521
                              2311944581""", day::part1));
        assertEquals("741", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day15 day = new Aoc2021Day15();
        assertEquals("315", test("""
                              1163751742
                              1381373672
                              2136511328
                              3694931569
                              7463417111
                              1319128137
                              1359912421
                              3125421639
                              1293138521
                              2311944581""", day::part2));
        assertEquals("2976", test(day.getInput2(), day::part2));
    }
}