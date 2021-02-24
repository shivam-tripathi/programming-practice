package com.leet.medium;

/**
 * 524. Longest Word in Dictionary through Deleting
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * Medium
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some
 * characters of the given string. If there are more than one possible results, return the longest word with the
 * smallest lexicographical order. If there is no possible result, return the empty string.
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */

import java.util.Arrays;
import java.util.List;

public class LongestWordInDictionaryThroughDeleting {
  // Method 1: Match each character in base String against each string 22ms
  public static String findLongestWord(String s, List<String> d) {
    int[] pointer = new int[d.size()];
    String ans = "";
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      for (int j = 0; j < d.size(); j++) {
        String cur = d.get(j);
        int curPointer = pointer[j];
        if (curPointer < cur.length() && ch == cur.charAt(curPointer)) {
          pointer[j]++;
          if (pointer[j] == cur.length()) {
            if (
                    cur.length() > ans.length() || (cur.length() == ans.length() && d.get(j).compareTo(ans) < 0)
            ) {
              ans = d.get(j);
            }
          }
        }
      }
    }
    return ans;
  }

  // Method 2: Match each string against base string. Index of makes it very fast for some reason. 6ms
  public static String findLongestWord_2(String s, List<String> d) {
    String longest = "";
    for (String word : d) {
      if (word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
        int start = -1;
        for (int i = 0; i < word.length(); i++) {
          start = s.indexOf(word.charAt(i), start + 1);
          if (start < 0) break;
        }
        if (start >= 0) longest = word;
      }
    }
    return longest;
  }

  // Method 3: Same as method 2, just without indexOf. Regular charAt is pretty slow. 22ms. Same as method 1.
  // If I convert base string to char array and then run comparisons, it becomes pretty fast. But not as fast as
  // indexOf. 9ms

  public static void main(String[] args) {
    String s = "";
    String[] d = new String[]{};
    System.out.println(findLongestWord(s, Arrays.asList(d)));
  }
}
