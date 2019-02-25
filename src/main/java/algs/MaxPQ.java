import java.util.Arrays;

/**
 * Priority Queue implementation.
 * For insert:
 * 1. add to end of array
 * 2. swim
 *
 * For maximum:
 * 1. swap last with a[1]
 * 2. sink
 */
class MaxPQ {
  double[] elements;
  int N;

  public MaxPQ(double[] elements) {
    this.elements = new double[elements.length + 1];
    for (int i = 0; i < elements.length; i++) {
      insert(elements[i]);
    }
  }

  public double[] toArray() {
    return elements;
  }

  // move last item to invariant position
  private static void swim(double[] items, int key) {
    while (key > 1) {
      if (items[key / 2] < items[key]) {
        swap(items, key / 2, key);
      }
      key /= 2;
    }
  }

  private static void swap(double[] items, int key1, int key2) {
    double cache;
    cache = items[key1];
    items[key1] = items[key2];
    items[key2] = cache;
  }

  // most top of heap (first item) to invariant posistion
  private static void sink(double[] items, int count) {
    int key = 1; // start from the top
    while (key < count) {
      int j = 2 * key;
      if (j > count) break;
      if (j + 1 < count && items[j] < items[j + 1]) j++;
      if (items[key] > items[j]) break;
      swap(items, key, j);
      key = j;
    }
  }

  public void insert(double element) {
    elements[++N] = element;
    swim(elements, N);
  }

  // delete max value (top of heap)
  public double delMax() {
    double max;
    max = elements[1];
    elements[1] = elements[N]; // "remove" first element
    N--;
    sink(elements, N);
    return max;
  }

  // heapsort
  public double[] sort() {
    double[] sorted = Arrays.copyOf(elements, N + 1); // dup pq array
    double[] sanitized = new double[N];
    for (int j = N; j > 0; j--) {
      sanitized[j - 1] = sorted[1]; //optional: can return sorted[1..N+1]
      swap(sorted, 1, j); // swap top of heap for last position
      sink(sorted, j - 1); // sink everything before last position
    }
    return sanitized;
  }

  // test client
  public static void main(String[] args) {
    double[] numbers = { 2.0, 3.0, 1.0, 8.0, 6.0, 7.0 };
    MaxPQ pq = new MaxPQ(numbers);
    System.out.println(Arrays.toString(numbers));

    double max = pq.delMax();
    assert max == 8.0 : "Incorrect max value";
    System.out.println("The max value is " + max);

    max = pq.delMax();
    assert max == 7.0 : "Incorrect max value";
    System.out.println("The max value is " + max);

    pq.insert(7.0);
    pq.insert(8.0);
    System.out.print("Priority Queue: ");
    System.out.println(Arrays.toString(pq.toArray()));
    double[] sorted = pq.sort();
    double[] expectedSort = { 1.0, 2.0, 3.0, 6.0, 7.0, 8.0 };
    assert sorted.equals(expectedSort) : "Not sorted";
    System.out.print("Sorted array: ");
    System.out.println(Arrays.toString(sorted));
  }
}
