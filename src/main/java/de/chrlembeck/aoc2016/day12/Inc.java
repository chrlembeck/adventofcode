package de.chrlembeck.aoc2016.day12;

public class Inc implements Operation {

    private Environment.Register register;

    public Inc(Environment.Register reg){
        this.register = reg;
    }

    @Override
    public void execute(Environment env) {
        int value = env.readRegister(register);
        env.setRegister(register, value+1);
        env.incPC();
    }
}