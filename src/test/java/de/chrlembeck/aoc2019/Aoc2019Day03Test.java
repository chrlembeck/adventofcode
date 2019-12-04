package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day03.Aoc2019Day03;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day03Test {

    @Test
    public void test1() {
        final Aoc2019Day03 day3 = new Aoc2019Day03();
        assertEquals("159", test("R75,D30,R83,U83,L12,D49,R71,U7,L72\n"
                + "U62,R66,U55,R34,D71,R55,D58,R83", day3::part1));
        assertEquals("135", test("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n"
                + "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", day3::part1));
        assertEquals("1674", test(day3.getInput1(), day3::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day03 day3 = new Aoc2019Day03();
        assertEquals("610", test("R75,D30,R83,U83,L12,D49,R71,U7,L72\n"
                + "U62,R66,U55,R34,D71,R55,D58,R83", day3::part2));
        assertEquals("410", test("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n"
                + "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7", day3::part2));
        assertEquals("14012", test(day3.getInput2(), day3::part2));
    }
}