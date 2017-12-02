package de.chrlembeck.aoc2017.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day1Test {

    @Test
    public void test1() {
        final Day1 day1 = new Day1();
        assertEquals("3", day1.part1("1122"));
        assertEquals("4", day1.part1("1111"));
        assertEquals("0", day1.part1("1234"));
        assertEquals("9", day1.part1("91212129"));
        assertEquals("1228", day1.part1(day1.getInput1()));
    }

    @Test
    public void test2() {
        final Day1 day1 = new Day1();
        assertEquals("6", day1.part2("1212"));
        assertEquals("0", day1.part2("1221"));
        assertEquals("4", day1.part2("123425"));
        assertEquals("12", day1.part2("123123"));
        assertEquals("4", day1.part2("12131415"));
        assertEquals("1238", day1.part2(day1.getInput2()));
    }
}