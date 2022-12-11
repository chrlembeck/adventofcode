package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day11.Aoc2022Day11;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo11Test {

    final static String INPUT = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
                            
            Monkey 1:
              Starting items: 54, 65, 75, 74
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 2
                If false: throw to monkey 0
                            
            Monkey 2:
              Starting items: 79, 60, 97
              Operation: new = old * old
              Test: divisible by 13
                If true: throw to monkey 1
                If false: throw to monkey 3
                            
            Monkey 3:
              Starting items: 74
              Operation: new = old + 3
              Test: divisible by 17
                If true: throw to monkey 0
                If false: throw to monkey 1""";

    private final AbstractAocBase testee = new Aoc2022Day11();

    @Test
    public void test1() {
        assertEquals("10605", test(INPUT, testee::part1));
        assertEquals("316888", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("2713310158", test(INPUT, testee::part2));
        assertEquals("35270398814", test(testee.getInput1(), testee::part2));
    }
}
