package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day23.Aoc2020Day23;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day23Test {

    @Test
    public void test1() {
        final Aoc2020Day23 day = new Aoc2020Day23();
        assertEquals("67384529", test("389125467", day::part1));
        assertEquals("82635947", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day23 day = new Aoc2020Day23();
        assertEquals("149245887792", test("389125467", day::part2));
        assertEquals("157047826689", test(day.getInput1(), day::part2));
    }
}