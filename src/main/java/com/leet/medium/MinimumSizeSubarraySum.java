package com.leet.medium;

/**
 * 209. Minimum Size Subarray Sum Medium
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ...,
 * numsr-1, numsr] of which the sum is greater than or equal to target. If there
 * is no such subarray, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3] Output: 2 Explanation: The subarray
 * [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4] Output: 1
 *
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1] Output: 0
 *
 *
 *
 * Constraints:
 *
 * 1 <= target <= 109 1 <= nums.length <= 105 1 <= nums[i] <= 105
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another
 * solution of which the time complexity is O(n log(n)).
 */

public class MinimumSizeSubarraySum {
  /**
   * Algorithm O(n) - Sum till you hit sum >= target. This then can be potential
   * solution. - Next, remove all numbers from the begining till you hit value <
   * target. This is being done because if there exists a subarray with sum >=
   * target, then adding more number to the array is only going to increase the
   * length of the array. It is already a valid array, and we need minimum - so it
   * makes no sense to add more values to the array. - Repeat. - Don't forget the
   * final case (after you exit from the regular loop)
   *
   *
   * [2,3,1,2,4,3] 2 >= 2 true 1 return
   *
   * 3 2 false 2+3=5 true 2 reduce window size till we have number < target 3 true
   * 1 return
   *
   * begin target = 5 2 false 2+3=5 true 2 3 false 3+1=4 false 3+1+2=6 true 3
   * 1+2=3 false 1+2+4=7 true 3 2+4=6 true 2 4 false 4+3=7 true 3 false end
   * min(2,3,3,2)
   *
   * [1,1,1,1,1,1,1,1] begin 1 false 1+1=2 false 1+1+1=3 false 1+1+1+1=4 false
   * 1+....=9 false end min(0)
   *
   */
  public int minSubArrayLen(int target, int[] nums) {
    // end points to next number we need to consider
    // start points to supposed beginning of subarray
    // Always, end > start. Subarray is [start, end)
    // Size is = end - start
    int start = 0, end = 0, sum = 0;
    int ans = 0;
    while (end < nums.length) {
      sum += nums[end++];
      while (start < end && sum >= target) {
        int size = end - start;
        if (size == 1)
          return 1;
        if (size < ans || ans == 0)
          ans = size;
        sum -= nums[start++];
      }
    }
    return ans;
  }
}
