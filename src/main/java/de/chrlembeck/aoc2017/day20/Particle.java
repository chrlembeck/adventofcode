package de.chrlembeck.aoc2017.day20;

import java.math.BigInteger;

public class Particle {

    int identifier;

    BigInteger posX, posY, posZ, velX, velY, velZ, accX, accY, accZ;

    public Particle(final int identifier, final BigInteger posX, final BigInteger posY, final BigInteger posZ,
            final BigInteger velX, final BigInteger velY, final BigInteger velZ, final BigInteger accX,
            final BigInteger accY, final BigInteger accZ) {
        this.identifier = identifier;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }

    public int getId() {
        return identifier;
    }

    public BigInteger getPx() {
        return posX;
    }

    public BigInteger getPy() {
        return posY;
    }

    public BigInteger getPz() {
        return posZ;
    }

    public BigInteger getVx() {
        return velX;
    }

    public BigInteger getVy() {
        return velY;
    }

    public BigInteger getVz() {
        return velZ;
    }

    public BigInteger getAx() {
        return accX;
    }

    public BigInteger getAy() {
        return accY;
    }

    public BigInteger getAz() {
        return accZ;
    }

    public BigInteger getPxn(final int steps) {
        final BigInteger stepsBi = BigInteger.valueOf(steps);
        return posX.add(stepsBi.multiply(velX)
                .add(stepsBi.pow(2).multiply(accX).add(stepsBi.multiply(accX)).divide(BigInteger.valueOf(2))));
    }

    public BigInteger getPyn(final int steps) {
        final BigInteger stepsBi = BigInteger.valueOf(steps);
        return posY.add(stepsBi.multiply(velY)
                .add(stepsBi.pow(2).multiply(accY).add(stepsBi.multiply(accY)).divide(BigInteger.valueOf(2))));
    }

    public BigInteger getPzn(final int steps) {
        final BigInteger stepsBi = BigInteger.valueOf(steps);
        return posZ.add(stepsBi.multiply(velZ)
                .add(stepsBi.pow(2).multiply(accZ).add(stepsBi.multiply(accZ)).divide(BigInteger.valueOf(2))));
    }

    @Override
    public String toString() {
        return "Particle{" +
                "identifier=" + identifier +
                ", posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                ", velX=" + velX +
                ", velY=" + velY +
                ", velZ=" + velZ +
                ", accX=" + accX +
                ", accY=" + accY +
                ", accZ=" + accZ +
                '}';
    }

    public void normalizeDirection() {
        if (this.accX.signum() < 0 || this.accX.signum() == 0 && this.velX.signum() < 0) {
            this.posX = this.posX.negate();
            this.velX = this.velX.negate();
            this.accX = this.accX.negate();
        }
        if (this.accY.signum() < 0 || this.accY.signum() == 0 && this.velY.signum() < 0) {
            this.posY = this.posY.negate();
            this.velY = this.velY.negate();
            this.accY = this.accY.negate();
        }
        if (this.accZ.signum() < 0 || this.accZ.signum() == 0 && this.velZ.signum() < 0) {
            this.posZ = this.posZ.negate();
            this.velZ = this.velZ.negate();
            this.accZ = this.accZ.negate();
        }
    }
}