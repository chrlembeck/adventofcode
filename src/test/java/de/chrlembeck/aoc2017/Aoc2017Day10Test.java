package de.chrlembeck.aoc2017;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import de.chrlembeck.aoc2017.day10.Aoc2017Day10;

@RunWith(JUnitPlatform.class)
public class Aoc2017Day10Test {

    @Test
    public void test1() {
        final Aoc2017Day10 day10 = new Aoc2017Day10();
        day10.setLength(5);
        assertEquals("12", test("3,4,1,5", day10::part1));
        day10.setLength(256);
        assertEquals("11375", test("88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205", day10::part1));
        assertEquals("52070", test(day10.getInput1(), day10::part1));
    }

    @Test
    public void test2() {
        final Aoc2017Day10 day10 = new Aoc2017Day10();
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", test("", day10::part2));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", test("AoC 2017", day10::part2));
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", test("1,2,3", day10::part2));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", test("1,2,4", day10::part2));
        assertEquals("e0387e2ad112b7c2ef344e44885fe4d8",
                test("88,88,211,106,141,1,78,254,2,111,77,255,90,0,54,205", day10::part2));
        assertEquals("7f94112db4e32e19cf6502073c66f9bb", test(day10.getInput2(), day10::part2));
    }
}