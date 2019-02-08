package algs;

import java.util.Arrays;

/**
 * Implementation of selection sort.
 * - ~N^2/4 compares and ~N2/4 exchanges (avg)
 * - ~N^2/2 compares and ~N2/2 exchanges (worst)
 * - ~N compares and 0 exchanges (best)
 */
public class InsertionSort extends Sorter {
  /**
   * Sorting method.
   */
  public static void sort(int[] numbers) {
    for (int i = 1; i < numbers.length; i++) {
      for (int j = 0; j < i; j++) {
        if (less(numbers[j], numbers[i])) {
          swap(numbers, j, i);
        }
      }
    }
  }
}
