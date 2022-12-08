package de.chrlembeck.aoc2021.day19;

import static java.lang.Math.abs;

public record Vector(int x, int y, int z) {

    Vector getAlternative(int direction) {
        return switch (direction) {
            case 0 -> new Vector(x, y, z);
            case 1 -> new Vector(x, -y, -z);
            case 2 -> new Vector(x, -z, y);
            case 3 -> new Vector(x, z, -y);

            case 4 -> new Vector(-x, y, -z);
            case 5 -> new Vector(-x, -y, z);
            case 6 -> new Vector(-x, z, y);
            case 7 -> new Vector(-x, -z, -y);

            case 8 -> new Vector(y, x, -z);
            case 9 -> new Vector(y, -x, z);
            case 10 -> new Vector(y, z, x);
            case 11 -> new Vector(y, -z, -x);

            case 12 -> new Vector(-y, x, z);
            case 13 -> new Vector(-y, -x, -z);
            case 14 -> new Vector(-y, z, -x);
            case 15 -> new Vector(-y, -z, x);

            case 16 -> new Vector(z, x, y);
            case 17 -> new Vector(z, -x, -y);
            case 18 -> new Vector(z, y, -x);
            case 19 -> new Vector(z, -y, x);

            case 20 -> new Vector(-z, x, -y);
            case 21 -> new Vector(-z, -x, y);
            case 22 -> new Vector(-z, y, x);
            case 23 -> new Vector(-z, -y, -x);
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

    public Vector translate(Vector position) {
        return new Vector(x + position.x, y + position.y, z + position.z);
    }

    public int distance(Vector other) {
        return abs(other.x - x) + abs(other.y - y) + abs(other.z - z);
    }
}