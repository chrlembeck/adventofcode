package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day02.Aoc2022Day02;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo02Test {

    private final AbstractAocBase testee = new Aoc2022Day02();

    @Test
    public void test1() {
        assertEquals("15", test("""
                A Y
                B X
                C Z""", testee::part1));
        assertEquals("12535", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("12", test("""
                A Y
                B X
                C Z""", testee::part2));
        assertEquals("15457", test(testee.getInput2(), testee::part2));
    }
}
