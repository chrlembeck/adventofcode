package de.chrlembeck.aoc2019.day15;

import de.chrlembeck.aoc2019.day11.Position;
import de.chrlembeck.aoccommon.LangUtils;
import de.chrlembeck.aoccommon.MathUtil;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TargetFinder implements Consumer<BigInteger> {

    private static final int NORTH = 1;

    private static final int SOUTH = 2;

    private static final int WEST = 3;

    private static final int EAST = 4;

    private final Consumer<BigInteger> input;

    private Position currentPosition = new Position(0, 0);

    private final BlockingQueue<BigInteger> outputQueue = new LinkedBlockingQueue<>();

    private final Map<Position, Status> area = new TreeMap<>();

    private final Map<Position, Integer> distanceMap = new TreeMap<>();

    private Position oxygenModulePosition;

    public int getStepsToOxygenModule() {
        return distanceMap.get(oxygenModulePosition);
    }

    public TargetFinder(final Consumer<BigInteger> inputConsumer) {
        this.input = inputConsumer;
        distanceMap.put(currentPosition, 0);
        area.put(currentPosition, Status.EMPTY);
    }

    @Override
    public void accept(final BigInteger result) {
        outputQueue.add(result);
    }

    public void readFully() {
        final Queue<Position> positionsToCheck = new LinkedList<>();
        Status state;
        positionsToCheck.add(currentPosition);
        while (!positionsToCheck.isEmpty()) {
            final Position posToCheck = positionsToCheck.poll();
            state = checkPosition(posToCheck);
            area.put(posToCheck, state);
            if ((state == Status.EMPTY || state == Status.OXYGEN_SYSTEM)) {
                addDistance(posToCheck);
                if (state == Status.OXYGEN_SYSTEM) {
                    oxygenModulePosition = posToCheck;
                }
                final Position left = posToCheck.leftNeighbour();
                final Position right = posToCheck.rightNeighbour();
                final Position top = posToCheck.topNeighbour();
                final Position bottom = posToCheck.bottomNeighbour();
                if (area.get(left) == null) {
                    positionsToCheck.add(left);
                }
                if (area.get(right) == null) {
                    positionsToCheck.add(right);
                }
                if (area.get(top) == null) {
                    positionsToCheck.add(top);
                }
                if (area.get(bottom) == null) {
                    positionsToCheck.add(bottom);
                }
            }
        }
        input.accept(BigInteger.ZERO); // stop program
    }

    private void addDistance(final Position position) {
        if (distanceMap.get(position) == null) {
            final int left = LangUtils.replaceIfNull(distanceMap.get(position.leftNeighbour()), Integer.MAX_VALUE);
            final int right = LangUtils.replaceIfNull(distanceMap.get(position.rightNeighbour()), Integer.MAX_VALUE);
            final int top = LangUtils.replaceIfNull(distanceMap.get(position.topNeighbour()), Integer.MAX_VALUE);
            final int bottom = LangUtils.replaceIfNull(distanceMap.get(position.bottomNeighbour()), Integer.MAX_VALUE);
            distanceMap.put(position, 1 + MathUtil.min(left, right, top, bottom));
        }
    }

    private Status checkPosition(final Position posToCheck) {
        if (area.containsKey(posToCheck)) {
            return area.get(posToCheck);
        }
        if (posToCheck.getPosX() == currentPosition.getPosX() + 1 && posToCheck.getPosY() == currentPosition.getPosY()) {
            return move(EAST);
        }
        if (posToCheck.getPosX() == currentPosition.getPosX() - 1 && posToCheck.getPosY() == currentPosition.getPosY()) {
            return move(WEST);
        }
        if (posToCheck.getPosX() == currentPosition.getPosX() && posToCheck.getPosY() == currentPosition.getPosY() + 1) {
            return move(SOUTH);
        }
        if (posToCheck.getPosX() == currentPosition.getPosX() && posToCheck.getPosY() == currentPosition.getPosY() - 1) {
            return move(NORTH);
        }

        final Stack<Integer> path = new Stack<>();
        final Set<Position> visited = new TreeSet<>();
        if (!findPath(currentPosition, posToCheck, path, visited)) {
            throw new IllegalStateException("no path found from " + currentPosition + " to " + posToCheck);
        }
        Status result = null;
        for (final int direction : path) {
            result = move(direction);
        }
        return result;
    }

    private boolean findPath(final Position cursor, final Position target, final Stack<Integer> path, final Set<Position> visited) {
        if (cursor.equals(target)) {
            return true;
        }
        visited.add(cursor);
        final Position left = cursor.leftNeighbour();
        if ((left.equals(target) || isKnownAndFree(left)) && !visited.contains(left)) {
            path.push(WEST);
            if (findPath(left, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        final Position right = cursor.rightNeighbour();
        if ((right.equals(target) || isKnownAndFree(right)) && !visited.contains(right)) {
            path.push(EAST);
            if (findPath(right, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        final Position top = cursor.topNeighbour();
        if ((top.equals(target) || isKnownAndFree(top)) && !visited.contains(top)) {
            path.push(NORTH);
            if (findPath(top, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        final Position bottom = cursor.bottomNeighbour();
        if ((bottom.equals(target) || isKnownAndFree(bottom)) && !visited.contains(bottom)) {
            path.push(SOUTH);
            if (findPath(bottom, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        return false;
    }

    private boolean isKnownAndFree(final Position pos) {
        final Status state = area.get(pos);
        return state != null && state != Status.WALL;
    }

    public Status move(final int direction) {
        input.accept(BigInteger.valueOf(direction));
        try {
            final int result = outputQueue.take().intValueExact();
            if (result == 1 || result == 2) {
                currentPosition = switch (direction) {
                    case NORTH -> currentPosition.topNeighbour();
                    case SOUTH -> currentPosition.bottomNeighbour();
                    case WEST -> currentPosition.leftNeighbour();
                    case EAST -> currentPosition.rightNeighbour();
                    default -> throw new IllegalArgumentException("unsupported direction: " + direction);
                };
            }
            return Status.fromResult(result);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    public int getOxygenFillTime() {
        int time = -1;
        final Set<Position> spaceToFill = area.entrySet().stream().filter(e -> e.getValue().equals(Status.EMPTY)).map(Map.Entry::getKey).collect(Collectors.toSet());
        List<Position> currentMinuteSpace = new ArrayList<>();
        currentMinuteSpace.add(oxygenModulePosition);
        while (!spaceToFill.isEmpty()) {
            time++;
            final List<Position> nextMinute = new ArrayList<>();
            for (final Position pos: currentMinuteSpace) {
                spaceToFill.remove(pos);
                final Position left = pos.leftNeighbour();
                if (spaceToFill.contains(left)) {
                    nextMinute.add(left);
                }
                final Position right = pos.rightNeighbour();
                if (spaceToFill.contains(right)) {
                    nextMinute.add(right);
                }
                final Position top = pos.topNeighbour();
                if (spaceToFill.contains(top)) {
                    nextMinute.add(top);
                }
                final Position bottom = pos.bottomNeighbour();
                if (spaceToFill.contains(bottom)) {
                    nextMinute.add(bottom);
                }
            }
            currentMinuteSpace = nextMinute;
        }
        return time;
    }
}