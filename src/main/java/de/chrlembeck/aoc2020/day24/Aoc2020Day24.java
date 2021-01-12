package de.chrlembeck.aoc2020.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Aoc2020Day24 extends AbstractAocBase {

    public static final Boolean BLACK = Boolean.TRUE;

    public static final Boolean WHITE = Boolean.FALSE;

    public static void main(final String[] args) {
        new Aoc2020Day24().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Map<Point, Boolean> tiles = new HashMap<>();
        input.tokens().forEach(line -> flip(tiles, line));
        return tiles.values().stream().filter(BLACK::equals).count();
    }

    @Override
    public Object part2(final Scanner input) {
        final Map<Point, Boolean> tiles = new HashMap<>();
        input.tokens().forEach(line -> flip(tiles, line));
        IntStream.rangeClosed(1,100).forEach(i->doArt(tiles));
        return tiles.values().stream().filter(BLACK::equals).count();
    }

    private void flip(final Map<Point, Boolean> tiles, final String line) {
        int index = 0;
        final Point pos = new Point(0, 0);
        while (index < line.length()) {
            switch (line.charAt(index++)) {
                case 'e':
                    pos.x++;
                    break;
                case 'w':
                    pos.x--;
                    break;
                case 's':
                    pos.y--;
                    if (line.charAt(index++) == 'w') {
                        pos.x--;
                    }
                    break;
                case 'n':
                    pos.y++;
                    if (line.charAt(index++) == 'e') {
                        pos.x++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException(line);
            }
        }
        tiles.put(pos, !tiles.getOrDefault(pos, Boolean.FALSE));
    }

    private void doArt(final Map<Point, Boolean> tiles) {
        final Map<Point, Boolean> nextDay = new HashMap<>(tiles);
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (final Point point : tiles.keySet()) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }
        for (int y = minY - 1; y <= maxY + 1; y++) {
            for (int x = minX - 1; x <= maxX + 1; x++) {
                final Point pos = new Point(x, y);
                int blackTiles = 0;
                if (BLACK.equals(tiles.get(new Point(pos.x + 1, pos.y)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(new Point(pos.x - 1, pos.y)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(new Point(pos.x + 1, pos.y + 1)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(new Point(pos.x, pos.y + 1)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(new Point(pos.x, pos.y - 1)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(new Point(pos.x - 1, pos.y - 1)))) {
                    blackTiles++;
                }
                if (BLACK.equals(tiles.get(pos)) && (blackTiles == 0 || blackTiles > 2)) {
                    nextDay.put(pos, WHITE);
                }
                if (WHITE.equals(tiles.getOrDefault(pos, WHITE)) && blackTiles == 2) {
                    nextDay.put(pos, BLACK);
                }
            }
        }
        tiles.clear();
        tiles.putAll(nextDay);
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day24.txt";
    }
}