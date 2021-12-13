package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day13.Aoc2021Day13;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day13Test {

    @Test
    public void test1() {
        final Aoc2021Day13 day = new Aoc2021Day13();
        assertEquals("17", test("""
                              6,10
                              0,14
                              9,10
                              0,3
                              10,4
                              4,11
                              6,0
                              6,12
                              4,1
                              0,13
                              10,12
                              3,4
                              3,0
                              8,4
                              1,10
                              2,14
                              8,10
                              9,0
                                                            
                              fold along y=7
                              fold along x=5""", day::part1));
        assertEquals("706", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day13 day = new Aoc2021Day13();
        assertEquals("""
                     #####
                     #...#
                     #...#
                     #...#
                     #####
                     """, test("""
                              6,10
                              0,14
                              9,10
                              0,3
                              10,4
                              4,11
                              6,0
                              6,12
                              4,1
                              0,13
                              10,12
                              3,4
                              3,0
                              8,4
                              1,10
                              2,14
                              8,10
                              9,0
                                                            
                              fold along y=7
                              fold along x=5""", day::part2));
        assertEquals("""
                     #....###..####...##.###....##.####.#..#
                     #....#..#.#.......#.#..#....#.#....#..#
                     #....#..#.###.....#.###.....#.###..####
                     #....###..#.......#.#..#....#.#....#..#
                     #....#.#..#....#..#.#..#.#..#.#....#..#
                     ####.#..#.#.....##..###...##..####.#..#
                     """, test(day.getInput2(), day::part2));
    }
}