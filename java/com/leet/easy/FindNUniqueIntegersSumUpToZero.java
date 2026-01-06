package com.leet.easy;

/**
 * 1304. Find N Unique Integers Sum up to Zero
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 * Easy
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 * Example 1:
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * Example 2:
 * Input: n = 3
 * Output: [-1,0,1]
 * Example 3:
 * Input: n = 1
 * Output: [0]
 * Constraints:
 *     1 <= n <= 1000
 */

public class FindNUniqueIntegersSumUpToZero {
  public int[] sumZero(int n) {
    int[] ans = new int[n];
    int mid = n / 2, low = 0, high = n - 1;
    while (low <= high) {
      ans[low++] = mid * -1;
      ans[high--] = mid;
      mid--;
    }
    return ans;
  }
}
