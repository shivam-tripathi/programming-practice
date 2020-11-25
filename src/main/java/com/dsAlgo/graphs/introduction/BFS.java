package com.dsAlgo.graphs.introduction; /**
 * BFS
 * Complexity O(V + E)
 */

import java.util.*;

public class BFS {

  static class Graph {
    private int V;
    private List<List<Integer>> adj;

    Graph(int v) {
      this.V = v;
      this.adj = new ArrayList<>();
      for (int i = 0; i < this.V; i++) {
        LinkedList<Integer> list = new LinkedList<>();
        this.adj.add(list);
      }
    }

    void addEdge(int v, int w) {
      this.adj.get(v).add(w);
      this.adj.get(w).add(v);
    }

    void BFSUtil(int root, boolean[] visited) {
      Queue<Integer> queue = new LinkedList<>();
      visited[root] = true;
      queue.add(root);

      while (queue.size() > 0) {
        int node = queue.poll();
        System.out.println("Visited " + node);
        ListIterator<Integer> iter = this.adj.get(node).listIterator();
        while (iter.hasNext()) {
          int next = iter.next();
          if (!visited[next]) {
            visited[next] = true;
            queue.add(next);
          }
        }
      }
    }

    void BFS() {
      boolean visited[] = new boolean[this.V];
      for (int i = this.V - 1; i >= 0; i--) {
        if (!visited[i]) {
          BFSUtil(i, visited);
        }
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

    System.out.println("BFS:");
    graph.BFS();
  }
}