package main

/*
330. Patching Array
https://leetcode.com/problems/patching-array/
Hard

Given a sorted integer array nums and an integer n, add/patch elements to the array such that any
number in the range [1, n] inclusive can be formed by the sum of some elements in the array.

Return the minimum number of patches required.

Example 1:

Input: nums = [1,3], n = 6
Output: 1
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:

Input: nums = [1,5,10], n = 20
Output: 2
Explanation: The two patches can be [2, 4].

Example 3:

Input: nums = [1,2,2], n = 5
Output: 0

Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= 104
    nums is sorted in ascending order.
    1 <= n <= 231 - 1
*/

import "fmt"

/*
Misc: If there were no nums or n, the minimum number of elements to form all numbers in a range
would be powers of two

Idea:
If we know that minimum sum which is missing from all possible sum of subsequences is k, then if we add k in the
array, the next possible minimum sum which is missing from all possible sum of subsequences is 2k (because till
k-1 is already present in the array - so 1->k-1 + k => 1->2k-1). So we start iterating from next = 1, and stop
when either all nums are exhausted or next > n, all the while patching elements if required.

Similarly, if num < next, then we can easily update next = next + num as 1->next-1 already exists and we can add
then to num to get all the values between 1->next-1+num

If next <= n and each num in nums
	for cur next < num AND next <= n: // first bring next to bigger value than num
		patch ++
		next = next + next // then we add next to array
	next = next + num

At the end, because there are no more nums, we do:
	for next <= n {
		patch++
		next = next + next
	}
*/

func minPatches(nums []int, n int) int {
	next, count := 1, 0
	// for each num in nums, add patch if next <= n AND next < num
	for _, num := range nums {
		for next < num && next <= n {
			count++
			next <<= 1
		}
		next = next + num
	}

	for next <= n {
		count++
		next <<= 1
	}
	return count
}

func main() {
	nums := []int{1, 3}
	ans := minPatches(nums, 6)
	fmt.Println(ans)
}
