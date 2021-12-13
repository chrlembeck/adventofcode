package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day12.Aoc2016Day12;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day12Test {

    @Test
    public void test1() {
        final Aoc2016Day12 day = new Aoc2016Day12();
        assertEquals("42", test("""
                              cpy 41 a
                              inc a
                              inc a
                              dec a
                              jnz a 2
                              dec a""", day::part1));
        assertEquals("318117", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2016Day12 day = new Aoc2016Day12();
        assertEquals("9227771", test(day.getInput2(), day::part2));
    }
}