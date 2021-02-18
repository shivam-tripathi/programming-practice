package com.leet.medium;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between
 * any two consecutive elements is the same.
 * https://leetcode.com/problems/arithmetic-slices
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 *
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 *
 *
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such
 * that 0 <= P < Q < N.
 *
 * A slice (P, Q) of the array A is called arithmetic if the sequence:
 * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 *
 * The function should return the number of arithmetic slices in the array A.
 *
 *
 * Example:
 *
 * A = [1, 2, 3, 4]
 *
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */

public class ArithmeticSlices {
  public int numberOfArithmeticSlices(int[] arr) {
    if (arr.length < 3) return 0;
    int diff = arr[0] - arr[1], count = 2, ans = 0;
    for (int i = 1; i < arr.length - 1; i++) {
      int curDiff = arr[i] - arr[i + 1];
      if (curDiff == diff) {
        count++;
      } else {
        if (count >= 3) {
          int n = (count - 3 + 1);
          ans += (n * (n+1))/2;
        }
        count = 2;
        diff = curDiff;
      }
    }
    if (count >= 3) {
      int n = (count - 3 + 1);
      ans += (n * (n+1))/2;
    }
    return ans;
  }
}
