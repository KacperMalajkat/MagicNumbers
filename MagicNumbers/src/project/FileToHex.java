package project;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileToHex {

    public static String[] getHeadAndTail(Path path, int hexdigits) throws IOException {

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File not found! " + path);
        }
        if (hexdigits < 1){
            throw new project.ValueException("Tail and head bytes must be greater than 0");
        }

        List<String> fileinHex = new ArrayList<>();

        int value;

        // path to inputstream....
        try (InputStream inputStream = Files.newInputStream(path)) {

            while ((value = inputStream.read()) != -1) {
                fileinHex.add(String.format("%02X ", value));
            }

            int rozmiar = fileinHex.size();

            String[] result = new String[2];
            //max i min są po to gdyby rozmiar pliku był mniejszy niż ilość bajtów co chcemy wczytać
            result[0] = String.join(" ",fileinHex.subList(0, Math.min(hexdigits, rozmiar))).replace("  "," ");
            result[1] = String.join(" ",fileinHex.subList(Math.max(0, rozmiar-hexdigits), rozmiar)).replace("  "," ");
            return result;
        }
    }

}