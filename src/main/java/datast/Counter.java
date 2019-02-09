package datast;

/**
 * Basic counter class.
 */
public class Counter {
  private int tally = 0;
  private String name;

  public Counter(String name) {
    this.name = name;
  }

  public void increment() {
    tally++;
  }

  public int tally() {
    return tally;
  }

  public String toString() {
    return Integer.toString(tally) + " " + name;
  }
}
