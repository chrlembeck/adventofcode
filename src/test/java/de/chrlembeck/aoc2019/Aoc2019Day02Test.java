package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day02.Aoc2019Day02;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day02Test {

    @Test
    public void test1() {
        final Aoc2019Day02 day2 = new Aoc2019Day02();
        assertEquals("2", test("1,0,0,0,99", day2::test1));
        assertEquals("2", test("2,3,0,3,99", day2::test1));
        assertEquals("2", test("2,4,4,5,99,0", day2::test1));
        assertEquals("30", test("1,1,1,4,99,5,6,0,99", day2::test1));
        assertEquals("9706670", test(day2.getInput1(), day2::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day02 day2 = new Aoc2019Day02();
        assertEquals("2552", test(day2.getInput2(), day2::part2));
    }
}