package de.chrlembeck.aoc2016.day12;

import java.util.List;
import java.util.function.IntPredicate;

public class Environment {

    private IntPredicate outputChannel;

    public enum Register implements Expression{
        A(0),
        B(1),
        C(2),
        D(3);

        private int idx;

        Register(int idx) {
            this.idx = idx;
        }

        public int getIdx() {
            return idx;
        }

        public static Register fromName(String name) {
            return switch (name) {
                case "a" -> A;
                case "b" -> B;
                case "c" -> C;
                case "d" -> D;
                default -> throw new IllegalArgumentException();
            };
        }

        @Override
        public int evaluate(Environment env) {
            return env.readRegister(this);
        }
    }

    private int[] registers = new int[4];

    private int pc = 0;

    private List<Operation> operations;

    public Environment(List<Operation> operations) {
        this.operations = operations;
    }

    public void run() {
        while (pc < operations.size()) {
            Operation op = operations.get(pc);
            op.execute(this);
        }
    }

    public void setRegister(Register register, int value) {
        registers[register.getIdx()] = value;
    }

    public int readRegister(Register register) {
        return registers[register.getIdx()];
    }

    public void incPC() {
        pc++;
    }

    public void incPC(int offset) {
        pc += offset;
    }

    public Operation getOperationAt(int pos) {
        return operations.get(pos);
    }

    public int getPc() {
        return pc;
    }

    public int getOperationCount() {
        return operations.size();
    }

    public void replaceOperation(int index, Operation newOperation) {
        operations.set(index, newOperation);
    }

    public void output(int value) {
        if (!outputChannel.test(value)) {
            pc = getOperationCount();
        }
    }

    public void setOutputChannel(IntPredicate outputChannel) {
        this.outputChannel = outputChannel;
    }
}