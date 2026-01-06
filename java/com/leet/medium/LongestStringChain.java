package com.leet.medium;

import java.util.*;

public class LongestStringChain {
  static class Node {
    Node[] subNodes = new Node[26];
  }

  static class Trie {
    Node root = new Node();

    void insert(String word) {
      Node node = root;
      for (char ch : word.toCharArray()) {
        if (node.subNodes[ch - 'a'] == null) {
          node.subNodes[ch - 'a'] = new Node();
        }
        node = node.subNodes[ch - 'a'];
      }
    }
  }

  public static int longestStrChain(String[] words) {
    Arrays.sort(words, Comparator.comparingInt(String::length));
    int runningLength = words[words.length - 1].length();
//    for (int i = words.length - 1; )
    return 0;
  }

  public static void main(String[] args) {
    var words = new String[]{"a", "b", "ba", "bca", "bda", "bdca"};
//    "a","b","ab","bac"
    System.out.println(longestStrChain(words));
  }
}
