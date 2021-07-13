package com.leet.medium;

/**
 * 128. Longest Consecutive Sequence Medium
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * <p>Given an unsorted array of integers nums, return the length of the longest consecutive
 * elements sequence.
 *
 * <p>You must write an algorithm that runs in O(n) time.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [100,4,200,1,3,2] Output: 4 Explanation: The longest consecutive elements
 * sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [0,3,7,2,5,8,4,6,0,1] Output: 9
 *
 * <p>Constraints:
 *
 * <p>0 <= nums.length <= 105 -109 <= nums[i] <= 109
 */

import java.util.HashSet;

public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    var set = new HashSet<Integer>();
    for (int num : nums) set.add(num);

    int ans = 0;

    for (int num : nums) {
      if (set.contains(num - 1)) continue;
      int cur = 0;
      while (set.contains(num)) {
        cur++;
        set.remove(num++);
      }
      ans = Math.max(ans, cur);
    }

    return ans;
  }
}
