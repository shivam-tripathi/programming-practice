package com.leet.medium;

/**
 * 1034. Coloring A Border
 * https://leetcode.com/problems/coloring-a-border/
 * Medium
 *
 * Given a 2-dimensional grid of integers, each value in the grid represents the color of the grid square at that
 * location.
 * Two squares belong to the same connected component if and only if they have the same color and are next to each other
 * in any of the 4 directions.
 * The border of a connected component is all the squares in the connected component that are either 4-directionally
 * adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
 * Given a square at location (r0, c0) in the grid and a color, color the border of the connected component of that
 * square with the given color, and return the final grid.
 *
 * Example 1:
 * Input: grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
 * Output: [[3, 3], [3, 2]]
 *
 * Example 2:
 * Input: grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
 * Output: [[1, 3, 3], [2, 3, 3]]
 *
 * Example 3:
 * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
 * Output: [[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 *
 * Note:
 *     1 <= grid.length <= 50
 *     1 <= grid[0].length <= 50
 *     1 <= grid[i][j] <= 1000
 *     0 <= r0 < grid.length
 *     0 <= c0 < grid[0].length
 *     1 <= color <= 1000
 */

import java.util.HashSet;
import java.util.Set;

public class ColoringABorder {
  int rows;
  int cols;
  int[][] grid;

  Set<Integer> visited = new HashSet<>();

  int getColor(int[][] grid, int row, int col) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length) return 0;
    return grid[row][col];
  }

  public int[][] getBorders(int row, int col, int base, int target) {

    // If this indexes are invalid or color of this grid is not same as we transforming
    if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != base) return grid;

    int index = row * grid[row].length + col; // index for current cell
    if (visited.contains(index)) return grid; // if already visited, return
    visited.add(index);

    // Find colors for all neighbours. We do this before we recur for neighbours so as to not affect calculation by
    // mutations
    int c1 = getColor(grid, row - 1, col);
    int c2 = getColor(grid, row + 1, col);
    int c3 = getColor(grid, row, col - 1);
    int c4 = getColor(grid, row, col + 1);

    var enclosed = c1 == c2 && c2 == c3 && c3 == c4 && c4 == base;

    // Recur for neighbours
    getBorders(row - 1, col, base, target);
    getBorders(row + 1, col, base, target);
    getBorders(row, col - 1, base, target);
    getBorders(row, col + 1, base, target);

    // If not enclosed, mutate to target
    if (!enclosed) grid[row][col] = target;

    return grid;
  }

  public int[][] colorBorder(int[][] grid, int row, int col, int color) {
    this.grid = grid;
    this.rows = grid.length;
    this.cols = grid[0].length;
    return getBorders(row, col, grid[row][col], color);
  }
}
