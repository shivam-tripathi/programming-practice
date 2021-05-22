package com.leet.medium;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 * Medium
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 * Example 1:
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 * Example 2:
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 * Example 3:
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 * Constraints:
 *     1 <= s.length <= 105
 *     s contains only lowercase English letters.
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
  public int minDeletions(String s) {
    int[] chars = new int[26];
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      chars[ch - 'a']++;
    }

    Map<Integer, Integer> count = new HashMap<>();
    int max = 0;

    for (int i = 0; i < 26; i++) {
      max = Math.max(max, chars[i]);
      count.merge(chars[i], 1, Integer::sum);
    }

    int ans = 0;
    for (int i = max; i > 0; i--) {
      int ccount = count.getOrDefault(i, 0);
      if (ccount > 1) {
        ans += ccount - 1;
        count.merge(i - 1, ccount - 1, Integer::sum);
      }
    }

    return ans;
  }
}
