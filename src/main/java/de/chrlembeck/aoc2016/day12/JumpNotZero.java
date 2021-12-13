package de.chrlembeck.aoc2016.day12;

public class JumpNotZero implements Operation {

    private int offset;

    private Environment.Register register;

    public JumpNotZero(Environment.Register register, int offset) {
        this.register = register;
        this.offset = offset;
    }

    @Override
    public void execute(Environment env) {
        int value = env.readRegister(register);
        if (value != 0) {
            env.incPC(offset);
        } else {
            env.incPC();
        }
    }
}