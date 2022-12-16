package de.chrlembeck.aoc2021.day24;

import java.util.Collection;
import java.util.List;

public class InpInstruction implements Instruction {

    private final Register register;

    private final int index;

    public InpInstruction(int index, Register register) {
        this.index = index;
        this.register = register;
    }

    @Override
    public Collection<State> executeSymbolic(State state) {
        return List.of(state.setRegisterAndIncPc(register, new Variable(index)));
    }
}