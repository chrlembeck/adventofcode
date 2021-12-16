package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day14.Aoc2015Day14;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day14Test {

    @Test
    public void test1() {
        final Aoc2015Day14 day = new Aoc2015Day14();
        assertEquals("2660", test("""
                              Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                              Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.""", day::part1));
        assertEquals("2696", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day14 day = new Aoc2015Day14();
        assertEquals("1564", test("""
                              Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                              Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.""", day::part2));
        assertEquals("1084", test(day.getInput2(), day::part2));
    }
}