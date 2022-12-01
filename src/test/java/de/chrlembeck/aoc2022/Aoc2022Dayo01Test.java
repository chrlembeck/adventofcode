package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day01.Aoc2022Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo01Test {

    @Test
    public void test1() {
        final Aoc2022Day01 day = new Aoc2022Day01();
        assertEquals("24000", test("""
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000""", day::part1));
        assertEquals("72718", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2022Day01 day = new Aoc2022Day01();
        assertEquals("45000", test("""
                1000
                2000
                3000
                                
                4000
                                
                5000
                6000
                                
                7000
                8000
                9000
                                
                10000""", day::part2));
        assertEquals("213089", test(day.getInput2(), day::part2));
    }
}
