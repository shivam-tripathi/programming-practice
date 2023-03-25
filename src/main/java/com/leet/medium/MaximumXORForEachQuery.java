package com.leet.medium;

public class MaximumXORForEachQuery {
  public int[] getMaximumXor(int[] nums, int maximumBit) {
    int val = (1<<maximumBit) - 1;
    int prev = val;
    int[] ans = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      ans[nums.length - i - 1] = prev ^ nums[i];
      prev = ans[nums.length - i - 1];
    }
    return ans;
  }
}
