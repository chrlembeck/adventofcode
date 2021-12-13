package de.chrlembeck.aoc2016.day16;

public class Bits {

    private final String data;

    private final int size;

    private final int width;

    public Bits(String data, int size) {
        this.data = data;
        this.width = size;
        int s = data.length();
        while (s < size) {
            s = s * 2 + 1;
        }
        this.size = s;
    }

    public boolean getBit(int idx) {
        return getBit(idx, size);
    }

    private boolean getBit(int idx, int size) {
        if (idx < data.length()) {
            return data.charAt(idx) == '1';
        }
        if (idx == (size - 1) / 2) {
            return false;
        }
        if (idx < (size - 1) / 2) {
            return getBit(idx, (size - 1) / 2);
        } else {
            return !getBit(size - idx - 1, (size - 1) / 2);
        }
    }

    private boolean checksum(int offset, int len) {
        if (len == 2) {
            return getBit(offset) == getBit(offset + 1);
        } else {
            int part = len / 2;
            return checksum(offset, part) == checksum(offset + part, part);
        }
    }

    public String checksum() {
        int d = 0;
        int w = width;
        int b = 1;
        while (w % 2 == 0) {
            w /= 2;
            b *= 2;
            d += 1;
        }
        System.out.println(width + " lässt sich " + d + " mal durch 2 teilen. Es bleiben " + w + " übrigt. Die Zweierpotenz ist " + b + ".");
        StringBuilder sb = new StringBuilder();
        for (int cPos = 0; cPos < w; cPos++) {
            boolean cs = checksum(cPos * b, b);
            sb.append(cs ? '1' : '0');
        }
        return sb.toString();
    }
}