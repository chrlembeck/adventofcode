package de.chrlembeck.aoc2016;

import de.chrlembeck.aoc2016.day25.Aoc2016Day25;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2016Day25Test {

    @Test
    public void test1() {
        final Aoc2016Day25 day = new Aoc2016Day25();
        assertEquals("180", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
    }
}