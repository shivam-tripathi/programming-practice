package com.leet.hard;

/**
 * 140. Word Break II Hard
 *
 * https://leetcode.com/problems/word-break-ii/
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word. Return all
 * such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the
 * segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"] Output:
 * ["cats and dog","cat sand dog"]
 *
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict =
 * ["apple","pen","applepen","pine","pineapple"] Output: ["pine apple pen
 * apple","pineapple pen apple","pine applepen apple"] Explanation: Note that
 * you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"] Output:
 * []
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20 1 <= wordDict.length <= 1000 1 <= wordDict[i].length <=
 * 10 s and wordDict[i] consist of only lowercase English letters. All the
 * strings of wordDict are unique.
 *
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WordBreakII {
	Map<Integer, List<String>> dp = new HashMap<>();

	public List<String> solve(String s, int position, HashSet<String> dict) {
		if (position >= s.length()) {
			return new ArrayList<>();
		}

		if (dp.containsKey(position)) {
			return dp.get(position);
		}

		// form the next word
		String collect = "";
		List<String> ans = new ArrayList<>();
		for (int i = position; i < s.length(); i++) {
			collect += s.charAt(i);
			if (dict.contains(collect)) {
				if (i == s.length() - 1) {
					ans.add(collect.toString());
				} else {
					List<String> cur = solve(s, i + 1, dict);
					if (cur.size() != 0) {
						for (String word : cur) {
							ans.add(collect + " " + word);
						}
					}
				}
			}
		}

		dp.put(position, ans);
		return ans;
	}

	public List<String> wordBreak(String s, List<String> wordDict) {
		var dict = new HashSet<String>(wordDict);
		var ans = solve(s, 0, dict);
		System.out.println(dp);
		return ans;
	}

	public static void main(String[] args) {
		var ans = new WordBreakII().wordBreak("pineapplepenapple",
				List.of("apple", "pen", "applepen", "pine", "pineapple"));
		System.out.println(ans);
	}
}
