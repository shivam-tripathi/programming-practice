/**
338. Counting Bits
https://leetcode.com/problems/counting-bits/
Easy

Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i]
is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

Constraints:
  0 <= n <= 105

Follow up:
	It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear
	time O(n) and possibly in a single pass?
	Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */

struct Solution {}

impl Solution {
  pub fn count_bits(n: i32) -> Vec<i32> {
    if n <= 2 {
      return vec![0, 1, 1][0_usize..((n + 1) as usize)].to_vec();
    }

    let mut ans = vec![0, 1];

    loop {
      let size = ans.len();
      for i in 0..size {
        ans.push(ans[i] + 1);
        if ans.len() as i32 == n + 1 {
          return ans;
        }
      }
    }

    ans
  }
}

fn main() {}
