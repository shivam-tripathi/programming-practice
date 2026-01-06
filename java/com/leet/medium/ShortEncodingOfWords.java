package com.leet.medium;

/**
 * 820. Short Encoding of Words
 * https://leetcode.com/problems/short-encoding-of-words/
 * Medium
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#'
 * character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 * Example 1:
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 * Constraints:
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 */

import java.util.Arrays;

public class ShortEncodingOfWords {
  // Check if lastIndexOf is suffix for all pairs, remove invalid ones
  public static int minimumLengthEncoding(String[] words) {
    boolean[] invalid = new boolean[words.length];
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words.length; j++) {
        if (i == j || invalid[j]) continue;
        int idx = words[j].lastIndexOf(words[i]);
        if (idx != -1 && idx == words[j].length() - words[i].length()) {
          invalid[i] = true;
          break;
        }
      }
    }

    int ans = 0;
    for (int i = 0; i < invalid.length; i++) {
      if (!invalid[i]) {
        ans = ans + words[i].length() + 1;
      }
    }
    return ans;
  }

  // Trie based solution. Check if the node is leaf, if yes add it answer. Delete all other answers in the path.
  static class Trie {
    int ans = 0;

    static class Node {
      Node[] subNodes = new Node[26];
      int depth;
      boolean isNotLeaf;
    }

    Node root = new Node();

    void insert(String word) {
      Node node = root;
      int toSubIfLeaf = 0;
      for (int i = word.length() - 1; i >= 0; i--) {
        int idx = word.charAt(i) - 'a';
        if (node.subNodes[idx] == null) {
          node.subNodes[idx] = new Node();
        } else {
          // remove from ans any previous string in this path; but only if this is the leaf.
          toSubIfLeaf += node.subNodes[idx].depth;
          node.subNodes[idx].depth = 0;
        }
        // This is not a leaf node
        node.isNotLeaf = true;
        node = node.subNodes[idx];
      }

      if (!node.isNotLeaf) {
        node.depth = word.length() + 1;
        ans += node.depth - toSubIfLeaf;
      }
    }
  }

  public static int minimumLengthEncoding_2(String[] words) {
    Trie trie = new Trie();
    Arrays.stream(words).forEach(trie::insert);
    for (String word : words) {
      trie.insert(word);
    }
    return trie.ans;
  }

  public static void main(String[] args) {
    System.out.println(minimumLengthEncoding(new String[]{"charge", "rge", "arge", "harge", "boolean", "bool", "ool"}));
    System.out.println(minimumLengthEncoding(new String[]{"time", "metoo", "toobell", "bell"}));
    System.out.println(minimumLengthEncoding(new String[]{"me", "time"}));
  }
}
