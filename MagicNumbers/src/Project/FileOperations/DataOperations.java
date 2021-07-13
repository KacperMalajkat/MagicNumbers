package Project.FileOperations;

public class DataOperations {
    private String data;
    private String[] dane;
    private String txt;
    private FileTypes fileTypes;

    public DataOperations(String data, String txt) {
        this.data = data;
        this.dane = data.split("\n");
        this.txt = txt;
    }

    public DataOperations(){}

    public void setData(String s){
        data = s;
        this.dane = data.split("\n");
    }

    public void setTxt(String s){
        txt = s;
    }

    public FileTypes checkTheType() {
        if (isJPG_JFIF()) return FileTypes.JPG_JFIF;
        if (isJPG_EXIF()) return FileTypes.JPG_EXIF;
        if (isJPG()) return FileTypes.JPG;
        else if (isGIF()) return FileTypes.GIF;
        else if (isPNG()) return FileTypes.PNG;
        else if (isPDF()) return FileTypes.PDF;
        else if (isTXT()) return FileTypes.TXT;
        else return FileTypes.NOT_FOUND;
    }

    /*
    JPEG image files begin with FF D8 and end with FF D9.
    JPEG/JFIF files contain the ASCII code for "JFIF" (4A 46 49 46) as a null terminated string.
    JPEG/Exif files contain the ASCII code for "Exif" (45 78 69 66) also as a null terminated string, followed by more metadata about the file.
     */
    private boolean isJPG(){
        if (dane[0].startsWith("FF D8") && dane[dane.length-1].contains("FF D9")){
            return true;
        }
        else return false;
    }

    private boolean isJPG_JFIF(){
        if (dane[0].startsWith("FF D8") && dane[dane.length-1].contains("FF D9") && data.contains("4A 46 49 46")){
            return true;
        }
        else return false;
    }

    private boolean isJPG_EXIF(){
        if (dane[0].startsWith("FF D8") && dane[dane.length-1].contains("FF D9") && data.contains("45 78 69 66")){
            return true;
        }
        else return false;
    }


    /*
    GIF image files have the ASCII code for "GIF89a" (47 49 46 38 39 61) or "GIF87a" (47 49 46 38 37 61)
     */
    private boolean isGIF(){
        if (dane[0].startsWith("47 49 46 38 39 61") || dane[0].startsWith("47 49 46 38 37 61")) return true;
        else return false;
    }

    /*
        ???
     */
    private boolean isTXT(){
        // TODO:
        return false;
    }

    /*
    PNG image files begin with an 8-byte signature which identifies the file as a PNG
    file and allows detection of common file transfer problems: \211 P N G \r \n \032 \n (89 50 4E 47 0D 0A 1A 0A).
    That signature contains various newline characters to permit detecting unwarranted automated newline conversions,
    such as transferring the file using FTP with the ASCII transfer mode instead of the binary mode.
     */
    private boolean isPNG(){
        if (dane[0].startsWith("89 50 4E 47 0D 0A 1A 0A")) return true;
        else return false;
    }

    /*
    PDF files start with "%PDF" (hex 25 50 44 46).
     */

    private boolean isPDF(){
        if (dane[0].startsWith("25 50 44 46")) return true;
        else return false;
    }


}
