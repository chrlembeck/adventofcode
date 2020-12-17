package de.chrlembeck.aoc2017.day20;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2017Day20 extends AbstractAocBase {

    private static final Pattern REGEX = Pattern
            .compile("p=<(-?\\d+),(-?\\d+),(-?\\d+)>, v=<(-?\\d+),(-?\\d+),(-?\\d+)>, a=<(-?\\d+),(-?\\d+),(-?\\d+)>");

    public Comparator<Particle> particleComparator = Comparator.comparing(Particle::getPosX)
            .thenComparing(Particle::getPosY)
            .thenComparing(Particle::getPosZ);

    public Comparator<Particle> accComp = Comparator
            .comparing((Particle part) -> part.getAccX().abs()
                    .add(part.getAccY().abs())
                    .add(part.getAccZ().abs()))
            .thenComparing(part-> part.getVelX().add(part.getVelY()).add(part.getVelZ()))
            .thenComparing(part -> part.getPosX().add(part.getPosY()).add(part.getPosZ()));


    public static void main(final String[] args) {
        new Aoc2017Day20().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        int pos = 0;
        Particle nearest = null;

        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = matchRegex(REGEX, line);
            final Particle particle = readParticle(pos, matcher);
            particle.normalizeDirection();

            if (nearest == null) {
                nearest = particle;
            } else {
                final int cmp = accComp.compare(nearest, particle);
                if (cmp >= 0) {
                    nearest = particle;
                }
            }
            pos++;
        }
        return nearest.getIdentifier();
    }

    private Particle readParticle(final int pos, final Matcher matcher) {
        return new Particle(pos, new BigInteger(matcher.group(1)),
                new BigInteger(matcher.group(2)), new BigInteger(matcher.group(3)),
                new BigInteger(matcher.group(4)), new BigInteger(matcher.group(5)),
                new BigInteger(matcher.group(6)), new BigInteger(matcher.group(7)),
                new BigInteger(matcher.group(8)), new BigInteger(matcher.group(9)));
    }

    @Override
    public Integer part2(final Scanner input) {
        final List<Particle> particles = new ArrayList<>();
        final int pos = 0;
        while (input.hasNextLine()) {
            final String line = input.nextLine();
            final Matcher matcher = matchRegex(REGEX, line);
            final Particle particle = readParticle(pos, matcher);
            particles.add(particle);
        }
        for (int i = 0; i < 1000; i++) {
            particles.sort(particleComparator);

            final List<Integer> removeList = new ArrayList<>();
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
                p.setVelX(p.getVelX().add(p.getAccX()));
                p.setVelY(p.getVelY().add(p.getAccY()));
                p.setVelZ(p.getVelZ().add(p.getAccZ()));
                p.setPosX(p.getPosX().add(p.getVelX()));
                p.setPosY(p.getPosY().add(p.getVelY()));
                p.setPosZ(p.getPosZ().add(p.getVelZ()));
            }
        }
        return particles.size();
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2017/aoc2017day20.txt";
    }
}