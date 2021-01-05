package de.chrlembeck.aoc2020.day17;

import java.util.Arrays;
import java.util.Scanner;

public class NCube {

    private final boolean[] states;

    private final int sizes[];

    private final int dimension;

    public NCube(final int dimension, final Scanner scanner) {
        String line = scanner.nextLine();
        this.dimension = dimension;
        sizes = new int[dimension];
        sizes[0] = line.length();
        sizes[1] = line.length(); // TODO: variable height
        for (int dimensionIndex = 2; dimensionIndex < dimension; dimensionIndex++) {
            sizes[dimensionIndex] = 1;
        }
        states = new boolean[Arrays.stream(sizes).reduce(1, Math::multiplyExact)];
        int yIndex = 0;
        do {
            for (int xIndex = 0; xIndex < sizes[0]; xIndex++) {
                int[] coordinates = new int[dimension];
                coordinates[0] = xIndex;
                coordinates[1] = yIndex;
                setActive(line.charAt(xIndex) == '#', coordinates);
            }
            yIndex++;
            line = scanner.hasNextLine() ? scanner.nextLine() : "";
        } while (line.length() > 0);
    }

    public NCube(final int... sizes) {
        this.sizes = sizes.clone();
        this.dimension = sizes.length;
        states = new boolean[Arrays.stream(sizes).reduce(1, Math::multiplyExact)];
    }

    public boolean isActive(final int... coordinates) {
        for (int dimensionIndex = 0; dimensionIndex < coordinates.length; dimensionIndex++) {
            if (coordinates[dimensionIndex] < 0 || coordinates[dimensionIndex] >= sizes[dimensionIndex]) {
                return false;
            }
        }
        int index = coordinates[coordinates.length - 1];
        for (int dimensionIndex = 0; dimensionIndex < dimension - 1; dimensionIndex++) {
            index = index * sizes[dimension - dimensionIndex - 2] + coordinates[dimension - dimensionIndex - 2];
        }
        return states[index];
    }

    public void setActive(final boolean active, final int... coordinates) {
        int index = coordinates[coordinates.length - 1];
        for (int dimensionIndex = 0; dimensionIndex < dimension - 1; dimensionIndex++) {
            index = index * sizes[dimension - dimensionIndex - 2] + coordinates[dimension - dimensionIndex - 2];
        }
        states[index] = active;
    }

    public NCube next() {
        final int[] newSizes = new int[dimension];
        for (int dimensionIndex = 0; dimensionIndex < dimension; dimensionIndex++) {
            newSizes[dimensionIndex] = sizes[dimensionIndex] + 2;
        }
        final NCube next = new NCube(newSizes);
        final int[] idx = new int[dimension];
        while (true) {
            final int[] testCoords = new int[dimension];
            for (int i = 0; i < dimension; i++) {
                testCoords[i] = idx[i] - 1;
            }
            final int activeNeighbours = getActiveNeighbours(testCoords);
            if (isActive(testCoords)) {
                next.setActive(activeNeighbours == 2 || activeNeighbours == 3, idx);
            } else {
                next.setActive(activeNeighbours == 3, idx);
            }

            idx[idx.length - 1]++;
            int dimensionIndex = idx.length - 1;
            while (dimensionIndex >= 0 && idx[dimensionIndex] == newSizes[dimensionIndex]) {
                idx[dimensionIndex] = 0;
                dimensionIndex--;
                if (dimensionIndex >= 0) {
                    idx[dimensionIndex]++;
                } else {
                    return next;
                }
            }
        }
    }

    private int getActiveNeighbours(final int... coordinates) {
        int counter = 0;
        final int[] idx = new int[dimension];
        for (int dimensionIndex = 0; dimensionIndex < dimension; dimensionIndex++) {
            idx[dimensionIndex] = coordinates[dimensionIndex] - 1;
        }
        while (true) {
            if (isActive(idx)) {
                counter++;
            }
            idx[idx.length - 1]++;
            int dimensionIndex = idx.length - 1;
            while (dimensionIndex >= 0 && idx[dimensionIndex] == coordinates[dimensionIndex] + 2) {
                idx[dimensionIndex] = coordinates[dimensionIndex] - 1;
                dimensionIndex--;
                if (dimensionIndex >= 0) {
                    idx[dimensionIndex]++;
                } else {
                    return isActive(coordinates) ? counter - 1 : counter;
                }
            }
        }
    }

    public int countActive() {
        int counter = 0;
        for (final boolean active : states) {
            if (active) {
                counter++;
            }
        }
        return counter;
    }
}