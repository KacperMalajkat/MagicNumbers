package Project.FileOperations;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileToHex {
    private static final String UNKNOWN_CHARACTER = ".";
    public static StringBuilder text = new StringBuilder();

    public static String[] convertFileToHex(Path path) throws IOException {

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File not found! " + path);
        }

        StringBuilder result = new StringBuilder();
        StringBuilder hex = new StringBuilder();
        StringBuilder input = new StringBuilder();

        int count = 0;
        int value;

        // path to inputstream....
        try (InputStream inputStream = Files.newInputStream(path)) {

            while ((value = inputStream.read()) != -1) {

                hex.append(String.format("%02X ", value));

                //If the character is unable to convert, just prints a dot "."
                if (!Character.isISOControl(value)) {
                    input.append((char) value);
                } else {
                    input.append(UNKNOWN_CHARACTER);
                }

                // After 15 bytes, reset everything for formatting purpose
                if (count == 14) {
                    result.append(String.format("%-60s | %s%n", hex, input));
                    text.append(input);
                    hex.setLength(0);
                    input.setLength(0);
                    count = 0;
                } else {
                    count++;
                }

            }

            // if the count>0, meaning there is remaining content
            if (count > 0) {
                result.append(String.format("%-60s | %s%n", hex, input));
                text.append(input);
            }

        }
        String[] tab = new String[2];
        tab[0] = result.toString();
        tab[1] = text.toString();
        return tab;
    }

}
