package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day03.Aoc2022Day03;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo03Test {

    private final AbstractAocBase testee = new Aoc2022Day03();

    @Test
    public void test1() {
        assertEquals("157", test("""
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw""", testee::part1));
        assertEquals("8176", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("70", test("""
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw""", testee::part2));
        assertEquals("2689", test(testee.getInput2(), testee::part2));
    }
}
