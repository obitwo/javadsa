package algs;

import java.util.Arrays;
import java.util.Collections;

/**
 * Implementation of binary search.
 */
public class BinarySearch {
  private String sortedList;

  /**
   * BinarySearch constructor.
   */
  public BinarySearch(String input) {
    sortedList = sortList(input);
  }

  private static String sortList(String input) {
    char[] tempArray = input.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }

  public int find(char element) {
    return searchPartition(element, 0, sortedList.length() - 1);
  }

  private int searchPartition(char element, int start, int end) {
    // is element >, <, or == to list[mid]
    // base case: list.[mid] == element -> return mid
    // base case: list.[last_mid] != element -> return -1
    //
    int mid = (start + end) / 2;
    char midValue = sortedList.charAt(mid);
    int comparator = element - midValue;

    if (start > end) {
      return -1;
    } else if (comparator == 0) {
      return mid;
    } else if (comparator > 0) {
      return searchPartition(element, mid + 1, end);
    } else if (comparator < 0) {
      return searchPartition(element, start, mid - 1);
    } else {
      return -1;
    }
  }

  public String sortedList() {
    return sortedList;
  }

  /**
   * Test client.
   */
  public static void main(String[] args) {
    String collection = "adklzohn";
    String expectedCollection = "adhklnoz";
    char value = 'c';
    int expected = -1;
    testSort(collection, expectedCollection);
    testFind(value, collection, expected);

    collection = "adklzcohn";
    value = 'c';
    expected = 1; // index 'c' should be at
    testFind(value, collection, expected);
  }

  private static void testSort(String collection, String expected) {
    BinarySearch binarySearch = new BinarySearch(collection);
    System.out.println("expected collection: " + expected);
    System.out.println("actual collection: " + collection);
    assert expected.equals(binarySearch.sortedList()) : "not sorted";
    System.out.println("Test for sortedList() passed");
  }

  private static void testFind(char value, String collection, int expected) {
    BinarySearch binarySearch = new BinarySearch(collection);
    int result = binarySearch.find(value);
    String errorMessage = expected == -1 ? "should not be found" : "should be found";
    assert result == expected : errorMessage;
    System.out.println("Test for find() passed");
  }
}
