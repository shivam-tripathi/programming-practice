use std::collections::VecDeque;

/*

907. Sum of Subarray Minimums
https://leetcode.com/problems/sum-of-subarray-minimums
Medium

Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous)
subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

Example 1:
Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.

Example 2:
Input: arr = [11,81,94,43,3]
Output: 444

Constraints:
  1 <= arr.length <= 3 * 104
  1 <= arr[i] <= 3 * 104
 */

struct Solution {}

static MOD: i32 = 10i32.pow(9) + 7;

impl Solution {
  pub fn sum_subarray_mins(arr: Vec<i32>) -> i32 {
    let mut stack = VecDeque::<(usize, i32)>::new();
    let mut res = 0;

    for i in 0..arr.len() {
      while !stack.is_empty() && arr[stack.back().unwrap().0] >= arr[i] {
        stack.pop_back();
      }
      let cur = if stack.is_empty() {
        (arr[i] * (i + 1) as i32) % MOD
      } else {
        let (prev_idx, prev_val) = stack.back().unwrap();
        ((arr[i] * (i - prev_idx) as i32) % MOD + prev_val) % MOD
      };
      stack.push_back((i, cur));
      res = (res + (cur % MOD)) % MOD;
    }
    res
  }
}

fn main() {
  println!("{}", Solution::sum_subarray_mins(vec![3, 1, 2, 4]));
  println!("{}", Solution::sum_subarray_mins(vec![11, 81, 94, 43, 3]));
}
