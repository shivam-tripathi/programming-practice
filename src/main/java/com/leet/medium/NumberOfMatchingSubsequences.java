package com.leet.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * https://leetcode.com/problems/number-of-matching-subsequences/
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *     For example, "ace" is a subsequence of "abcde".
 * Example 1:
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 * Constraints:
 *     1 <= s.length <= 5 * 104
 *     1 <= words.length <= 5000
 *     1 <= words[i].length <= 50
 *     s and words[i] consist of only lowercase English letters
 */

public class NumberOfMatchingSubsequences {
  int binarySearchUpperBound(List<Integer> arr, int target, int low) {
    // Binary search is one of the optimisation, we can do it linearly (it was giving better performance as size of string is just 1e4)
    // if this character hasn't been recorded or target is already out of bounds or this character has been exhausted
    if (arr.size() == 0 || target > arr.get(arr.size() - 1) || low >= arr.size()) {
      return arr.size();
    }
    int high = arr.size() - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (arr.get(mid) > target) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

  public int numMatchingSubseq(String s, String[] words) {
    // First save the occurrences by character
    // eg: abbcda -> [a:[0,5], b:[1,2], c:[3], d:[4]]
    List<List<Integer>> positions = new ArrayList<>();
    for (int i = 0; i < 27; i++) positions.add(new ArrayList<>());
    for (int i = 0; i < s.length(); i++) {
      positions.get(s.charAt(i) - 'a').add(i);
    }

    // Store the answer
    int count = 0;
    for (String word : words) {
      int lastIdx = -1; // last processed index in s (we begin with -1 so we can select char at 0 if required)
      int done = 0; // how many characters in current word has been done. If finally done < word.length() then it is not the answer.
      // store the last recorded position of this particular character. If we have picked character from a previous
      // position, we may not pick it again.
      int[] nextCharIdx = new int[27];
      for (int i = 0; i < word.length(); i++) {
        int idx = word.charAt(i) - 'a';
        var charPositions = positions.get(idx);
        // this can be a loop: find the valid index (> lastIdx) in char positions array (lastRecordedPos for this char,
        // len(positions for this char)) (exclusive at both ends). If exceeds the size, it is invalid.
        int charIdx = binarySearchUpperBound(charPositions, lastIdx, nextCharIdx[idx]);
        if (charIdx >= charPositions.size()) break;
        // If it is valid charIdx, then update last idx and continue.
        lastIdx = charPositions.get(charIdx);
        // Update next candidate char idx
        nextCharIdx[idx] = charIdx + 1;
        done++;
      }
      if (done == word.length()) {
        count++;
      }
    }
    return count;
  }
}
