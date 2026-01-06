package com.leet.medium;

/**
 * 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 * Medium
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 *     1 <= strs.length <= 104
 *     0 <= strs[i].length <= 100
 *     strs[i] consists of lower-case English letters.
 */

import java.util.*;

public class GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, Integer> map = new HashMap<>(); // saves index in ans corresponding to a given key
    List<List<String>> ans = new ArrayList<>(); // saves final ans

    for (String str : strs) {
      // Counting sort
      char[] counts = new char[26];
      for (char ch : str.toCharArray()) counts[ch - 'a']++;

      String key = new String(counts); // smart: Calculate key by converting char array to String directly

      // If key is missing, add a new row to ans and add index to map
      if (!map.containsKey(key)) {
        map.put(key, ans.size());
        ans.add(new ArrayList<>());
      }

      ans.get(map.get(key)).add(str);
    }

    return ans;
  }
}
