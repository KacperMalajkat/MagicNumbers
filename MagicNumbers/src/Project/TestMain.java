package Project;

import Project.FileOperations.DataOperations;
import Project.FileOperations.FileToHex;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException {
        String file1 = "C:\\Users\\kacpe\\Desktop\\Pierdoły\\gakko.jpg";
        String file2 = "C:\\Users\\kacpe\\Downloads\\pierwszy-obraz-moja-dusza-450x450.jpg";
        String file3 = "C:\\Users\\kacpe\\Downloads\\nyan-cat-gif-25-happy-bi-4023761,730,0,0,0.gif";

        List<String> list = new ArrayList<>();
        list.add(file1);
        list.add(file2);
        list.add(file3);

        for (int i=0; i< list.size(); i++){
            DataOperations dataOperations = new DataOperations(FileToHex.convertFileToHex(Paths.get(list.get(i))));
            System.out.println(dataOperations.checkTheType().toString());
        }




    }
}
