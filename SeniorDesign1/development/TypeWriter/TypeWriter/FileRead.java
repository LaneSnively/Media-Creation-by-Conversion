package TypeWriter;
import java.nio.file.*;
public class FileRead{
    private String text;
    public FileRead(String fileName) throws Exception{
        text = readFileAsString(fileName);
    }
    public String readFileAsString(String fileName) throws Exception{
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    public String getText(){
        return text;
    }
    public void main(String[] args) throws Exception{
        FileRead f = new FileRead("C:\\Users\\snive\\OneDrive\\Documents\\GitHub\\School\\COSC4950 - Senior Design 1\\development\\TypeWriter\\colorTypeWriter.txt");
        System.out.println(f.getText());
    }
}
