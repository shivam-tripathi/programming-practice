package com.leet.easy;

/**
 * 1337. The K Weakest Rows in a Matrix
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of
 * the k weakest rows in the matrix ordered from the weakest to the strongest.
 *
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or
 * they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that
 * is, always ones may appear first and then zeros.
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Function;

public class KWeakestRowsInAMatrix {
  // Brute force
  public int[] kWeakestRows1(int[][] mat, int k) {
    int[] counts = new int[mat.length];
    int strongest = -1;
    for (int i = 0; i < mat.length; i++) {
      int count = 0;
      for (int j = 0; j < mat[i].length; j++) {
        if (mat[i][j] == 1) count++;
        else break;
      }
      counts[i] = count;
      strongest = Math.max(strongest, count + 1);
    }

    int[] weakest = new int[k];
    for (int c = 0; c < k; c++) {
      int min = strongest, minIndex = -1;
      for (int i = 0; i < counts.length; i++) {
        if (min > counts[i]) {
          min = counts[i];
          minIndex = i;
        }
      }
      weakest[c] = minIndex;
      counts[minIndex] = strongest;
    }
    return weakest;
  }

  // Binary search, priority queue (heap)
  int[] kWeakestRows2(int[][] matrix, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    // Upper bound is the count
    Function<int[], Integer> countOnes = (a) -> {
      int low = 0, high = a.length; // Important! high bound is set to a.length as in case all ones we need last idx
      while (low < high) {
        int mid = low + (high - low) / 2;
        if (a[mid] == 0) {
          high = mid;
        } else if (a[mid] == 1) {
          low = mid + 1;
        }
      }
      return low;
    };

    for (int i = 0; i < matrix.length; i++) {
      pq.offer(new int[]{countOnes.apply(matrix[i]), i});
    }
    int[] weakest = new int[k];
    for (int i = 0; i < k; i++) {
      weakest[i] = pq.poll()[1];
    }
    return weakest;
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][]{
            new int[]{1, 1, 1, 1, 1},
            new int[]{1, 0, 0, 0, 0},
            new int[]{1, 1, 0, 0, 0},
            new int[]{1, 1, 1, 1, 0},
            new int[]{1, 1, 1, 1, 1}
    };
    System.out.println(Arrays.toString(new KWeakestRowsInAMatrix().kWeakestRows1(matrix, 3)));
  }
}
