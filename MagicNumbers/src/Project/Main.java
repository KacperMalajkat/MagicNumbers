package Project;

import Project.MyExceptions.ZeroArgsException;

public class Main {
    public static void main(String[] args) throws ZeroArgsException {

        // the number of arguments must be greater than zero
        if (args.length < 1){
            throw new ZeroArgsException();
        }

        for (int i=0; i< args.length; i++){

        }

    }
}
