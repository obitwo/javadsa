package algs;

import java.io.IOException;
import java.util.Arrays;

/**
 * Sort algorithim superclass.
 * Uses Arrays.sort by default
 */
public class Sorter {
  public Sorter() {
    super();
  }

  /**
   * Sorts and prints.
   */
  public static void main(String[] args) throws IOException {
    int[] numbers = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
    sort(numbers);
    System.out.println(Arrays.toString(numbers));
  }

  public static void sort(int[] numbers) {
    Arrays.sort(numbers); // uses quicksort
  }

  static Boolean less(int val1, int val2) {
    return val1 - val2 < 0;
  }

  static void swap(int[] numbers, int key1, int key2) {
    int cache;
    cache = numbers[key1];
    numbers[key1] = numbers[key2];
    numbers[key2] = cache;
  }
}
