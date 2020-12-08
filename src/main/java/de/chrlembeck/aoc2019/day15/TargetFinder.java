package de.chrlembeck.aoc2019.day15;

import de.chrlembeck.aoc2019.day11.Position;
import de.chrlembeck.aoccommon.LangUtils;
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

    private BlockingQueue<BigInteger> outputQueue = new LinkedBlockingQueue<>();

    private Map<Position, Status> area = new TreeMap<>();

    private Map<Position, Integer> distanceMap = new TreeMap<>();

    private Position oxygenModulePosition;

    public int getStepsToOxygenModule() {
        return distanceMap.get(oxygenModulePosition);
    }

    public TargetFinder(Consumer<BigInteger> inputConsumer) {
        this.input = inputConsumer;
        distanceMap.put(currentPosition, 0);
        area.put(currentPosition, Status.EMPTY);
    }

    @Override
    public void accept(BigInteger result) {
        outputQueue.add(result);
    }

    public void readFully() {
        Queue<Position> positionsToCheck = new LinkedList<>();
        Status state;
        positionsToCheck.add(currentPosition);
        while (!positionsToCheck.isEmpty()) {
            Position posToCheck = positionsToCheck.poll();
            state = checkPosition(posToCheck);
            area.put(posToCheck, state);
            if ((state == Status.EMPTY || state == Status.OXYGEN_SYSTEM)) {
                addDistance(posToCheck);
                if (state == Status.OXYGEN_SYSTEM) {
                    oxygenModulePosition = posToCheck;
                }
                Position left = posToCheck.leftNeighbour();
                Position right = posToCheck.rightNeighbour();
                Position top = posToCheck.topNeighbour();
                Position bottom = posToCheck.bottomNeighbour();
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

    private void addDistance(Position position) {
        if (distanceMap.get(position) == null) {
            int left = LangUtils.isNull(distanceMap.get(position.leftNeighbour()), Integer.MAX_VALUE);
            int right = LangUtils.isNull(distanceMap.get(position.rightNeighbour()), Integer.MAX_VALUE);
            int top = LangUtils.isNull(distanceMap.get(position.topNeighbour()), Integer.MAX_VALUE);
            int bottom = LangUtils.isNull(distanceMap.get(position.bottomNeighbour()), Integer.MAX_VALUE);
            distanceMap.put(position, 1 + LangUtils.min(left, right, top, bottom));
        }
    }

    private Status checkPosition(Position posToCheck) {
        if (area.containsKey(posToCheck)) {
            return area.get(posToCheck);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() + 1 && posToCheck.getyPos() == currentPosition.getyPos()) {
            return move(EAST);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() - 1 && posToCheck.getyPos() == currentPosition.getyPos()) {
            return move(WEST);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() && posToCheck.getyPos() == currentPosition.getyPos() + 1) {
            return move(SOUTH);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() && posToCheck.getyPos() == currentPosition.getyPos() - 1) {
            return move(NORTH);
        }

        Stack<Integer> path = new Stack<>();
        Set<Position> visited = new TreeSet<>();
        if (!findPath(currentPosition, posToCheck, path, visited)) {
            throw new IllegalStateException("no path found from " + currentPosition + " to " + posToCheck);
        }
        Status result = null;
        for (int direction : path) {
            result = move(direction);
        }
        return result;
    }

    private boolean findPath(Position cursor, Position target, Stack<Integer> path, Set<Position> visited) {
        if (cursor.equals(target)) {
            return true;
        }
        visited.add(cursor);
        Position left = cursor.leftNeighbour();
        Position right = cursor.rightNeighbour();
        Position top = cursor.topNeighbour();
        Position bottom = cursor.bottomNeighbour();
        if ((left.equals(target) || isKnownAndFree(left)) && !visited.contains(left)) {
            path.push(WEST);
            if (findPath(left, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((right.equals(target) || isKnownAndFree(right)) && !visited.contains(right)) {
            path.push(EAST);
            if (findPath(right, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((top.equals(target) || isKnownAndFree(top)) && !visited.contains(top)) {
            path.push(NORTH);
            if (findPath(top, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((bottom.equals(target) || isKnownAndFree(bottom)) && !visited.contains(bottom)) {
            path.push(SOUTH);
            if (findPath(bottom, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        return false;
    }

    private boolean isKnownAndFree(Position pos) {
        Status state = area.get(pos);
        return state != null && state != Status.WALL;
    }

    public Status move(int direction) {
        input.accept(BigInteger.valueOf(direction));
        try {
            int result = outputQueue.take().intValueExact();
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
        Set<Position> spaceToFill = area.entrySet().stream().filter(e -> e.getValue().equals(Status.EMPTY)).map(Map.Entry::getKey).collect(Collectors.toSet());
        List<Position> currentMinuteSpace = new ArrayList<>();
        currentMinuteSpace.add(oxygenModulePosition);
        while (!spaceToFill.isEmpty()) {
            time++;
            List<Position> nextMinute = new ArrayList<>();
            for (Position pos: currentMinuteSpace) {
                spaceToFill.remove(pos);
                Position left = pos.leftNeighbour();
                Position right = pos.rightNeighbour();
                Position top = pos.topNeighbour();
                Position bottom = pos.bottomNeighbour();
                if (spaceToFill.contains(left)) {
                    nextMinute.add(left);
                }
                if (spaceToFill.contains(right)) {
                    nextMinute.add(right);
                }
                if (spaceToFill.contains(top)) {
                    nextMinute.add(top);
                }
                if (spaceToFill.contains(bottom)) {
                    nextMinute.add(bottom);
                }
            }
            currentMinuteSpace = nextMinute;
        }
        return time;
    }
}