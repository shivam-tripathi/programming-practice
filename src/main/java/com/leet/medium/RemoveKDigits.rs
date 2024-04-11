/*
402. Remove K Digits
https://leetcode.com/problems/remove-k-digits/
Medium

Given string num representing a non-negative integer num, and an integer k, return the smallest
possible integer after removing k digits from num.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:
  1 <= k <= num.length <= 105
  num consists of only digits.
  num does not have any leading zeros except for the zero itself.
*/

struct RemoveKDigits {}

impl RemoveKDigits {
  pub fn remove_kdigits(num: String, k: i32) -> String {
    let mut stack = Vec::new();
    let mut k = k;
    for c in num.chars() {
      while k > 0 && !stack.is_empty() && stack[stack.len() - 1] > c {
        stack.pop();
        k -= 1;
      }
      stack.push(c);
    }
    while k > 0 {
      stack.pop();
      k -= 1;
    }
    let mut result = stack
      .into_iter()
      .collect::<String>()
      .trim_start_matches('0')
      .to_string();
    if result.is_empty() {
      result = "0".to_string();
    }
    result
  }

  pub fn remove_kdigits_test() {
    assert_eq!(
      RemoveKDigits::remove_kdigits("1432219".to_string(), 3),
      "1219".to_string()
    );
    assert_eq!(
      RemoveKDigits::remove_kdigits("10200".to_string(), 1),
      "200".to_string()
    );
    assert_eq!(
      RemoveKDigits::remove_kdigits("10".to_string(), 2),
      "0".to_string()
    );
    assert_eq!(
      RemoveKDigits::remove_kdigits("9".to_string(), 1),
      "0".to_string()
    );
    assert_eq!(
      RemoveKDigits::remove_kdigits("112".to_string(), 1),
      "11".to_string()
    );
  }
}

pub fn main() {
  RemoveKDigits::remove_kdigits_test();
}
