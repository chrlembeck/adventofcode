package de.chrlembeck.aoccommon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public final class FileUtil {

    private FileUtil() {
    }

    public static String readString(final String location) {
        try (InputStream input = FileUtil.class.getResourceAsStream(location);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (final IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }

    public static String[] readLines(final String location) {
        try (InputStream input = FileUtil.class.getResourceAsStream(location);
                BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().toArray(String[]::new);
        } catch (final IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }
}