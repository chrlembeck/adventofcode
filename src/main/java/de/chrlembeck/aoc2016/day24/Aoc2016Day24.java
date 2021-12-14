package de.chrlembeck.aoc2016.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Permutation;
import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aoc2016Day24 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day24().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        BiFunction<int[][], Integer[], Integer> lengthFunction = (distances, order) -> {
            int current = 0;
            int length = 0;
            for (Integer next : order) {
                length += distances[current][next];
                current = next;
            }
            return length;
        };
        return calc(input, lengthFunction);
    }

    @Override
    public Integer part2(final Scanner input) {
        BiFunction<int[][], Integer[], Integer> lengthFunction = (distances, order) -> {
            int current = 0;
            int length = 0;
            for (Integer next : order) {
                length += distances[current][next];
                current = next;
            }
            length += distances[current][0];
            return length;
        };
        return calc(input, lengthFunction);
    }

    private int calc(Scanner input, BiFunction<int[][], Integer[], Integer> lengthFunction) {
        List<String> area = input.tokens().collect(Collectors.toList());
        Map<Integer, Point> pois = new TreeMap<>();
        for (int y = 0; y < area.size(); y++) {
            for (int x = 0; x < area.get(0).length(); x++) {
                if (Character.isDigit(area.get(y).charAt(x))) {
                    pois.put(area.get(y).charAt(x) - '0', new Point(x, y));
                }
            }
        }
        int[][] distances = new int[pois.size()][pois.size()];
        for (int i = 1; i < distances.length; i++) {
            for (int j = 0; j < i; j++) {
                int dist = calcDistance(area, pois.get(i), pois.get(j));
                distances[i][j] = dist;
                distances[j][i] = dist;
            }
        }
        Permutation<Integer> perm = new Permutation<>(IntStream.range(1, pois.size()).mapToObj(Integer::valueOf).collect(Collectors.toList()), Integer[]::new);
        int min = Integer.MAX_VALUE;
        for (Integer[] order : perm) {
            int length = lengthFunction.apply(distances, order);
            min = Math.min(length, min);
        }
        return min;
    }

    private int calcDistance(List<String> area, Point from, Point to) {
        Map<Point, Integer> distances = new HashMap<>();
        distances.put(from, 0);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(from);
        while (!distances.containsKey(to)) {
            Point current = queue.remove();
            int currentDist = distances.get(current);
            Point left = new Point(current.x - 1, current.y);
            Point right = new Point(current.x + 1, current.y);
            Point top = new Point(current.x, current.y - 1);
            Point bottom = new Point(current.x, current.y + 1);
            List.of(left, right, top, bottom).stream().filter(p -> !distances.containsKey(p)).forEach(
                    p -> {
                        if (area.get(p.y).charAt(p.x) != '#') {
                            distances.put(p, currentDist + 1);
                            queue.offer(p);
                        }
                    }
            );
        }
        return distances.get(to);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day24.txt";
    }
}