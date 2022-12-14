package de.chrlembeck.aoc2022.day12;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

public class Aoc2022Day12 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2022Day12().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        char[][] area = parseArea(input);
        Point start = null;
        for (int y = 0; y < area.length && start == null; y++) {
            char[] row = area[y];
            for (int x = 0; x < row.length && start == null; x++) {
                if (row[x] == 'S') {
                    start = new Point(x, y);
                }
            }
        }
        return findPath(area, start).orElseThrow();
    }

    @Override
    public Integer part2(final Scanner input) {
        int best = Integer.MAX_VALUE;
        char[][] area = parseArea(input);
        for (int y = 0; y < area.length; y++) {
            for (int x = 0; x < area[0].length; x++) {
                if (area[y][x] == 'a' || area[y][x] == 'S') {
                    Optional<Integer> path = findPath(area, new Point(x, y));
                    if (path.isPresent()) {
                        best = Math.min(best, path.get());
                    }
                }
            }
        }
        return best;
    }

    private Optional<Integer> findPath(char[][] area, Point start) {
        boolean[][] visited = new boolean[area.length][area[0].length];
        Queue<Point> next = new LinkedList<>();
        next.offer(start);
        visited[start.y][start.x] = true;
        int steps = 0;
        while (!next.isEmpty()) {
            Queue<Point> current = next;
            next = new LinkedList<>();
            while (!current.isEmpty()) {
                Point p = current.poll();
                if (area[p.y][p.x] == 'E') {
                    return Optional.of(steps);
                }
                char elevation = area[p.y][p.x];
                if (elevation == 'S') {
                    elevation = 'a';
                }
                if (check(p.x, p.y, p.x - 1, p.y, area, visited)) {
                    next.offer(new Point(p.x - 1, p.y));
                    visited[p.y][p.x - 1] = true;
                }
                if (check(p.x, p.y, p.x , p.y-1, area, visited)) {
                    next.offer(new Point(p.x, p.y - 1));
                    visited[p.y - 1][p.x] = true;
                }
                if (check(p.x, p.y, p.x + 1, p.y, area, visited)) {
                    next.offer(new Point(p.x + 1, p.y));
                    visited[p.y][p.x + 1] = true;
                }
                if (check(p.x, p.y, p.x , p.y+1, area, visited)) {
                    next.offer(new Point(p.x, p.y + 1));
                    visited[p.y + 1][p.x] = true;
                }
            }
            steps++;
        }
        return Optional.empty();
    }

    private boolean check(int x, int y, int xn, int yn, char[][] area, boolean[][] visited) {
        if (xn < 0 || yn < 0 || yn >= area.length || xn >= area[0].length || visited[yn][xn]) {
            return false;
        }
        char elevation = area[y][x];
        if (elevation == 'S') {
            elevation = 'a';
        }
        char newElevation = area[yn][xn];
        if (newElevation == 'E') {
            newElevation = 'z';
        }
        return newElevation - elevation <= 1;
    }


    private char[][] parseArea(final Scanner input) {
        return input.useDelimiter("\\n").tokens().map(String::toCharArray).toArray(char[][]::new);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day12.txt";
    }
}