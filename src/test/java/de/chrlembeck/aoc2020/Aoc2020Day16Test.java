package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day16.Aoc2020Day16;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day16Test {

    @Test
    public void test1() {
        final Aoc2020Day16 day = new Aoc2020Day16();
        assertEquals("71", test("""
                                class: 1-3 or 5-7
                                row: 6-11 or 33-44
                                seat: 13-40 or 45-50
                                                                
                                your ticket:
                                7,1,14
                                                                
                                nearby tickets:
                                7,3,47
                                40,4,50
                                55,2,20
                                38,6,12""", day::part1));
        assertEquals("27850", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day16 day = new Aoc2020Day16();
        assertEquals("491924517533", test(day.getInput1(), day::part2));
    }
}