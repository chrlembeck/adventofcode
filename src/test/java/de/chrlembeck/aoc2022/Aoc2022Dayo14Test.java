package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day14.Aoc2022Day14;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo14Test {

    private final AbstractAocBase testee = new Aoc2022Day14();

    @Test
    public void test1() {
        assertEquals("24", test("""
                498,4 -> 498,6 -> 496,6
                503,4 -> 502,4 -> 502,9 -> 494,9""", testee::part1));
        assertEquals("825", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("93", test("""
                498,4 -> 498,6 -> 496,6
                503,4 -> 502,4 -> 502,9 -> 494,9""", testee::part2));
        assertEquals("26729", test(testee.getInput1(), testee::part2));
    }
}
