package algs;

import java.util.Arrays;
/**
 * Algorithm for merge sort.
 * 1. Divide array into halves recursively
 * 2. Stop when subarray has length of 2 or less
 * 3. Create aux array and copy elements of 2 subarray in order
 * 4. Run insertion sort on entire array
 */
class MergeSort {
  private double[] numbers;

  public MergeSort(double[] numbers) {
    this.numbers = numbers;
  }

  public void sort() {
    internalSort(0, numbers.length - 1);
  }

  private void internalSort(int low, int high) {
    if (low >= high || low < 0 || high > numbers.length - 1) return;
    if (low + 1 == high) {
      merge(low, low, high);
      return;
    }
    int mid = ((high - low) / 2) + low;
    internalSort(low, mid);
    internalSort(mid + 1, high);
    merge(low, mid, high);
  }

  // merge uses an additoinal N amount of space
  private void merge(int low, int mid, int high){
    double[] aux = new double[(high - low + 1)];
    int i = low;
    int j = mid + 1;

    // Order in aux array
    for (int k = 0; k < high - low + 1; k++) {
      if (j > high) aux[k] = numbers[i++];
      else if (i > mid) aux[k] = numbers[j++];
      else if (numbers[i] <= numbers[j]) aux[k] = numbers[i++];
      else if (numbers[j] < numbers[i]) aux[k] = numbers[j++];
      else throw new RuntimeException("Undefined merge condition");
    }

    // Copy aux array back into numbers
    for (int k = 0; k < aux.length; k++) {
      numbers[low + k] = aux[k];
    }
  }

  private void swap(int index1, int index2) {
    double cache = numbers[index1];
    numbers[index1] = numbers[index2];
    numbers[index2] = cache;
  }

  public double[] result() {
    return numbers;
  }

  /**
   * Test client
   */
  public static void main(String[] args) {
    double[] numbers = { 6.0, 3.0, 1.0, 4.0, 5.0, 2.0 };
    double[] expectedResult = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    System.out.println(Arrays.toString(numbers));

    // Test sort
    MergeSort sorter = new MergeSort(numbers);
    sorter.sort();
    assert expectedResult.equals(sorter.result()) : "Mismatched arrays";
      System.out.println(Arrays.toString(sorter.result()));
  }
}
