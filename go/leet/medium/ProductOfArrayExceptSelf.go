package main

/*
238. Product of Array Except Self

https://leetcode.com/problems/product-of-array-except-self/

Medium

Given an integer array nums, return an array answer such that answer[i] is equal to the product of
all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]



Constraints:

    2 <= nums.length <= 105
    -30 <= nums[i] <= 30
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.



Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not
	count as extra space for space complexity analysis.)
*/

type ProductExceptSelf struct{}

func (this *ProductExceptSelf) solve(nums, ans []int, pos int, prev int) int {
	if pos >= len(ans) {
		return 1
	}
	next := this.solve(nums, ans, pos+1, prev*nums[pos])
	ans[pos] = next * prev
	return next * nums[pos]
}

func (this *ProductExceptSelf) productExceptSelf(nums []int) []int {
	ans := make([]int, len(nums))
	this.solve(nums, ans, 0, 1)
	return ans
}

// O(1) (except output array space)
func (this *ProductExceptSelf) productExceptSelfIter(nums []int) []int {
	size := len(nums)
	ans := make([]int, size)
	prev := 1
	// fist place all products from left to right
	for i := 0; i < len(nums); i++ {
		ans[i] = prev
		prev *= nums[i]
	}
	prev = 1
	// then place all products from right to left
	for i := size - 1; i >= 0; i-- {
		ans[i] *= prev
		prev *= nums[i]
	}
	return ans
}
