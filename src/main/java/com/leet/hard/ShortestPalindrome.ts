/**
214. Shortest Palindrome
https://leetcode.com/problems/shortest-palindrome/
Hard

You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: s = "abcd"
Output: "dcbabcd"

Constraints:
		0 <= s.length <= 5 * 104
		s consists of lowercase English letters only.
 */


// we verify if the given string is a palindrome between these ranges
function isPalindrome(s: string, start: number, end: number) {
	while (start < end) {
		if (s[start] !== s[end]) {
			return false;
		}
		start++;
		end--;
	}
	return true;
}

// We find the farthest distance from the start which can form a palindrome
function longestPalindromeFromStart(s: string) {
	for (let i = s.length - 1; i >= 0; i--) {
		if (isPalindrome(s, 0, i)) {
			return i;
		}
	}
	return 0;
}

function shortestPalindrome(s: string): string {
	const index = longestPalindromeFromStart(s);
	let ans = "";
	for (let i = s.length - 1; i > index; i--) {
		ans += s[i];
	}
	ans += s;
	return ans;
};

// Ideal solution would require variation of KMP algorithm
const ans = shortestPalindrome("abcd");
console.log(ans);
