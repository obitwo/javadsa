import java.util.ArrayList;

/**
 * Symbol Table binary search implementation.
 * - uses ArrayList ADT because frankly I'm not about to resize a damn array
 * - no more than lgN compares for search (rank())
 * - N additional spaces for parallel keys and values (inefficient for large N)
 * - put is 4N or 2N^2 (for empty trees)
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
  private ArrayList<Key> keys;
  private ArrayList<Value> values;
  private int length = 0;

  public BinarySearchST() {
    keys = new ArrayList<>();
    values = new ArrayList<>();
    length = 0;
  }

  public void put(Key key, Value value) {
    int pos = rank(key, 0, length - 1);
    if (pos < length && key.compareTo(keys.get(pos)) == 0) {
      values.set(pos, value);
      return;
    }
    keys.add(key);
    values.add(value);
    length++;
    for (int i = length - 1; i > pos; i--) {
      Key tempKey = keys.get(i);
      keys.set(i, keys.get(i - 1));
      keys.set(i - 1, tempKey);

      Value tempValue = values.get(i);
      values.set(i, values.get(i - 1));
      values.set(i - 1, tempValue);
    }
  }

  public Value get(Key key) {
    int pos = rank(key, 0, length - 1);
    if (keys.get(pos).compareTo(key) == 0) return values.get(pos);
    return null; // or throw an exception
  }

  public void delete(Key key) {
    int pos = rank(key, 0, length - 1);
    if (!(pos < length && key.compareTo(keys.get(pos)) == 0)) return;
    for (int i = pos; i < length - 1; i++) {
      keys.set(i,  keys.get(i+1));
      values.set(i, values.get(i+1));
    }
    length--;
  }

  public boolean contains(Key key) {
    int pos = rank(key, 0, length - 1);
    return keys.get(pos) == key;
  }

  public boolean isEmpty() {
    return !(length > 0);
  }

  public int size() {
    return length;
  }

  // return key of min; use get to return value
  public Key min() {
    return keys.get(0);
  }

  // return key of max; use get to return value
  public Key max() {
    return keys.get(length - 1);
  }

  public Key floor(Key key) {
    return keys.get(floorIndex(key));
  }

  private int floorIndex(Key key) {
    int pos = rank(key, 0, length - 1);
    if (key.compareTo(keys.get(pos)) == 0) return pos;
    return pos - 1;
  }

  public Key ceiling(Key key) {
    return keys.get(ceilingIndex(key));
  }

  private int ceilingIndex(Key key) {
    return rank(key, 0, length - 1);
  }

  public Key select(int k) {
    return keys.get(k);
  }

  public void deleteMin() {
    delete(keys.get(0));
  }

  public void deleteMax() {
    delete(keys.get(length - 1));
  }

  // Size of range from lo to hi
  public int size(Key lo, Key hi) {
    return floorIndex(hi) - ceilingIndex(lo);
  }

  public Iterable<Key> keys(Key lo, Key hi) {
    int loIndex = ceilingIndex(lo);
    int hiIndex = floorIndex(hi);
    ArrayList<Key> range = new ArrayList<>(hiIndex - loIndex + 1);
    for (int i = loIndex; i <= hiIndex; i++) {
      range.add(keys.get(i));
    }
    return range;
  }

  public Iterable<Key> keys() {
    return keys(keys.get(0), keys.get(length - 1));
  }

  private int rank(Key key, int lo, int hi) {
    if (hi < lo) return lo;
    int mid = lo + (hi - lo) / 2;
    int cmp = key.compareTo(keys.get(mid));
    if (cmp < 0) {
      return rank(key, lo, mid - 1);
    } else if (cmp > 0) {
      return rank(key, mid + 1, hi);
    } else {
      return mid;
    }
  }

  private void keyValueLogger(String message) {
    System.out.println("-- " + message + " --");
    System.out.print("The keys: ");
    System.out.println(keys.toString());
    System.out.print("The values: ");
    System.out.println(values.toString());
    System.out.println();
  }

  // Test client
  public static void main(String[] args) {
    String[] strings = { "this", "is", "sparta", "man" };
    BinarySearchST<Character, String> bsst = new BinarySearchST<>();
    assert bsst.isEmpty() == true : "Should be empty";

    for (String s : strings) {
      // use first char value as key
      bsst.put(Character.valueOf(s.charAt(0)), s);
    }

    assert bsst.size() == strings.length : "Incorrect size";
    assert bsst.get(bsst.min()) == "is" : "Incorrect min value";
    assert bsst.get(bsst.max()) == "this" : "Incorrect max value";

    bsst.delete('i');
    assert bsst.size() == strings.length - 1 : "Incorrect size after delete";
    assert bsst.contains('i') == false : "Should not contain  'i'";

    bsst.deleteMax();
    assert bsst.size() == strings.length - 2 : "Incorrect size after deleteMax";
    assert bsst.contains('t') == false : "Should not contain  'i'";
  }
}
