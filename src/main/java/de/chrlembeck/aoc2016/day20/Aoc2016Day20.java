package de.chrlembeck.aoc2016.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Aoc2016Day20 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2016Day20().run();
    }

    @Override
    public Long part1(final Scanner input) {
        return blacklist(input).iterator().next().end+1;
    }

    @Override
    public Long part2(final Scanner input) {
        Set<Intervall> blacklist = blacklist(input);
        long anz = 4294967295L+1;
        for (Intervall inter: blacklist) {
            anz -= (inter.end - inter.start +1);
        }
        return anz;
    }

    private Set<Intervall> blacklist(Scanner input) {
        Set<Intervall> blacklist = new TreeSet<>();
        Set<Intervall> removers = new TreeSet<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] ab = line.split("-");
            Intervall newIntervall = new Intervall(Long.parseLong(ab[0]), Long.parseLong(ab[1]));
            for (Intervall inter : blacklist) {
                Intervall merged = inter.merge(newIntervall);
                if (merged != null) {
                    removers.add(inter);
                    newIntervall = merged;
                }
            }
            if (!removers.isEmpty()) {
                blacklist.removeAll(removers);
            }
            removers.clear();
            blacklist.add(newIntervall);
        }
        return blacklist;
    }


    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day20.txt";
    }

static final class Intervall implements Comparable<Intervall> {

    long start;

    long end;

    public Intervall(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Intervall merge(Intervall inter) {
        if (inter.start <= this.start && inter.end >= this.end) {
            return inter;
        }
        if (inter.start >= this.start && inter.end <= this.end) {
            return this;
        }
        if ((inter.start >= this.start && inter.start <= this.end + 1) ||
                (inter.end >= this.start - 1 && inter.end <= this.end)) {
            return new Intervall(Math.min(this.start, inter.start), Math.max(this.end, inter.end));
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Intervall) {
            Intervall i = (Intervall) obj;
            return i.start == start && i.end == end;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Long.valueOf(start ^ end).hashCode();
    }

    @Override
    public int compareTo(Intervall o) {
        return this.start == o.start ? 0 : (this.start < o.start ? -1 : 1);
    }

    @Override
    public String toString() {
        return "[" + start + "; " + end + "]";
    }
}
}