package com.leet.medium;

/**
 * [running * cur, cur, runningNeg * cur] ans, negAns
 */

public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		int ans = Integer.MIN_VALUE;
		int running = 1, runningNeg = 1;
		for (int num : nums) {
			running = Math.max(running * num, num);

			int nextNeg = runningNeg * num;
			if (nextNeg >= 0) {
				nextNeg = running;
			} else if (nextNeg > num) {
				nextNeg = num;
			}
			runningNeg = nextNeg;

			ans = Math.max(ans, Math.max(running * num, runningNeg * num));
		}
		return ans;
	}
}
