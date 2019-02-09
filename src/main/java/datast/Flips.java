package datast;

import java.io.IOException;
import java.util.Random;

/**
 * Coin flipper.
 */
public class Flips {
  /**
   * Makes N amount of coin flips; records results.
   */
  public static void main(String[] args) {
    int attempts = Integer.parseInt(args[0]);
    Counter heads = new Counter("heads");
    Counter tails = new Counter("tails");

    runAttempts(attempts, heads, tails);
    printResults(heads, tails);
  }

  private static void runAttempts(int attempts, Counter heads, Counter tails) {
    for (int i = 0; i < attempts; i++) {
      Random rand = new Random();
      if (rand.nextBoolean()) {
        heads.increment();
      } else {
        tails.increment();
      }
    }
  }

  private static void printResults(Counter heads, Counter tails) {
    int delta = Math.abs(heads.tally() - tails.tally());

    System.out.println(heads.toString());
    System.out.println(tails.toString());
    System.out.println(Integer.toString(delta));
  }
}
