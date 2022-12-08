package de.chrlembeck.aoc2021.day19;

import java.util.*;
import java.util.stream.IntStream;

public class BeaconScanner {

    private final int id;

    private Map<Integer, Set<Vector>> beacons = new HashMap();

    private Set<Vector> fixedBeacons;

    private Integer fixedDirection;

    private Vector fixedPosition;

    public BeaconScanner(int id) {
        this.id = id;
        IntStream.range(0, 24).forEach(i -> beacons.put(i, new HashSet<>()));
    }

    public void addBeacon(Vector vector) {
        IntStream.range(0, 24).forEach(i -> beacons.get(i).add(vector.getAlternative(i)));
    }

    public int getId() {
        return id;
    }

    public Set<Vector> getBeacons(int direction) {
        return Collections.unmodifiableSet(beacons.get(direction));
    }

    public Set<Vector> getFixedBeacons() {
        return fixedBeacons;
    }

    public void fix(Vector position, int direction) {
        this.fixedDirection = direction;
        this.fixedPosition = position;
        Set<Vector> fixed = new HashSet<>();
        getBeacons(direction).stream().map(v -> v.translate(position)).forEach(fixed::add);
        this.fixedBeacons = Collections.unmodifiableSet(fixed);
    }

    public boolean isFixed() {
        return fixedPosition != null;
    }

    public Vector getFixedPosition() {
        return fixedPosition;
    }
}