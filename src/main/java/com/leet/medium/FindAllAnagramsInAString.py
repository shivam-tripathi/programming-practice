from typing import List

'''
438. Find All Anagrams in a String

https://leetcode.com/problems/find-all-anagrams-in-a-string/

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may
return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:
    1 <= s.length, p.length <= 3 * 104
    s and p consist of lowercase English letters.
'''

class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        idx = lambda ch: ord(ch)-97
        counts, running = [0] * 26, [0] * 26
        for ch in p:
            counts[idx(ch)] += 1

        ans = []
        left, right = 0, 0
        while right < len(s):
            ch = s[right]
            right += 1
            ch_idx = idx(ch)
            running[ch_idx] += 1
            while left < right and running[ch_idx] > counts[ch_idx]:
                running[idx(s[left])] -= 1
                left += 1
            if running == counts:
                ans.append(left)

        return ans
