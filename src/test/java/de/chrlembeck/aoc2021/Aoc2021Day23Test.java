package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day23.Aoc2021Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day23Test {

    private final Aoc2021Day23 day = new Aoc2021Day23();

    @Test
    public void test1() {
        assertEquals("12521", test("""
                #############
                #...........#
                ###B#C#B#D###
                  #A#D#C#A#
                  #########""", day::part1));
        assertEquals("10321", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2a() {
        assertEquals("44169", test("""
                #############
                #...........#
                ###B#C#B#D###
                  #A#D#C#A#
                  #########""", day::part2));
    }

    @Test
    public void test2b() {
        assertEquals("46451", test(day.getInput2(), day::part2));
    }
}