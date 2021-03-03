package com.leet.medium;

/**
 * 1329. Sort the Matrix Diagonally
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * Medium
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 * Example 1:
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * Example 2:
 * Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */

import java.util.*;

// i-j is same for the entire diagonal
public class SortTheMatrixDiagonally {
  public int[][] diagonalSort(int[][] mat) {
    var diagonals = new HashMap<Integer, PriorityQueue<Integer>>();
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        diagonals.putIfAbsent(i - j, new PriorityQueue<>());
        diagonals.get(i - j).offer(mat[i][j]);
      }
    }
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        mat[i][j] = diagonals.get(i - j).remove();
      }
    }
    return mat;
  }

  int[][] diagonalSort_2(int[][] mat) {
    if (mat == null || mat.length == 1 || mat[0].length == 1) return mat;
    int rows = mat.length, cols = mat[0].length;
    int maxDiagonalSize = (int) Math.ceil(Math.sqrt(Math.abs(rows * rows + cols * cols)));
    int[] pointers = new int[rows + cols - 1];
    int[][] diagonals = new int[rows + cols - 1][maxDiagonalSize];
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        int diagonalIdx = i - j + cols - 1;
        diagonals[diagonalIdx][pointers[diagonalIdx]] = mat[i][j];
        pointers[diagonalIdx]++;
      }
    }

    for (int i = 0; i < diagonals.length; i++) {
      if (diagonals[i] == null || pointers[i] == 0) continue;
      Arrays.sort(diagonals[i], 0, pointers[i]);
    }

    int[] newPointers = new int[rows + cols - 1];
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        int diagonalIdx = i - j + cols - 1;
        mat[i][j] = diagonals[diagonalIdx][newPointers[diagonalIdx]];
        newPointers[diagonalIdx]++;
      }
    }
    return mat;
  }
}
