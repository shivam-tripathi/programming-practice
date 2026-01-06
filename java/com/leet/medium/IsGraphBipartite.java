package com.leet.medium;
/**
 * 785. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 *     There are no self-edges (graph[u] does not contain u).
 *     There are no parallel edges (graph[u] does not contain duplicate values).
 *     If v is in graph[u], then u is in graph[v] (the graph is undirected).
 *     The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 *
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 */

import java.util.Arrays;

public class IsGraphBipartite {
  static boolean setAlt(int idx, int[][] graph, int[] sets) {
    int currentSet = sets[idx];
    int nextSet = currentSet == 1 ? 2 : 1;
    for (int i = 0; i < graph[idx].length; i++) {
      int node = graph[idx][i];
      int nodeSet = sets[node];
      if (nodeSet == 0) {
        sets[node] = nextSet;
        if (!setAlt(node, graph, sets)) {
          return false;
        }
      } else if (nodeSet != nextSet) {
        System.out.println(Arrays.toString(sets) + " " + idx);
        return false;
      }
    }
    return true;
  }

  public static boolean isBipartite(int[][] graph) {
    int[] sets = new int[graph.length + 1]; // zero indexed
    for (int i = 0; i < graph.length; i++) {
      if (sets[i] == 0) {
        sets[i] = 1;
        if (!setAlt(i, graph, sets)) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    var graph = new int[][]{new int[]{1, 2, 3}, new int[]{0, 2}, new int[]{0, 1, 3}, new int[]{0, 2}};
    System.out.println(isBipartite(graph));
    graph = new int[][]{new int[]{1, 3}, new int[]{0, 2}, new int[]{1, 3}, new int[]{0, 2}};
    System.out.println(isBipartite(graph));
  }
}
