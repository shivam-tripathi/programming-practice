use std::collections::HashMap;

/**
560. Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/
Medium

Given an array of integers nums and an integer k, return the total number of subarrays whose sum
equals to k.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:
  1 <= nums.length <= 2 * 104
  -1000 <= nums[i] <= 1000
  -107 <= k <= 107
 */

impl Solution {
  pub fn subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
    let mut map: HashMap<i32, i32> = HashMap::new();
    let (mut running, mut ans) = (0, 0);
    map.insert(0, 1); // the empty subarray
    for num in nums {
      running += num;
      ans += map.get(&(running - k)).unwrap_or(&0);
      *map.entry(running).or_insert(0) += 1;
    }
    ans
  }
}
