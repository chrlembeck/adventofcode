package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;
import java.util.List;

public abstract class Instruction {

    private final State state;

    private final List<BigInteger> program;

    private int opcode;

    private int firstParameter;

    private int secondParameter;

    private int thirdParameter;

    public static Instruction of(List<BigInteger> program, State state) {
        int code = program.get(state.getProgCount()).intValueExact();
        int opcode = code % 100;
        switch (opcode) {
            case 1:
                return new ArithmeticInstruction( program, state, BigInteger::add);
            case 2:
                return new ArithmeticInstruction( program, state, BigInteger::multiply);
            case 3:
                return new Input(program, state);
            case 4:
                return new Output(program, state);
            case 5:
                return new JumpInstruction( program, state, operand -> operand.compareTo(BigInteger.ZERO) != 0);
            case 6:
                return new JumpInstruction( program, state, operand -> operand.compareTo(BigInteger.ZERO) == 0);
            case 7:
                return new CompareInstruction( program, state, (operand1, operand2) -> operand1.compareTo(operand2) < 0);
            case 8:
                return new CompareInstruction( program, state, (operand1, operand2) -> operand1.compareTo(operand2) == 0);
            case 99:
                return new Exit( program, state);
            default:
                throw new RuntimeException("unknown opcode: " + opcode);
        }
    }

    Instruction(List<BigInteger> program, State state) {
        int code = program.get(state.getProgCount()).intValueExact();
        this.program = program;
        this.state = state;
        opcode = code % 100;
        firstParameter = (code % 1000) / 100;
        secondParameter = (code % 10_000) / 1_000;
        thirdParameter = (code % 100_000) / 10_000;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getThirdParameter() {
        return thirdParameter;
    }

    public int getSecondParameter() {
        return secondParameter;
    }

    public int getFirstParameter() {
        return firstParameter;
    }

    public abstract void exec(List<BigInteger> program, State state);

    public BigInteger getAndEvaluateOperand1() {
        BigInteger operand1 = program.get(state.getProgCount() + 1);
        if (getFirstParameter() == 0) {
            operand1 = program.get(operand1.intValueExact());
        }
        return operand1;
    }

    public BigInteger getAndEvaluateOperand2() {
        BigInteger operand2 = program.get(state.getProgCount() + 2);
        if (getSecondParameter() == 0) {
            operand2 = program.get(operand2.intValueExact());
        }
        return operand2;
    }
}