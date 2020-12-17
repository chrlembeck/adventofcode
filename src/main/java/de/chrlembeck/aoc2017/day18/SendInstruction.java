package de.chrlembeck.aoc2017.day18;

public class SendInstruction implements Instruction {

    private final Token token;

    public SendInstruction(final String value) {
        token = Aoc2017Day18.createToken(value);
    }

    @Override
    public void execute(final State state) {
        state.send(token.intValue(state));
    }
}