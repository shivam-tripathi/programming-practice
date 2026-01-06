/**
1658. Minimum Operations to Reduce X to Zero
https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
Medium

You are given an integer array nums and an integer x. In one operation, you can either remove the
leftmost or the rightmost element from the array nums and subtract its value from x. Note that this
modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise,
return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements
(5 operations in total) to reduce x to zero.

Constraints:
  1 <= nums.length <= 105
  1 <= nums[i] <= 104
  1 <= x <= 109
 */

struct Solution {}

impl Solution {
  pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
    let mut ans: i32 = -1;
    let sum = nums.iter().sum::<i32>();
    let target = sum - x;

    let mut running_sum = 0;
    let (mut hi, mut lo): (usize, usize) = (0, 0);

    while hi < nums.len() {
      running_sum += nums[hi];

      // can use binary search here
      while running_sum > target && lo <= hi {
        running_sum -= nums[lo];
        lo += 1;
      }

      if running_sum == target {
        let cur_ans = (hi - lo + 1) as i32;
        if ans == -1 {
          ans = cur_ans;
        } else {
          ans = ans.max(cur_ans);
        }
      }

      hi += 1;
    }

    if ans == -1 {
      return -1;
    }

    nums.len() as i32 - ans
  }
}

fn main() {
  let test_case = vec![3, 2, 20, 1, 1, 3];
  let x = 10;
  assert_eq!(Solution::min_operations(test_case.clone(), x), 5);
  println!("{:?}", Solution::min_operations(test_case, x));
}
