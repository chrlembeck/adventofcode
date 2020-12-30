package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day14.Aoc2020Day14;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day14Test {

    @Test
    public void test1() {
        final Aoc2020Day14 day = new Aoc2020Day14();
        assertEquals("165", test("""
                                mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
                                mem[8] = 11
                                mem[7] = 101
                                mem[8] = 0""", day::part1));
        assertEquals("7817357407588", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day14 day = new Aoc2020Day14();
        assertEquals("208", test("""
                                mask = 000000000000000000000000000000X1001X
                                mem[42] = 100
                                mask = 00000000000000000000000000000000X0XX
                                mem[26] = 1""", day::part2));
        assertEquals("4335927555692", test(day.getInput1(), day::part2));
    }
}