package com.leet.easy;

/**
 *
 * 342. Power of Four
 *
 * https://leetcode.com/problems/power-of-four/
 *
 * Easy
 *
 * Given an integer n, return true if it is a power of four. Otherwise, return
 * false. An integer n is a power of four, if there exists an integer x such
 * that n == 4x.
 *
 * Example 1: Input: n = 16 Output: true
 *
 * Example 2: Input: n = 5 Output: false
 *
 * Example 3: Input: n = 1 Output: true
 *
 * Constraints: -231 <= n <= 231 - 1
 *
 * Follow up: Could you solve it without loops/recursion?
 */

public class PowerOfFour {
	public boolean isPowerOfFour(int n) {
		// return n > 0 && (Math.pow(4, (int)(Math.log(n) / Math.log(4)))) == n;
		// 0x55555555
		return (n > 0) && ((n & (n - 1)) == 0) && ((n & 0b1010101010101010101010101010101) != 0);
	}
}
