/**
1239. Maximum Length of a Concatenated String with Unique Characters
https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
Medium

You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of
arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements
without changing the order of the remaining elements.

Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.

Example 2:
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").

Example 3:
Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.

Constraints:
  1 <= arr.length <= 16
  1 <= arr[i].length <= 26
  arr[i] contains only lowercase English letters.
 */

struct Solution {}

impl Solution {
  pub fn max_length(arr: Vec<String>) -> i32 {
    let sets: Vec<i32> = arr
      .iter()
      .filter_map(|s| {
        let mut bitset = 0;
        for ch in s.bytes() {
          let idx = 1 << (ch - b'a');
          if bitset & idx != 0 {
            return None;
          }
          bitset |= idx;
        }
        return Some(bitset);
      })
      .collect();

    let mut stack = vec![(0_i32, 0_usize)];
    let mut ans = 0;

    while let Some((bitset, index)) = stack.pop() {
      if index == sets.len() {
        ans = ans.max(bitset.count_ones());
      } else {
        if bitset & sets[index] == 0 {
          stack.push((bitset | sets[index], index + 1));
        }
        stack.push((bitset, index + 1));
      }
    }

    ans as i32
  }
}

fn main() {}
