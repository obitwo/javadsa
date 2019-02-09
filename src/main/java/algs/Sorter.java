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
    double[] numbers = Arrays.stream(args).mapToDouble(Double::parseDouble).toArray();
    sort(numbers);
    if (isSorted(numbers)) {
      System.out.println(Arrays.toString(numbers));
    } else {
      System.out.println("Array not sorted");
    }
  }

  public static void sort(double[] numbers) {
    Arrays.sort(numbers); // uses quicksort
  }

  /**
   * Returns true if array sorted.
   */
  static boolean isSorted(double[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      if (!less(numbers[i], numbers[i + 1])) {
        return false;
      }
    }
    return true;
  }

  static Boolean less(double val1, double val2) {
    return val1 - val2 <= 0;
  }

  static void swap(double[] numbers, int key1, int key2) {
    double cache;
    cache = numbers[key1];
    numbers[key1] = numbers[key2];
    numbers[key2] = cache;
  }
}
