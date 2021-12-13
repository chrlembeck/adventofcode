package de.chrlembeck.aoc2016.day11;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;

public class Aoc2016Day11 extends AbstractAocBase {

    private static final int READY_STATE = 0b111111111111111111111111111111;
    // ELEVATOR 20 21
    // PLUTONIUM 18 19
    // PLUTONIUM 16 17
    // PROMETHIUM 14 15
    // PROMETHIUM 12 13
    // RUTHENIUM 10 11
    // RUTHENIUM 8 9
    // STRONTIUM 6 7
    // STRONTIUM 4 5
    // THULIUM 2 3
    // THULIUM 0 1

    public static final int ELEVATOR_OFFSET = 28;

    public static final int ELERIUM_CHIP_OFFSET = 26;

    public static final int ELERIUM_GEN_OFFSET = 24;

    public static final int DILITHIUM_CHIP_OFFSET = 22;

    public static final int DILITHIUM_GEN_OFFSET = 20;

    public static final int PLUTONIUM_CHIP_OFFSET = 18;

    public static final int PLUTONIUM_GEN_OFFSET = 16;

    public static final int PROMETHIUM_CHIP_OFFSET = 14;

    public static final int PROMETHIUM_GEN_OFFSET = 12;

    public static final int RUTHENIUM_CHIP_OFFSET = 10;

    public static final int RUTHENIUM_GEN_OFFSET = 8;

    public static final int STRONTIUM_CHIP_OFFSET = 6;

    public static final int STRONTIUM_GEN_OFFSET = 4;

    public static final int THULIUM_CHIP_OFFSET = 2;

    public static final int THULIUM_GEN_OFFSET = 0;

    public static final int getElevatorLevel(final int state) {
        return state >> ELEVATOR_OFFSET & 0b11;
    }

    public static final int setElevatorLevel(final int state, final int level) {
        return (state & ~(0b11 << ELEVATOR_OFFSET)) | (level << ELEVATOR_OFFSET);
    }

    public static final int getPlutoniumChipLevel(final int state) {
        return state >> PLUTONIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setPlutoniumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << PLUTONIUM_CHIP_OFFSET)) | (level << PLUTONIUM_CHIP_OFFSET);
    }

    public static final int getPlutoniumGenLevel(final int state) {
        return state >> PLUTONIUM_GEN_OFFSET & 0b11;
    }

    public static final int setPlutoniumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << PLUTONIUM_GEN_OFFSET)) | (level << PLUTONIUM_GEN_OFFSET);
    }

    public static final int getPromethiumChipLevel(final int state) {
        return state >> PROMETHIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setPromethiumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << PROMETHIUM_CHIP_OFFSET)) | (level << PROMETHIUM_CHIP_OFFSET);
    }

    public static final int getPromethiumGenLevel(final int state) {
        return state >> PROMETHIUM_GEN_OFFSET & 0b11;
    }

    public static final int setPromethiumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << PROMETHIUM_GEN_OFFSET)) | (level << PROMETHIUM_GEN_OFFSET);
    }

    public static final int getRutheniumChipLevel(final int state) {
        return state >> RUTHENIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setRutheniumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << RUTHENIUM_CHIP_OFFSET)) | (level << RUTHENIUM_CHIP_OFFSET);
    }

    public static final int getRutheniumGenLevel(final int state) {
        return state >> RUTHENIUM_GEN_OFFSET & 0b11;
    }

    public static final int setRutheniumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << RUTHENIUM_GEN_OFFSET)) | (level << RUTHENIUM_GEN_OFFSET);
    }

    public static final int getStrontiumChipLevel(final int state) {
        return state >> STRONTIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setStrontiumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << STRONTIUM_CHIP_OFFSET)) | (level << STRONTIUM_CHIP_OFFSET);
    }

    public static final int getStrontiumGenLevel(final int state) {
        return state >> STRONTIUM_GEN_OFFSET & 0b11;
    }

    public static final int setStrontiumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << STRONTIUM_GEN_OFFSET)) | (level << STRONTIUM_GEN_OFFSET);
    }

    public static final int getThuliumChipLevel(final int state) {
        return state >> THULIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setThuliumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << THULIUM_CHIP_OFFSET)) | (level << THULIUM_CHIP_OFFSET);
    }

    public static final int getThuliumGenLevel(final int state) {
        return state >> THULIUM_GEN_OFFSET & 0b11;
    }

    public static final int setThuliumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << THULIUM_GEN_OFFSET)) | (level << THULIUM_GEN_OFFSET);
    }

    public static final int getEleriumChipLevel(final int state) {
        return state >> ELERIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setEleriumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << ELERIUM_CHIP_OFFSET)) | (level << ELERIUM_CHIP_OFFSET);
    }

    public static final int getEleriumGenLevel(final int state) {
        return state >> ELERIUM_GEN_OFFSET & 0b11;
    }

    public static final int setEleriumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << ELERIUM_GEN_OFFSET)) | (level << ELERIUM_GEN_OFFSET);
    }

    public static final int getDilithiumChipLevel(final int state) {
        return state >> DILITHIUM_CHIP_OFFSET & 0b11;
    }

    public static final int setDilithiumChipLevel(final int state, final int level) {
        return (state & ~(0b11 << DILITHIUM_CHIP_OFFSET)) | (level << DILITHIUM_CHIP_OFFSET);
    }

    public static final int getDilithiumGenLevel(final int state) {
        return state >> DILITHIUM_GEN_OFFSET & 0b11;
    }

    public static final int setDilithiumGenLevel(final int state, final int level) {
        return (state & ~(0b11 << DILITHIUM_GEN_OFFSET)) | (level << DILITHIUM_GEN_OFFSET);
    }

    public static void main(final String[] args) {
        new Aoc2016Day11().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int state = 0;
        state = readInput(input, state);
        state = setEleriumChipLevel(state, 3);
        state = setEleriumGenLevel(state, 3);
        state = setDilithiumChipLevel(state, 3);
        state = setDilithiumGenLevel(state, 3);
        return solve(state, false);
    }

    @Override
    public Integer part2(final Scanner input) {
        int state = 0;
        state = readInput(input, state);
        state = setEleriumChipLevel(state, 0);
        state = setEleriumGenLevel(state, 0);
        state = setDilithiumChipLevel(state, 0);
        state = setDilithiumGenLevel(state, 0);
        return solve(state, true);
    }

    private int readInput(Scanner input, int state) {
        for (int floor = 0; floor < 4; floor++) {
            String line = input.nextLine();
            if (line.contains("thulium generator")) {
                state = setThuliumGenLevel(state, floor);
            }
            if (line.contains("thulium-compatible microchip")) {
                state = setThuliumChipLevel(state, floor);
            }
            if (line.contains("plutonium generator")) {
                state = setPlutoniumGenLevel(state, floor);
            }
            if (line.contains("plutonium-compatible microchip")) {
                state = setPlutoniumChipLevel(state, floor);
            }
            if (line.contains("strontium generator")) {
                state = setStrontiumGenLevel(state, floor);
            }
            if (line.contains("strontium-compatible microchip")) {
                state = setStrontiumChipLevel(state, floor);
            }
            if (line.contains("promethium generator")) {
                state = setPromethiumGenLevel(state, floor);
            }
            if (line.contains("promethium-compatible microchip")) {
                state = setPromethiumChipLevel(state, floor);
            }
            if (line.contains("ruthenium generator")) {
                state = setRutheniumGenLevel(state, floor);
            }
            if (line.contains("ruthenium-compatible microchip")) {
                state = setRutheniumChipLevel(state, floor);
            }
        }
        return state;
    }

    public static int solve(final int startState, boolean step2) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startState);
        Map<Integer, Integer> predecessors = new HashMap<>();
        predecessors.put(startState, -1);
        Integer currentState;
        out:
        while (!queue.isEmpty()) {
            currentState = queue.poll();
            List<Integer> possibleStates = getPossibleStates(currentState, step2);
            for (Integer possibleState : possibleStates) {
                Integer pred = predecessors.get(possibleState);
                if (pred == null) {
                    queue.add(possibleState);
                    predecessors.put(possibleState, currentState);
                    if (isReady(possibleState)) {
                        break out;
                    }
                }
            }
        }

        int count = 0;

        Integer back = READY_STATE;
        while (back != null && back != -1) {
            count++;
            back = predecessors.get(back);
        }

        return count - 1;
    }

    public static final List<Integer> getPossibleStates(final int state, boolean step2) {
        List<Integer> list = new ArrayList<>();
        final int elevatorLevel = getElevatorLevel(state);
        List<Integer> offsets = getOffsetsOnLevel(state, elevatorLevel, step2);
        // eine Verschiebung
        for (int offset : offsets) {
            if (elevatorLevel < 3) {
                int newState = (state & ~(0b11 << offset)) | ((elevatorLevel + 1) << offset);
                newState = setElevatorLevel(newState, elevatorLevel + 1);
                if (isValid(newState)) {
                    list.add(newState);
                }
            }
            if (elevatorLevel > 0) {
                int newState = (state & ~(0b11 << offset)) | ((elevatorLevel - 1) << offset);
                newState = setElevatorLevel(newState, elevatorLevel - 1);
                if (isValid(newState)) {
                    list.add(newState);
                }
            }
        }
        // zwei Verschiebungen
        for (int i = 0; i < offsets.size() - 1; i++) {
            for (int j = i + 1; j < offsets.size(); j++) {
                int offset1 = offsets.get(i);
                int offset2 = offsets.get(j);
                if (elevatorLevel < 3) {
                    int newState = (state & ~(0b11 << offset1)) | ((elevatorLevel + 1) << offset1);
                    newState = (newState & ~(0b11 << offset2)) | ((elevatorLevel + 1) << offset2);
                    newState = setElevatorLevel(newState, elevatorLevel + 1);
                    if (isValid(newState)) {
                        list.add(newState);
                    }
                }
                if (elevatorLevel > 0) {
                    int newState = (state & ~(0b11 << offset1)) | ((elevatorLevel - 1) << offset1);
                    newState = (newState & ~(0b11 << offset2)) | ((elevatorLevel - 1) << offset2);
                    newState = setElevatorLevel(newState, elevatorLevel - 1);
                    if (isValid(newState)) {
                        list.add(newState);
                    }
                }
            }
        }
        return list;
    }

    public static final boolean isReady(final int state) {
        return state == READY_STATE;
    }

    public final static boolean isValid(final int state) {
        final int plChipLevel = getPlutoniumChipLevel(state);
        final int plGenLevel = getPlutoniumGenLevel(state);
        final int prChipLevel = getPromethiumChipLevel(state);
        final int prGenLevel = getPromethiumGenLevel(state);
        final int ruChipLevel = getRutheniumChipLevel(state);
        final int ruGenLevel = getRutheniumGenLevel(state);
        final int stChipLevel = getStrontiumChipLevel(state);
        final int stGenLevel = getStrontiumGenLevel(state);
        final int thChipLevel = getThuliumChipLevel(state);
        final int thGenLevel = getThuliumGenLevel(state);

        final int elChipLevel = getEleriumChipLevel(state);
        final int elGenLevel = getEleriumGenLevel(state);
        final int diChipLevel = getDilithiumChipLevel(state);
        final int diGenLevel = getDilithiumGenLevel(state);

        return (plGenLevel == plChipLevel || (plChipLevel != prGenLevel && plChipLevel != ruGenLevel && plChipLevel != stGenLevel && plChipLevel != thGenLevel
                && plChipLevel != elGenLevel && plChipLevel != diGenLevel)) &&
                (prGenLevel == prChipLevel || (prChipLevel != plGenLevel && prChipLevel != ruGenLevel && prChipLevel != stGenLevel && prChipLevel != thGenLevel
                        && prChipLevel != elGenLevel && prChipLevel != diGenLevel)) &&
                (ruGenLevel == ruChipLevel || (ruChipLevel != plGenLevel && ruChipLevel != prGenLevel && ruChipLevel != stGenLevel && ruChipLevel != thGenLevel
                        && ruChipLevel != elGenLevel && ruChipLevel != diGenLevel)) &&
                (stGenLevel == stChipLevel || (stChipLevel != plGenLevel && stChipLevel != prGenLevel && stChipLevel != ruGenLevel && stChipLevel != thGenLevel
                        && stChipLevel != elGenLevel && stChipLevel != diGenLevel)) &&
                (thGenLevel == thChipLevel || (thChipLevel != plGenLevel && thChipLevel != prGenLevel && thChipLevel != ruGenLevel && thChipLevel != stGenLevel
                        && thChipLevel != elGenLevel && thChipLevel != diGenLevel)) &&
                (elGenLevel == elChipLevel || (elChipLevel != plGenLevel && elChipLevel != prGenLevel && elChipLevel != ruGenLevel && elChipLevel != stGenLevel
                        && elChipLevel != thGenLevel && elChipLevel != diGenLevel)) &&
                (diGenLevel == diChipLevel || (diChipLevel != plGenLevel && diChipLevel != prGenLevel && diChipLevel != ruGenLevel && diChipLevel != stGenLevel
                        && diChipLevel != thGenLevel && diChipLevel != elGenLevel));
    }

    public static void print(final int state) {
        System.out.println("L|E|PL|PR|RU|ST|TH|EL|DI");
        int elevatorLevel = getElevatorLevel(state);
        int plChipLevel = getPlutoniumChipLevel(state);
        int plGenLevel = getPlutoniumGenLevel(state);
        int prChipLevel = getPromethiumChipLevel(state);
        int prGenLevel = getPromethiumGenLevel(state);
        int ruChipLevel = getRutheniumChipLevel(state);
        int ruGenLevel = getRutheniumGenLevel(state);
        int stChipLevel = getStrontiumChipLevel(state);
        int stGenLevel = getStrontiumGenLevel(state);
        int thChipLevel = getThuliumChipLevel(state);
        int thGenLevel = getThuliumGenLevel(state);
        int elChipLevel = getEleriumChipLevel(state);
        int elGenLevel = getEleriumGenLevel(state);
        int diChipLevel = getDilithiumChipLevel(state);
        int diGenLevel = getDilithiumGenLevel(state);

        for (int i = 3; i >= 0; i--) {
            System.out.print((i + 1) + "|");
            System.out.print(elevatorLevel == i ? "X" : " ");
            System.out.print("|");
            System.out.print(plGenLevel == i ? "G" : " ");
            System.out.print(plChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(prGenLevel == i ? "G" : " ");
            System.out.print(prChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(ruGenLevel == i ? "G" : " ");
            System.out.print(ruChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(stGenLevel == i ? "G" : " ");
            System.out.print(stChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(thGenLevel == i ? "G" : " ");
            System.out.print(thChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(elGenLevel == i ? "G" : " ");
            System.out.print(elChipLevel == i ? "M" : " ");
            System.out.print("|");
            System.out.print(diGenLevel == i ? "G" : " ");
            System.out.println(diChipLevel == i ? "M" : " ");
        }
    }

    public static final List<Integer> getOffsetsOnLevel(final int state, final int level, boolean step2) {
        List<Integer> list = new ArrayList<>();
        if (getPlutoniumChipLevel(state) == level) {
            list.add(PLUTONIUM_CHIP_OFFSET);
        }
        if (getPlutoniumGenLevel(state) == level) {
            list.add(PLUTONIUM_GEN_OFFSET);
        }
        if (getPromethiumChipLevel(state) == level) {
            list.add(PROMETHIUM_CHIP_OFFSET);
        }
        if (getPromethiumGenLevel(state) == level) {
            list.add(PROMETHIUM_GEN_OFFSET);
        }
        if (getRutheniumChipLevel(state) == level) {
            list.add(RUTHENIUM_CHIP_OFFSET);
        }
        if (getRutheniumGenLevel(state) == level) {
            list.add(RUTHENIUM_GEN_OFFSET);
        }
        if (getStrontiumChipLevel(state) == level) {
            list.add(STRONTIUM_CHIP_OFFSET);
        }
        if (getStrontiumGenLevel(state) == level) {
            list.add(STRONTIUM_GEN_OFFSET);
        }
        if (getThuliumChipLevel(state) == level) {
            list.add(THULIUM_CHIP_OFFSET);
        }
        if (getThuliumGenLevel(state) == level) {
            list.add(THULIUM_GEN_OFFSET);
        }
        if (step2) {
            if (getEleriumChipLevel(state) == level) {
                list.add(ELERIUM_CHIP_OFFSET);
            }
            if (getEleriumGenLevel(state) == level) {
                list.add(ELERIUM_GEN_OFFSET);
            }
            if (getDilithiumChipLevel(state) == level) {
                list.add(DILITHIUM_CHIP_OFFSET);
            }
            if (getDilithiumGenLevel(state) == level) {
                list.add(DILITHIUM_GEN_OFFSET);
            }
        }

        return list;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day11.txt";
    }
}