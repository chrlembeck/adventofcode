package de.chrlembeck.aoc2016.day12;


public class Copy implements Operation {

    private Expression value;

    private Expression destination;

    public Copy(Expression value, Expression destination) {
        this.value = value;
        this.destination = destination;
    }

    @Override
    public void execute(Environment env) {
        if (destination instanceof Environment.Register register) {
            int value = this.value.evaluate(env);
            env.setRegister(register, value);
        }
        env.incPC();
    }

    @Override
    public Operation toggle() {
        return new JumpNotZero(value, destination);
    }

    @Override
    public String toString() {
        return "cpy " + value + " " + destination;
    }
}