/*
1323. Maximum 69 Number
https://leetcode.com/problems/maximum-69-number/description/
Easy

You are given a positive integer num consisting only of digits 6 and 9.
Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

Example 1:
Input: num = 9669
Output: 9969
Explanation:
Changing the first digit results in 6669.
Changing the second digit results in 9969.
Changing the third digit results in 9699.
Changing the fourth digit results in 9666.
The maximum number is 9969.

Example 2:
Input: num = 9996
Output: 9999
Explanation: Changing the last digit 6 to 9 results in the maximum number.

Example 3:
Input: num = 9999
Output: 9999
Explanation: It is better not to apply any change.

Constraints:
  1 <= num <= 104
  num consists of only 6 and 9 digits.
*/

struct Solution {}

impl Solution {
  pub fn maximum69_number(num: i32) -> i32 {
    let (mut num, copy, mut done, mut last_seen_base, mut base) = (num, num, false, -1, 1);
    while num > 0 {
      let digit = num % 10;
      num = num / 10;
      if digit == 6 {
        last_seen_base = base;
      }
      base = base * 10;
    }
    if last_seen_base != -1 {
      return copy - (6 * last_seen_base) + (9 * last_seen_base);
    }
    copy
  }
}
