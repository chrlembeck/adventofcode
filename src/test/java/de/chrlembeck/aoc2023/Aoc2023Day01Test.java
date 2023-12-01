package de.chrlembeck.aoc2023;

import de.chrlembeck.aoc2023.day01.Aoc2023Day01;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2023Day01Test {

    @Test
    public void test1() {
        final Aoc2023Day01 day = new Aoc2023Day01();
        assertEquals("142", test("""
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet""", day::part1));
        assertEquals("53386", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2023Day01 day = new Aoc2023Day01();
        assertEquals("281", test("""
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen""", day::part2));
        assertEquals("53312", test(day.getInput2(), day::part2));
    }
}
