package de.chrlembeck.aoccommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public class Permutation<T> implements Iterator<T[]>, Iterable<T[]> {

    private long counter;

    private ArrayList<T> originalList;

    private int[] positions;

    private Function<Integer, T[]> arrayProducer;

    public Permutation(final T[] originalSet, final Function<Integer, T[]> arrayProducer) {
        this.originalList = new ArrayList<T>(originalSet.length);
        this.arrayProducer = arrayProducer;
        for (final T element : originalSet) {
            this.originalList.add(element);
        }
        this.counter = 0;
        this.positions = new int[originalSet.length];
    }

    public Permutation(final Collection<T> originalSet, final Function<Integer, T[]> arrayProducer) {
        this.originalList = new ArrayList<T>(originalSet.size());
        this.arrayProducer = arrayProducer;
        for (final T element : originalSet) {
            this.originalList.add(element);
        }
        this.counter = 0;
        this.positions = new int[originalSet.size()];
    }

    @Override
    public boolean hasNext() {
        return remaining() > 0;
    }

    public long remaining() {
        return factorial(originalList.size()) - counter;
    }

    private static long factorial(final int argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("argument may not be less than zero: " + argument);
        }
        long result = argument;
        for (int i = argument - 1; i >= 2; i--) {
            result *= i;
        }
        return result;
    }

    @Override
    public T[] next() {
        counter++;
        int posIndex;
        @SuppressWarnings("unchecked")
        final ArrayList<T> tmp = (ArrayList<T>) originalList.clone();
        final T[] result = arrayProducer.apply(originalList.size());
        for (int i = 0; i < positions.length; i++) {
            result[i] = tmp.remove(positions[i]);
        }
        posIndex = positions.length - 1;
        positions[posIndex]++;
        while (posIndex > 0 && positions[posIndex] == positions.length - posIndex) {
            positions[posIndex] = 0;
            posIndex--;
            positions[posIndex]++;
        }
        return result;
    }

    private static int[] getProgram(int index, final int elementCount) {
        final int[] result = new int[elementCount];
        for (int i = elementCount - 1; i >= 0; i--) {
            result[i] = index % (i + 1);
            index /= (i + 1);
        }
        return result;
    }

    @SuppressWarnings("PMD.UseArraysAsList")
    public static <T> T[] getPerm(final T[] original, final int index, final Function<Integer, T[]> arrayProducer) {
        final int[] program = getProgram(index, original.length);
        final ArrayList<T> list = new ArrayList<T>();
        for (int i = 0; i < original.length; i++) {
            list.add(program[i], original[i]);
        }
        final T[] result = arrayProducer.apply(original.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(original.length - i - 1);
        }
        return result;
    }

    @Override
    public void remove() {
        // not supported
    }

    @Override
    public Iterator<T[]> iterator() {
        return this;
    }
}