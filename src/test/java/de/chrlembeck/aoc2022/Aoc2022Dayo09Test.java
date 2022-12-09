package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day09.Aoc2022Day09;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo09Test {

    private final AbstractAocBase testee = new Aoc2022Day09();

    @Test
    public void test1() {
        assertEquals("13", test("""
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2""", testee::part1));
        assertEquals("6018", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("1", test("""
                R 4
                U 4
                L 3
                D 1
                R 4
                D 1
                L 5
                R 2""", testee::part2));
        assertEquals("36", test("""
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20""", testee::part2));
        assertEquals("2619", test(testee.getInput1(), testee::part2));
    }
}
