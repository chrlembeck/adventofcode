package de.chrlembeck.aoc2021.day15;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.aoccommon.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aoc2021Day15 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day15().run();
    }

    @Override
    public Object part1(final Scanner input) {
        int[][] map = input.useDelimiter("\n").tokens().map(this::convertLine).toArray(int[][]::new);
        return dijkstra(map);
    }

    @Override
    public Object part2(final Scanner input) {
        final int[][] minimap = input.useDelimiter("\n").tokens().map(this::convertLine).toArray(int[][]::new);
        final int width = minimap[0].length;
        final int height = minimap.length;
        final int[][] map = new int[5 * height][5 * width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int dx = 0; dx < 5; dx++) {
                    for (int dy = 0; dy < 5; dy++) {
                        map[y + dy * height][x + dx * width] = (minimap[y][x] + dx + dy - 1) % 9 + 1;
                    }
                }
            }
        }
        return dijkstra(map);
    }

    private int dijkstra(int[][] map) {
        List<Point> pointsInProgress = new ArrayList<>();
        final int width = map[0].length;
        final int height = map.length;
        boolean[][] visited = new boolean[height][width];
        int[][] distances = new int[height][width];
        Point[][] predecessors = new Point[height][width];
        for (int y = 0; y < height; y++) {
            Arrays.fill(distances[y], Integer.MAX_VALUE);
        }
        distances[0][0] = 0;
        pointsInProgress.add(new Point(0,0));

        while (!pointsInProgress.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            Point minPos = null;
            int idx = 0;
            for (int i = 0; i < pointsInProgress.size(); i++) {
                Point candidate= pointsInProgress.get(i);
                if (distances[candidate.getY()][candidate.getX()] < minDist) {
                    minDist = distances[candidate.getY()][candidate.getX()];
                    minPos = candidate;
                    idx = i;
                }
            }
            pointsInProgress.remove(idx);
            visited[minPos.getY()][minPos.getX()] = true;
            List<Point> neighbours = List.of(minPos.left(), minPos.right(), minPos.top(), minPos.bottom());
            for (Point next : neighbours) {
                if (next.getX() >= 0 && next.getX() < width && next.getY() >= 0 && next.getY() < height && !visited[next.getY()][next.getX()]) {
                    int testDistance = distances[minPos.getY()][minPos.getX()] + map[next.getY()][next.getX()];
                    if (testDistance < distances[next.getY()][next.getX()]) {
                        distances[next.getY()][next.getX()] = testDistance;
                        predecessors[next.getY()][next.getX()] = minPos;
                        pointsInProgress.add(next);
                    }
                }
            }
        }
        return distances[height - 1][width - 1];
    }

    private int[] convertLine(String line) {
        int[] result = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            result[i] = line.charAt(i) - '0';
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day15.txt";
    }
}