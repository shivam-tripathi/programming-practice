/**
704. Binary Search
https://leetcode.com/problems/binary-search/
Easy

Given an array of integers nums which is sorted in ascending order, and an integer target, write a
function to search target in nums. If target exists, then return its index. Otherwise, return -1.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

Constraints:
    1 <= nums.length <= 104
    -104 < nums[i], target < 104
    All the integers in nums are unique.
    nums is sorted in ascending order.
 */

struct Solution {}

impl Solution {
  pub fn search(nums: Vec<i32>, target: i32) -> i32 {
    let (mut begin, mut end) = (0 as i32, nums.len() as i32 - 1);
    while begin <= end {
      let mid = begin + (end - begin) / 2;
      if nums[mid as usize] == target {
        return mid;
      } else if nums[mid as usize] < target {
        begin = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    -1
  }
}
