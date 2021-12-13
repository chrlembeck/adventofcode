package de.chrlembeck.aoc2016.day12;


public class Copy implements Operation {

    private Environment.Register from;

    private Environment.Register to;

    public Copy(Environment.Register from, Environment.Register to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Environment env) {
        int value = env.readRegister(from);
        env.setRegister(to, value);
        env.incPC();
    }
}