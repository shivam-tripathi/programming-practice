/**
918. Maximum Sum Circular Subarray
https://leetcode.com/problems/maximum-sum-circular-subarray/
Medium

Given a circular integer array nums of length n, return the maximum possible sum
of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array.
Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element
of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once.
Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not
exist i <= k1, k2 <= j with k1 % n == k2 % n.

Example 1:
Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

Example 2:
Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:
Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.

Constraints:
		n == nums.length
		1 <= n <= 3 * 104
		-3 * 104 <= nums[i] <= 3 * 104
 */


function maxSubarraySumCircular(nums: number[]): number {
	let ans = nums[0], running = nums[0];

	// Subarray using Kadance - for any array in the middle
	for (let i = 1; i < nums.length; i++) {
		running = Math.max(running + nums[i], nums[i])
		ans = Math.max(ans, running);
	}

	// For wrap around array, we will have to sum maximum subarray sum starting at the
	// start position and compare it to the maximum from ending
	// [---> . . . . <----]
	// we save maximum sum at every position left and right
	// [===><=============]
	let left = [nums[0]], max = nums[0];
	running = nums[0];
	for (let i = 1; i < nums.length; i++) {
		running += nums[i];
		max = Math.max(max, running)
		left.push(max);
	}

	running = nums[nums.length - 1];
	max = nums[nums.length - 1];
	for (let i = nums.length - 2; i >= 0; i--) {
		ans = Math.max(ans, left[i] + max);
		running += nums[i];
		max = Math.max(max, running);
	}

	return ans;
};

