package de.chrlembeck.aoc2019.day05;

import java.math.BigInteger;

public abstract class AbstractInstruction {

    private final State state;

    private final IntcodeProgram program;

    private int opcode;

    private int firstParameter;

    private int secondParameter;

    private int thirdParameter;

    public static AbstractInstruction readNextInstruction(final IntcodeProgram program, final State state) {
        final int code = program.get(state.getProgCount()).intValueExact();
        final int opcode = code % 100;
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
            case 9:
                return new AdjustRelativeBaseInstruction(program, state);
            case 99:
                return new Exit( program, state);
            default:
                throw new RuntimeException("unknown opcode: " + opcode);
        }
    }

    AbstractInstruction(final IntcodeProgram program, final State state) {
        final int code = program.get(state.getProgCount()).intValueExact();
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

    public abstract void exec(final IntcodeProgram program, final State state);

    protected static final int PARAMETER_POSITION_MODE = 0;

    protected static final int PARAMETER_IMMIDIATE_MODE = 1;

    protected static final int PARAMETER_RELATIVE_MODE = 2;

    public BigInteger getAndEvaluateOperand1() {
        BigInteger operand1 = program.get(state.getProgCount() + 1);
        switch(getFirstParameter()) {
            case PARAMETER_IMMIDIATE_MODE:
                return operand1;
            case PARAMETER_POSITION_MODE:
                return program.get(operand1.intValueExact());
            case PARAMETER_RELATIVE_MODE:
                return program.get(operand1.intValueExact() + state.getRelativeBase());
            default:
                throw new IllegalArgumentException("unknown parameter mode " + getFirstParameter());
        }
    }

    public BigInteger getAndEvaluateOperand2() {
        BigInteger operand2 = program.get(state.getProgCount() + 2);
        switch(getSecondParameter()) {
            case PARAMETER_IMMIDIATE_MODE:
                return operand2;
            case PARAMETER_POSITION_MODE:
                return program.get(operand2.intValueExact());
            case PARAMETER_RELATIVE_MODE:
                return program.get(operand2.intValueExact() + state.getRelativeBase());
            default:
                throw new IllegalArgumentException("unknown parameter mode " + getSecondParameter());
        }
    }
}