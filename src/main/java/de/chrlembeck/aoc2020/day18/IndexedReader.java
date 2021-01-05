package de.chrlembeck.aoc2020.day18;

public class IndexedReader {

    private final String input;

    private int pos;

    public IndexedReader(final String input) {
        this.input = input;
    }

    public void inc() {
        if (pos >= input.length()) {
            throw new IndexOutOfBoundsException("EOL");
        }
        pos++;
    }

    public char next() {
        return input.charAt(pos);
    }

    public boolean isEOL() {
        return pos == input.length();
    }
}