import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Scans text and prints every word to a newline.
 */
public class ScanText {

  /**
   * Main method.
   */
  public static void main(String[] args) throws IOException {
    Scanner scan = null;
    try {
      FileReader file = new FileReader("test/scantext.txt");
      BufferedReader buffer = new BufferedReader(file);
      scan = new Scanner(buffer);
      while (scan.hasNext()) {
        System.out.println(scan.next());
      }
    } finally {
      if (scan != null) {
        scan.close();
      }
    }
  }
}
