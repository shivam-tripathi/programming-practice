/**
1296. Divide Array in Sets of K Consecutive Numbers
https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
Medium

Given an array of integers nums and a positive integer k, check whether it is possible to divide
this array into sets of k consecutive numbers.
Return true if it is possible. Otherwise, return false.

Example 1:
Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

Example 2:
Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

Example 3:
Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.

Constraints:
		1 <= k <= nums.length <= 105
		1 <= nums[i] <= 109
 */

function isPossibleDivide(nums: number[], k: number): boolean {
	const counts = nums.reduce((acc, num) => {
		acc[num] = (acc[num] ?? 0) + 1;
		return acc;
	}, {} as Record<number, number>);

	nums.sort((a, b) => a - b);

	let pos = 0;
	while (pos < nums.length) {
		if (counts[nums[pos]] > 0) {
			for (let i = 0; i < k; i++) {
				if (!counts[nums[pos] + i]) {
					return false;
				}
				counts[nums[pos] + i]--;
			}
		} else {
			pos++;
		}
	}

	return true;
};
