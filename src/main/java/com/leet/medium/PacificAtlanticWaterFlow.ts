/**
 * 417. Pacific Atlantic Water Flow Medium
 *
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and
 * Atlantic Ocean. The Pacific Ocean touches the island's left and top edges,
 * and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n
 * integer matrix heights where heights[r][c] represents the height above sea
 * level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring
 * cells directly north, south, east, and west if the neighboring cell's height
 * is less than or equal to the current cell's height. Water can flow from any
 * cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci]
 * denotes that rain water can flow from cell (ri, ci) to both the Pacific and
 * Atlantic oceans.
 *
 * Example 1:
 *
 * Input: heights =
 * [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]] Output:
 * [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * Example 2:
 *
 * Input: heights = [[2,1],[1,2]] Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 * Constraints:
 *
 * m == heights.length n == heights[r].length 1 <= m, n <= 200 0 <=
 * heights[r][c] <= 105
 */

function dfs(
  row: number,
  col: number,
  mat: number[][],
  status: number[][],
  tag: number, // set this bit for the current traversal
  prev: number,
): void {
  // If invalid location, ignore
  if (row >= mat.length || row < 0 || col >= mat[row].length || col < 0) {
    return;
  }

  // If the current bit is already set OR the current height is less than prev height, ignore
  if ((status[row][col] & tag) !== 0 || mat[row][col] < prev) {
    return;
  }

  status[row][col] |= tag;

  const cur = mat[row][col];
  dfs(row, col - 1, mat, status, tag, cur);
  dfs(row, col + 1, mat, status, tag, cur);
  dfs(row - 1, col, mat, status, tag, cur);
  dfs(row + 1, col, mat, status, tag, cur);
}

function pacificAtlantic(heights: number[][]): number[][] {
  const rows = heights.length, cols = heights[0].length;

  const status: number[][] = new Array(rows);
  for (let i = 0; i < rows; i++) {
    status[i] = new Array(cols).fill(0);
  }

  for (let row = 0; row < rows; row++) {
    dfs(row, 0, heights, status, 1, -1);
    dfs(row, cols - 1, heights, status, 2, -1);
  }

  for (let col = 0; col < cols; col++) {
    dfs(0, col, heights, status, 1, -1);
    dfs(rows - 1, col, heights, status, 2, -1);
  }

  const ans: number[][] = [];

  // This step might be skipped by passing ans within DFS itself
  for (let row = 0; row < rows; row++) {
    for (let col = 0; col < cols; col++) {
      if (status[row][col] == 3) {
        ans.push([row, col]);
      }
    }
  }

  return ans;
}
