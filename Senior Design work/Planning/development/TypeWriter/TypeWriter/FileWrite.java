package TypeWriter;
import java.nio.file.*;
import java.io.*;
public class FileWrite{
    public static String text;
    public static FileWriter fw;
    public FileWrite(String fileName) throws Exception{
        fw = new FileWriter(new File(fileName));
        FileRead f = new FileRead("C:\\Users\\snive\\OneDrive\\Documents\\GitHub\\School\\COSC4950 - Senior Design 1\\development\\TypeWriter\\colorTypeWriter.txt");
        text = f.getText();
    }
    public static void main(String[] args) throws Exception{
        FileWrite f = new FileWrite("output.txt");
        fw.write(text);
        fw.close();
    }
}
