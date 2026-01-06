package com.leet.medium;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		if (sum % k != 0 || nums.length < k) {
			return false;
		}

		int target = sum / k;

		Arrays.sort(nums);

		if (nums[nums.length - 1] > target) {
			return false;
		}

		return canPartitionKSubsetsUtil(nums, target, nums.length - 1, new int[k]);
	}

	private boolean canPartitionKSubsetsUtil(int[] nums, int target, int i, int[] buckets) {
		if (i == -1) {
			return false;
		}

		for (int j = 0; j < buckets.length; j++) {
			if (buckets[j] + nums[i] <= target) {
				buckets[j] += nums[i];
				if (canPartitionKSubsetsUtil(nums, target, i - 1, buckets)) {
					return true;
				}
				buckets[j] -= nums[i];
			}
		}

		return false;
	}
}
