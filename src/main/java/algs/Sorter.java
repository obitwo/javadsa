package algs;

import java.io.IOException;
import java.util.Arrays;

/**
 * Sort algorithim superclass.
 * Uses Arrays.sort by default
 */
public class Sorter {
  /**
   * Sorts and prints.
   */
  public static void main(String[] args) throws IOException {
    int[] numbers = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
    sort(numbers);
    if (isSorted(numbers)) {
      System.out.println(Arrays.toString(numbers));
    } else {
      System.out.println("Array not sorted");
    }
  }

  public static void sort(int[] numbers) {
    Arrays.sort(numbers); // uses quicksort
  }

  /**
   * Returns true if array sorted.
   */
  public static boolean isSorted(int[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      if (!less(numbers[i], numbers[i + 1])) {
        return false;
      }
    }
    return true;
  }

  static Boolean less(int val1, int val2) {
    return val1 - val2 <= 0;
  }

  static void swap(int[] numbers, int key1, int key2) {
    int cache;
    cache = numbers[key1];
    numbers[key1] = numbers[key2];
    numbers[key2] = cache;
  }
}
