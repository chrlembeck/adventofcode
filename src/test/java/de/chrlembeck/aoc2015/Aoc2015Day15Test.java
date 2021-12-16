package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day15.Aoc2015Day15;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day15Test {

    @Test
    public void test1() {
        final Aoc2015Day15 day = new Aoc2015Day15();
        assertEquals("62842880", test("""
                              Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                              Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3""", day::part1));
        assertEquals("222870", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day15 day = new Aoc2015Day15();
        assertEquals("57600000", test("""
                              Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
                              Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3""", day::part2));
        assertEquals("117936", test(day.getInput2(), day::part2));
    }
}