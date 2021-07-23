package project;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int HEXDIGITS = 8;

    private static boolean checkmagicbytes(String head, String tail, Map<String, String[]> bytesmap) {
        boolean startcheck = false;
        boolean endcheck = false;
        boolean middlecheck = false;

        if (!bytesmap.containsKey("starting")){
            startcheck = true;
        }
        else{
            for (String s : bytesmap.get("starting")){
                if (head.startsWith(s)){
                    startcheck = true;
                    break;
                }
            }
        }

        if (!startcheck){
            return false;
        }

        if(!bytesmap.containsKey("ending")){
            endcheck = true;
        }
        else{
            for (String s : bytesmap.get("ending")){
                if (tail.endsWith(s+" ")){
                    endcheck = true;
                    break;
                }
            }
        }
        if (!endcheck){
            return false;
        }

        if (!bytesmap.containsKey("middle")){
            middlecheck = true;
        }
        else{
            for (String s : bytesmap.get("middle")){
                if (tail.contains(s) || head.contains(s)){
                    middlecheck = true;
                    break;
                }
            }
        }
        return middlecheck;
    }

    public static void main(String[] args) throws IOException {

        Map<String, Map<String, String[]>> testermap = new HashMap<String, Map<String, String[]>>(){{
            // dodajemy nowe rozszerzenie do sprawdzania
            put(FileTypes.JPG.toString(), new HashMap<String, String[]>(){{
                //lista ciagów na jakie może się zaczynać
                put("starting", new String[]{"FF D8"});
                //lista ciągów na jakie może się kończyć
                put("ending", new String[]{"D9"});
//                //lista ciągów które ma gdzieś w środku na końcu bądź początku gdziekolwiek choć raz
//                put("middle", new String[]{"JFIF"});
            }});
            put(FileTypes.PNG.toString(), new HashMap<String, String[]>(){{
                put("starting", new String[]{"89 50 4E 47 0D 0A 1A 0A"});
            }});
            put(FileTypes.PDF.toString(), new HashMap<String, String[]>(){{
                put("starting", new String[]{"25 50 44 46"});
            }});
//            put("txt", new HashMap<String, String[]>(){{
//            TODO:
//            }});
        }};

        // the number of arguments must be greater than zero
        if (args.length < 1) {
            System.out.println("Nie podano plików do sprawdzanie");
            return;
        }

        for (String filename : args){
            //hex digits czyli ile pierwszych i ostatnich bajtów w hexie ma wczytać

            String[] headAndTail = FileToHex.getHeadAndTail(Paths.get(filename), HEXDIGITS);
            boolean sprawdzenie = false;
            for (Map.Entry<String, Map<String, String[]>> entry: testermap.entrySet())
            {
                String key = entry.getKey();
                Map<String, String[]> parametry = entry.getValue();
                sprawdzenie = checkmagicbytes(headAndTail[0], headAndTail[1], parametry);
                if (sprawdzenie){
                    System.out.println("Plik: "+filename+" jest typu: "+key);
                    break;
                }
            }

            if (!sprawdzenie) System.out.println("Plik: "+filename+" jest nieokreslonego typu");

            System.out.println(Arrays.toString(headAndTail));

        }

    }
}