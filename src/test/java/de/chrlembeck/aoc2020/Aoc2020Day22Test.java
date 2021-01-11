package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day22.Aoc2020Day22;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day22Test {

    @Test
    public void test1() {
        final Aoc2020Day22 day = new Aoc2020Day22();
        assertEquals("306", test("""
                                Player 1:
                                9
                                2
                                6
                                3
                                1
                                                                
                                Player 2:
                                5
                                8
                                4
                                7
                                10""", day::part1));
        assertEquals("33772", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day22 day = new Aoc2020Day22();
        assertEquals("291", test("""
                                Player 1:
                                9
                                2
                                6
                                3
                                1
                                                                
                                Player 2:
                                5
                                8
                                4
                                7
                                10""", day::part2));
        assertEquals("35070", test(day.getInput1(), day::part2));
    }
}