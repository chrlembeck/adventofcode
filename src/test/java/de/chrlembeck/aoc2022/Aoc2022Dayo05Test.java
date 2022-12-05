package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day05.Aoc2022Day05;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo05Test {

    private final AbstractAocBase testee = new Aoc2022Day05();

    @Test
    public void test1() {
        assertEquals("CMZ", test("""
                    [D]
                [N] [C]
                [Z] [M] [P]
                 1   2   3
                   
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2""", testee::part1));
        assertEquals("VCTFTJQCG", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("MCD", test("""
                    [D]
                [N] [C]
                [Z] [M] [P]
                 1   2   3
                   
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2""", testee::part2));
        assertEquals("GCFGLDNJZ", test(testee.getInput2(), testee::part2));
    }
}
