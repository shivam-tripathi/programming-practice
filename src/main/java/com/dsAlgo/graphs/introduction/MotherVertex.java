package com.dsAlgo.graphs.introduction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Mother vertex: DFS O(E + V)
 * Case 1:- Undirected Connected Graph : In this case, all the vertices are mother vertices as we can reach to all the other nodes in the graph.
 * Case 2:- Undirected/Directed Disconnected Graph : In this case, there is no mother vertices as we cannot reach to all the other nodes in the graph.
 * Case 3:- Directed Connected Graph : In this case, we have to find a vertex v in the graph such that we can reach to all the other nodes in the graph through a directed path.
 * Last node to finish dfs is the parent vertex. Another method could be using disjoint data structures.
 */


public class MotherVertex {
  static class Graph {
    int V;
    List<List<Integer>> adj;

    Graph(int v) {
      this.V = v;
      this.adj = new ArrayList<>();
      for (int i = 0; i < v; i++) {
        adj.add(new LinkedList<>());
      }
    }

    void addEdge(int v, int w) {
      this.adj.get(v).add(w);
    }

    void DFS(int src, List<Boolean> visited) {
      visited.set(src, true);
      for (Integer node : adj.get(src)) {
        if (!visited.get(node)) {
          DFS(node, visited);
        }
      }
    }

    int findMotherVertex() {
      List<Boolean> visited = Arrays.asList(new Boolean[this.V]);
      // Last vertex to be visited via DFS is mother vertex
      int motherVertex = 0;
      for (int i = 0; i < this.V; i++) {
        if (visited.get(i) == null || !visited.get(i)) {
          this.DFS(i, visited);
          motherVertex = i;
        }
      }

      // Verify if the graph is not diconnected
      for (int i = 0; i < V; i++) {
        visited.set(i, false);
      }
      DFS(motherVertex, visited);
      for (int i = 0; i < this.V; i++) {
        if (visited.get(i) == null || !visited.get(i)) {
          return -1;
        }
      }

      return motherVertex;
    }
  }

  ;

  public static void main(String[] args) {
    Graph graph = new Graph(7);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(4, 1);
    graph.addEdge(6, 4);
    graph.addEdge(5, 6);
    graph.addEdge(5, 2);
    graph.addEdge(6, 0);

    System.out.println(graph.findMotherVertex());
  }
}
