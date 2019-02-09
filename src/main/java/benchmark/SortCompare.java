package benchmark;

import algs.InsertionSort;
import algs.SelectionSort;
import algs.ShellSort;
import algs.Sorter;
import java.util.Arrays;
import java.util.Random;

public class SortCompare {
  private static final String OUTPUT_FORMAT = "%s is %f times faster than %s";

  private static double time(String alg, double[] numbers) {
    long startTime = System.nanoTime();

    if (alg.equals("InsertionSort")) {
      InsertionSort.sort(numbers);
    } else if (alg.equals("SelectionSort")) {
      SelectionSort.sort(numbers);
    } else if (alg.equals("ShellSort")) {
      ShellSort.sort(numbers);
    } else if (alg.equals("Sorter")) {
      Sorter.sort(numbers);
    } else {
      throw new RuntimeException("Undefined algorithm");
    }
    return (double) (System.nanoTime() - startTime);
  }

  private static double timeSortRandom(String alg, int numItems, int numExps) {
    double[] nums = new double[numItems];
    double totalTime = 0.0;
    Random rand = new Random();

    for (int i = 0; i < numExps; i++) {
      for (int j = 0; j < numItems; j++) {
        nums[j] = rand.nextDouble();
      }
      totalTime += time(alg, nums);
    }
    return totalTime / (double) numExps;
  }

  private static void printComparisons(String[] names, double[] elapsedTimes) {
    int bestIndex = elapsedTimes[0] >= elapsedTimes[1] ? 1 : 0;
    int worstIndex = bestIndex == 1 ? 0 : 1;
    double algFactor = elapsedTimes[worstIndex] / elapsedTimes[bestIndex];
    String output = String.format(OUTPUT_FORMAT, names[bestIndex], algFactor, names[worstIndex]);

    System.out.println(output);
  }

  /**
   * Displays sorting algorithm benchmarks.
   */
  public static void main(String[] args) {
    String alg1 = args[0];
    String alg2 = args[1];
    int numItems = Integer.parseInt(args[2]);
    int numExps = Integer.parseInt(args[3]);

    double alg1Time = timeSortRandom(alg1, numItems, numExps);
    double alg2Time = timeSortRandom(alg2, numItems, numExps);

    String[] algNames = new String[]{ alg1, alg2 };
    double[] algElapsedTimes = new double[]{ alg1Time, alg2Time };
    printComparisons(algNames, algElapsedTimes);
  }
}
