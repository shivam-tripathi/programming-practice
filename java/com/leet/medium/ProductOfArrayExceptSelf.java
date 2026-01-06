package com.leet.medium;

class Solution {
	public int[] productExceptSelf(int[] nums) {
		int[] ans = new int[nums.length];

		// right running product
		int prev = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			ans[i] = nums[i] * prev;
			prev = ans[i];
		}

		int left = 1; // left running product
		for (int i = 0; i < nums.length; i++) {
			int right = i == (nums.length - 1) ? 1 : ans[i + 1]; // get right if possible
			ans[i] = left * right; // current value is left * right
			left = left * nums[i]; // move left to cover current value
		}

		return ans;
	}
}
