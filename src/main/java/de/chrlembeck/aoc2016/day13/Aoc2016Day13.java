package de.chrlembeck.aoc2016.day13;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Aoc2016Day13 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day13().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return wayTo(input.nextInt(), 31, 39, false);
    }

    @Override
    public Integer part2(final Scanner input) {
        return wayTo(input.nextInt(), 31, 39, true);
    }

    static boolean isFree(int input, long x, long y) {
        if (x < 0 || y < 0) {
            return false;
        }
        long code = x * x + 3 * x + 2 * x * y + y + y * y + input;
        boolean result = true;
        while (code != 0) {
            if ((code & 1) == 1) {
                result = !result;
            }
            code >>>= 1;
        }
        return result;
    }

    public int wayTo(int input, int x, int y, boolean step2) {
        PriorityQueue<Position> queue = new PriorityQueue<>();
        Set<Point> ready = new HashSet<>();
        queue.add(new Position(1, 1, 0));
        int counter = 0;
        while (!queue.isEmpty()) {
            Position currentPos = queue.poll();
            if (currentPos.dist > 0 && currentPos.dist <= 50) {
                counter++;
            } else if (step2 && currentPos.dist >50) {
                return counter;
            }
            if (currentPos.x == x && currentPos.y == y) {
                return currentPos.dist;
            }
            Point left = new Point(currentPos.x - 1, currentPos.y);
            Point right = new Point(currentPos.x + 1, currentPos.y);
            Point top = new Point(currentPos.x, currentPos.y - 1);
            Point bottom = new Point(currentPos.x, currentPos.y + 1);
            if (isFree(input, currentPos.x - 1, currentPos.y) && !ready.contains(left)) {
                queue.add(new Position(currentPos.x - 1, currentPos.y, currentPos.dist + 1));
                ready.add(left);
            }
            if (isFree(input, currentPos.x + 1, currentPos.y) && !ready.contains(right)) {
                queue.add(new Position(currentPos.x + 1, currentPos.y, currentPos.dist + 1));
                ready.add(right);
            }
            if (isFree(input, currentPos.x, currentPos.y - 1) && !ready.contains(top)) {
                queue.add(new Position(currentPos.x, currentPos.y - 1, currentPos.dist + 1));
                ready.add(top);
            }
            if (isFree(input, currentPos.x, currentPos.y + 1) && !ready.contains(bottom)) {
                queue.add(new Position(currentPos.x, currentPos.y + 1, currentPos.dist + 1));
                ready.add(bottom);
            }
        }
        return -1;
    }

    static class Position implements Comparable<Position> {

        int x;

        int y;

        int dist;

        Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Position o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + dist + ")";
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day13.txt";
    }
}