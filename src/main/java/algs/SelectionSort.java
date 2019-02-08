package algs;

import java.util.Arrays;

/**
 * Implementation of selection sort.
 * ~N^2/2 compares and N exchanges
 */
public class SelectionSort extends Sorter {
  /**
   * main method.
   */
  public static void sort(int[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i + 1; j < numbers.length; j++) {
        if (less(numbers[j], numbers[i])) {
          swap(numbers, i, j);
        }
      }
    }
    System.out.println(Arrays.toString(numbers));
  }
}
