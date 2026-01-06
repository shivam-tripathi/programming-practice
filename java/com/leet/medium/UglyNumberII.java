package com.leet.medium;

/**
 * 264. Ugly Number II
 *
 * https://leetcode.com/problems/ugly-number-ii/
 *
 * Medium
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3,
 * and 5.
 *
 * Given an integer n, return the nth ugly number.
 *
 * Example 1: Input: n = 10 Output: 12 Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12] is the sequence of the first 10 ugly numbers.
 *
 * Example 2: Input: n = 1 Output: 1 Explanation: 1 has no prime factors,
 * therefore all of its prime factors are limited to 2, 3, and 5.
 *
 * Constraints: 1 <= n <= 1690
 */

public class UglyNumberII {
	public int nthUglyNumber(int n) {
		int two = 0, three = 0, five = 0;

		int[] arr = new int[n + 1];
		int pos = 0;
		arr[pos++] = 1;

		int max = Integer.MAX_VALUE;

		for (int i = 1; i < n; i++) {
			int a = arr[two] > max / 2 ? max : 2 * arr[two];
			int b = arr[three] > max / 3 ? max : 3 * arr[three];
			int c = arr[five] > max / 5 ? max : 5 * arr[five];

			int ins = Math.min(a, Math.min(b, c));
			arr[pos++] = ins;

			if (ins == a)
				two++;
			if (ins == b)
				three++;
			if (ins == c)
				five++;
		}

		return arr[n - 1];
	}
}
