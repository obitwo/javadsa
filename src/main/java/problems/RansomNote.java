package problems;

import algs.BinarySearch;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Can a ransom note (String note) be formed from a magazine page (String page).
 * Approach:
 * - class RansomNote that takes the note and magazine page as string args to initialize
 * - public boolean canBlackmail() tells us if the ransom note can be created
 *
 */
public class RansomNote {
  private boolean canBlackmail;

  /**
   * RansomNote constructor.
   */
  public RansomNote(String note, String page) {
    note = stripWS(note);
    page = stripWS(page);

    // note string cant be long than page string
    if (note.length() > page.length()) {
      return;
    }

    //int remaining = findQuadratic(note, page);
    int remaining = findLinearithmic(note, page);

    if (remaining == 0) {
      canBlackmail = true;
    }
  }

  // Linearithmic (MlogM + NLogM)
  private int findLinearithmic(String note, String page) {
    int count = note.length();
    if (count == 0) return count;
    BinarySearch bs = new BinarySearch(page); // PlogP

    // for each N do a binary search; return false if
    for (int i = 0; i < note.length(); i++) {
      if (bs.find(note.charAt(i)) == -1) break;
      else count--;
    }
    return count;
  }

  // Quadratic (NM) (O^2)
  private int findQuadratic(String note, String page) {
    int n = note.length();
    int m = page.length();

    for (int i = 0; (i < page.length()) && (n > 0) && (n <= m); i++) {
      for (int j = 0; j < note.length(); j++) {
        if (note.charAt(j) == page.charAt(j)) {
          n--;
        }
      }
      m--;
    }

    return n;
  }

  // not enough letters
  public boolean canBlackmail() {
    return this.canBlackmail;
  }

  // strip spaces and return a char array
  private static String stripWS(String input) {
    return input.replaceAll(" ", "");
  }

  /**
   * Test client.
   */
  public static void main(String[] args) {
    // test note.length > page.length
    String note = "I am your fatherr";
    String page = "I am your father";
    //testCanBlackmail(false, note, page);

    // test string with match
    note = "abcdefghijklmnopqrstuvwxyz";
    page = "the quick brown fox jumps over the lazy dog";
    //testCanBlackmail(true, note, page);

    // test same string
    note = "abcdefghijklmnopqrstuvwxyz";
    page = "abcdefghijklmnopqrstuvwxyz";
    testCanBlackmail(true, note, page);

    // test blank string
    note = "";
    page = "";
    //testCanBlackmail(true, note, page);
  }

  private static void testCanBlackmail(boolean expectedBoolean, String note, String page) {
    String failedAssertionText = "Should be " + Boolean.toString(expectedBoolean);
    assert assertCanBlackmail(note, page) == expectedBoolean : failedAssertionText;
    printNoteAssertion(expectedBoolean, note, page);
  }

  private static boolean assertCanBlackmail(String note, String page) {
    return (new RansomNote(note, page).canBlackmail());
  }

  private static void printNoteAssertion(Boolean assertion, String note, String page) {
    String logicalWord = assertion ? "CAN" : "CANNOT";
    System.out.println("The ransom note:");
    System.out.println(String.join("","'", note, "'"));
    System.out.println(String.join("", logicalWord, " be derived from the page:"));
    System.out.println(String.join("", "'", page,"'"));
  }
}
