package de.chrlembeck.aoc2017.day20;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2017Day20 extends AbstractAocBase {

    // p=<1500,413,-535>, v=<-119,22,36>, a=<-5,-12,3>
    Pattern regex = Pattern
            .compile("p=<(-?\\d+),(-?\\d+),(-?\\d+)>, v=<(-?\\d+),(-?\\d+),(-?\\d+)>, a=<(-?\\d+),(-?\\d+),(-?\\d+)>");

    public Comparator<Particle> particleComparator = Comparator.comparing(Particle::getPx)
            .thenComparing(Particle::getPy)
            .thenComparing(Particle::getPz);

    public Comparator<Particle> accComp = Comparator
            .comparing(part -> part.getAx().abs()
                    .add(part.getAy().abs())
                    .add(part.getAz().abs()));

    public static void main(final String[] args) {
        new Aoc2017Day20().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int pos = 0;

        Particle nearest = null;

        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = matchRegex(regex, line);
            final Particle particle = readParticle(pos, matcher);
            if (nearest == null) {
                nearest = particle;
            } else {
                final int cmp = accComp.compare(nearest, particle);
                if (cmp > 0) {
                    System.out.println("new minimum at pos " + pos + " " + line);
                    nearest = particle;
                } else if (cmp == 0) {
                    System.out.println("same minimum at pos " + pos + " " + line);
                    nearest = particle;
                }
            }
            pos++;
        }
        return nearest.identifier;
    }

    private Particle readParticle(final int pos, final Matcher matcher) {
        final Particle particle = new Particle(pos, new BigInteger(matcher.group(1)),
                new BigInteger(matcher.group(2)), new BigInteger(matcher.group(3)),
                new BigInteger(matcher.group(4)), new BigInteger(matcher.group(5)),
                new BigInteger(matcher.group(6)), new BigInteger(matcher.group(7)),
                new BigInteger(matcher.group(8)), new BigInteger(matcher.group(9)));
        return particle;
    }

    @Override
    public Integer part2(final Scanner input) {
        final List<Particle> particles = new ArrayList<>();
        final int pos = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = matchRegex(regex, line);
            final Particle particle = readParticle(pos, matcher);
            particles.add(particle);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println(i + " " + particles.size());
            particles.sort(particleComparator);

            final List<Integer> removeList = new ArrayList<Integer>();
            for (int j = particles.size() - 2; j >= 0; j--) {
                final Particle particleA = particles.get(j);
                final Particle particleB = particles.get(j + 1);
                if (particleComparator.compare(particleA, particleB) == 0) {
                    if (!removeList.contains(j + 1)) {
                        removeList.add(j + 1);
                    }
                    if (!removeList.contains(j)) {
                        removeList.add(j);
                    }
                }
            }
            for (final int idx : removeList) {
                particles.remove(idx);
            }

            for (final Particle p : particles) {
                p.velX = p.velX.add(p.accX);
                p.velY = p.velY.add(p.accY);
                p.velZ = p.velZ.add(p.accZ);
                p.posX = p.posX.add(p.velX);
                p.posY = p.posY.add(p.velY);
                p.posZ = p.posZ.add(p.velZ);
            }
        }
        System.out.println(particles.size());
        return particles.size();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day20.txt";
    }
}