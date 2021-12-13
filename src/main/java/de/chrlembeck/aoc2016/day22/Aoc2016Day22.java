package de.chrlembeck.aoc2016.day22;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2016Day22 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("/dev/grid/node-x(\\d+)-y(\\d+)\\W+(\\d+)T\\W+(\\d+)T\\W+(\\d+)T\\W+\\d+%");

    public static void main(final String[] args) {
        new Aoc2016Day22().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        Node[][] nodes = readNodes(input);
        int counter = 0;
        for (int y1 = 0; y1 < nodes.length; y1++) {
            for (int x1 = 0; x1 < nodes[0].length; x1++) {
                Node nodeA = nodes[y1][x1];
                for (int y2 = 0; y2 < nodes.length; y2++) {
                    for (int x2 = 0; x2 < nodes[0].length; x2++) {
                        Node nodeB = nodes[y2][x2];
                        if (nodeA != nodeB && nodeA.getUsed() > 0 && nodeA.getUsed() <= nodeB.getAvail()) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    private Node[][] readNodes(Scanner input) {
        Map<Point, Node> nodeMap = new HashMap<>();
        int maxX = 0;
        int maxY = 0;
        while (input.hasNextLine()) {
            Matcher matcher = REGEX.matcher(input.nextLine());
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);
                nodeMap.put(new Point(x, y), new Node(Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))));
            }
        }

        Node[][] nodes = new Node[maxY + 1][maxX + 1];
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                nodes[y][x] = nodeMap.get(new Point(x, y));
            }
        }

        nodes[0][maxX].mark(true);
        return nodes;
    }

    @Override
    public Integer part2(final Scanner input) {
        Node[][] nodes = readNodes(input);
        Point[][] dataPathes = calculatePathes(nodes, nodes[0].length - 1, 0);
        List<Point> dataPath = extractPath(dataPathes, 0, 0);

        Point startPoint = null;
        for (int y = 0; y < nodes.length; y++) {
            for (int x = 0; x < nodes[0].length; x++) {
                if (nodes[y][x].getUsed() == 0) {
                    startPoint = new Point(x, y);
                }
            }
        }
        if (startPoint == null) {
            throw new IllegalStateException("no staring point found.");
        }
        Point firstTarget = dataPath.get(dataPath.size() - 2);
        List<Point> firstPath = extractPath(calculatePathes(nodes, firstTarget.x, firstTarget.y), startPoint.x, startPoint.y);

        return firstPath.size() + 5 * (dataPath.size() - 1) + 1;
    }

    private List<Point> extractPath(Point[][] dataPathes, int fromX, int fromY) {
        List<Point> dataPath = new ArrayList<>();
        Point p = dataPathes[fromY][fromX];
        while (dataPathes[p.y][p.x] != p) {
            dataPath.add(p);
            p = dataPathes[p.y][p.x];
        }
        return dataPath;
    }

    private Point[][] calculatePathes(Node[][] nodes, int destinationX, int destinationY) {
        int load = nodes[0][nodes[0].length-1].getUsed();
        Point[][] pathes = new Point[nodes.length][nodes[0].length];
        pathes[destinationY][destinationX] = new Point(destinationX, destinationY);
        Point current = new Point(destinationX, destinationY);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(current);
        while (!queue.isEmpty()) {
            current = queue.remove();
            Point p = new Point(current.x - 1, current.y);
            if (current.x > 0 && nodes[p.y][p.x].getSize() >= load && pathes[p.y][p.x] == null
                    && nodes[p.y][p.x].getUsed() <= nodes[current.y][current.x].getSize()) {
                queue.offer(p);
                pathes[p.y][p.x] = current;
            }
            p = new Point(current.x, current.y - 1);
            if (current.y > 0 && nodes[p.y][p.x].getSize() >= load && pathes[p.y][p.x] == null
                    && nodes[p.y][p.x].getUsed() <= nodes[current.y][current.x].getSize()) {
                queue.offer(p);
                pathes[p.y][p.x] = current;
            }
            p = new Point(current.x + 1, current.y);
            if (current.x < nodes[0].length-1 && nodes[p.y][p.x].getSize() >= load && pathes[p.y][p.x] == null
                    && nodes[p.y][p.x].getUsed() <= nodes[current.y][current.x].getSize()) {
                queue.offer(p);
                pathes[p.y][p.x] = current;
            }
            p = new Point(current.x, current.y + 1);
            if (current.y < nodes.length-1 && nodes[p.y][p.x].getSize() >= load && pathes[p.y][p.x] == null
                    && nodes[p.y][p.x].getUsed() <= nodes[current.y][current.x].getSize()) {
                queue.offer(p);
                pathes[p.y][p.x] = current;
            }
        }
        return pathes;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day22.txt";
    }
}