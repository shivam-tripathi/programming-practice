package com.dsAlgo.graphs.introduction;
/**
 * Count the number of nodes at given level in a tree using BFS.
 * Given a tree represented as undirected graph. Count the number of nodes at given level l.
 * It may be assumed that vertex 0 is root of the tree.
 */

import java.util.*;

public class NodesLevel {
  static class Graph {
    int V;
    List<List<Integer>> adj;
    int levelCount[];

    Graph(int v) {
      this.V = v;
      this.adj = new ArrayList<>();
      for (int i = 0; i < this.V; i++) {
        List<Integer> list = new LinkedList<>();
        this.adj.add(list);
      }
      this.levelCount = new int[this.V];
    }

    void addEdge(int v, int w) {
      this.adj.get(v).add(w);
      this.adj.get(w).add(v);
    }

    class Node {
      int value;
      int level;

      Node(int value, int level) {
        this.value = value;
        this.level = level;
      }
    }

    // Alternatively, store level values in a separate array
    void countLevel() {
      Queue<Node> queue = new LinkedList<>();
      boolean visited[] = new boolean[this.V];

      queue.add(new Node(0, 0));
      visited[0] = true;
      this.levelCount[0]++;

      while (queue.size() != 0) {
        Node front = queue.remove();
        int node = front.value;
        int level = front.level;

        ListIterator<Integer> iter = this.adj.get(node).listIterator();
        while (iter.hasNext()) {
          int nextNode = iter.next();
          if (!visited[nextNode]) {
            visited[nextNode] = true;
            this.levelCount[level + 1]++;
            queue.add(new Node(nextNode, level + 1));
          }
        }
      }
    }

    void printCount() {
      for (int i = 0; i < this.V; i++) {
        if (this.levelCount[i] == 0) {
          break;
        }
        System.out.println(i + ": " + this.levelCount[i]);
      }
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(6);

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);

    graph.countLevel();
    graph.printCount();
  }
}