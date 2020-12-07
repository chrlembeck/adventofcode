package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day07.Aoc2020Day07;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day07Test {

    @Test
    public void test1() {
        final Aoc2020Day07 day = new Aoc2020Day07();
        assertEquals("4", test("""
                                light red bags contain 1 bright white bag, 2 muted yellow bags.
                                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                                bright white bags contain 1 shiny gold bag.
                                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                                faded blue bags contain no other bags.
                                dotted black bags contain no other bags.""", day::part1));
        assertEquals("208", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day07 day = new Aoc2020Day07();
        assertEquals("32", test("""
                                light red bags contain 1 bright white bag, 2 muted yellow bags.
                                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                                bright white bags contain 1 shiny gold bag.
                                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                                faded blue bags contain no other bags.
                                dotted black bags contain no other bags.""", day::part2));
        assertEquals("126", test("""
                                shiny gold bags contain 2 dark red bags.
                                dark red bags contain 2 dark orange bags.
                                dark orange bags contain 2 dark yellow bags.
                                dark yellow bags contain 2 dark green bags.
                                dark green bags contain 2 dark blue bags.
                                dark blue bags contain 2 dark violet bags.
                                dark violet bags contain no other bags.""", day::part2));
        assertEquals("1664", test(day.getInput1(), day::part2));
    }
}