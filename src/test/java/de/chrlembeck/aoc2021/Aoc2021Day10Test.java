package de.chrlembeck.aoc2021;

import de.chrlembeck.aoc2021.day10.Aoc2021Day10;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2021Day10Test {

    @Test
    public void test1() {
        final Aoc2021Day10 day = new Aoc2021Day10();
        assertEquals("26397", test("""
                              [({(<(())[]>[[{[]{<()<>>
                              [(()[<>])]({[<{<<[]>>(
                              {([(<{}[<>[]}>{[]{[(<()>
                              (((({<>}<{<{<>}{[]{[]{}
                              [[<[([]))<([[{}[[()]]]
                              [{[{({}]{}}([{[{{{}}([]
                              {<[[]]>}<{[{[{[]{()[[[]
                              [<(<(<(<{}))><([]([]()
                              <{([([[(<>()){}]>(<<{{
                              <{([{{}}[<[[[<>{}]]]>[]]""", day::part1));
        assertEquals("462693", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2021Day10 day = new Aoc2021Day10();
        assertEquals("288957", test("""
                              [({(<(())[]>[[{[]{<()<>>
                              [(()[<>])]({[<{<<[]>>(
                              {([(<{}[<>[]}>{[]{[(<()>
                              (((({<>}<{<{<>}{[]{[]{}
                              [[<[([]))<([[{}[[()]]]
                              [{[{({}]{}}([{[{{{}}([]
                              {<[[]]>}<{[{[{[]{()[[[]
                              [<(<(<(<{}))><([]([]()
                              <{([([[(<>()){}]>(<<{{
                              <{([{{}}[<[[[<>{}]]]>[]]""", day::part2));
        assertEquals("3094671161", test(day.getInput2(), day::part2));
    }
}