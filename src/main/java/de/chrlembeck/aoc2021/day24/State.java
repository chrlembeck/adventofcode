package de.chrlembeck.aoc2021.day24;

public record State(Expression w,
                    Expression x,
                    Expression y,
                    Expression z,
                    int pc,
                    BooleanCondition condition) {

    public static final State INITIAL = new State(IntValue.ZERO, IntValue.ZERO, IntValue.ZERO, IntValue.ZERO, 0, BooleanConstant.TRUE);

    public State setRegisterAndIncPc(Register register, Expression value) {
        return switch (register) {
            case W -> new State(value, x, y, z, pc + 1, condition);
            case X -> new State(w, value, y, z, pc + 1, condition);
            case Y -> new State(w, x, value, z, pc + 1, condition);
            case Z -> new State(w, x, y, value, pc + 1, condition);
        };
    }

    public State addCondition(BooleanCondition newCondition) {
        return new State(w, x, y, z, pc, BooleanCondition.createAnd(condition, newCondition));
    }
}