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
    // note string cant be long than page string
    if (note.length() > page.length()) {
      return;
    }

    char[] noteChars = stripToCharArray(note);
    char[] pageChars = stripToCharArray(page);

    // int remainingChars = findCharsQuadratic(noteChars, pageChars);
    int remainingChars = findCharsLinearithmic(noteChars, pageChars);

    if (remainingChars == 0) {
      canBlackmail = true;
    }
  }

  // Linearithmic (MlogM + NLogM)
  private int findCharsLinearithmic(char[] noteChars, char[] pageChars) {
    int count = noteChars.length;
    BinarySearch bs = new BinarySearch(pageChars);

    // for each N do a binary search; return false if
    for (int i = 0; i < noteChars.length; i++) {
      if (bs.find(noteChars[i]) == 1) {
        break;
      } else {
        count--;
      }
    }
    return count;
  }

  // Quadratic (NM) (O^2)
  private int findCharsQuadratic(char[] noteChars, char[] pageChars) {
    int n = noteChars.length;
    int m = pageChars.length;

    for (int i = 0; (i < pageChars.length) && (n > 0) && (n <= m); i++) {
      for (int j = 0; j < noteChars.length; j++) {
        if (noteChars[j] == pageChars[i]) {
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
  private static char[] stripToCharArray(String input) {
    return input.replaceAll(" ", "").toCharArray();
  }

  /**
   * Test client.
   */
  public static void main(String[] args) {
    // test note.length > page.length
    String note = "I am your fatherr";
    String page = "I am your father";
    testCanBlackmail(false, note, page);

    // test string with match
    note = "abcdefghijklmnopqrstuvwxyz";
    page = "the quick brown fox jumps over the lazy dog";
    testCanBlackmail(true, note, page);

    // test same string
    note = "abcdefghijklmnopqrstuvwxyz";
    page = "abcdefghijklmnopqrstuvwxyz";
    testCanBlackmail(true, note, page);

    // test blank string
    note = "";
    page = "";
    testCanBlackmail(true, note, page);
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
