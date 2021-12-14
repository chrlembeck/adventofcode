package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day14.Aoc2021Day14;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day14Test {

    @Test
    public void test1() {
        final Aoc2021Day14 day = new Aoc2021Day14();
        assertEquals("1588", test("""
                              NNCB
                                                            
                              CH -> B
                              HH -> N
                              CB -> H
                              NH -> C
                              HB -> C
                              HC -> B
                              HN -> C
                              NN -> C
                              BH -> H
                              NC -> B
                              NB -> B
                              BN -> B
                              BB -> N
                              BC -> B
                              CC -> N
                              CN -> C""", day::part1));
        assertEquals("3587", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day14 day = new Aoc2021Day14();
        assertEquals("2188189693529", test("""
                              NNCB
                                                            
                              CH -> B
                              HH -> N
                              CB -> H
                              NH -> C
                              HB -> C
                              HC -> B
                              HN -> C
                              NN -> C
                              BH -> H
                              NC -> B
                              NB -> B
                              BN -> B
                              BB -> N
                              BC -> B
                              CC -> N
                              CN -> C""", day::part2));
        assertEquals("3906445077999", test(day.getInput2(), day::part2));
    }
}