/**
767. Reorganize String
https://leetcode.com/problems/reorganize-string/
Medium

Given a string s, rearrange the characters of s so that any two adjacent characters are not the
same. Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

Constraints:
		1 <= s.length <= 500
		s consists of lowercase English letters.
 */

function reorganizeString(s: string): string {
	// Frequency map
	const freq = s.split('').reduce((acc, elem) => {
		acc[elem] = acc[elem] ? acc[elem] + 1 : 1;
		return acc;
	}, {} as Record<string, number>);

	// all unique characters
	const keys = Object.keys(freq);

	// Helper method to generate the next character
	const next = (except: string) => {
		const ans = keys.reduce((acc, key) => {
			if (key === except || freq[key] === 0 || freq[acc] > freq[key]) {
				return acc;
			}
			return key;
		}, null);
		if (ans == null) {
			return null;
		}
		freq[ans]--;
		return ans;
	};

	const ans: string[] = [];

	for (let i = 0; i < s.length; i++) {
		const char = next(ans[i - 1]);
		if (char === null) {
			return "";
		}
		ans.push(char);
	}

	return ans.join('');
};
