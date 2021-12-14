package de.chrlembeck.aoc2016.day12;

public class JumpNotZero implements Operation {

    private Expression value;

    private Expression offset;

    public JumpNotZero(Expression value, Expression offset) {
        this.value = value;
        this.offset = offset;
    }

    @Override
    public void execute(Environment env) {
        int intValue = value.evaluate(env);
        if (intValue != 0) {
            env.incPC(offset.evaluate(env));
        } else {
            env.incPC();
        }
    }

    @Override
    public Operation toggle() {
        return new Copy(value, offset);
    }

    @Override
    public String toString() {
        return "jnz " + value + " " + offset;
    }
}