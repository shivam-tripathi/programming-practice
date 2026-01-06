package main

/*
565. Array Nesting
https://leetcode.com/problems/array-nesting/
Medium

You are given an integer array nums of length n where nums is a permutation of the numbers in the
range [0, n - 1].

You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the
following rule:

    The first element in s[k] starts with the selection of the element nums[k] of index = k.
    The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
    We stop adding right before a duplicate element occurs in s[k].

Return the longest length of a set s[k].

Example 1:
Input: nums = [5,4,0,3,1,6,2]
Output: 4
Explanation:
nums[0] = 5, nums[1] = 4, nums[2] = 0, nums[3] = 3, nums[4] = 1, nums[5] = 6, nums[6] = 2.
One of the longest sets s[k]:
s[0] = {nums[0], nums[5], nums[6], nums[2]} = {5, 6, 2, 0}

Example 2:
Input: nums = [0,1,2]
Output: 1

Constraints:
    1 <= nums.length <= 105
    0 <= nums[i] < nums.length
    All the values of nums are unique.
*/

/*
Because value at each index is a valid index, if we repeatedly jump between positions, this problem
will form a cycle at the end, no matter from which element in cycle we start out.
We keep track of all the visited nodes, by turning element at that index = -1, and find the entire
cycle length. We pick the cycle with longest length.
*/

func arrayNesting(nums []int) int {
	ans := 0
	for i := 0; i < len(nums); i++ {
		node, size := i, 0
		for nums[node] != -1 {
			size++
			tmp := nums[node]
			nums[node] = -1
			node = tmp
		}
		if ans < size {
			ans = size
		}
	}
	return ans
}
