package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day18 extends AbstractAocBase {

    public static final Pattern REGEX = Pattern.compile("([a-z]+)\\s+(\\w+)\\s*(-?\\w+)?");

    public static void main(final String[] args) {
        new Aoc2017Day18().run();
    }

    @Override
    public BigInteger part1(final Scanner input) {
        final List<Instruction> program = loadProgram(input, 1);
        final State state = new State(0);
        while (state.getRecoveredSound() == null) {
            final int pos = state.getPosition();
            final Instruction instruction = program.get(pos);
            instruction.execute(state);
            state.incPos();
        }
        return state.getRecoveredSound();
    }

    @Override
    public BigInteger part2(final Scanner input) {
        final List<Instruction> program = loadProgram(input, 2);
        final State state0 = new State(0);
        final State state1 = new State(1);
        state0.setOtherState(state1);
        while (state0.isRunning() || state1.isRunning()) {
            executeStep(program, state0);
            executeStep(program, state1);
        }
        return state1.getSentCount();
    }

    public static List<Instruction> loadProgram(final Scanner input, final int part) {
        final List<Instruction> program = new ArrayList<Instruction>();
        while (input.hasNextLine()) {
            program.add(createInstruction(matchRegex(REGEX, input.nextLine()), part));
        }
        return program;
    }

    private static Instruction createInstruction(final Matcher matcher, final int part) {
        switch (matcher.group(1)) {
            case "snd":
                if (part == 1) {
                    return new SoundInstruction(matcher.group(2));
                } else {
                    return new SendInstruction(matcher.group(2));
                }
            case "add":
                return new AddInstruction(matcher.group(2), matcher.group(3));
            case "sub":
                return new SubInstruction(matcher.group(2), matcher.group(3));
            case "mul":
                return new MulInstruction(matcher.group(2), matcher.group(3));
            case "mod":
                return new ModInstruction(matcher.group(2), matcher.group(3));
            case "set":
                return new SetInstruction(matcher.group(2), matcher.group(3));
            case "rcv":
                if (part == 1) {
                    return new RecoverInstruction(matcher.group(2));
                } else {
                    return new ReceiveInstruction(matcher.group(2));
                }
            case "jgz":
                return new JgzInstruction(matcher.group(2), matcher.group(3));
            case "jnz":
                return new JnzInstruction(matcher.group(2), matcher.group(3));
            default:
                throw new IllegalArgumentException(matcher.toString());
        }
    }

    private void executeStep(final List<Instruction> program, final State state) {
        if (state.isRunning()) {
            final int pos = state.getPosition();
            final Instruction instruction = program.get(pos);
            instruction.execute(state);
            if (state.isRunning()) {
                state.incPos();
            }
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day18.txt";
    }

    public static Token createToken(final String value) {
        try {
            return new Constant(new BigInteger(value));
        } catch (final NumberFormatException nfe) {
            return new Variable(value);
        }
    }
}