package de.chrlembeck.aoc2017.day21;

public class SmallField extends AbstractField {

    public SmallField(final int state) {
        super(state);
    }

    @Override
    public SmallField flipVertically() {
        int newState = 0;
        for (int i = 0; i <= 1; i++) {
            newState |= (state & (1 << (2 * i))) << 1;
            newState |= (state & (1 << (2 * i + 1))) >> 1;
        }
        return new SmallField(newState);
    }

    @Override
    public SmallField flipHorizontally() {
        int newState = 0;
        for (int i = 0; i <= 1; i++) {
            newState |= (state & (1 << i)) << 2;
            newState |= (state & (1 << (i + 2))) >> 2;
        }
        return new SmallField(newState);
    }

    @Override
    public SmallField rotate() {
        return new SmallField(((state & 0b1000) >> 1) | ((state & 0b0100) >> 2) | ((state & 0b0010) << 2)
                | ((state & 0b0001) << 1));
    }
}