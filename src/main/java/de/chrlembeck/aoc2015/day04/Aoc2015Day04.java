package de.chrlembeck.aoc2015.day04;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Aoc2015Day04 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day04().run();
    }

    private MessageDigest initMD5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public final byte[] md5Hash(final String input) {
        try {
            return initMD5().digest(input.getBytes("ascii"));
        } catch (final UnsupportedEncodingException e) {
            // will never happen
            return null;
        }
    }

    @Override
    public Integer part1(final Scanner input) {
        final String line = input.nextLine();
        int result = 0;
        byte[] hash;
        do {
            hash = md5Hash(line + (++result));
        } while (hash[0] != 0 || hash[1] != 0 || ((hash[2] & 0xf0) != 0));
        return result;
    }

    @Override
    public Integer part2(final Scanner input) {
        final String line = input.nextLine();
        int result = 0;
        byte[] hash;
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