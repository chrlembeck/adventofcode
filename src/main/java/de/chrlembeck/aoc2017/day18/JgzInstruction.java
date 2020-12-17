package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class JgzInstruction implements Instruction {

    private final Token condition;

    private final Token offset;

    public JgzInstruction(final String cond, final String offs) {
        this.condition = Aoc2017Day18.createToken(cond);
        this.offset = Aoc2017Day18.createToken(offs);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);
        if (intValue.signum() > 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}