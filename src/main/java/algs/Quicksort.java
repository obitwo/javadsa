package algs;

import java.util.Arrays;

/**
 *
 * Algorithm for Quicksort.
 * 1. parition array by pivot index (last element)
 * 2. for each pivot, if low higher swap with curr index, increment index
 * 3. after N swap pivot with current index
 * 4. return pivot and recursively partition values to the left and right of pivot
 */
class Quicksort {
  public static void sort(double[] numbers, int low, int high) {
    if (low >= high || low < 0 || high > numbers.length - 1) return;
    int pivotIndex = partition(numbers, low, high);
    sort(numbers, low, pivotIndex - 1);
    sort(numbers, pivotIndex + 1, high);
  }

  public static int partition(double[] numbers, int low, int high) {
    double pivot = numbers[high];
    int i = low;

    for (int j = low; j < high; j++) {
      if (pivot > numbers[j]) {
        swap(numbers, i, j);
        i++;
      }
    }
    swap(numbers, i, high);
    return i;
  }

  public static void swap(double[] numbers, int num1, int num2) {
    double cache = numbers[num1];
    numbers[num1] = numbers[num2];
    numbers[num2] = cache;
  }

  public static void main(String[] args) {
    double[] numbers = { 1.0, 3.1, 5.0, 4.0, 3.1 };
    System.out.println(Arrays.toString(numbers));
    sort(numbers, 0, numbers.length - 1);

    // Test client
    double[] expectedValue = { 1.0, 3.1, 3.1, 4.0, 5.0 };
    assert numbers.equals(expectedValue) : true;
    System.out.println(Arrays.toString(numbers));
  }
}
