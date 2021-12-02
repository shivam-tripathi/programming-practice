package com.leet.medium;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
	int solve(int[] nums, int pos, Map<Integer, Integer> dp) {
		if (pos >= nums.length)
			return 0;
		if (dp.containsKey(pos))
			return dp.get(pos);
		int with = nums[pos] + solve(nums, pos + 2, dp);
		int without = solve(nums, pos + 1, dp);
		int ans = with > without ? with : without;
		dp.put(pos, ans);
		return ans;
	}

	public int rob(int[] nums) {
		return solve(nums, 0, new HashMap<>());
	}
}
