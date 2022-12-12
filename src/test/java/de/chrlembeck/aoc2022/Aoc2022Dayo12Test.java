package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day12.Aoc2022Day12;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo12Test {

    private final AbstractAocBase testee = new Aoc2022Day12();

    @Test
    public void test1() {
        assertEquals("31", test("""
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi""", testee::part1));
        assertEquals("462", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("29", test("""
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi""", testee::part2));
        assertEquals("451", test(testee.getInput1(), testee::part2));
    }
}
