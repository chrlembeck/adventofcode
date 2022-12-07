package de.chrlembeck.aoc2022;

import de.chrlembeck.aoc2022.day07.Aoc2022Day07;
import de.chrlembeck.aoccommon.AbstractAocBase;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2022Dayo07Test {

    private final AbstractAocBase testee = new Aoc2022Day07();

    @Test
    public void test1() {
        assertEquals("95437", test("""
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k""", testee::part1));
        assertEquals("1367870", test(testee.getInput1(), testee::part1));
    }

    @Test
    public void test2() {
        assertEquals("24933642", test("""
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k""", testee::part2));
        assertEquals("549173", test(testee.getInput1(), testee::part2));
    }
}
