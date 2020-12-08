package de.chrlembeck.aoc2020.day08;

public abstract class AbstractInstruction {

    private final int argument;

    public AbstractInstruction(final int argument) {
        this.argument = argument;
    }

    public static AbstractInstruction fromSource(final String operation, final int argument) {
        return switch (operation) {
            case "nop" -> new NoOperation(argument);
            case "acc" -> new AccumulatorInstruction(argument);
            case "jmp" -> new Jump(argument);
            default -> throw new IllegalArgumentException("unkonwn operation " + operation);
        };
    }

    public int getArgument() {
        return argument;
    }

    public abstract void execute(Program program);
}