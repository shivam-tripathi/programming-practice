package com.leet.medium;

/**
 * 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * Medium
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
 * The matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class SearchA2DMatrixII {
  // Standard binary search solution
  public static boolean searchMatrix(int[][] matrix, int target) {
    int rows = matrix.length, cols = matrix[0].length;
    if (matrix[0][0] > target) return false;
    if (matrix[rows - 1][cols - 1] < target) return false;

    int rowStart, rowEnd, colStart, colEnd, low, high;

    low = 0;
    high = cols - 1;
    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      if (matrix[0][mid] == target) return true;
      if (matrix[0][mid] < target) low = mid;
      else high = mid - 1;
    }
    colEnd = low;

    low = 0;
    high = cols - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (matrix[rows - 1][mid] == target) return true;
      if (matrix[rows - 1][mid] > target) high = mid;
      else low = mid + 1;
    }
    colStart = high;

    low = 0;
    high = rows - 1;
    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      if (matrix[mid][0] == target) return true;
      if (matrix[mid][0] < target) low = mid;
      else high = mid - 1;
    }
    rowEnd = low;

    low = 0;
    high = rows - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (matrix[mid][cols - 1] == target) return true;
      if (matrix[mid][cols - 1] > target) high = mid;
      else low = mid + 1;
    }
    rowStart = high;

    // System.out.println(Map.of("rowStart", rowStart, "rowEnd", rowEnd, "colStart", colStart, "colEnd", colEnd));
    // Can optimize it further to make it such that larger of the two (row, columns) have logn complexity
    for (int row = rowStart; row <= rowEnd; row++) {
      low = colStart;
      high = colEnd;
      while (low <= high) {
        int mid = low + (high - low) / 2;
        if (matrix[row][mid] == target) return true;
        else if (matrix[row][mid] < target) low = mid + 1;
        else high = mid - 1;
      }
    }
    return false;
  }

  // O(rows*cols) optimized solution
  // We start with right most end. If the number if greater than this number, it can be in the same row. So we increase
  // row. If number is smaller than this number, then it cannot be in the same column. So we decrement column.
  public static boolean searchMatrix_2(int[][] matrix, int target) {
    if (matrix == null || matrix[0] == null) return false;
    int rows = matrix.length, cols = matrix[0].length;
    int i = 0, j = cols - 1;
    while (i < rows && j >= 0) {
      if (matrix[i][j] == target) return true;
      if (matrix[i][j] < target) i++;
      else j--;
    }
    return false;
  }


  public static void main(String[] args) {
    int[][] matrix = new int[][]{
            new int[]{1, 2, 3, 4, 5},
            new int[]{6, 7, 8, 9, 10},
            new int[]{11, 12, 13, 14, 15},
            new int[]{16, 17, 18, 19, 20},
            new int[]{21, 22, 23, 24, 25}
    };
    int target = 12;
    System.out.println(searchMatrix(matrix, target));
  }
}
