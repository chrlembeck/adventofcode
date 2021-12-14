package de.chrlembeck.aoc2016.day12;

public class Inc implements Operation {

    private Expression expression;

    public Inc(Expression expression){
        this.expression = expression;
    }

    @Override
    public void execute(Environment env) {
        if (expression instanceof Environment.Register register) {
            int value = env.readRegister(register);
            env.setRegister(register, value + 1);
        }
        env.incPC();
    }

    @Override
    public Operation toggle() {
        return new Dec(expression);
    }

    @Override
    public String toString() {
        return "inc " + expression;
    }
}