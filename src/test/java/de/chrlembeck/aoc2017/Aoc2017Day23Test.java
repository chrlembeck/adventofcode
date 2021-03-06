package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day23.Aoc2017Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day23Test {

    @Test
    public void test1() {
        final Aoc2017Day23 day23 = new Aoc2017Day23();
        assertEquals("6724", test(day23.getInput1(), day23::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day23 day23 = new Aoc2017Day23();
        assertEquals("917", test(day23.getInput2(), day23::part2));
    }
}