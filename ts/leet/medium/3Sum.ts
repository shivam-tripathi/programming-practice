/**
 * 15.
 * 3Sum
 * https://leetcode.com/problems/3sum/
 * Medium
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
 * nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 * Input: nums = [] Output: []
 *
 * Example 3:
 * Input: nums = [0] Output: []
 *
 * Constraints:
 * 0 <= nums.length <= 3000 -105 <= nums[i] <= 105
 */

function twoSum(
  nums: number[],
  low: number,
  high: number,
  target: number,
  ans: number[][] = []
): void {
  if (low >= high) {
    return;
  }
  while (low < high) {
    const sum = nums[low] + nums[high];
    if (sum === target) {
      ans.push([-target, nums[low], nums[high]]);
      const l = nums[low],
        r = nums[high];
      while (low < high && nums[low] === l && nums[high] === r) {
        low++;
        high--;
      }
    } else if (sum < target) {
      low++;
    } else {
      high--;
    }
  }
}

function threeSum(nums: number[]): number[][] {
  nums.sort((a, b) => a - b);
  const ans: number[][] = [];
  for (let i = 0; i < nums.length; i++) {
    if (i === 0 || nums[i] !== nums[i - 1]) {
      twoSum(nums, i + 1, nums.length - 1, -nums[i], ans);
    }
  }
  return ans;
}
