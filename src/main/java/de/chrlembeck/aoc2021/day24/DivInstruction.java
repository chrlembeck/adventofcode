package de.chrlembeck.aoc2021.day24;

import java.util.Collection;
import java.util.List;

public class DivInstruction implements Instruction {

    private final Register register;

    private final Value value;

    public DivInstruction(Register register, Value value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public Collection<State> executeSymbolic(State state) {
        return List.of(state.setRegisterAndIncPc(register, Expression.createDiv(register.eval(state), value.eval(state))));
    }
}