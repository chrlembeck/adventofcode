package de.chrlembeck.aoc2017;

import de.chrlembeck.aoc2017.day20.Aoc2017Day20;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2017Day20Test {

    @Test
    public void test1() {
        final Aoc2017Day20 day20 = new Aoc2017Day20();
        assertEquals("1", test("p=<1500,413,-535>, v=<-119,22,36>, a=<-5,-12,3>\n" +
                "p=<65,1223,-530>, v=<-14,-136,52>, a=<2,2,0>", day20::part1));
        assertEquals("0", test("p=<65,1223,-530>, v=<-14,-136,52>, a=<2,2,0>\n" +
                "p=<1500,413,-535>, v=<-119,22,36>, a=<-5,-12,3>", day20::part1));
        assertEquals("0", test("p=<1,1,1>, v=<1,1,1>, a=<1,0,0>\n" +
                "p=<1,1,1>, v=<1,1,1>, a=<1,1,0>", day20::part1));
        assertEquals("1", test("p=<1,1,1>, v=<1,1,1>, a=<1,1,0>\n" +
                "p=<1,1,1>, v=<1,1,1>, a=<1,0,0>", day20::part1));
        assertEquals("0", test("p=<1,1,1>, v=<1,1,1>, a=<-1,0,0>\n" +
                "p=<1,1,1>, v=<1,1,1>, a=<-1,-1,0>", day20::part1));
        assertEquals("1", test("p=<1,1,1>, v=<1,1,1>, a=<-1,-1,0>\n" +
                "p=<1,1,1>, v=<1,1,1>, a=<-1,0,0>", day20::part1));
        // assertEquals(null, test(day20.getInput1(), day20::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day20 day20 = new Aoc2017Day20();
        // assertEquals(null, test("", day20::part2));
        // assertEquals(null, test(day20.getInput2(), day20::part2));
    }
}