package de.chrlembeck.aoc2019.day12;

import java.util.Arrays;

public class Moon {

    private final Dimension[] dimensions = new Dimension[3];

    public Moon(final long xPos, final long yPos, final long zPos) {
        this.dimensions[0] = new Dimension(xPos, 0);
        this.dimensions[1] = new Dimension(yPos, 0);
        this.dimensions[2] = new Dimension(zPos, 0);
    }

    public Dimension getDimension(final int idx) {
        return dimensions[idx];
    }

    public long getEnergy() {
        return Arrays.stream(dimensions).mapToLong(Dimension::getPosition).map(Math::abs).sum() *
                Arrays.stream(dimensions).mapToLong(Dimension::getVelocity).map(Math::abs).sum();
    }

    public void applyVelocity() {
        Arrays.stream(dimensions).forEach(Dimension::applyVelocity);
    }

    public void applyGravityMutually(final Moon other) {
        for (int i = 0; i < 3; i++) {
            dimensions[i].applyGravityMutually(other.dimensions[i]);
        }
    }
}