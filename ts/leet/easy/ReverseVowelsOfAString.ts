/*
345. Reverse Vowels of a String
https://leetcode.com/problems/reverse-vowels-of-a-string/description/
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more
than once.

Example 1:
Input: s = "hello"
Output: "holle"

Example 2:
Input: s = "leetcode"
Output: "leotcede"

Constraints:
	1 <= s.length <= 3 * 105
	s consist of printable ASCII characters
 */

const vowels: Record<string, boolean> = {
  a: true,
  A: true,
  e: true,
  E: true,
  i: true,
  I: true,
  o: true,
  O: true,
  u: true,
  U: true,
};

function reverseVowels(s: string): string {
  const chars = s.split("");
  let begin = 0, end = s.length - 1;
  while (begin < end) {
    while (begin < end && vowels[chars[begin]] !== true) {
      begin++;
    }
    while (end > begin && vowels[chars[end]] !== true) {
      end--;
    }
    const tmp = s[begin];
    chars[begin++] = s[end];
    chars[end--] = tmp;
  }

  return chars.join("");
}
