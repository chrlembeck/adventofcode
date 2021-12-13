package de.chrlembeck.aoc2016.day22;

import java.util.Objects;

public class Node {

    private int size;

    private int used;

    private boolean marked;

    public Node(int size, int used) {
        this.size = size;
        this.used = used;
    }

    public void mark(boolean mark) {
        this.marked = mark;
    }

    public boolean isMarked() {
        return marked;
    }

    public int getSize() {
        return size;
    }

    public int getUsed() {
        return used;
    }

    public int getAvail() {
        return size - used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return size == node.size && used == node.used && marked == node.marked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, used, marked);
    }
}