package com.dsAlgo.graphs.introduction;
/**
 * Given a directed graph, find out if a vertex v is reachable from another vertex u for all vertex pairs (u, v) in the given graph.
 * Complexity O (V(V + E)) => For sparse graph O(V^2); for dense graph O(V^3) for a fully connected graph, no of edges E = V^2
 */

import java.util.*;


public class TransitiveClosure {
  static class Graph {
    private int V;
    private List<List<Integer>> adjList;
    boolean[][] connectivityMatrix;

    Graph(int v) {
      this.V = v;
      this.adjList = new ArrayList<>();
      for (int i = 0; i < this.V; i++) {
        List<Integer> list = new LinkedList<>();
        this.adjList.add(list);
      }

      connectivityMatrix = new boolean[this.V][this.V];
    }

    void addEdge(int v, int w) {
      this.adjList.get(v).add(w);
    }

    void dfsUtilClosure(int base, int src) {
      this.connectivityMatrix[base][src] = true;
      ListIterator<Integer> iter = this.adjList.get(src).listIterator();
      while (iter.hasNext()) {
        int nextNode = iter.next();
        if (!this.connectivityMatrix[base][nextNode]) { // Avoid loops
          this.connectivityMatrix[base][nextNode] = true;
          this.dfsUtilClosure(base, nextNode);
        }
      }
    }

    void transitiveClosure() {
      // DFS for every vertex -> O(V(V + E))
      for (int i = 0; i < this.V; i++) {
        this.dfsUtilClosure(i, i);
      }
    }

    void printConnectivityMatrix() {
      System.out.print("x : ");
      for (int i = 0; i < this.V; i++) {
        System.out.print(i + " ");
      }
      System.out.println();
      System.out.print("- : ");
      for (int i = 0; i < this.V; i++) {
        System.out.print("- ");
      }
      System.out.println();

      for (int i = 0; i < this.V; i++) {
        System.out.print(i + " : ");
        for (int j = 0; j < this.V; j++) {
          System.out.print((this.connectivityMatrix[i][j] ? 1 : 0) + " ");
        }
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(4);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 3);

    graph.transitiveClosure();
    graph.printConnectivityMatrix();
  }
}
