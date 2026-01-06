package com.leet.medium;

/**
 * 300. Longest Increasing Subsequence Medium
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of
 * the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18] Output: 4 Explanation: The longest increasing subsequence
 * is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3] Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7] Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 2500 -104 <= nums[i] <= 104
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */

public class LongestIncreasingSubsequence {
  int lowerBound(int[] arr, int size, int target) {
    if (size == 0 || arr[0] >= target) return -1;
    if (target > arr[size - 1]) return size - 1;
    int low = 0, high = size - 1;
    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      if (arr[mid] < target) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  public int lengthOfLIS(int[] nums) {
    int size = 0;
    // Keep track of all LIS endings with a given size (index+1 corresponds to LIS of the
    // given size)
    // Maximum length of a subsequence can be at most size of nums
    int[] lisEnd = new int[nums.length];
    for (int num : nums) {
      int lowerIdx = lowerBound(lisEnd, size, num);
      if ((lowerIdx + 1) == size) {
        lisEnd[size++] = num;
      } else if (lisEnd[lowerIdx + 1] > num) {
        lisEnd[lowerIdx + 1] = num;
      }
    }
    return size;
  }
}
