package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day06.Aoc2020Day06;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day06Test {

    @Test
    public void test1() {
        final Aoc2020Day06 day = new Aoc2020Day06();
        assertEquals("6", test("""
                                abcx
                                abcy
                                abcz""", day::part1));
        assertEquals("11", test("""
                                abc
                                                                
                                a
                                b
                                c
                                                                
                                ab
                                ac
                                                                
                                a
                                a
                                a
                                a
                                                                
                                b""", day::part1));
        assertEquals("6291", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day06 day = new Aoc2020Day06();
        assertEquals("6", test("""
                                abc
                                                                
                                a
                                b
                                c
                                                                
                                ab
                                ac
                                                                
                                a
                                a
                                a
                                a
                                                                
                                b""", day::part2));
        assertEquals("3", test("""
                                abcx
                                abcy
                                abcz""", day::part2));
        assertEquals("3052", test(day.getInput1(), day::part2));
    }
}