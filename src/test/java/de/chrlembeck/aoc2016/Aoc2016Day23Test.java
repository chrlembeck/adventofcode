package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day23.Aoc2016Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day23Test {

    @Test
    public void test1() {
        final Aoc2016Day23 day = new Aoc2016Day23();
        assertEquals("3", test("""
                              cpy 2 a
                              tgl a
                              tgl a
                              tgl a
                              cpy 1 a
                              dec a
                              dec a""", day::part1));
        assertEquals("11736", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day23 day = new Aoc2016Day23();
        assertEquals("479008296", test(day.getInput2(), day::part2));
    }
}