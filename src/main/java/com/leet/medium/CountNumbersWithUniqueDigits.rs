/**
357. Count Numbers with Unique Digits
https://leetcode.com/problems/count-numbers-with-unique-digits/
Medium

Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.



Example 1:
Input: n = 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
11,22,33,44,55,66,77,88,99

Example 2:
Input: n = 0
Output: 1

Constraints:
    0 <= n <= 8
 */

impl CountNumbersWithUniqueDigits {
	pub fn count_numbers_with_unique_digits(n: i32) -> i32 {
			if (n == 0) {
				return 1;
			}
			if (n == 1) {
				return 10;
			}
			let mut fac: i32 = 9;
			let mut next: i32 = 9;
			let mut ans: i32 = 10;
			for i in 2..n+1 {
				fac *= next;
				next = next-1;
				ans += fac;
			}
			return ans;
	}
}
