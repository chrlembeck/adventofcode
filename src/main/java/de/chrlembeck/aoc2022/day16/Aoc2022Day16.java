package de.chrlembeck.aoc2022.day16;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Aoc2022Day16 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("Valve (\\w+) has flow rate=(\\d+); tunnels? leads? to valves? (\\w+(, \\w+)*)");

    public static void main(final String[] args) {
        new Aoc2022Day16().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, 30, m -> m.get("AA"), m -> null);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, 26, m -> m.get("AA"), m -> m.get("AA"));
    }

    private static int[][] calculateDistances(Map<String, Valve> valves) {
        int[][] dist = new int[valves.size()][valves.size()];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        boolean ready;
        do {
            ready = true;
            for (Valve valve : valves.values()) {
                dist[valve.index][valve.index] = 0;
                for (Valve destination : valve.tunnels) {
                    dist[valve.index][destination.index] = dist[destination.index][valve.index] = 1;
                    for (Valve other : valves.values()) {
                        if (dist[destination.index][other.index] != -1 && (dist[valve.index][other.index] == -1 || dist[destination.index][other.index] + 1 < dist[valve.index][other.index])) {
                            dist[valve.index][other.index] = dist[other.index][valve.index] = dist[destination.index][other.index] + 1;
                            ready = false;
                        }
                    }
                }
            }
        } while (!ready);
        return dist;
    }

    private Map<String, Valve> readValves(final Scanner input) {
        final Map<String, Valve> valves = new TreeMap<>();
        matcherStream(input, "\\n", pattern).forEach(m -> parseValve(valves, m));
        final AtomicInteger idx = new AtomicInteger(0);
        valves.keySet().stream().sorted().forEach(name -> valves.get(name).setIndex(idx.getAndIncrement()));
        return valves;
    }

    record State(Valve myLocation, int myMinutes, Valve elephantLocation, int elephantMinutes, int released,
                 long opened, State pred) {
    }

    private void parseValve(Map<String, Valve> valves, Matcher matcher) {
        Valve valve = valves.computeIfAbsent(matcher.group(1), n -> new Valve());
        valve.setRate(parseInt(matcher.group(2)));
        valve.setTunnels(Arrays.stream(matcher.group(3).split(", ")).map(n -> valves.computeIfAbsent(n, name -> new Valve())).toArray(Valve[]::new));
    }

    private int calc(final Scanner input, final int maxMinutes, Function<Map<String, Valve>, Valve> pos1, Function<Map<String, Valve>, Valve> elephantPosFinder) {
        final Map<String, Valve> valves = readValves(input);
        final Valve[] relevantValves = valves.values().stream().filter(v -> v.rate > 0).toArray(Valve[]::new);
        final int[][] dist = calculateDistances(valves);
        final int maxRate = valves.values().stream().mapToInt(Valve::getRate).max().orElseThrow();

        final PriorityQueue<State> states = new PriorityQueue<>(Comparator.comparing(State::released).reversed());
        states.add(new State(pos1.apply(valves), 0, elephantPosFinder.apply(valves), 0, 0, 0, null));
        int maxReleased = -1;
        while (!states.isEmpty()) {
            final State currentState = states.poll();
            maxReleased = Math.max(maxReleased, currentState.released);

            final int remainingMovesMax = (maxMinutes - currentState.myMinutes) / 2 + (currentState.elephantLocation == null ? 0 : (maxMinutes - currentState.elephantMinutes)) / 2;

            if (maxReleased < currentState.released + (remainingMovesMax * remainingMovesMax / 2) * maxRate) {
                for (Valve dest : relevantValves) {
                    if ((dest != currentState.myLocation && ((currentState.opened & dest.mask) == 0))) {
                        if (currentState.elephantLocation == null || currentState.myMinutes <= currentState.elephantMinutes) {
                            final int newTime = currentState.myMinutes + dist[currentState.myLocation.index][dest.index] + 1;
                            if (newTime < maxMinutes) {
                                states.add(new State(dest, newTime, currentState.elephantLocation, currentState.elephantMinutes, currentState.released + dest.rate * (maxMinutes - newTime), currentState.opened | dest.mask, currentState));
                            }
                        } else {
                            final int newTime = currentState.elephantMinutes + dist[currentState.elephantLocation.index][dest.index] + 1;
                            if (newTime < maxMinutes) {
                                states.add(new State(currentState.myLocation, currentState.myMinutes, dest, newTime, currentState.released + dest.rate * (maxMinutes - newTime), currentState.opened | dest.mask, currentState));
                            }
                        }
                    }
                }
            }
        }
        return maxReleased;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day16.txt";
    }

    static class Valve {

        private Valve[] tunnels;

        private int rate;

        private int index;

        private long mask;

        public void setRate(int rate) {
            this.rate = rate;
        }

        public void setTunnels(Valve[] tunnels) {
            this.tunnels = tunnels;
        }

        public void setIndex(int index) {
            this.index = index;
            this.mask = 1L << index;
        }

        public int getIndex() {
            return index;
        }

        public int getRate() {
            return rate;
        }
    }
}