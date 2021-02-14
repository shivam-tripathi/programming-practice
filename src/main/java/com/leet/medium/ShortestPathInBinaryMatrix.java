package com.leet.medium;

/**
 * 1091. Shortest Path in Binary Matrix
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 *     Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 *     C_1 is at location (0, 0) (ie. has value grid[0][0])
 *     C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 *     If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 *
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 */

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
  static class QueueElement {
    int x;
    int y;
    int dist;

    QueueElement(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

  static int[][] grid;
  static int lastIdx;
  //  static boolean[][] visited;
  static int[][] minValue;

//  static void getColumnarNeighbours(int x, int y, List<int[]> list) {
//    if (y - 1 >= 0 && grid[x][y - 1] != 1 && minValue[x][y - 1] == 0) list.add(new int[]{x, y - 1});
//    if (grid[x][y] != 1 && minValue[x][y] == 0) list.add(new int[]{x, y});
//    if (y + 1 <= lastIdx && grid[x][y + 1] != 1 && minValue[x][y + 1] == 0) list.add(new int[]{x, y + 1});
//  }
//
//  static List<int[]> getNeighbours(int x, int y) {
//    var list = new ArrayList<int[]>();
//    if (x - 1 >= 0) getColumnarNeighbours(x - 1, y, list);
//    getColumnarNeighbours(x, y, list);
//    if (x + 1 <= lastIdx) getColumnarNeighbours(x + 1, y, list);
//    return list;
//  }

  static int[][] neighbourHood = new int[][]{{1, 1}, {1, 0}, {0, 1}, {1, -1}, {-1, 1}, {0, -1}, {-1, 0}, {-1, -1}};
  /*
  (-1,-1) (-1,0) (-1,1)
  (0,-1)  (0,0)  (0,1)
  (1,-1)  (1,0)  (1,1)
   */

  public static int shortestPathBinaryMatrix(int[][] g) {
    grid = g;
    lastIdx = grid.length - 1;
    if (grid[0][0] == 1 || grid[lastIdx][lastIdx] == 1) {
      return -1;
    }

    // visited = new boolean[grid.length][grid.length];
    minValue = new int[grid.length][grid.length];


    // Queue holds: 1. Point(x, y) 2. Distance to the point following the current path
    Queue<QueueElement> queue = new LinkedList<>();
    // visited[0][0] = true;
    minValue[0][0] = 1;
    queue.add(new QueueElement(0, 0, 1));

    while (!queue.isEmpty()) {
      var queueElement = queue.poll();
      int x = queueElement.x, y = queueElement.y;
      int dist = queueElement.dist;
      if (x == lastIdx && y == lastIdx) {
        return minValue[x][y] != 0 ? Math.min(dist, minValue[x][y]) : dist;
      }
      for (int[] nh : neighbourHood) { // looping over -1 to 1 and nested loop over -1 to 1 will perform better
        int xx = x + nh[0], yy = y + nh[1];
        if (xx >= 0 && xx <= lastIdx && yy >= 0 && yy <= lastIdx && grid[xx][yy] != 1 && minValue[xx][yy] == 0) {
          minValue[xx][yy] = dist + 1;
          queue.offer(new QueueElement(xx, yy, dist + 1));
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[][] grid = new int[][]{
            new int[]{0, 0, 0},
            new int[]{1, 1, 0},
            new int[]{1, 1, 0},
    };
    System.out.println(shortestPathBinaryMatrix(grid));
  }
}
