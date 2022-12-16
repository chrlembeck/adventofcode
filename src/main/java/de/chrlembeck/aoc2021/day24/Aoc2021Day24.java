package de.chrlembeck.aoc2021.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

import static de.chrlembeck.aoc2021.day24.BooleanConstant.FALSE;

public class Aoc2021Day24 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day24().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calc(input, Range::upper);
    }

    @Override
    public String part2(final Scanner input) {
        return calc(input, Range::lower);
    }

    private String calc(final Scanner input, Function<Range, Long> valueFinder) {
        AtomicInteger index = new AtomicInteger(0);
        List<Instruction> instructions = input.useDelimiter("\\n").tokens().map(s -> Instruction.parseInstruction(index, s)).toList();

        Stack<State> states = new Stack<>();
        states.add(State.INITIAL);
        List<State> results = new ArrayList<>();
        while (!states.isEmpty()) {
            State currentState = states.pop();
            if (currentState.pc() >= instructions.size()) {
                if (BooleanCondition.createEqual(currentState.z(), IntValue.ZERO) != FALSE) {
                    results.add(currentState);
                }
            } else {
                Instruction instruction = instructions.get(currentState.pc());
                Collection<State> newStates = instruction.executeSymbolic(currentState);
                for (State newState : newStates) {
                    if (!(newState.condition() instanceof BooleanConstant c) || c != FALSE) {
                        states.add(newState);
                    }
                }
            }
        }

        Map<Integer, Range> ranges = calcRanges((AndCondition) results.get(0).condition());
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 14).mapToObj(ranges::get).map(valueFinder::apply).forEach(sb::append);
        return sb.toString();
    }

    private Map<Integer, Range> calcRanges(AndCondition and) {
        Map<Integer, Range> ranges = new HashMap<>();
        for (BooleanCondition cond : and.getConditions()) {
            if (cond instanceof EqualCondition equal){
                equal.collectRanges(ranges);
            } else {
                throw new RuntimeException();
            }
        }
        return ranges;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day24.txt";
    }
}