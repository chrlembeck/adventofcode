package de.chrlembeck.aoc2015.day03;

import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day03 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day03().run();
    }

    @Override
    public String part1(final Scanner input) {
        Point pos = new Point(0, 0);
        final Set<Point> visitedPlaces = new HashSet<>();
        visitedPlaces.add(pos);
        final String line = input.nextLine();
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '<':
                    pos = new Point(pos.x - 1, pos.y);
                    break;
                case '>':
                    pos = new Point(pos.x + 1, pos.y);
                    break;
                case '^':
                    pos = new Point(pos.x, pos.y - 1);
                    break;
                case 'v':
                    pos = new Point(pos.x, pos.y + 1);
                    break;
                default:
                    throw new IllegalArgumentException(Character.toString(line.charAt(i)));
            }
            visitedPlaces.add(pos);
        }
        return Integer.toString(visitedPlaces.size());
    }

    @Override
    public String part2(final Scanner input) {
        Point santaPos = new Point(0, 0);
        Point robotPos = new Point(0, 0);
        final Set<Point> visitedPlaces = new HashSet<>();
        visitedPlaces.add(santaPos);
        final String line = input.nextLine();
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '<':
                    if (i % 2 == 1) {
                        santaPos = new Point(santaPos.x - 1, santaPos.y);
                    } else {
                        robotPos = new Point(robotPos.x - 1, robotPos.y);
                    }
                    break;
                case '>':
                    if (i % 2 == 1) {
                        santaPos = new Point(santaPos.x + 1, santaPos.y);
                    } else {
                        robotPos = new Point(robotPos.x + 1, robotPos.y);
                    }
                    break;
                case '^':
                    if (i % 2 == 1) {
                        santaPos = new Point(santaPos.x, santaPos.y - 1);
                    } else {
                        robotPos = new Point(robotPos.x, robotPos.y - 1);
                    }
                    break;
                case 'v':
                    if (i % 2 == 1) {
                        santaPos = new Point(santaPos.x, santaPos.y + 1);
                    } else {
                        robotPos = new Point(robotPos.x, robotPos.y + 1);
                    }
                    break;
                default:
                    throw new IllegalArgumentException(Character.toString(line.charAt(i)));
            }
            visitedPlaces.add(santaPos);
            visitedPlaces.add(robotPos);
        }
        return Integer.toString(visitedPlaces.size());
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day03.txt";
    }
}