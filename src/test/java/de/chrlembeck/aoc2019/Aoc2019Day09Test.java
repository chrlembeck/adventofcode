package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day09.Aoc2019Day09;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day09Test {

    @Test
    public void test1() {
        final Aoc2019Day09 day = new Aoc2019Day09();
        assertEquals("1125899906842624", test("104,1125899906842624,99", day::part1));
        assertEquals("1219070632396864", test("1102,34915192,34915192,7,4,7,99,0", day::part1));
        assertEquals("99", test("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99", day::part1));
        assertEquals("3518157894", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day09 day = new Aoc2019Day09();
        assertEquals("80379", test(day.getInput2(), day::part2));
    }
}