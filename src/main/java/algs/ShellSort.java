package algs;

public class ShellSort extends Sorter {
  /**
   * Shellsort implementation.
   */
  public static void sort(double[] nums) {
    int increment = 1;
    int count = nums.length;

    while (increment < count) {
      increment = 3 * increment + 1;
    }

    while (increment >= 1) {
      for (int i = increment; i < count; i++) {
        for (int j = i; j >= increment && less(nums[j], nums[j - increment]); j -= increment) {
          swap(nums, j, j - increment);
        }
      }
      increment /= 3;
    }
  }
}
