package de.chrlembeck.aoc2015.day04;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import de.chrlembeck.aoccommon.AbstractAocBase;

public class Aoc2015Day04 extends AbstractAocBase {

    private final MessageDigest md5;

    public static void main(final String[] args) {
        new Aoc2015Day04().run();
    }

    public Aoc2015Day04() {
        try {
            this.md5 = MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public final byte[] md5Hash(final String input) {
        md5.reset();
        try {
            return md5.digest(input.getBytes("ascii"));
        } catch (final UnsupportedEncodingException e) {
            // will never happen
            return null;
        }
    }

    @Override
    public Integer part1(final Scanner input) {
        final String line = input.nextLine();
        int result = 0;
        byte[] hash = null;
        do {
            hash = md5Hash(line + (++result));
        } while (hash[0] != 0 || hash[1] != 0 || ((hash[2] & 0xf0) != 0));
        return result;
    }

    @Override
    public Integer part2(final Scanner input) {
        final String line = input.nextLine();
        int result = 0;
        byte[] hash = null;
        do {
            hash = md5Hash(line + (++result));
        } while (hash[0] != 0 || hash[1] != 0 || hash[2] != 0);
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day04.txt";
    }
}