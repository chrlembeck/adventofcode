package de.chrlembeck.aoc2016.day12;

public class Dec implements Operation {

    private Expression expression;

    public Dec(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(Environment env) {
        if (expression instanceof Environment.Register register) {
            int value = env.readRegister(register);
            env.setRegister(register, value - 1);
        }
        env.incPC();
    }

    @Override
    public Operation toggle() {
        return new Inc(expression);
    }

    @Override
    public String toString() {
        return "dec " + expression;
    }
}