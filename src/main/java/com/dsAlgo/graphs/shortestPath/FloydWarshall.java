package com.dsAlgo.graphs.shortestPath;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. The problem is
 * to find shortest distances between every pair of vertices in a given edge weighted directed
 * Graph.
 */
public class FloydWarshall {
  public static void floydWarshall (int[][] graph, int vertices) {
    int[][] dist = new int[vertices][vertices];
    int i, j, k;

    // Initialize the solution matrix with direct distance between nodes
    for (i = 0; i < vertices; i++) {
      for (j = 0; j < vertices; j++) {
        dist[i][j] = graph[i][j];
      }
    }

    for (k = 0; k < vertices; k++) {
      for (i = 0; i < vertices; i++) {
        for (j = 0; j < vertices; j++) {
          if (dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
  }

  public static void printMatrix(int[][] matrix) {
    List<List<Integer>> mat =
        Arrays.stream(matrix)
            .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
            .collect(Collectors.toList());
    System.out.println(mat);
  }

  public static void main(String[] args) {}
}
