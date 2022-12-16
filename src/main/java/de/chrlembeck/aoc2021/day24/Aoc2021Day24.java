package de.chrlembeck.aoc2021.day24;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.chrlembeck.aoc2021.day24.BooleanConstant.FALSE;
import static de.chrlembeck.aoc2021.day24.Range.VARIABLE_RANGE;

public class Aoc2021Day24 extends AbstractAocBase {

    private final Pattern pattern = Pattern.compile("(\\w{3}) ([wxyz])( (-?\\d+|w|x|y|z))?");

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

    private String calc(final Scanner input, Function<Range, BigInteger> valueFinder) {
        AtomicInteger index = new AtomicInteger(0);
        List<Instruction> instructions = input.useDelimiter("\\n").tokens().map(s -> parseInstruction(index, s)).toList();

        Stack<State> states = new Stack<>();
        states.add(State.INITIAL);
        List<State> results = new ArrayList<>();
        while (!states.isEmpty()) {
            State currentState = states.pop();
            if (currentState.pc() >= instructions.size()) {
                results.add(currentState);
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

        results.removeIf(s -> BooleanCondition.createEqual(s.z(), IntValue.ZERO) == FALSE);
        Map<Integer, Range> ranges = calcRanges((AndCondition) results.get(0).condition());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            sb.append(valueFinder.apply(ranges.get(i)));
        }
        return sb.toString();
    }

    private Map<Integer, Range> calcRanges(AndCondition and) {
        Map<Integer, Range> ranges = new HashMap<>();
        for (BooleanCondition cond : and.getConditions()) {
            if (cond instanceof EqualCondition equal
                    && equal.right() instanceof Variable y
                    && equal.left() instanceof Sum add
                    && add.getAddends().size() == 2
                    && add.getAddends().get(0) instanceof Variable x
                    && add.getAddends().get(1) instanceof IntValue i) {
                ranges.put(x.index(), VARIABLE_RANGE.merge(new Range(BigInteger.ONE.subtract(i.getValue()), BigInteger.valueOf(9).subtract(i.getValue()))));
                ranges.put(y.index(), VARIABLE_RANGE.merge(new Range(BigInteger.ONE.add(i.getValue()), BigInteger.valueOf(9).add(i.getValue()))));
            } else if (cond instanceof EqualCondition equal
                    && equal.left() instanceof Variable x
                    && equal.right() instanceof Variable y) {
                ranges.put(x.index(), VARIABLE_RANGE);
                ranges.put(y.index(), VARIABLE_RANGE);
            } else {
                throw new RuntimeException();
            }
        }
        return ranges;
    }

    private Instruction parseInstruction(AtomicInteger index, String line) {
        Matcher matcher = matchRegex(pattern, line);
        Register left = Register.valueOf(matcher.group(2).toUpperCase());
        Value right = null;
        if (matcher.groupCount() >= 4 && matcher.group(4) != null) {
            right = matcher.group(4).length() == 1 && Character.isLetter(matcher.group(4).charAt(0))
                    ? Register.valueOf(matcher.group(4).toUpperCase())
                    : new IntValue(new BigInteger(matcher.group(4)));
        }
        return switch (matcher.group(1)) {
            case "inp" -> new InpInstruction(index.getAndIncrement(), left);
            case "add" -> new AddInstruction(left, right);
            case "mul" -> new MulInstruction(left, right);
            case "div" -> new DivInstruction(left, right);
            case "mod" -> new ModInstruction(left, right);
            case "eql" -> new EqlInstruction(left, right);
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day24.txt";
    }
}