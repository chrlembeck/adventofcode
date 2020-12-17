package de.chrlembeck.aoc2017.day18;

public class SetInstruction implements Instruction {

    private final Token value;

    private final String varName;

    public SetInstruction(final String varName, final String value) {
        this.varName = varName;
        this.value = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        state.setValue(varName, value.intValue(state));
    }

    public Token getValue() {
        return value;
    }
}