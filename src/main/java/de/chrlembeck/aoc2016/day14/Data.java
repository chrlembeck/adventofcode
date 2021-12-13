package de.chrlembeck.aoc2016.day14;

import java.security.MessageDigest;
import java.util.TreeMap;

public class Data {

    public static final MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    String data;

    long index;

    private String hash;

    private Character tripletChar;

    private TreeMap<Character, Boolean> successMap = new TreeMap<>();

    public Data(String input, long index, boolean step2) {
        this.index = index;
        this.data = input + index;
        this.hash = md5(data);
        if (step2) {
            for (int i = 0; i < 2016; i++) {
                this.hash = md5(this.hash);
            }
        }

        for (int i = 0; i < hash.length(); ) {
            int count = 1;
            char ch = hash.charAt(i);
            while (i + count < hash.length() && hash.charAt(i + count) == ch) {
                count++;
            }
            if (count >= 3) {
                this.tripletChar = Character.valueOf(ch);
                break;
            }
            i += count;
        }
    }

    public boolean hasQuint(Character ch) {
        Boolean quint = successMap.get(ch);
        if (quint == null) {
            String q = "";
            for (int i = 0; i < 5; i++) {
                q = q + ch;
            }
            quint = hash.contains(q);
            successMap.put(ch, quint);
        }
        return quint.booleanValue();
    }

    public static final String md5(final String input) {
        final byte[] digest = md5.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((b & 0xf0) >> 4));
            sb.append(Integer.toHexString(b & 0xf));
        }
        return sb.toString();
    }

    public String getHash() {
        return hash;
    }

    public Character getTripletChar() {
        return tripletChar;
    }
}
