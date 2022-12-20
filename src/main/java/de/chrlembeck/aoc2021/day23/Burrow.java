package de.chrlembeck.aoc2021.day23;

import java.util.*;

import static de.chrlembeck.aoc2021.day23.AmphipodType.*;
import static de.chrlembeck.aoc2021.day23.Position.*;

public record Burrow(AmphipodType h0, AmphipodType h1, AmphipodType h3, AmphipodType h5, AmphipodType h7,
                     AmphipodType h9, AmphipodType h10, AmphipodType a1, AmphipodType a2, AmphipodType a3,
                     AmphipodType a4,
                     AmphipodType b1, AmphipodType b2, AmphipodType b3, AmphipodType b4, AmphipodType c1,
                     AmphipodType c2, AmphipodType c3, AmphipodType c4, AmphipodType d1, AmphipodType d2,
                     AmphipodType d3, AmphipodType d4, int energy) implements Comparable<Burrow> {

    public int getEnergy() {
        return energy;
    }

    public boolean isFree(Position position) {
        return getAmphipod(position) == null;
    }

    public boolean isRoomEmptyOrReady(AmphipodType type, boolean step2) {
        if (step2) {
            return switch (type) {
                case AMBER ->
                        (a1 == null && a2 == null && a3 == null && a4 == null) || (a1 == null && a2 == null && a3 == null && a4 == type) || (a1 == null && a2 == null && a3 == type && a4 == type) || (a1 == null && a2 == type && a3 == type && a4 == type) || (a1 == type && a2 == type && a3 == type && a4 == type);
                case BRONZE ->
                        (b1 == null && b2 == null && b3 == null && b4 == null) || (b1 == null && b2 == null && b3 == null && b4 == type) || (b1 == null && b2 == null && b3 == type && b4 == type) || (b1 == null && b2 == type && b3 == type && b4 == type) || (b1 == type && b2 == type && b3 == type && b4 == type);
                case COPPER ->
                        (c1 == null && c2 == null && c3 == null && c4 == null) || (c1 == null && c2 == null && c3 == null && c4 == type) || (c1 == null && c2 == null && c3 == type && c4 == type) || (c1 == null && c2 == type && c3 == type && c4 == type) || (c1 == type && c2 == type && c3 == type && c4 == type);
                case DESERT ->
                        (d1 == null && d2 == null && d3 == null && d4 == null) || (d1 == null && d2 == null && d3 == null && d4 == type) || (d1 == null && d2 == null && d3 == type && d4 == type) || (d1 == null && d2 == type && d3 == type && d4 == type) || (d1 == type && d2 == type && d3 == type && d4 == type);
            };
        } else {
            return switch (type) {
                case AMBER -> (a2 == null && a1 == null) || (a2 == type && a1 == null) || (a2 == type && a1 == type);
                case BRONZE -> (b2 == null && b1 == null) || (b2 == type && b1 == null) || (b2 == type && b1 == type);
                case COPPER -> (c2 == null && c1 == null) || (c2 == type && c1 == null) || (c2 == type && c1 == type);
                case DESERT -> (d2 == null && d1 == null) || (d2 == type && d1 == null) || (d2 == type && d1 == type);
            };
        }
    }

    public AmphipodType getAmphipod(final Position position) {
        return switch (position) {
            case H0 -> h0;
            case H1 -> h1;
            case H3 -> h3;
            case H5 -> h5;
            case H7 -> h7;
            case H9 -> h9;
            case H10 -> h10;
            case A1 -> a1;
            case A2 -> a2;
            case A3 -> a3;
            case A4 -> a4;
            case B1 -> b1;
            case B2 -> b2;
            case B3 -> b3;
            case B4 -> b4;
            case C1 -> c1;
            case C2 -> c2;
            case C3 -> c3;
            case C4 -> c4;
            case D1 -> d1;
            case D2 -> d2;
            case D3 -> d3;
            case D4 -> d4;
            default -> null;
        };
    }

    public boolean isReady(final boolean step2) {
        return a1 == AMBER && a2 == AMBER && (!step2 || (a3 == AMBER && a4 == AMBER))
                && b1 == BRONZE && b2 == BRONZE && (!step2 || (b3 == BRONZE && b4 == BRONZE))
                && c1 == COPPER && c2 == COPPER && (!step2 || (c3 == COPPER && c4 == COPPER))
                && d1 == DESERT && d2 == DESERT && (!step2 || (d3 == DESERT && d4 == DESERT));
    }

    public Collection<Move> getMoves(final Position from, final boolean step2) {
        final AmphipodType type = getAmphipod(from);
        if (type == null) {
            return Collections.emptyList();
        }

        if (type.isHome(from) && (from.down(step2) == null || getAmphipod(from.down(step2)) == type)) {
            return Collections.emptyList();
        }

        final List<Move> targets = new ArrayList<>();
        if (from.isRoom() && (!from.up().isValid() || isFree(from.up()))) {
            Position pos = from.up();
            int stepsOut = 1;
            while (pos.isRoom() && (!pos.up().isValid() || isFree(pos.up()))) {
                pos = pos.up();
                stepsOut++;
            }
            if (!pos.isValid()) {
                if (!pos.left().isValid() || isFree(pos.left())) {
                    Position left = pos.left();
                    int steps = stepsOut + 1;
                    while (left != null && (!left.isValid() || isFree(left))) {
                        if (left.isValid()) {
                            targets.add(new Move(from, left, type, steps));
                        }
                        left = left.left();
                        steps++;
                    }
                }
                if (!pos.right().isValid() || isFree(pos.right())) {
                    Position right = pos.right();
                    int steps = stepsOut + 1;
                    while (right != null && (!right.isValid() || isFree(right))) {
                        if (right.isValid()) {
                            targets.add(new Move(from, right, type, steps));
                        }
                        right = right.right();
                        steps++;
                    }
                }
            }
        }
        if (!from.isRoom()) {
            findWayHome(from, step2).ifPresent(targets::add);
        }
        return targets;
    }

    private Optional<Move> findWayHome(final Position from, boolean step2) {
        AmphipodType type = getAmphipod(from);
        if (!isRoomEmptyOrReady(type, step2)) {
            return Optional.empty();
        }
        int steps = 0;
        Position pos = from;
        while (pos != null) {
            Optional<Move> downMove = checkDownMove(pos, step2, steps, type, from);
            if (downMove.isPresent()) {
                return downMove;
            }
            pos = pos.left() != null && (!pos.left().isValid() || isFree(pos.left())) ? pos.left() : null;
            steps++;
        }
        steps = 0;
        pos = from;
        while (pos != null) {
            Optional<Move> downMove = checkDownMove(pos, step2, steps, type, from);
            if (downMove.isPresent()) {
                return downMove;
            }
            pos = pos.right() != null && (!pos.right().isValid() || isFree(pos.right())) ? pos.right() : null;
            steps++;
        }
        return Optional.empty();
    }

    private Optional<Move> checkDownMove(Position pos, boolean step2, int steps, AmphipodType type, Position from) {
        Position down = pos.down(step2);
        if (down != null && type.isHome(down)) {
            while (down != null && isFree(down)) {
                pos = down;
                down = down.down(step2);
                steps++;
            }
            return Optional.of(new Move(from, pos, type, steps));
        }
        return Optional.empty();
    }

    @Override
    public int compareTo(Burrow o) {
        return Integer.compare(energy, o.energy);
    }

    public Burrow move(Position from, Position to, int energy) {
        AmphipodType movedType = getAmphipod(from);
        AmphipodType h0 = to == H0 ? movedType : from == H0 ? null : this.h0;
        AmphipodType h1 = to == H1 ? movedType : from == H1 ? null : this.h1;
        AmphipodType h3 = to == H3 ? movedType : from == H3 ? null : this.h3;
        AmphipodType h5 = to == H5 ? movedType : from == H5 ? null : this.h5;
        AmphipodType h7 = to == H7 ? movedType : from == H7 ? null : this.h7;
        AmphipodType h9 = to == H9 ? movedType : from == H9 ? null : this.h9;
        AmphipodType h10 = to == H10 ? movedType : from == H10 ? null : this.h10;
        AmphipodType a1 = to == A1 ? movedType : from == A1 ? null : this.a1;
        AmphipodType a2 = to == A2 ? movedType : from == A2 ? null : this.a2;
        AmphipodType a3 = to == A3 ? movedType : from == A3 ? null : this.a3;
        AmphipodType a4 = to == A4 ? movedType : from == A4 ? null : this.a4;
        AmphipodType b1 = to == B1 ? movedType : from == B1 ? null : this.b1;
        AmphipodType b2 = to == B2 ? movedType : from == B2 ? null : this.b2;
        AmphipodType b3 = to == B3 ? movedType : from == B3 ? null : this.b3;
        AmphipodType b4 = to == B4 ? movedType : from == B4 ? null : this.b4;
        AmphipodType c1 = to == C1 ? movedType : from == C1 ? null : this.c1;
        AmphipodType c2 = to == C2 ? movedType : from == C2 ? null : this.c2;
        AmphipodType c3 = to == C3 ? movedType : from == C3 ? null : this.c3;
        AmphipodType c4 = to == C4 ? movedType : from == C4 ? null : this.c4;
        AmphipodType d1 = to == D1 ? movedType : from == D1 ? null : this.d1;
        AmphipodType d2 = to == D2 ? movedType : from == D2 ? null : this.d2;
        AmphipodType d3 = to == D3 ? movedType : from == D3 ? null : this.d3;
        AmphipodType d4 = to == D4 ? movedType : from == D4 ? null : this.d4;
        return new Burrow(h0, h1, h3, h5, h7, h9, h10, a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4, this.energy + energy);
    }
}