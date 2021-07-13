package Project;

import Project.FileOperations.DataOperations;
import Project.FileOperations.FileToHex;
import Project.FileOperations.FileTypes;
import Project.MyExceptions.ZeroArgsException;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws ZeroArgsException, IOException {

        // colors :)
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";

        // the number of arguments must be greater than zero
        if (args.length < 1){
            throw new ZeroArgsException();
        }

        DataOperations dataOperations = new DataOperations();
        FileTypes fileTypes;
        for (int i=0; i< args.length; i++){
            String dane[] = FileToHex.convertFileToHex(Paths.get(args[i]));
            dataOperations.setData(dane[0]);
            dataOperations.setTxt(dane[1]);
            fileTypes = dataOperations.checkTheType();
            if (fileTypes == FileTypes.NOT_FOUND){
                System.out.print(ANSI_BLUE + "| " + ANSI_RESET);
                System.out.print(ANSI_RED + "Type not found!" + ANSI_RESET);
                System.out.print(ANSI_BLUE + " |" + ANSI_RESET + "\n");
            } else {
                System.out.print(ANSI_BLUE + "| " + ANSI_RESET);
                System.out.print(ANSI_GREEN + "Your file type is: " + fileTypes.toString() + ANSI_RESET);
                System.out.print(ANSI_BLUE + " |" + ANSI_RESET + "\n");
            }
        }

    }
}
