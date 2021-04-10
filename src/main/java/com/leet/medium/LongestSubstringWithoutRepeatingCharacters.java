package com.leet.medium;

/**
3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/
Medium

Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Example 4:
Input: s = ""
Output: 0

Constraints:
    0 <= s.length <= 5 * 104
    s consists of English letters, digits, symbols and spaces.
 */

import java.util.*;
import java.io.*;

class LongestSubstringWithoutRepeatingCharacters {
  public static int lengthOfLongestSubstring(String s) {
    int size = s.length();
    HashMap<Character, Integer> map = new HashMap<>();
    int l = 0, r = 0, maxSize = 0;
    for (; r < size; r++) {
      Integer lastSeen = map.get(s.charAt(r));
      if (lastSeen != null && lastSeen.compareTo(l) >= 0) {
        maxSize = Math.max(r - l, maxSize);
        l = lastSeen + 1;
      }
      map.put(s.charAt(r), r);
    }
    maxSize = Math.max(r - l, maxSize);
    return maxSize;
  }

  public static int lengthOfLongestSubstringArr(String s) {
    int[] lastFoundAt = new int[128]; // all default to 0; save 1 indexed positions
    int count = 0, maxcount = 0, left = 0, right = 0;
    for (right = 0; right < s.length(); right++) {
      char charIdx = s.charAt(right);
      int lastFoundAtIndex = lastFoundAt[charIdx] - 1; // Subtract 1 as it is one indexed
      if (lastFoundAtIndex >= left) {
        maxcount = Math.max(maxcount, right - left);
        left = lastFoundAtIndex + 1;
      }
      lastFoundAt[charIdx] = right + 1; // Save 1 indexed
    }
    maxcount = Math.max(maxcount, right - left);
    return maxcount;
  }

  public int lengthOfLongestSubstring_2(String A) {
      int ans = 0, cur = 0;
      int[] occ = new int[256];
      int i = 0;
      for (char ch : A.toCharArray()) {
          if (occ[ch] != 0 && occ[ch] >= i - cur) {
              ans = Math.max(ans, cur);
              cur = i - occ[ch] + 1;
          } else {
              cur++;
          }
          occ[ch] = i + 1;
          i++;
      }
      return Math.max(ans, cur);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
//    String s = obj.readLine();
    String s = " ";
    System.out.printf("%d%n", lengthOfLongestSubstring(s));
    System.out.printf("%d%n", lengthOfLongestSubstringArr(s));
  }
}

/* Notice:
 * lastSeen >= l (equal to part)
 * After loop action
 * Correct initialization of initial values (don't be lazy)
 */
