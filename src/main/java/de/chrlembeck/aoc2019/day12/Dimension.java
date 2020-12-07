package de.chrlembeck.aoc2019.day12;

public class Dimension {

    private long pos;

    private long velocity;

    public Dimension(final long pos, final long velocity) {
        this.pos = pos;
        this.velocity = velocity;
    }

    public void applyVelocity() {
        pos += velocity;
    }

    public void incVel() {
        velocity++;
    }

    public void decVel() {
        velocity--;
    }

    public long getPosition() {
        return pos;
    }

    public long getVelocity() {
        return velocity;
    }

    public void applyGravityMutually(final Dimension other) {
        if (this.pos < other.pos) {
            this.incVel();
            other.decVel();
        } else if (this.pos > other.pos) {
            this.decVel();
            other.incVel();
        }
    }

    public Dimension clone() {
        return new Dimension(pos, velocity);
    }
}
