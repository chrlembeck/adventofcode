package de.chrlembeck.aoc2021.day24;

public enum Register implements Value {
    W,X,Y,Z;

    public Expression eval(State state) {
        return switch (this) {
            case W -> state.w();
            case X -> state.x();
            case Y -> state.y();
            case Z -> state.z();
        };
    }
}