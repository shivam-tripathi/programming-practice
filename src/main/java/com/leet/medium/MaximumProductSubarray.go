package main

/*
152. Maximum Product Subarray
https://leetcode.com/problems/maximum-product-subarray/
Medium

Given an integer array nums, find a contiguous non-empty subarray within the array that has the
largest product, and return the product.
It is guaranteed that the answer will fit in a 32-bit integer.
A subarray is a contiguous subsequence of the array.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:
    1 <= nums.length <= 2 * 104
    -10 <= nums[i] <= 10
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/

func maxProduct(nums []int) int {
	// neg and pos are initialized to 1 to capture value for the current position
	// ans is by default the first num. length of nums is >= 1 always, so no check required
	neg, pos, ans := 1, 1, nums[0]

	for i := 0; i < len(nums); i++ {
		// using the prev pos or neg chain, find the product with current elements
		a, b := pos*nums[i], neg*nums[i]

		// pos is max of a or b, but if both a and b are less than equal to 0, we reset pos to 1
		if a > b {
			pos = a
		} else {
			pos = b
		}
		if pos <= 0 {
			pos = 1
		}

		// neg is min of a or b, but if both a and b are greater than equal to 0, we reset neg to 1
		if a < b {
			neg = a
		} else {
			neg = b
		}
		if neg >= 0 {
			neg = 1
		}

		// ans is max of a or b
		if ans < a {
			ans = a
		}
		if ans < b {
			ans = b
		}
	}

	return ans
}
