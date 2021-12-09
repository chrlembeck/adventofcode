package de.chrlembeck.aoc2019;

import de.chrlembeck.aoc2019.day10.Aoc2019Day10;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.Scanner;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2019Day10Test {

    @Test
    public void angle() {
        assertEquals(0, Aoc2019Day10.angle(0,-1));
        assertEquals(45, Aoc2019Day10.angle(1,-1));
        assertEquals(90, Aoc2019Day10.angle(1,0));
        assertEquals(135, Aoc2019Day10.angle(1,1));
        assertEquals(180, Aoc2019Day10.angle(0,1));
        assertEquals(225, Aoc2019Day10.angle(-1,1));
        assertEquals(270, Aoc2019Day10.angle(-1,0));
        assertEquals(315, Aoc2019Day10.angle(-1,-1));
    }

    @Test
    public void compare() {
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(1,0), new Point(2,0)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(1,1), new Point(2,2)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(0,1), new Point(0,2)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(-1,1), new Point(-2,2)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(-1,0), new Point(-2,0)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(-1,-1), new Point(-2,-2)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(0,-1), new Point(0,-2)));
        assertEquals(0, Aoc2019Day10.ANGLE_COMPARATOR.compare(new Point(1,-1), new Point(2,-2)));
    }

    @Test
    public void test1() {
        final Aoc2019Day10 day = new Aoc2019Day10();
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,4,0));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,0,2));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,1,2));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,2,2));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,3,2));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,4,2));
        assertEquals(false, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,4,3));
        assertEquals(false, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,3,4));
        assertEquals(true, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 1,0,4,4));

        assertEquals(false, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 4,0,4,3));
        assertEquals(false, day.isVisible(day.parseAstroids(new Scanner(".#..#\n.....\n#####\n....#\n...##")), 4,0,4,4));


        assertEquals("8", test("""
                              .#..#
                              .....
                              #####
                              ....#
                              ...##""", day::part1));
        assertEquals("33", test("""
                              ......#.#.
                              #..#.#....
                              ..#######.
                              .#.#.###..
                              .#..#.....
                              ..#....#.#
                              #..#....#.
                              .##.#..###
                              ##...#..#.
                              .#....####""", day::part1));
        assertEquals("35", test("""
                                 #.#...#.#.
                                 .###....#.
                                 .#....#...
                                 ##.#.#.#.#
                                 ....#.#.#.
                                 .##..###.#
                                 ..#...##..
                                 ..##....##
                                 ......#...
                                 .####.###.""", day::part1));
        assertEquals("41", test("""
                                 .#..#..###
                                 ####.###.#
                                 ....###.#.
                                 ..###.##.#
                                 ##.##.#.#.
                                 ....###..#
                                 ..#.#..#.#
                                 #..#.#.###
                                 .##...##.#
                                 .....#.#..""", day::part1));
        assertEquals("210", test("""
                                   .#..##.###...#######
                                   ##.############..##.
                                   .#.######.########.#
                                   .###.#######.####.#.
                                   #####.##.#.##.###.##
                                   ..#####..#.#########
                                   ####################
                                   #.####....###.#.#.##
                                   ##.#################
                                   #####.##.###..####..
                                   ..######..##.#######
                                   ####.##.####...##..#
                                   .#####..#.######.###
                                   ##...#.##########...
                                   #.##########.#######
                                   .####.#.###.###.#.##
                                   ....##.##.###..#####
                                   .#.#.###########.###
                                   #.#.#.#####.####.###
                                   ###.##.####.##.#..##""", day::part1));

        assertEquals("299", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2019Day10 day = new Aoc2019Day10();
        assertEquals("1419", test(day.getInput2(), day::part2));
    }
}