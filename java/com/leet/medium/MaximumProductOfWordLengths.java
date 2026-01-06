package com.leet.medium;

/**
 * 318. Maximum Product of Word Lengths
 *
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 *
 * Medium
 *
 * Given a string array words, return the maximum value of length(word[i]) *
 * length(word[j]) where the two words do not share common letters. If no such
 * two words exist, return 0.
 *
 * Example 1:
 *
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"] Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 *
 * Example 2:
 *
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"] Output: 4 Explanation:
 * The two words can be "ab", "cd".
 *
 * Example 3:
 *
 * Input: words = ["a","aa","aaa","aaaa"] Output: 0 Explanation: No such pair of
 * words.
 *
 * Constraints: 2 <= words.length <= 1000 1 <= words[i].length <= 1000 words[i]
 * consists only of lowercase English letters.
 */

public class MaximumProductOfWordLengths {
	public int maxProduct(String[] words) {

		// First we check which characters have been used and which haven't
		// O(number of letters)
		int[] dp = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			int state = 0;
			for (char ch : word.toCharArray()) {
				state |= 1 << (ch - 'a');
			}
			dp[i] = state;
		}

		// Finally brute force all legit pairs and find the answer
		// O(n^2)
		int ans = 0;
		for (int i = 0; i < dp.length; i++) {
			for (int j = i + 1; j < dp.length; j++) {
				if ((dp[i] & dp[j]) == 0) {
					ans = Math.max(ans, words[i].length() * words[j].length());
				}
			}
		}

		return ans;
	}
}
