package Project.MyExceptions;

public class TypeNotFoundException extends Exception{
    public TypeNotFoundException() {
        System.out.println("File type not found! ");
    }
}
