package datast;

/**
 * Implements a linked list ADT.
 * - recursive chain of nodes
 * - load factor of 75%
 * - inner Node class
 */
public class LinkedList {
  private static Node root;
  private static int count = 0;

  public LinkedList(int value) {
    root = new Node(value);
  }

  /**
   * Adds value to linked list.
   */
  public void add(int value) {
    Node node = root;
    while (node.hasNext()) {
      node = node.next();
    }
    node.link(new Node(value));
  }

  private class Node {
    private int value;
    private Node next;

    public Node(int value) {
      this.value = value;
    }

    public boolean hasNext() {
      return next != null;
    }

    public Node next() {
      return next;
    }

    public void link(Node node) {
      next = node;
    }
  }
}
