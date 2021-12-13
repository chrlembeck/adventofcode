package de.chrlembeck.aoc2016.day12;


public class CopyConst implements Operation {

    private int value;

    private Environment.Register register;

    public CopyConst(int value, Environment.Register a) {
        this.value = value;
        this.register = a;
    }

    @Override
    public void execute(Environment env) {
        env.setRegister(register, value);
        env.incPC();
    }
}