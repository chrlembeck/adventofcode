package de.chrlembeck.aoc2019.day15;

import de.chrlembeck.aoc2019.day11.Position;
import de.chrlembeck.aoccommon.LangUtils;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class TargetFinder implements Consumer<BigInteger> {

    private static final BigInteger NORTH = BigInteger.ONE;

    private static final BigInteger SOUTH = BigInteger.TWO;

    private static final BigInteger WEST = BigInteger.valueOf(3);

    private static final BigInteger EAST = BigInteger.valueOf(4);

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
    }

    @Override
    public void accept(BigInteger result) {
        outputQueue.add(result);
    }

    public void readFully() {
        Queue<Position> positionsToCheck = new LinkedList<>();
        Status state = Status.EMPTY;
        positionsToCheck.add(currentPosition);
        area.put(currentPosition, Status.EMPTY);
        distanceMap.put(currentPosition, 0);
        while (/*state != Status.OXYGEN_SYSTEM && */!positionsToCheck.isEmpty()) {
            Position posToCheck = positionsToCheck.poll();
            state = checkPosition(posToCheck);
            area.put(posToCheck, state);
            if ((state == Status.EMPTY || state == Status.OXYGEN_SYSTEM)) {
                addDistance(posToCheck);
                if (state == Status.OXYGEN_SYSTEM) {
                    oxygenModulePosition = posToCheck;
                }
                Position left = new Position(posToCheck.getxPos() - 1, posToCheck.getyPos());
                Position right = new Position(posToCheck.getxPos() + 1, posToCheck.getyPos());
                Position top = new Position(posToCheck.getxPos(), posToCheck.getyPos() - 1);
                Position bottom = new Position(posToCheck.getxPos(), posToCheck.getyPos() + 1);
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
            System.out.println("\n" + printArea() + "\n");
        }
    }

    private String printArea() {
        StringBuilder sb = new StringBuilder();
        int minX = area.keySet().stream().mapToInt(Position::getxPos).min().getAsInt();
        int minY = area.keySet().stream().mapToInt(Position::getyPos).min().getAsInt();
        int maxX = area.keySet().stream().mapToInt(Position::getxPos).max().getAsInt();
        int maxY = area.keySet().stream().mapToInt(Position::getyPos).max().getAsInt();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Status state = area.get(new Position(x, y));
                if (x == 0 && y == 0) {
                    sb.append("0");
                } else if (state == null) {
                    sb.append(' ');
                } else if (state == Status.EMPTY) {
                    sb.append('.');
                } else if (state == Status.WALL) {
                    sb.append('#');
                } else if (state == Status.OXYGEN_SYSTEM) {
                    sb.append('O');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    private void addDistance(Position position) {
        if (distanceMap.get(position) == null) {
            int left = LangUtils.isNull(distanceMap.get(new Position(position.getxPos() - 1, position.getyPos())), Integer.MAX_VALUE);
            int right = LangUtils.isNull(distanceMap.get(new Position(position.getxPos() + 1, position.getyPos())), Integer.MAX_VALUE);
            int top = LangUtils.isNull(distanceMap.get(new Position(position.getxPos(), position.getyPos() - 1)), Integer.MAX_VALUE);
            int bottom = LangUtils.isNull(distanceMap.get(new Position(position.getxPos(), position.getyPos() + 1)), Integer.MAX_VALUE);
            distanceMap.put(position, 1 + LangUtils.min(left, right, top, bottom));
        }
    }

    private Status checkPosition(Position posToCheck) {
        if (area.containsKey(posToCheck)) {
            return area.get(posToCheck);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() + 1 && posToCheck.getyPos() == currentPosition.getyPos()) {
            return move(4);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() - 1 && posToCheck.getyPos() == currentPosition.getyPos()) {
            return move(3);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() && posToCheck.getyPos() == currentPosition.getyPos() + 1) {
            return move(2);
        }
        if (posToCheck.getxPos() == currentPosition.getxPos() && posToCheck.getyPos() == currentPosition.getyPos() - 1) {
            return move(1);
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
        Position left = new Position(cursor.getxPos() - 1, cursor.getyPos());
        Position right = new Position(cursor.getxPos() + 1, cursor.getyPos());
        Position top = new Position(cursor.getxPos(), cursor.getyPos() - 1);
        Position bottom = new Position(cursor.getxPos(), cursor.getyPos() + 1);
        if ((left.equals(target) || isKnownAndFree(left)) && !visited.contains(left)) {
            path.push(3);
            if (findPath(left, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((right.equals(target) || isKnownAndFree(right)) && !visited.contains(right)) {
            path.push(4);
            if (findPath(right, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((top.equals(target) || isKnownAndFree(top)) && !visited.contains(top)) {
            path.push(1);
            if (findPath(top, target, path, visited)) {
                return true;
            }
            path.pop();
        }
        if ((bottom.equals(target) || isKnownAndFree(bottom)) && !visited.contains(bottom)) {
            path.push(2);
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
                    case 1 -> new Position(currentPosition.getxPos(), currentPosition.getyPos() - 1);
                    case 2 -> new Position(currentPosition.getxPos(), currentPosition.getyPos() + 1);
                    case 3 -> new Position(currentPosition.getxPos() - 1, currentPosition.getyPos());
                    case 4 -> new Position(currentPosition.getxPos() + 1, currentPosition.getyPos());
                    default -> throw new IllegalArgumentException("unsupported direction: " + direction);
                };
            }
            return Status.fromResult(result);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}