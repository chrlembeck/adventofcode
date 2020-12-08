package de.chrlembeck.aoc2020.day08;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.*;
import java.util.regex.Pattern;

public class Aoc2020Day08 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern.compile("(\\w+) ([+-]\\d+)");

    public static void main(final String[] args) {
        new Aoc2020Day08().run();
    }

    @Override
    public Object part1(final Scanner input) {
        final Program program = readProgram(input);
        checkTermination(program);
        return program.getAccumulator();
    }

    private boolean checkTermination(final Program program) {
        final Set<Integer> history = new TreeSet<>();
        int progCount;
        while (!history.contains(progCount = program.getProgCount())) {
            history.add(progCount);
            if (progCount == program.getInstructionCount()) {
                return true;
            }
            final AbstractInstruction instruction = program.getInstruction(progCount);
            instruction.execute(program);
        }
        return false;
    }

    private Program readProgram(final Scanner input) {
        final List<AbstractInstruction> instructions = new ArrayList<>();
        input.findAll(REGEX).map(mr -> AbstractInstruction.fromSource(mr.group(1), Integer.parseInt(mr.group(2)))).forEachOrdered(instructions::add);
        return new Program(instructions);
    }

    @Override
    public Object part2(final Scanner input) {
        final Program program = readProgram(input);
        for (int i = 0; i < program.getInstructionCount(); i++) {
            final AbstractInstruction instruction = program.getInstruction(i);
            AbstractInstruction replacement = null;
            if (instruction instanceof Jump) {
                replacement = new NoOperation(instruction.getArgument());
            } else if (instruction instanceof NoOperation) {
                replacement = new Jump(instruction.getArgument());
            }
            if (replacement != null) {
                program.replace(i, replacement);
                program.reset();
                if (checkTermination(program)) {
                    return program.getAccumulator();
                }
                program.replace(i, instruction);
            }
        }
        return null;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2020/aoc2020day08.txt";
    }
}