package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day04.Aoc2022Day04;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo04Test {

    private final AbstractAocBase testee = new Aoc2022Day04();

    @Test
    public void test1() {
        assertEquals("2", test("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8""", testee::part1));
        assertEquals("584", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("4", test("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8""", testee::part2));
        assertEquals("933", test(testee.getInput2(), testee::part2));
    }
}
