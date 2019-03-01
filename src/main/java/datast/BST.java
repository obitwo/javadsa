/**
 * Implementation of a Binary Search Tree.
 * - logarithmic for get()
 * - logarithmic for put()
 * - worst case is linear (when nodes are in inputed in ascending or descending ordere)
 */
public class BST<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    public Key key;
    public Value value;
    public Node leftChild, rightChild;
    public int subtreeLength; // number of nodes child subtrees

    Node (Key key, Value value, int subtreeLength) {
      this.key = key;
      this.value = value;
      this.subtreeLength = subtreeLength;
    }
  }

  public int size() {
    return size(root);
  }

  public int size(Node node) {
    if (node == null) {
      return 0;
    } else {
      return node.subtreeLength;
    }
  }

  public Value get(Key key) {
    return get(root, key);
  }

  public Value get(Node node, Key key){
    if (node == null) return null;
    int compare = key.compareTo(node.key);
    if (compare < 0) {
      return get(node.leftChild, key);
    } else if (compare > 0) {
      return get(node.rightChild, key);
    }
    return node.value;
  }

  public void put(Key key, Value value) {
    root = put(root, key, value);
  }

  public Node put(Node node, Key key, Value value) {
    if (node == null) {
      node = new Node(key, value, 1);
      return node;
    }
    int compare = key.compareTo(node.key);
    if (compare < 0) {
      node.leftChild = put(node.leftChild, key, value);
    } else if (compare > 0) {
      node.rightChild = put(node.rightChild, key, value);
    } else {
      node.value = value;
    }
    node.subtreeLength = size(node.leftChild) + size(node.rightChild) + 1;
    return node;
  }

  public Value min() {
    return min(root);
  }

  public Value min(Node node) {
    if (node.leftChild == null) {
      return node.value;
    }
    return min(node.leftChild);
  }

  public Value max() {
    return max(root);
  }

  public Value max(Node node) {
    if (node.rightChild == null) {
      return node.value;
    }
    return max(node.rightChild);
  }

  public Key floor(Key key) {
    Node node = floor(root, key);
    if (node == null) {
      return null;
    }
    return node.key;
  }

  public Node floor(Node node, Key key) {
    if (node == null) {
      return null;
    }
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      return node;
    }
    if (cmp < 0) {
      return floor(node.leftChild, key);
    }
    Node rightFloor = floor(node.rightChild, key);
    if (rightFloor != null) {
      return rightFloor;
    }
    return node;
  }

  public Key ceil(Key key) {
    Node node = ceil(root, key);
    if (node == null) {
      return null;
    }
    return node.key;
  }

  public Node ceil(Node node, Key key) {
    if (node == null) {
      return null;
    }
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      return node;
    }
    if (cmp > 0) {
      return ceil(node.rightChild, key);
    }
    Node leftCeil = ceil(node.leftChild, key);
    if (leftCeil != null) {
      return leftCeil;
    }
    return node;
  }

  // find subtree counts and compare them to pos
  public Node select(Node node, int pos) {
    return null;
  }

  public Node delete(Key key) {
    // implement later
    return null;
  }

  // implement range queries here

  public Key rank(int pos) {
    // implement later
    return null;
  }

  public void print() {
    print(root);
  }

  public void print(Node node) {
    if (node == null) {
      return;
    }
    print(node.leftChild);
    System.out.println(node.key);
    print(node.rightChild);
  }

  public static void main(String[] args) {
    BST<Character, String> bst = new BST<>();
    Character[] chars = { 's', 'd', 'c', 't', 'f' };
    String[] words = { "snake", "dear", "cat", "turtle", "frog" };
    for (int i = 0; i < chars.length; i++) {
      bst.put(chars[i], words[i]);
    }

    assert bst.size() == chars.length : "incorrect BST length: " + bst.size() + " instead of " + chars.length;
    System.out.println("+ returned correct size");

    String expect = words[0];
    String actual = bst.get(chars[0]);
    assert actual.equals(expect) == true : "incorrect value retrieved: " + actual + " instead of " + expect;
    System.out.println("+ returned correct");

    assert bst.min().equals(words[2]) : "incorrect min";
      System.out.println("+ returned correct min");

      assert bst.max().equals(words[3]) : "incorrect max";
        System.out.println("+ returned correct max");

        assert bst.floor('e').equals(chars[1]) : "incorrect floor";
          System.out.println("+ returned correct floor");

          assert bst.ceil('e').equals(chars[4]) : "incorrect ceiling";
            System.out.println("+ returned correct ceiling");

            bst.print();
  }
}
