package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day12.Aoc2019Day12;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day12Test {

    @Test
    public void test1() {
        final Aoc2019Day12 day = new Aoc2019Day12();
        assertEquals("13045", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day12 day = new Aoc2019Day12();
        assertEquals("2772", test("""
                                        <x=-1, y=0, z=2>
                                        <x=2, y=-10, z=-7>
                                        <x=4, y=-8, z=8>
                                        <x=3, y=5, z=-1>""", day::part2));
        assertEquals("4686774924", test("""
                                        <x=-8, y=-10, z=0>
                                        <x=5, y=5, z=10>
                                        <x=2, y=-7, z=3>
                                        <x=9, y=-8, z=-3>""", day::part2));
        assertEquals("344724687853944", test(day.getInput2(), day::part2));
    }
}