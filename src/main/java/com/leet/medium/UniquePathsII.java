package com.leet.medium;

/**
 * 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/
 * Medium
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 *
 *
 * Example 1:
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     m == obstacleGrid.length
 *     n == obstacleGrid[i].length
 *     1 <= m, n <= 100
 *     obstacleGrid[i][j] is 0 or 1.
 */

import java.util.Arrays;

public class UniquePathsII {
  int dfs(int[][] obstacleGrid, int x, int y, int[] dist) {
    if (x >= obstacleGrid.length || y >= obstacleGrid[0].length || obstacleGrid[x][y] == 1) {
      return 0;
    }
    if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
      return 1;
    }
    if (dist[x * obstacleGrid[0].length + y] == -1) {
      dist[x * obstacleGrid[0].length + y] = dfs(obstacleGrid, x + 1, y, dist) + dfs(obstacleGrid, x, y + 1, dist);
    }
    return dist[x * obstacleGrid[0].length + y];
  }

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int[] dist = new int[obstacleGrid.length * obstacleGrid[0].length];
    Arrays.fill(dist, -1);
    return dfs(obstacleGrid, 0, 0, dist);
  }
}
