package de.chrlembeck.aoc2016.day05;

import de.chrlembeck.aoccommon.AbstractAocBase;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Aoc2016Day05 extends AbstractAocBase {

    public static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static void main(final String[] args) {
        new Aoc2016Day05().run();
    }

    @Override
    public String part1(final Scanner input) {
        return calcPassword(input, (password, length, byte6, bytes78) -> {
            password[length] = digits[byte6];
            return true;
        });
    }

    @Override
    public String part2(final Scanner input) {
        return calcPassword(input, (password, length, byte6, bytes78) -> {
            if (byte6 < 8 && password[byte6] == 0) {
                password[byte6] = digits[(bytes78 & 0xf0) >> 4];
                return true;
            }
            return false;
        });
    }

    public String calcPassword(final Scanner input, final PasswordCombiner passwordCombiner) {
        final String doorId = input.nextLine();
        long l = 0;
        int length = 0;
        final char[] password = new char[8];
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            while (length < 8) {
                final byte[] md5 = digest.digest((doorId + l++).getBytes());
                if (md5[0] == 0 && md5[1] == 0 && (md5[2] & 0xf0) == 0) {
                    if (passwordCombiner.combinePassword(password, length, md5[2] & 0x0f, md5[3])) {
                        length++;
                    }
                }
            }
            return new String(password);
        } catch (NoSuchAlgorithmException nse) {
            throw new RuntimeException(nse);
        }
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2016/aoc2016day05.txt";
    }

    @FunctionalInterface
    interface PasswordCombiner {

        boolean combinePassword(char[] password, int length, int byte6, byte bytes78);
    }
}