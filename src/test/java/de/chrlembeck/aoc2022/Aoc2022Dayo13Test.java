package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day13.Aoc2022Day13;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo13Test {

    private final AbstractAocBase testee = new Aoc2022Day13();

    @Test
    public void test1() {
        assertEquals("1", test("""
                [[[0],5,9,2,8],[7,10,0],[2,0],[[9,[3],[0],2,[0,2,0,9]],4,[0]]]
                [[[[0],[7,0,0]],1,9,[[2]]]]""", testee::part1));
        assertEquals("0", test("""
                []
                []""", testee::part1));
        assertEquals("1", test("""
                [1]
                [[2]]""", testee::part1));
        assertEquals("1", test("""
                [[1]]
                [2]""", testee::part1));
        assertEquals("1", test("""
                [1,1,3,1,1]
                [1,1,5,1,1]""", testee::part1));
        assertEquals("1", test("""
                [[1],[2,3,4]]
                [[1],4]""", testee::part1));
        assertEquals("0", test("""
                [9]
                [[8,7,6]]""", testee::part1));
        assertEquals("1", test("""
                [[4,4],4,4]
                [[4,4],4,4,4]""", testee::part1));
        assertEquals("0", test("""
                [7,7,7,7]
                [7,7,7]""", testee::part1));
        assertEquals("1", test("""
                []
                [3]""", testee::part1));
        assertEquals("0", test("""
                [[[]]]
                [[]]""", testee::part1));
        assertEquals("0", test("""
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]""", testee::part1));
        assertEquals("13", test("""
                [1,1,3,1,1]
                [1,1,5,1,1]
                                        
                [[1],[2,3,4]]
                [[1],4]
                                        
                [9]
                [[8,7,6]]
                                        
                [[4,4],4,4]
                [[4,4],4,4,4]
                                        
                [7,7,7,7]
                [7,7,7]
                                        
                []
                [3]
                                        
                [[[]]]
                [[]]
                                        
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]""", testee::part1));
        assertEquals("6076", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("140", test("""
                [1,1,3,1,1]
                [1,1,5,1,1]
                                        
                [[1],[2,3,4]]
                [[1],4]
                                        
                [9]
                [[8,7,6]]
                                        
                [[4,4],4,4]
                [[4,4],4,4,4]
                                        
                [7,7,7,7]
                [7,7,7]
                                        
                []
                [3]
                                        
                [[[]]]
                [[]]
                                        
                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]""", testee::part2));
        assertEquals("24805", test(testee.getInput1(), testee::part2));
    }
}
