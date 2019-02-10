package datast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnionGraphFind {
  private ArrayList<ArrayList<Connection>> sites;
  private boolean[] marked;

  /**
   * UnionGraphFind constructor.
   */
  public UnionGraphFind(int count) {
    sites = new ArrayList<ArrayList<Connection>>();
    for (int i = 0; i < count; i++) {
      sites.add(new ArrayList<Connection>());
    }
  }

  public int size() {
    return sites.size();
  }

  /**
   * Links two sites.
   */
  public void union(int site1, int site2) {
    Connection connection = new Connection(site1, site2);
    if (sites.get(site1) == null) {
      sites.set(site1, new ArrayList<Connection>());
    }
    if (sites.get(site2) == null) {
      sites.set(site2, new ArrayList<Connection>());
    }
    sites.get(site1).add(connection);
    sites.get(site2).add(connection);
  }

  /**
   * Finds component id from site.
   */
  public int find(int site) {
    return 0;
  }

  /**
   * Returns true if sites are connected.
   */
  public boolean connected(int siteStart, int siteEnd) {
    marked = new boolean[sites.size()];
    markConnections(siteStart, siteEnd);
    return marked[siteStart] && marked[siteEnd];
  }

  private void markConnections(int site1, int site2) {
    marked[site1] = true;

    ArrayList<Connection> connections = sites.get(site1);
    for (Connection connection : connections) {
      int otherSite = connection.other(site1);
      if (otherSite == site2) {
        marked[otherSite] = true;
        return;
      }
      if (marked[otherSite]) {
        continue;
      }
      markConnections(otherSite, site2);
    }
  }

  /**
   * Number of components.
   */
  public int count() {
    marked = new boolean[sites.size()];
    int count = 1;
    return countComponents(0, count);
  }

  private int countComponents(int site, int count) {
    marked[site] = true;

    for (int i = 0; i < sites.get(site).size(); i++) {
      Connection connection = sites.get(site).get(i);
      int otherSite = connection.other(site);
      if (!marked[otherSite]) {
        return countComponents(otherSite, count);
      }
    }

    for (int i = 0; i < marked.length; i++) {
      if (!marked[i]) {
        count++;
        return countComponents(i, count);
      }
    }
    return count;
  }

  /**
   * Returns string representation of UnionGraphFind instance.
   */
  public String toString() {
    String output = "";
    for (int i = 0; i < sites.size(); i++) {
      output += i + ": ";
      for (int j = 0; j < sites.get(i).size(); j++) {
        output += sites.get(i).get(j).toString();
        output += " ";
      }
      output += "\n";
    }
    return output;
  }

  /**
   * UnionGraphFind test client.
   */
  public static void main(String[] args) {
    int sitesSize = Integer.parseInt(args[0]);
    int connectionsSize = Integer.parseInt(args[1]);
    int start = Integer.parseInt(args[2]);
    int end = Integer.parseInt(args[3]);

    if (start > (sitesSize - 1) || end > (sitesSize - 1)) {
      throw new RuntimeException("start and/or end site is invalid");
    }

    UnionGraphFind ugf = new UnionGraphFind(sitesSize);
    for (int i = 0; i < connectionsSize; i++) {
      buildConnections(ugf);
    }

    System.out.println(ugf.toString());

    System.out.println(ugf.count() + " components");
    String assertString = ugf.connected(start, end) ? "" : "NOT ";
    System.out.println(start + " is " + assertString + "connected to " + end);
  }

  private static void buildConnections(UnionGraphFind ugf) {
    Random rand = new Random();
    int site1 = rand.nextInt(ugf.size());
    int site2 = rand.nextInt(ugf.size());
    ugf.union(site1, site2);
  }

  private class Connection {
    int vertex1;
    int vertex2;

    public Connection(int vertex1, int vertex2) {
      this.vertex1 = vertex1;
      this.vertex2 = vertex2;
    }

    public String toString() {
      return String.format("%d-%d", vertex1, vertex2);
    }

    public int other(int vertex) {
      if (vertex == vertex1) {
        return vertex2;
      } else if (vertex == vertex2) {
        return vertex1;
      } else {
        throw new RuntimeException("Undefined vertex in connection");
      }
    }
  }
}
