package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day07.Aoc2019Day07;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day07Test {

    @Test
    public void test1() {
        final Aoc2019Day07 day = new Aoc2019Day07();
        assertEquals("43210", test("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0", day::part1));
        assertEquals("54321", test("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0", day::part1));
        assertEquals("65210", test("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0", day::part1));
        assertEquals("255840", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day07 day = new Aoc2019Day07();
        assertEquals("139629729", test("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5", day::part2));
        assertEquals("18216", test("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10", day::part2));
        assertEquals("84088865", test(day.getInput2(), day::part2));
    }
}