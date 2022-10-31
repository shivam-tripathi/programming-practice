/*
300. Longest Increasing Subsequence
https://leetcode.com/problems/longest-increasing-subsequence/
Medium

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements
without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the
array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
	1 <= nums.length <= 2500
	-104 <= nums[i] <= 104

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

function upper_bound(arr: number[], size: number, target: number): number {
  let low = 0, high = size - 1;
  if (arr[size - 1] < target) return size;
  while (low != high) {
    const mid = low + Math.floor((high - low) / 2);
    if (arr[mid] >= target) {
      high = mid;
    } else {
      low = mid + 1;
    }
  }
  return low;
}

export function lengthOfLIS(nums: number[]): number {
  const sizes = new Array<number>(nums.length).fill(0);
  sizes[0] = nums[0];
  let size = 1;
  for (const num of nums) {
    const ub = upper_bound(sizes, size, num);
    if (ub === size) {
      sizes[size++] = num;
    } else if (sizes[ub] !== num) {
      sizes[ub] = num;
    }
  }
  return size;
}

// O(n^2)
// function lengthOfLIS(nums: number[]): number {
//   const list: number[] = new Array(nums.length).fill(1);
//   let ans = 1;
//   for (let i = 0; i < nums.length; i++) {
//     for (let j = 0; j < i; j++) {
//       if (nums[i] > nums[j] && list[i] < (list[j]+1)) {
//         list[i] = list[j] + 1;
//         ans = Math.max(ans, list[i]);
//       }
//     }
//   }
//   return ans;
// };
