package de.chrlembeck.aoc2021.day22;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day22 extends AbstractAocBase {

    private final Pattern regex = Pattern.compile("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day22().run();
    }

    @Override
    public Object part1(final Scanner input) {
        return calc(input, Cube::smallCube);
    }

    @Override
    public Object part2(final Scanner input) {
        return calc(input, c -> true);
    }

    private BigInteger calc(final Scanner input, Predicate<Cube> filter) {
        List<Cube> cubeDefs = tokenStream(input, "\\n", regex, this::toCube)
                .filter(filter)
                .toList();
        Universum universum = new Universum();
        for (Cube cube : cubeDefs) {
            universum.addCube(cube);
        }
        return universum.onVolume();
    }

    private Cube toCube(Matcher matcher) {
        return new Cube(matcher.group(1).equals("on") ? Mode.ON : Mode.OFF,
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)),
                Integer.parseInt(matcher.group(6)), Integer.parseInt(matcher.group(7)));
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day22.txt";
    }

    public record Cube(Mode mode, int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {

        boolean smallCube() {
            return xMin >= -50 && xMax <= 50 && yMin >= -50 && yMax <= 50 && zMin >= -50 && zMax <= 50;
        }

        public boolean containsComplete(Cube other) {
            return xMin <= other.xMin && other.xMax <= xMax &&
                    yMin <= other.yMin && other.yMax <= yMax &&
                    zMin <= other.zMin && other.zMax <= zMax;
        }

        public boolean intersects(Cube other) {
            return other.xMin <= xMax && other.xMax >= xMin && other.yMin <= yMax && other.yMax >= yMin && other.zMin <= zMax && other.zMax >= zMin;
        }

        public Collection<Cube> subtract(Cube other) {
            if (other.containsComplete(this)) {
                return Collections.emptyList();
            }
            if (!intersects(other)) {
                return List.of(this);
            }
            if (xMin < other.xMin && other.xMin <= xMax) {
                return list(new Cube(mode, xMin, other.xMin - 1, yMin, yMax, zMin, zMax).subtract(other),
                        new Cube(mode, other.xMin, xMax, yMin, yMax, zMin, zMax).subtract(other));
            }
            if (yMin < other.yMin && other.yMin <= yMax) {
                return list(new Cube(mode, xMin, xMax, yMin, other.yMin - 1, zMin, zMax).subtract(other),
                        new Cube(mode, xMin, xMax, other.yMin, yMax, zMin, zMax).subtract(other));
            }
            if (zMin < other.zMin && other.zMin <= zMax) {
                return list(new Cube(mode, xMin, xMax, yMin, yMax, zMin, other.zMin - 1).subtract(other),
                        new Cube(mode, xMin, xMax, yMin, yMax, other.zMin, zMax).subtract(other));
            }
            if (xMin <= other.xMax && other.xMax < xMax) {
                return list(new Cube(mode, xMin, other.xMax, yMin, yMax, zMin, zMax).subtract(other),
                        new Cube(mode, other.xMax + 1, xMax, yMin, yMax, zMin, zMax).subtract(other));
            }
            if (yMin <= other.yMax && other.yMax < yMax) {
                return list(new Cube(mode, xMin, xMax, yMin, other.yMax, zMin, zMax).subtract(other),
                        new Cube(mode, xMin, xMax, other.yMax + 1, yMax, zMin, zMax).subtract(other));
            }
            if (zMin <= other.zMax && other.zMax < zMax) {
                return list(new Cube(mode, xMin, xMax, yMin, yMax, zMin, other.zMax).subtract(other),
                        new Cube(mode, xMin, xMax, yMin, yMax, other.zMax + 1, zMax).subtract(other));
            }
            throw new RuntimeException("do not know, how to subtract " + other + " from " + this);
        }

        public BigInteger volume() {
            BigInteger dx = BigInteger.valueOf(xMax).subtract(BigInteger.valueOf(xMin)).add(BigInteger.ONE);
            BigInteger dy = BigInteger.valueOf(yMax).subtract(BigInteger.valueOf(yMin)).add(BigInteger.ONE);
            BigInteger dz = BigInteger.valueOf(zMax).subtract(BigInteger.valueOf(zMin)).add(BigInteger.ONE);
            return dx.multiply(dy).multiply(dz);
        }

        static List<Cube> list(Collection<Cube> a, Collection<Cube> b) {
            List<Cube> result = new ArrayList<>();
            result.addAll(a);
            result.addAll(b);
            return result;
        }
    }

    static class Universum {

        List<Cube> onCubes = new ArrayList<>();

        public void addCube(Cube cube) {
            List<Cube> newCubes = new ArrayList<>();
            if (cube.mode() == Mode.ON) {
                for (Cube onCube : onCubes) {
                    if (onCube.containsComplete(cube)) {
                        return;
                    } else if (!onCube.intersects(cube)) {
                        newCubes.add(onCube);
                    } else {
                        newCubes.addAll(onCube.subtract(cube));
                    }
                }
                newCubes.add(cube);
            } else {
                for (Cube onCube : onCubes) {
                    if (!cube.containsComplete(onCube)) {
                        if (cube.intersects(onCube)) {
                            newCubes.addAll(onCube.subtract(cube));
                        } else {
                            newCubes.add(onCube);
                        }
                    }
                }
            }
            onCubes = newCubes;
        }

        public BigInteger onVolume() {
            return onCubes.stream().map(Cube::volume).reduce(BigInteger.ZERO, BigInteger::add);
        }
    }

    public enum Mode {ON, OFF}
}