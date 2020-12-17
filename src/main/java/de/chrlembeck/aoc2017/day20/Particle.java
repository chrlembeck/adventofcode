package de.chrlembeck.aoc2017.day20;

import java.math.BigInteger;

public class Particle {

    private final int identifier;

    private BigInteger posX;

    private BigInteger posY;

    private BigInteger posZ;

    private BigInteger velX;

    private BigInteger velY;

    private BigInteger velZ;

    private BigInteger accX;

    private BigInteger accY;

    private BigInteger accZ;

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

    public int getIdentifier() {
        return identifier;
    }

    public BigInteger getPosX() {
        return posX;
    }

    public BigInteger getPosY() {
        return posY;
    }

    public BigInteger getPosZ() {
        return posZ;
    }

    public BigInteger getVelX() {
        return velX;
    }

    public BigInteger getVelY() {
        return velY;
    }

    public BigInteger getVelZ() {
        return velZ;
    }

    public BigInteger getAccX() {
        return accX;
    }

    public BigInteger getAccY() {
        return accY;
    }

    public BigInteger getAccZ() {
        return accZ;
    }

    public void setPosX(final BigInteger posX) {
        this.posX = posX;
    }

    public void setPosY(final BigInteger posY) {
        this.posY = posY;
    }

    public void setPosZ(final BigInteger posZ) {
        this.posZ = posZ;
    }

    public void setVelX(final BigInteger velX) {
        this.velX = velX;
    }

    public void setVelY(final BigInteger velY) {
        this.velY = velY;
    }

    public void setVelZ(final BigInteger velZ) {
        this.velZ = velZ;
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