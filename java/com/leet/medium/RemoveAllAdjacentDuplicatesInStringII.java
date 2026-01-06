package com.leet.medium;

/**
 * 1209. Remove All Adjacent Duplicates in String II Medium
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k
 * adjacent and equal letters from s and removing them, causing the left and the right side of the
 * deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed
 * that the answer is unique.
 *
 * Example 1:
 * Input: s = "abcd", k = 2 Output: "abcd" Explanation: There's nothing to delete.
 *
 * Example 2:
 * Input: s = "deeedbbcccbdaa", k = 3 Output: "aa" Explanation: First delete "eee" and "ccc", get
 * "ddbbbdaa" Then delete "bbb", get "dddaa" Finally delete "ddd", get "aa"
 *
 * Example 3:
 * Input: s = "pbbcggttciiippooaais", k = 2 Output: "ps"
 *
 * Constraints:
 * 1 <= s.length <= 105 2 <= k <= 104 s only contains lower case English letters.
 */

// Another approach is to use two pointer - one tracks ans string and other tracks current position
// in iteration
// Another approach is to use string builder as a stack - push elements there and keep count in
// another array. Similar
// to one below
public class RemoveAllAdjacentDuplicatesInStringII {
  public String removeDuplicates(String s, int k) {
    // Maximum stack size can be length of string (all chars are unique)
    // Stack contains two things - character and count for this character
    int[][] stack = new int[s.length()][2];

    // Size of the stack - initially zero
    int size = 0;

    // Iterate over all characters in string
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      // If this is the first character or if stack top doesn't match this character - it is a new
      // unique character
      // We insert it into the stack with a count = 1
      if (size == 0 || stack[size - 1][0] != ch) {
        stack[size++] = new int[] {ch, 1};
      } else {
        // If there are some elements in the stack and top of the stack = cur character, then we
        // check:
        //  1. If adding this new character would increase count = k. In this case we remove the top
        // element from stack
        //  2. If adding this new character would keep count != k. In this case we increase the
        // count of the stack top.
        if (stack[size - 1][1] + 1 == k) {
          size--;
        } else {
          stack[size - 1][1]++;
        }
      }
    }

    // Finally, we collect all the elements in the stack. We repeat the chars by count.
    var sb = new StringBuilder();
    for (int i = 0; i < size; i++) {
      sb.append(String.valueOf((char) stack[i][0]).repeat(Math.max(0, stack[i][1])));
    }

    return sb.toString();
  }
}
