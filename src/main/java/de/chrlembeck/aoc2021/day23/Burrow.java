package de.chrlembeck.aoc2021.day23;

import java.util.*;

import static de.chrlembeck.aoc2021.day23.AmphipodType.AMBER;
import static de.chrlembeck.aoc2021.day23.AmphipodType.COPPER;
import static de.chrlembeck.aoc2021.day23.Position.A1;

public class Burrow implements Comparable<Burrow> {

    public static void main(String[] args) {
        Burrow burrow = new Burrow();
        burrow.h5 = AMBER;
        burrow.a1 = COPPER;
        for (Move move : burrow.getMoves(A1)) {
            System.out.println(move);
        }
    }

    private AmphipodType h0;
    private AmphipodType h1;
    private AmphipodType h3;
    private AmphipodType h5;
    private AmphipodType h7;
    private AmphipodType h9;
    private AmphipodType h10;
    private AmphipodType a1;
    private AmphipodType a2;
    private AmphipodType b1;
    private AmphipodType b2;
    private AmphipodType c1;
    private AmphipodType c2;
    private AmphipodType d1;
    private AmphipodType d2;

    private int energy;

    public void incEnergy(int delta) {
        energy += delta;
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

    public void setAmphipod(Position position, AmphipodType type) {
        switch (position) {
            case H0 -> h0 = type;
            case H1 -> h1 = type;
            case H3 -> h3 = type;
            case H5 -> h5 = type;
            case H7 -> h7 = type;
            case H9 -> h9 = type;
            case H10 -> h10 = type;
            case A1 -> a1 = type;
            case A2 -> a2 = type;
            case B1 -> b1 = type;
            case B2 -> b2 = type;
            case C1 -> c1 = type;
            case C2 -> c2 = type;
            case D1 -> d1 = type;
            case D2 -> d2 = type;
            default -> throw new RuntimeException(""+position);
        }
        ;
    }

    public boolean isReady() {
        return a1 == AMBER && a2 == AMBER
                && b1 == AmphipodType.BRONZE && b2 == AmphipodType.BRONZE
                && c1 == COPPER && c2 == COPPER
                && d1 == AmphipodType.DESERT && d2 == AmphipodType.DESERT;
    }

    public int countReady() {
        int result = 0;
        if (a1 == AMBER) {
            result++;
        }
        if (a2 == AMBER) {
            result++;
        }
        if (b1 == AmphipodType.BRONZE) {
            result++;
        }
        if (b2 == AmphipodType.BRONZE) {
            result++;
        }
        if (c1 == COPPER) {
            result++;
        }
        if (c2 == COPPER) {
            result++;
        }
        if (d1 == AmphipodType.DESERT) {
            result++;
        }
        if (d2 == AmphipodType.DESERT) {
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
            if (pos.isRoom()) {
                pos = pos.up();
                stepsOut++;
            }
            if (!pos.isValid() || isFree(pos)) {
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
        int steps = 0;
        Position pos = from;
        while (pos != null) {
            if (pos.down() != null && type.isHome(pos.down())) {
                if (isFree(pos.down())) {
                    pos = pos.down();
                    steps++;
                    return isFree(pos.down())
                            ? Optional.of(new Move(from, pos.down(), type, steps + 1))
                            : ((getAmphipod(pos.down()) == type)
                            ? Optional.of(new Move(from, pos, type, steps))
                            : Optional.empty());
                } else {
                    return Optional.empty();
                }
            }
            pos = pos.left() != null && (!pos.left().isValid() || isFree(pos.left())) ? pos.left() : null;
            steps++;
        }
        steps = 0;
        pos = from;
        while (pos != null) {
            if (pos.down() != null && type.isHome(pos.down())) {
                if (isFree(pos.down())) {
                    pos = pos.down();
                    steps++;
                    return isFree(pos.down())
                            ? Optional.of(new Move(from, pos.down(), type, steps + 1))
                            : ((getAmphipod(pos.down()) == type)
                            ? Optional.of(new Move(from, pos, type, steps))
                            : Optional.empty());
                } else {
                    return Optional.empty();
                }
            }
            pos = pos.right() != null && (!pos.right().isValid() || isFree(pos.right())) ? pos.right() : null;
            steps++;
        }
        return Optional.empty();
    }

    public Burrow copy() {
        Burrow copy = new Burrow();
        copy.h0 = h0;
        copy.h1 = h1;
        copy.h3 = h3;
        copy.h5 = h5;
        copy.h7 = h7;
        copy.h9 = h9;
        copy.h10 = h10;
        copy.a1 = a1;
        copy.a2 = a2;
        copy.b1 = b1;
        copy.b2 = b2;
        copy.c1 = c1;
        copy.c2 = c2;
        copy.d1 = d1;
        copy.d2 = d2;
        copy.energy = energy;
        return copy;
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
        sb.append(a2 == null ? '.' : a2.name().charAt(0));
        sb.append('#');
        sb.append(b2 == null ? '.' : b2.name().charAt(0));
        sb.append('#');
        sb.append(c2 == null ? '.' : c2.name().charAt(0));
        sb.append('#');
        sb.append(d2 == null ? '.' : d2.name().charAt(0));
        sb.append("###\n  #");
        sb.append(a1 == null ? '.' : a1.name().charAt(0));
        sb.append('#');
        sb.append(b1 == null ? '.' : b1.name().charAt(0));
        sb.append('#');
        sb.append(c1 == null ? '.' : c1.name().charAt(0));
        sb.append('#');
        sb.append(d1 == null ? '.' : d1.name().charAt(0));
        sb.append("#\n  #########");
        return sb.toString();
    }

    @Override
    public int compareTo(Burrow o) {
        return Integer.compare(o.countReady(), countReady());
    }

    public void move(Position from, Position to) {
        AmphipodType type = getAmphipod(from);
        setAmphipod(to, type);
        setAmphipod(from, null);
    }
}