use std::collections::HashMap;

/**
1695. Maximum Erasure Value
https://leetcode.com/problems/maximum-erasure-value/
Medium

You are given an array of positive integers nums and want to erase a subarray containing unique
elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it
is equal to a[l],a[l+1],...,a[r] for some (l,r).

Example 1:
Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].

Example 2:
Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

Constraints:
    1 <= nums.length <= 105
    1 <= nums[i] <= 104
 */

struct Solution {}

impl Solution {
  pub fn maximum_unique_subarray(nums: Vec<i32>) -> i32 {
    let mut ans = 0;
    let mut map: HashMap<i32, usize> = HashMap::new();
    let mut prefix_sum = vec![0; nums.len() + 1];
    let mut lo = 0;
    for (hi, &num) in nums.iter().enumerate() {
      prefix_sum[hi + 1] = prefix_sum[hi] + num;
      if let Some(pos) = map.insert(num, hi + 1) {
        lo = lo.max(pos);
      }
      ans = ans.max(prefix_sum[hi + 1] - prefix_sum[lo]);
    }
    ans
  }
}

fn main() {
  let test_case = vec![5, 2, 1, 2, 5, 2, 1, 2, 5];
  println!("{:?}", Solution::maximum_unique_subarray(test_case));
}
