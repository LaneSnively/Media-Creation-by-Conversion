package TypeWriter;
public class Pair<A, B> {
  A first;
  B second;
  public Pair(A a, B b) {
    this.first = a;
    this.second = b;
  }
  public A getFirst() {
    return this.first;
  }
  public B getSecond() {
    return this.second;
  }
  @Override
  public String toString() {
    return "(" + this.first + "," + this.second + ")";
  }
  @Override
  public boolean equals(Object other) {
    try {
      Pair<A, B> pair = (Pair<A, B>) other;
      return this.first.equals(pair.first) && this.second.equals(pair.second);
    } catch (ClassCastException e) {
      return false;
    }
  }
}
