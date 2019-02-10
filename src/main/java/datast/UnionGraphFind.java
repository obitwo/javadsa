package datast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UnionGraphFind {
  private ArrayList<ArrayList<Connection>> sites;
  private boolean[] marked;
  private int[] components;
  private int componentCount;

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
    if (site1 != site2) { // dont add self loops twice
      sites.get(site2).add(connection);
    }
  }

  /**
   * Finds component id from site.
   */
  public int find(int site) {
    countComponents();
    return components[site];
  }

  /**
   * Returns true if sites are connected.
   */
  public boolean connected(int siteStart, int siteEnd) {
    marked = new boolean[sites.size()];
    countComponents();
    return components[siteStart] == components[siteEnd];
  }

  /**
   * Number of components.
   */
  public int count() {
    countComponents();
    return componentCount; // start at root
  }

  private int countComponents() {
    components = new int[sites.size()];
    componentCount = 0;

    for (int i = 0; i < components.length; i++) {
      if (components[i] == 0) {
        componentCount++;
        countSiteComponents(i, componentCount);
      }
    }

    return componentCount;
  }

  private void countSiteComponents(int site, int count) {
    components[site] = count;

    for (int i = 0; i < sites.get(site).size(); i++) {
      Connection connection = sites.get(site).get(i);
      int otherSite = connection.other(site);
      if (components[otherSite] == 0) {
        countSiteComponents(otherSite, count);
      }
    }
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
    System.out.print(start + " is component " + ugf.find(start));
    System.out.print(" and " + end + " in " + ugf.find(end) + "\n");
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
