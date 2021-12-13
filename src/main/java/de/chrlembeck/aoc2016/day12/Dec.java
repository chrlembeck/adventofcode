package de.chrlembeck.aoc2016.day12;


public class Dec implements Operation {

    private Environment.Register register;

    public Dec(Environment.Register reg){
        this.register = reg;
    }

    @Override
    public void execute(Environment env) {
        int value = env.readRegister(register);
        env.setRegister(register, value-1);
        env.incPC();
    }
}