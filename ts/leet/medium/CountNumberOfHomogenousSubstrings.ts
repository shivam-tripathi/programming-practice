/**
1759. Count Number of Homogenous Substrings
https://leetcode.com/problems/count-number-of-homogenous-substrings/
Medium

Given a string s, return the number of homogenous substrings of s. Since the answer may be too
large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.

Example 2:
Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".

Example 3:
Input: s = "zzzzz"
Output: 15

Constraints:
    1 <= s.length <= 105
    s consists of lowercase letters.
 */

function countHomogenous(s: string): number {
  let index = 0;
  let ans = 0;
  const mod = 1e9 + 7;
  while (index < s.length) {
    let count = 1;
    index += 1;
    while (index < s.length && s[index] === s[index - 1]) {
      index += 1;
      count += 1;
    }
    ans = (ans % mod + (count * (count + 1) / 2) % mod) % mod;
    count = 0;
  }
  return ans;
}
