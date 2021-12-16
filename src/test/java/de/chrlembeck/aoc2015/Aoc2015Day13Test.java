package de.chrlembeck.aoc2015;

import de.chrlembeck.aoc2015.day13.Aoc2015Day13;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2015Day13Test {

    private static final String INPUT = """
                                        Alice would gain 54 happiness units by sitting next to Bob.
                                        Alice would lose 79 happiness units by sitting next to Carol.
                                        Alice would lose 2 happiness units by sitting next to David.
                                        Bob would gain 83 happiness units by sitting next to Alice.
                                        Bob would lose 7 happiness units by sitting next to Carol.
                                        Bob would lose 63 happiness units by sitting next to David.
                                        Carol would lose 62 happiness units by sitting next to Alice.
                                        Carol would gain 60 happiness units by sitting next to Bob.
                                        Carol would gain 55 happiness units by sitting next to David.
                                        David would gain 46 happiness units by sitting next to Alice.
                                        David would lose 7 happiness units by sitting next to Bob.
                                        David would gain 41 happiness units by sitting next to Carol.""";

    @Test
    public void test1() {
        final Aoc2015Day13 day = new Aoc2015Day13();
        assertEquals("330", test(INPUT, day::part1));
        assertEquals("664", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2015Day13 day = new Aoc2015Day13();
        assertEquals("640", test(day.getInput2(), day::part2));
    }
}