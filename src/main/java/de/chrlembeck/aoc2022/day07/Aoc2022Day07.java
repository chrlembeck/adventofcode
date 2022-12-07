package de.chrlembeck.aoc2022.day07;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2022Day07 extends AbstractAocBase {

    private final Pattern commandPattern = Pattern.compile("\\$ (cd|ls)( (/|..|\\w+))?");

    private final Pattern entryPattern = Pattern.compile("(dir (\\w+))|((\\d+) \\w+(.\\w+)?)");

    public static void main(final String[] args) {
        new Aoc2022Day07().run();
    }

    @Override
    public BigInteger part1(final Scanner input) {
        Directory root = tree(input);
        return root.part1Sum();
    }

    @Override
    public BigInteger part2(final Scanner input) {
        Directory root = tree(input);
        BigInteger toDelete = new BigInteger("30000000").subtract(new BigInteger("70000000").subtract(root.totalSize()));
        return root.findBestFit(toDelete, root.totalSize());
    }

    private Directory tree(final Scanner input) {
        Directory root = new Directory("/", null);
        Directory current = root;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.charAt(0) == '$') {
                Matcher matcher = matchRegex(commandPattern, line);
                if ("cd".equals(matcher.group(1))) {
                    current = current.getDirectory(matcher.group(3));
                }
            } else {
                Matcher matcher = matchRegex(entryPattern, line);
                if (matcher.group(1) != null) {
                    String name = matcher.group(2);
                    current.mkdir(name);
                } else {
                    current.size = current.size.add(new BigInteger(matcher.group(4)));
                }
            }
        }
        return root;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2022/aoc2022day07.txt";
    }

    static class Directory {
        private final String name;
        private final List<Directory> directories = new ArrayList<>();
        private BigInteger size = BigInteger.ZERO;
        private final Directory parent;

        public Directory(String name, Directory parent) {
            this.name = name;
            this.parent = parent;
        }

        public Directory getDirectory(String name) {
            if ("..".equals(name)) {
                return parent;
            }
            if ("/".equals(name)) {
                return parent == null ? this : parent.getDirectory(name);
            }
            for (Directory directory : directories) {
                if (directory.name.equals(name)) {
                    return directory;
                }
            }
            throw new IllegalArgumentException("not found");
        }

        public void mkdir(String name) {
            directories.add(new Directory(name, this));
        }

        public BigInteger part1Sum() {
            BigInteger sum = directories.stream().map(Directory::part1Sum).reduce(BigInteger.ZERO, BigInteger::add);
            return totalSize().compareTo(new BigInteger("100000")) <= 0 ? sum.add(totalSize()) : sum;
        }

        public BigInteger totalSize() {
            return size.add(directories.stream().map(Directory::totalSize).reduce(BigInteger.ZERO, BigInteger::add));
        }

        public BigInteger findBestFit(BigInteger toDelete, BigInteger currentBest) {
            BigInteger totalSize = totalSize();
            currentBest = (totalSize.compareTo(toDelete) >= 0 && totalSize.compareTo(currentBest) < 0) ? totalSize : currentBest;
            for (Directory directory : directories) {
                currentBest = directory.findBestFit(toDelete, currentBest);
            }
            return currentBest;
        }
    }
}