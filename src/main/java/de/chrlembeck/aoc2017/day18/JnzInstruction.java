package de.chrlembeck.aoc2017.day18;

import java.math.BigInteger;

public class JnzInstruction implements Instruction {

    private Token condition;

    private Token offset;

    public JnzInstruction(final String cond, final String offs) {
        this.condition = Aoc2017Day18.createToken(cond);
        this.offset = Aoc2017Day18.createToken(offs);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);
        if (intValue.signum() != 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}