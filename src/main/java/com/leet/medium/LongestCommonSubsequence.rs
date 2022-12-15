/*
1143. Longest Common Subsequence
https://leetcode.com/problems/longest-common-subsequence/description/
Medium

Given two strings text1 and text2, return the length of their longest common subsequence. If there
is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters
(can be none) deleted without changing the relative order of the remaining characters.
  For example, "ace" is a subsequence of "abcde".

A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Constraints:
  1 <= text1.length, text2.length <= 1000
  text1 and text2 consist of only lowercase English characters.
 */

struct Solution {}

impl Solution {
  pub fn longest_common_subsequence(text1: String, text2: String) -> i32 {
    let mut prev = &mut vec![0; text2.len() + 1];
    let mut cur = &mut vec![0; text2.len() + 1];

    let str1 = text1.chars().collect::<Vec<char>>();
    let str2 = text2.chars().collect::<Vec<char>>();

    for i in 1_usize..=str1.len() {
      for j in 1_usize..=str2.len() {
        let mut matches = prev[j - 1];
        if str1[i - 1] == str2[j - 1] {
          matches = matches + 1;
        }
        cur[j] = prev[j].max(cur[j - 1]).max(matches);
      }
      let tmp = prev;
      prev = cur;
      cur = tmp;
    }

    prev[text2.len()]
  }
}

fn main() {}
