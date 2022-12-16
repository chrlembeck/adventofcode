package de.chrlembeck.aoc2021.day24;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.chrlembeck.aoccommon.AbstractAocBase.matchRegex;

public interface Instruction {

    Pattern PATTERN = Pattern.compile("(\\w{3}) ([wxyz])( (-?\\d+|w|x|y|z))?");

    Collection<State> executeSymbolic(State state);

    static Instruction parseInstruction(AtomicInteger index, String line) {
        Matcher matcher = matchRegex(PATTERN, line);
        Register left = Register.valueOf(matcher.group(2).toUpperCase());
        Value right = null;
        if (matcher.groupCount() >= 4 && matcher.group(4) != null) {
            right = matcher.group(4).length() == 1 && Character.isLetter(matcher.group(4).charAt(0))
                    ? Register.valueOf(matcher.group(4).toUpperCase())
                    : new IntValue(Long.parseLong(matcher.group(4)));
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
}