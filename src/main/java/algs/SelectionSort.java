package algs;

import java.util.Arrays;

/**
 * Implementation of selection sort.
 * - ~N^2/2 compares and N exchanges
 * - running time insensitive to input
 */
public class SelectionSort extends Sorter {
  /**
   * Sorting method.
   */
  public static void sort(double[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        if (less(numbers[j], numbers[i])) {
          swap(numbers, i, j);
        }
      }
    }
  }
}
