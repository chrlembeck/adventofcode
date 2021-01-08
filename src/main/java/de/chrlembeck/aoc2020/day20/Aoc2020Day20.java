package de.chrlembeck.aoc2020.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Aoc2020Day20 extends AbstractAocBase {

    public static final char[][] SEA_MONSTER = {
            "                  # ".toCharArray(),
            "#    ##    ##    ###".toCharArray(),
            " #  #  #  #  #  #   ".toCharArray()
    };

    public static void main(final String[] args) {
        new Aoc2020Day20().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final List<Tile> tiles = readTiles(input);
        final int size = (int) Math.sqrt(tiles.size());
        final Tile[][] imageGrid = new Tile[size][size];
        puzzle(imageGrid, tiles, 0, 0);
        return imageGrid[0][0].getIdentifier()
                .multiply(imageGrid[size - 1][0].getIdentifier())
                .multiply(imageGrid[0][size - 1].getIdentifier())
                .multiply(imageGrid[size - 1][size - 1].getIdentifier());
    }

    @Override
    public Object part2(final Scanner input) {
        final List<Tile> tiles = readTiles(input);
        final int size = (int) Math.sqrt(tiles.size());
        final Tile[][] imageGrid = new Tile[size][size];
        puzzle(imageGrid, tiles, 0, 0);
        char[][] image = new char[8 * size][8 * size];
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image.length; x++) {
                image[y][x] = imageGrid[y / 8][x / 8].getRepresentation()[1 + y % 8][1 + x % 8];
            }
        }
        int seaMonsters = 0;
        char[][] flipped = Tile.flip(image);
        for (int i = 0; i < 4; i++) {
            seaMonsters = Math.max(seaMonsters, countSeaMonsters(image = Tile.rotate(image)));
            seaMonsters = Math.max(seaMonsters, countSeaMonsters(flipped = Tile.rotate(flipped)));
        }
        return count(image, '#') - seaMonsters * count(SEA_MONSTER, '#');
    }

    private static int count(final char[][] image, final char character) {
        int counter = 0;
        for (final char[] row : image) {
            for (final char element : row) {
                if (element == character) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private void puzzle(final Tile[][] image, final List<Tile> tiles, final int posX, final int posY) {
        for (int i = 0; i < tiles.size(); i++) {
            final Tile currentTile = tiles.remove(i);
            for (final Tile variant : currentTile.getVariants()) {
                if (fits(image, variant, posX, posY)) {
                    image[posY][posX] = variant;
                    puzzle(image, tiles, (posX + 1) % image.length, posX == image.length - 1 ? posY + 1 : posY);
                    if (tiles.isEmpty()) {
                        return;
                    }
                    image[posY][posX] = null;
                }
            }
            tiles.add(i, currentTile);
        }
    }

    private boolean fits(final Tile[][] image, final Tile newTile, final int posX, final int posY) {
        if (posX > 0 && image[posY][posX - 1] != null && image[posY][posX - 1].getRight() != newTile.getLeft()) {
            return false;
        }
        if (posY > 0 && image[posY - 1][posX] != null && image[posY - 1][posX].getBottom() != newTile.getTop()) {
            return false;
        }
        if (posX < image.length - 1 && image[posY][posX + 1] != null && image[posY][posX + 1].getLeft() != newTile.getRight()) {
            return false;
        }
        return posY >= image.length - 1 || image[posY + 1][posX] == null || image[posY + 1][posX].getTop() == newTile.getBottom();
    }

    private List<Tile> readTiles(final Scanner input) {
        final List<Tile> tiles = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            final BigInteger identifier = new BigInteger(line.substring(5, 9));
            final char[][] representation = new char[10][];
            for (int i = 0; i < 10; i++) {
                line = input.nextLine();
                representation[i] = line.toCharArray();
            }
            if (input.hasNextLine()) {
                input.nextLine();
            }
            tiles.add(new Tile(identifier, representation));
        }
        return tiles;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day20.txt";
    }

    public static int countSeaMonsters(final char[]... image) {
        return IntStream.rangeClosed(0, image.length - SEA_MONSTER.length)
                .map(y -> (int)IntStream.rangeClosed(0, image[0].length - SEA_MONSTER[0].length).filter(x -> isMonsterAt(image, x, y)).count())
                .sum();
    }

    private static boolean isMonsterAt(final char[][] image, final int posX, final int posY) {
        for (int my = 0; my < SEA_MONSTER.length; my++) {
            for (int mx = 0; mx < SEA_MONSTER[0].length; mx++) {
                if (SEA_MONSTER[my][mx] == '#' && image[posY + my][posX + mx] != '#') {
                    return false;
                }
            }
        }
        return true;
    }
}