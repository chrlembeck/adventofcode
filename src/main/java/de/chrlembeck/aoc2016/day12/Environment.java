package de.chrlembeck.aoc2016.day12;

import java.util.List;

public class Environment {

    enum Register {
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
}