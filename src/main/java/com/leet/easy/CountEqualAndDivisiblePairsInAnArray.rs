use std::collections::HashMap;

/**
2176. Count Equal and Divisible Pairs in an Array
https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/
Easy
Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.

Example 1:
Input: nums = [3,1,2,2,2,1,3], k = 2
Output: 4
Explanation:
There are 4 pairs that meet all the requirements:
- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.

Example 2:
Input: nums = [1,2,3,4], k = 1
Output: 0
Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.

Constraints:
    1 <= nums.length <= 100
    1 <= nums[i], k <= 100
 */

struct Solution {}

impl Solution {
  pub fn count_pairs(nums: Vec<i32>, k: i32) -> i32 {
    let mut map = HashMap::new();
    let mut ans = 0;

    for i in 0..nums.len() {
      let num = &nums[i];
      if !map.contains_key(num) {
        map.insert(num.clone(), vec![i]);
      } else {
        map.get_mut(num).unwrap().push(i);
      }
    }

    // Possible optimisation here - instead of iterating over all indexes in the map,
    // we calculate GCD and save it in a hashmap. (i * j) % k == 0 => gcd(i, k) * gcd(j, k) % k == 0
    // Basically we compress the numbers into a smaller range, in sqrt n.
    for (_, occur) in map.into_iter() {
      for i in 0..occur.len() {
        for j in (i + 1)..occur.len() {
          if (occur[i] * occur[j]) as i32 % k == 0 {
            ans += 1;
          }
        }
      }
    }

    ans
  }
}

fn main() {
  let ans = Solution::count_pairs(vec![3, 1, 2, 2, 2, 1, 3], 2);
  println!("{:?}", ans);
}
