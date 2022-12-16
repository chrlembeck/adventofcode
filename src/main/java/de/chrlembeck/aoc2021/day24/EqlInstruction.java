package de.chrlembeck.aoc2021.day24;

import java.util.Collection;
import java.util.List;

public class EqlInstruction implements Instruction {

    private final Register register;

    private final Value value;

    public EqlInstruction(Register register, Value value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public Collection<State> executeSymbolic(State state) {
        return List.of(
                state.setRegisterAndIncPc(register, IntValue.ONE).addCondition(BooleanCondition.createEqual(register.eval(state), value.eval(state))),
                state.setRegisterAndIncPc(register, IntValue.ZERO).addCondition(BooleanCondition.createNotEqual(register.eval(state), value.eval(state)))
        );
    }
}