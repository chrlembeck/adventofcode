package de.chrlembeck.aoc2021.day23;

import java.util.*;

import static de.chrlembeck.aoc2021.day23.AmphipodType.AMBER;
import static de.chrlembeck.aoc2021.day23.AmphipodType.COPPER;
import static de.chrlembeck.aoc2021.day23.Position.*;

public record Burrow(
        AmphipodType h0,
        AmphipodType h1,
        AmphipodType h3,
        AmphipodType h5,
        AmphipodType h7,
        AmphipodType h9,
        AmphipodType h10,
        AmphipodType a1,
        AmphipodType a2,
        AmphipodType b1,
        AmphipodType b2,
        AmphipodType c1,
        AmphipodType c2,
        AmphipodType d1,
        AmphipodType d2,
        int energy) implements Comparable<Burrow> {

    public static void main(String[] args) {
//        Burrow burrow = new Burrow();
//        burrow.h5 = AMBER;
//        burrow.a1 = COPPER;
//        for (Move move : burrow.getMoves(A1)) {
//            System.out.println(move);
//        }
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isFree(Position position) {
        return switch (position) {
            case H0 -> h0 == null;
            case H1 -> h1 == null;
            case H3 -> h3 == null;
            case H5 -> h5 == null;
            case H7 -> h7 == null;
            case H9 -> h9 == null;
            case H10 -> h10 == null;
            case A1 -> a1 == null;
            case A2 -> a2 == null;
            case B1 -> b1 == null;
            case B2 -> b2 == null;
            case C1 -> c1 == null;
            case C2 -> c2 == null;
            case D1 -> d1 == null;
            case D2 -> d2 == null;
            default -> throw new RuntimeException("" + position);
        };
    }

    public boolean isRoomEmptyOrReady(AmphipodType type) {
        return switch (type) {
            case AMBER -> (a2 == null && a1 == null) || (a2 == type && a1 == null) || (a2 == type && a1 == type);
            case BRONZE -> (b2 == null && b1 == null) || (b2 == type && b1 == null) || (b2 == type && b1 == type);
            case COPPER -> (c2 == null && c1 == null) || (c2 == type && c1 == null) || (c2 == type && c1 == type);
            case DESERT -> (d2 == null && d1 == null) || (d2 == type && d1 == null) || (d2 == type && d1 == type);
        };
    }

    public AmphipodType getAmphipod(Position position) {
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
            case B1 -> b1;
            case B2 -> b2;
            case C1 -> c1;
            case C2 -> c2;
            case D1 -> d1;
            case D2 -> d2;
            default -> null;
        };
    }

    public boolean isReady() {
        return a2 == AMBER && a1 == AMBER
                && b2 == AmphipodType.BRONZE && b1 == AmphipodType.BRONZE
                && c2 == COPPER && c1 == COPPER
                && d2 == AmphipodType.DESERT && d1 == AmphipodType.DESERT;
    }

    public int countReady() {
        int result = 0;
        if (a2 == AMBER) {
            result++;
        }
        if (a1 == AMBER) {
            result++;
        }
        if (b2 == AmphipodType.BRONZE) {
            result++;
        }
        if (b1 == AmphipodType.BRONZE) {
            result++;
        }
        if (c2 == COPPER) {
            result++;
        }
        if (c1 == COPPER) {
            result++;
        }
        if (d2 == AmphipodType.DESERT) {
            result++;
        }
        if (d1 == AmphipodType.DESERT) {
            result++;
        }
        return result;
    }

    public Collection<Move> getMoves(Position from) {
        AmphipodType type = getAmphipod(from);
        if (type == null) {
            return Collections.emptyList();
        }

        if (type.isHome(from) && (from.down() == null || getAmphipod(from.down()) == type)) {
            return Collections.emptyList();
        }

        List<Move> targets = new ArrayList<>();
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
            findWayHome(from).ifPresent(targets::add);
        }
        return targets;
    }

    private Optional<Move> findWayHome(final Position from) {
        AmphipodType type = getAmphipod(from);
        if (!isRoomEmptyOrReady(type)) {
            return Optional.empty();
        }
        int steps = 0;
        Position pos = from;
        while (pos != null) {
            if (pos.down() != null && type.isHome(pos.down())) {
                while (pos.down() != null && isFree(pos.down())) {
                    pos = pos.down();
                    steps++;
                }
                return Optional.of(new Move(from, pos, type, steps));
            }
            pos = pos.left() != null && (!pos.left().isValid() || isFree(pos.left())) ? pos.left() : null;
            steps++;
        }
        steps = 0;
        pos = from;
        while (pos != null) {
            if (pos.down() != null && type.isHome(pos.down())) {
                while (pos.down() != null && isFree(pos.down())) {
                    pos = pos.down();
                    steps++;
                }
                return Optional.of(new Move(from, pos, type, steps));
            }
            pos = pos.right() != null && (!pos.right().isValid() || isFree(pos.right())) ? pos.right() : null;
            steps++;
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#############\n#");
        sb.append(h0 == null ? '.' : h0.name().charAt(0));
        sb.append(h1 == null ? '.' : h1.name().charAt(0));
        sb.append('.');
        sb.append(h3 == null ? '.' : h3.name().charAt(0));
        sb.append('.');
        sb.append(h5 == null ? '.' : h5.name().charAt(0));
        sb.append('.');
        sb.append(h7 == null ? '.' : h7.name().charAt(0));
        sb.append('.');
        sb.append(h9 == null ? '.' : h9.name().charAt(0));
        sb.append(h10 == null ? '.' : h10.name().charAt(0));
        sb.append("#\n###");
        sb.append(a1 == null ? '.' : a1.name().charAt(0));
        sb.append('#');
        sb.append(b1 == null ? '.' : b1.name().charAt(0));
        sb.append('#');
        sb.append(c1 == null ? '.' : c1.name().charAt(0));
        sb.append('#');
        sb.append(d1 == null ? '.' : d1.name().charAt(0));
        sb.append("###\n  #");
        sb.append(a2 == null ? '.' : a2.name().charAt(0));
        sb.append('#');
        sb.append(b2 == null ? '.' : b2.name().charAt(0));
        sb.append('#');
        sb.append(c2 == null ? '.' : c2.name().charAt(0));
        sb.append('#');
        sb.append(d2 == null ? '.' : d2.name().charAt(0));
        sb.append("#\n  #########");
        return sb.toString();
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
        AmphipodType b1 = to == B1 ? movedType : from == B1 ? null : this.b1;
        AmphipodType b2 = to == B2 ? movedType : from == B2 ? null : this.b2;
        AmphipodType c1 = to == C1 ? movedType : from == C1 ? null : this.c1;
        AmphipodType c2 = to == C2 ? movedType : from == C2 ? null : this.c2;
        AmphipodType d1 = to == D1 ? movedType : from == D1 ? null : this.d1;
        AmphipodType d2 = to == D2 ? movedType : from == D2 ? null : this.d2;
        return new Burrow(h0, h1, h3, h5, h7, h9, h10, a1, a2, b1, b2, c1, c2, d1, d2, this.energy + energy);
    }
}