package TypeWriter;
import java.util.*;
public class MapCharacter{
    private List<String> eng = new ArrayList<>();
    private List<String> sym = new ArrayList<>();
    private List<Pair<String, String>> mapping;
    public MapCharacter(){
        String e = "aAbBcCdDwEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
        String s = "αΑβΒψΨδΔεΕφΦγΓηΗιΙξΞκΚλΛμΜνΝοΟπΠ;;ρΡσΣτΤθΘωΩςςχΧυΥζΖ";
        List<String> eA = new ArrayList<String>(
            Arrays.asList(e.split(""))
        );
        List<String> sA = new ArrayList<String>(
            Arrays.asList(s.split(""))
        );
        mapping = ListUtils.zip(eA,sA);
    }
    public List<Pair<String, String>> getMapping(){
        return mapping;
    }
}
