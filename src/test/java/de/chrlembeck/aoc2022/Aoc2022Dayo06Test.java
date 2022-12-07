package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day06.Aoc2022Day06;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo06Test {

    private final AbstractAocBase testee = new Aoc2022Day06();

    @Test
    public void test1() {
        assertEquals("5", test("bvwbjplbgvbhsrlpgdmjqwftvncz", testee::part1));
        assertEquals("6", test("nppdvjthqldpwncqszvftbrmjlhg", testee::part1));
        assertEquals("10", test("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", testee::part1));
        assertEquals("11", test("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", testee::part1));
        assertEquals("1538", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("19", test("mjqjpqmgbljsphdztnvjfqwrcgsmlb", testee::part2));
        assertEquals("23", test("bvwbjplbgvbhsrlpgdmjqwftvncz", testee::part2));
        assertEquals("23", test("nppdvjthqldpwncqszvftbrmjlhg", testee::part2));
        assertEquals("29", test("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", testee::part2));
        assertEquals("26", test("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", testee::part2));
        assertEquals("2315", test(testee.getInput1(), testee::part2));
    }
}
