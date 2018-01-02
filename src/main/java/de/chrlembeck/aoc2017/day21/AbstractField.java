package de.chrlembeck.aoc2017.day21;

public abstract class AbstractField {

    protected final int state;

    public abstract AbstractField rotate();

    public abstract AbstractField flipHorizontally();

    public abstract AbstractField flipVertically();

    public AbstractField(final int state) {
        this.state = state;
    }

    public AbstractField[] variants() {
        final AbstractField[] variants = new AbstractField[16];
        variants[0] = this;
        variants[1] = flipVertically();
        variants[2] = flipHorizontally();
        variants[3] = flipHorizontally().flipVertically();
        for (int i = 4; i < 16; i++) {
            variants[i] = variants[i - 4].rotate();
        }
        return variants;
    }

    public int getState() {
        return state;
    }
}