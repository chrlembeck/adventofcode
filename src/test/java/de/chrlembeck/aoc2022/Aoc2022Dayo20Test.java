package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day20.Aoc2022Day20;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo20Test {

    private final AbstractAocBase testee = new Aoc2022Day20();

    @Test
    public void test1() {
        assertEquals("3", test("1\n2\n-3\n3\n-2\n0\n4", testee::part1));
        assertEquals("10763", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("1623178306", test("1\n2\n-3\n3\n-2\n0\n4", testee::part2));
        assertEquals("4979911042808", test(testee.getInput1(), testee::part2));
    }
}
