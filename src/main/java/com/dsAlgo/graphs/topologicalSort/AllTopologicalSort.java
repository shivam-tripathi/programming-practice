package com.dsAlgo.graphs.topologicalSort;

/**
 * Print all possible topological sorts.
 *
 * Create a stack for storing one single possible iteration.
 *
 * Start with node having zero indegree. Branch off for each case where on
 * removing this node, indegree becomes zero.
 *
 * Repeat till all elements have been done, then print.
 *
 * Preparing for next branching, remove last branch details DFS for topological
 * sorting basically root -> (all nodes with indegree 0) -> (nodes getting
 * indegree 0 on removing node above) -> Continue branching.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class AllTopologicalSort {

  static class Graph {
    int V;
    List<List<Integer>> adj;

    Graph(int v) {
      this.V = v;
      this.adj = new ArrayList<>();
      for (int i = 0; i < this.V; i++) {
        List<Integer> list = new LinkedList<>();
        this.adj.add(list);
      }
    }

    void addEdge(int v, int w) {
      this.adj.get(v).add(w);
    }

    void allTopologicalSorts() {
      boolean[] visited = new boolean[this.V];
      int[] indegree = new int[this.V];

      for (int i = 0; i < this.V; i++) {
        for (Iterator<Integer> iter = this.adj.get(i).iterator(); iter.hasNext();) {
          int node = iter.next();
          indegree[node]++;
        }
      }

      Stack<Integer> stack = new Stack<>();
      this.allTopologicalSortsUtils(visited, indegree, stack);
    }

    void allTopologicalSortsUtils(boolean[] visited, int[] indegree, Stack<Integer> stack) {
      boolean allVisited = true;
      for (int i = 0; i < this.V; i++) {
        if (!visited[i] && indegree[i] == 0) {
          visited[i] = true;
          stack.add(i);
          for (int adjNode : this.adj.get(i)) {
            indegree[adjNode]--;
          }

          allTopologicalSortsUtils(visited, indegree, stack);

          visited[i] = false;
          stack.pop();

          for (int adjNode : this.adj.get(i)) {
            indegree[adjNode]++;
          }
          allVisited = false;
        }
      }
      if (allVisited) {
        stack.forEach(i -> System.out.print(i + " "));
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(6);
    graph.addEdge(5, 2);
    graph.addEdge(5, 0);
    graph.addEdge(4, 0);
    graph.addEdge(4, 1);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);
    System.out.println("All Topological sorts");
    graph.allTopologicalSorts();
  }
}