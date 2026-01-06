package com.leet.medium;

/**
 * 34. Find First and Last Position of Element in Sorted Array Medium
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value. If target is not found in the
 * array, return [-1, -1]. You must write an algorithm with O(log n) runtime
 * complexity.
 *
 * Example 1: Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4]
 *
 * Example 2: Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
 *
 * Example 3: Input: nums = [], target = 0 Output: [-1,-1]
 *
 * Constraints: 0 <= nums.length <= 105 -109 <= nums[i] <= 109 nums is a
 * non-decreasing array. -109 <= target <= 109
 */

public class FindFirstAndLastPositionOfElementInSortedArray {
	int lowerBound(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		if (nums[0] == target)
			return -1;
		while (low != high) {
			int mid = low + (high - low + 1) / 2;
			if (nums[mid] < target) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	int upperBound(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		if (nums[nums.length - 1] == target)
			return nums.length;
		while (low != high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] > target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public int[] searchRange(int[] nums, int target) {
		int[] invalid = new int[] { -1, -1 };
		if (nums.length == 0) {
			return invalid;
		}
		int upper = upperBound(nums, target);
		if (upper >= 0 && upper < nums.length && nums[upper] <= target) {
			return invalid;
		}
		int lower = lowerBound(nums, target);
		if (lower >= 0 && lower < nums.length && nums[lower] >= target) {
			return invalid;
		}
		if (nums[lower + 1] != target || nums[upper - 1] != target) {
			return invalid;
		}
		return new int[] { lower + 1, upper - 1 };
	}
}
