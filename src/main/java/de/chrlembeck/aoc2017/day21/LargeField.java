package de.chrlembeck.aoc2017.day21;

public class LargeField extends AbstractField {

    public LargeField(final int state) {
        super(state);
    }

    @Override
    public LargeField flipVertically() {
        int newState = 0;
        for (int i = 0; i <= 2; i++) {
            newState |= ((state & (1 << (3 * i))) << 2);
            newState |= state & (1 << (3 * i + 1));
            newState |= ((state & (1 << (3 * i + 2))) >> 2);
        }
        return new LargeField(newState);
    }

    @Override
    public LargeField flipHorizontally() {
        int newState = 0;
        for (int i = 0; i <= 2; i++) {
            newState |= ((state & (1 << i)) << 6);
            newState |= state & (1 << (i + 3));
            newState |= ((state & (1 << (i + 6))) >> 6);
        }
        return new LargeField(newState);
    }

    @Override
    public LargeField rotate() {
        return new LargeField(((state & 0b100_000_000) >> 2) | ((state & 0b010_000_000) >> 4)
                | ((state & 0b001_000_000) >> 6) | ((state & 0b000_100_000) << 2) | ((state & 0b000_010_000) >> 0)
                | ((state & 0b000_001_000) >> 2) | ((state & 0b000_000_100) << 6) | ((state & 0b000_000_010) << 4)
                | ((state & 0b000_000_001) << 2));
    }

}