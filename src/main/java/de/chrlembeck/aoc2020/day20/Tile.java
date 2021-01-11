package de.chrlembeck.aoc2020.day20;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tile {

    private final BigInteger identifier;

    private final int left;

    private final int right;

    private final int top;

    private final int bottom;

    private final char[][] representation;

    private transient Tile[] variants;

    public Tile(final BigInteger identifier, final char[][] representation, final int top, final int right, final int bottom, final int left) {
        this.identifier = identifier;
        this.representation = representation.clone();
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public Tile(final BigInteger identifier, final char[]... representation) {
        int newLeft = 0;
        int newRight = 0;
        for (int i = 0; i < 10; i++) {
            newLeft = newLeft | ((representation[9 - i][0] == '#' ? 1 : 0) << i);
            newRight = newRight | ((representation[9 - i][9] == '#' ? 1 : 0) << i);
        }
        this.identifier = identifier;
        this.representation = representation.clone();
        this.top = Integer.parseInt(new String(representation[0]).replace('.', '0').replace('#', '1'), 2);
        this.bottom = Integer.parseInt(new String(representation[9]).replace('.', '0').replace('#', '1'), 2);
        this.left = newLeft;
        this.right = newRight;
    }

    public BigInteger getIdentifier() {
        return identifier;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public Tile flip() {
        return new Tile(identifier, flip(representation), Integer.reverse(top) >>> 22, left, Integer.reverse(bottom) >>> 22, right);
    }

    public Tile rotate() {
        return new Tile(identifier, rotate(representation), right, Integer.reverse(bottom) >>> 22, left, Integer.reverse(top) >>> 22);
    }

    public Tile[] getVariants() {
        if (variants == null) {
            final Set<Tile> variantsSet = new HashSet<>();
            Tile var1 = this;
            Tile var2 = flip();
            for (int i = 0; i < 4; i++) {
                variantsSet.add(var1);
                variantsSet.add(var2);
                var1 = var1.rotate();
                var2 = var2.rotate();
            }
            this.variants = variantsSet.toArray(Tile[]::new);
        }
        return variants.clone();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Tile tile = (Tile) other;
        return left == tile.left && right == tile.right && top == tile.top && bottom == tile.bottom && identifier.equals(tile.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, left, right, top, bottom);
    }

    public char[][] getRepresentation() {
        return representation.clone();
    }

    public static char[][] rotate(final char[]... image) {
        final char[][] newImage = new char[image.length][image.length];
        for (int y = 0; y < newImage.length; y++) {
            for (int x = 0; x < newImage.length; x++) {
                newImage[y][x] = image[x][image.length - 1 - y];
            }
        }
        return newImage;
    }

    public static char[][] flip(final char[]... image) {
        final char[][] newImage = new char[image.length][image.length];
        for (int y = 0; y < newImage.length; y++) {
            for (int x = 0; x < newImage.length; x++) {
                newImage[y][x] = image[y][image.length - 1 - x];
            }
        }
        return newImage;
    }
}