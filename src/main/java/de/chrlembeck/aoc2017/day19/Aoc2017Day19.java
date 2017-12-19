package de.chrlembeck.aoc2017.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day19 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2017Day19().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input)[0];
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input)[1];
    }

    private String[] calc(final Scanner input) {
        final char[][] diagram = readDiagram(input);
        final StringBuilder result = new StringBuilder();
        Position pos = findStart(diagram[0]);
        int stepCount = 1;
        while (canMove(diagram, pos)) {
            final Position nextPos = nextPos(diagram, pos);
            pos = nextPos;
            if (Character.isLetter(diagram[pos.posY][pos.posX])) {
                result.append(diagram[pos.posY][pos.posX]);
            }
            stepCount++;
        }
        return new String[] { result.toString(), Integer.toString(stepCount) };
    }

    private Position nextPos(final char[][] diagram, final Position pos) {
        Position next = pos.forward();
        if (get(diagram, next) != ' ') {
            return next;
        }
        next = pos.left();
        if (get(diagram, next) != ' ') {
            return next;
        }
        next = pos.right();
        if (get(diagram, next) != ' ') {
            return next;
        }
        throw new RuntimeException("where to go?");
    }

    private boolean canMove(final char[][] diagram, final Position pos) {
        return get(diagram, pos.forward()) != ' '
                || get(diagram, pos.left()) != ' '
                || get(diagram, pos.right()) != ' ';
    }

    public char get(final char[][] diagram, final Position pos) {
        final int height = diagram.length;
        final int width = diagram[0].length;
        return pos.posX < 0 || pos.posY < 0 || pos.posX >= width || pos.posY >= height ? ' '
                : diagram[pos.posY][pos.posX];
    }

    private Position findStart(final char... firstLine) {
        for (int x = 0; x < firstLine.length; x++) {
            if (firstLine[x] == '|') {
                return new Position(x, 0, Direction.DOWN);
            }
        }
        throw new IllegalArgumentException("no start");
    }

    private char[][] readDiagram(final Scanner input) {
        final List<String> lines = new ArrayList<>();
        int width = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            lines.add(line);
            width = Math.max(line.length(), width);
        }
        final int height = lines.size();
        final char[][] diagram = new char[height][width];
        for (int y = 0; y < height; y++) {
            final String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                diagram[y][x] = x < line.length() ? line.charAt(x) : ' ';
            }
        }
        return diagram;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day19.txt";
    }
}