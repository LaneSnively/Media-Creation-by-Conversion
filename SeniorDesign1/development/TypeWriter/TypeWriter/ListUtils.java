package TypeWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.BinaryOperator;
public abstract class ListUtils {
  public static <A, B> List<B> map(Function<A, B> fn, List<A> list) {
    if (list == null || list.size() == 0) return new ArrayList<B>();
    else {
      ArrayList<B> l = new ArrayList<>();
      for(A e : list) l.add(fn.apply(e)); //push fn(e) to list
      return l;
    }
  }
  public static <A, B> List<B> flatmap(Function<A, List<B>> fn, List<A> list) {
    if (list == null || list.size() == 0) return new ArrayList<B>();
    else {
      ArrayList<List<B>> lol = new ArrayList<>();
      for(A e : list) lol.add(fn.apply(e)); //push fn(e) to list of lists
      ArrayList<B> flat = new ArrayList<>();
      for(List<B> l : lol) flat.addAll(l); //concatenate l to flattened list
      return flat;
    }
  }
  public static <A> List<A> filter(Function<A, Boolean> fn, List<A> list) {
    if (list == null || list.size() == 0) return new ArrayList<A>();
    else {
      ArrayList<A> l = new ArrayList<>();
      for(A e : list) if(fn.apply(e)) l.add(e);
      return l;
    }
  }
  public static <A, B> List<Pair<A, B>> zip(List<A> list1, List<B> list2) {
    if (list1 == null || list2 == null || list1.size() == 0 
        || list2.size() ==0 || list1.size() != list2.size())
      return new ArrayList<Pair<A,B>>();
    else {
      ArrayList<Pair<A,B>> l = new ArrayList<>();
      for(int i = 0; i < list1.size(); i++) 
        l.add(new Pair(list1.get(i), list2.get(i))); 
      return l; //[(A,B), (A,B), ...]
    }
  }
  public static <A> A fold(BinaryOperator<A> fn, List<A> list, A identity) {
    if (list == null || list.size() == 0) return fn.apply(identity, identity);
    else {
      A result = identity; //lowest possible element that makes sense
      for(A e : list) result = fn.apply(result, e); //(((+)+)+)=
      return result;
    }
  }
}
