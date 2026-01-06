package com.leet.medium;

/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 * Medium
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'
 */

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
  //    -1,0
  // 0,-1  x  0,1
  //     1,0
  int[][] neighbours = new int[][]{new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}};

  // 2ms
  void markDone(int row, int col, char[][] grid, char marker) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{row, col});
    while (!queue.isEmpty()) {
      int[] pos = queue.poll();
      grid[pos[0]][pos[1]] = marker;

      for (int[] n : neighbours) {
        int i = n[0], j = n[1];
        int curRow = pos[0] + i, curCol = pos[1] + j;
        if (curRow < 0 || curCol < 0 || curRow >= grid.length || curCol >= grid[curRow].length || grid[curRow][curCol] != '1') {
          continue;
        }
        grid[curRow][curCol] = marker;
        queue.offer(new int[]{curRow, curCol});
      }
    }
  }

  // 1ms 100%. DFS is usually faster than BFS due to lesser overhead of queue.
  void markDoneDFS(int row, int col, char[][] grid, char marker) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] != '1') return;
    grid[row][col] = marker;
    for (int[] n : neighbours) {
      markDoneDFS(row + n[0], col + n[1], grid, marker);
    }
  }

  public int numIslands(char[][] grid) {
    char marker = '2';
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          markDoneDFS(i, j, grid, marker++);
        }
      }
    }
    return marker - '2';
  }
}
