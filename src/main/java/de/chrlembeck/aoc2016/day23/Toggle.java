package de.chrlembeck.aoc2016.day23;

import de.chrlembeck.aoc2016.day12.Environment;
import de.chrlembeck.aoc2016.day12.Inc;
import de.chrlembeck.aoc2016.day12.Operation;

public class Toggle implements Operation {

    private final Environment.Register register;

    public Toggle(Environment.Register register) {
        this.register = register;
    }

    @Override
    public void execute(Environment env) {
        int value = env.readRegister(register);
        int index = env.getPc() + value;
        if (index >=0 && index < env.getOperationCount()) {
            Operation operation = env.getOperationAt(index);
            Operation newOperation = operation.toggle();
            env.replaceOperation(index, newOperation);
        }
        env.incPC();
    }

    @Override
    public Operation toggle() {
        return new Inc(register);
    }

    @Override
    public String toString() {
        return "tgl " + register;
    }
}