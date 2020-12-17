package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day11.Aoc2020Day11;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day11Test {

    @Test
    public void test1() {
        final Aoc2020Day11 day = new Aoc2020Day11();
        assertEquals("37", test("""
                                L.LL.LL.LL
                                LLLLLLL.LL
                                L.L.L..L..
                                LLLL.LL.LL
                                L.LL.LL.LL
                                L.LLLLL.LL
                                ..L.L.....
                                LLLLLLLLLL
                                L.LLLLLL.L
                                L.LLLLL.LL""", day::part1));
        assertEquals("2334", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day11 day = new Aoc2020Day11();
        assertEquals("26", test("""
                                L.LL.LL.LL
                                LLLLLLL.LL
                                L.L.L..L..
                                LLLL.LL.LL
                                L.LL.LL.LL
                                L.LLLLL.LL
                                ..L.L.....
                                LLLLLLLLLL
                                L.LLLLLL.L
                                L.LLLLL.LL""", day::part2));
        assertEquals("2100", test(day.getInput1(), day::part2));
    }
}