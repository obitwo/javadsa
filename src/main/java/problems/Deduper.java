package problems;

import java.util.HashSet;

/**
 * Print an unsorted array of integers; removing duplicates (only using arrays)
 * strategy: create index array that tracks state of the count
 * init: int[] dataIndex[N] = null; int D = 0 (the count of single occurrences)
 * loop -> if (dataIndex[N] == null) dataIndex[N] =
 */

public class Deduper {
  private int[] collection;

  public Deduper(int[] collection) {
    this.collection = collection;
  }

  public String dedup() {
    HashSet<Integer> cache = new HashSet<>();
    StringBuffer output = new StringBuffer("");

    for (int i = 0; i < collection.length; i++) {
      if (!(cache.contains(collection[i]))) {
        output.append(collection[i] + " ");
        cache.add(collection[i]);
      }
    }
    return output.toString();
  }

  /**
   * Test client.
   */
  public static void main(String[] args) {
    int[] collection = { 1, 10, 5, 1, 6, 7, 5, 1 };
    String expectedString = "1 10 5 6 7 ";
    String dedupedString = new Deduper(collection).dedup();
    assert dedupedString.equals(expectedString) : "should equal expected string";
      System.out.println(dedupedString);
  }
}
