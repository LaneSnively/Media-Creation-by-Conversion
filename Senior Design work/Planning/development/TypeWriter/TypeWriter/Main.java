package TypeWriter;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        FileWrite f = new FileWrite("output.txt");
        f.fw.write(convert(f.text));
        f.fw.close();
    }
    public static String convert(String a){
        MapCharacter m = new MapCharacter();
        List<String> eA = new ArrayList<String>(
            Arrays.asList(a.split(""))
        );
        List<String> eG = new ArrayList<>();
        for(String e : eA){
            Boolean added = false;
            for(Pair<String, String> ee : m.getMapping()){
                if(ee.first.equals(e)){
                    eG.add(ee.second);
                    added = true;
                    break;
                }
            }
            if(!added){
                eG.add(e);
            }
        }
        return String.join("", eG);
    }
}
