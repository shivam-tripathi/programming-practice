package com.codemonk.arraysAndStrings;

/**
 * Problem
 * https://www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/minimum-and-xor-or-6a05bbd4/
 * You are given an array \(A\) of \(N\) integers. Determine the minimum value of the following expression for all
 * valid \(i,j\):
 * \((A_i \ and \ A_j) \ xor \ (A_i \ or \ A_j)\), where \(i \ne j\).
 * Input format
 * First line: A single integer \(T\) denoting the number of test cases
 * For each test case:
 * First line contains a single integer \(N\), denoting the size of the array
 * Second line contains \(N\) space-separated integers \(A_1,A_2,...,A_n\)
 * Output format
 * For each test case, print a single line containing one integer that represents the minimum value of the given
 * expression
 * Constraints
 * \(1 \le T \le 10^3\\ 1 \le N \le 10^5\\ 0 \le A_i \le 10^9\)
 * Note: Sum of \(N\) overall test cases does not exceed \(10^6\)
 */

import java.util.Arrays;
import java.io.*;

// XOR is monotonic in the absolute difference between numbers. (If the numbers are identical, then the XOR is zero).
// If you ignore the possibility of negative numbers and write the numbers in binary, it becomes obvious.
public class MinimumAndXorOr {
  // Trie based solution fails due to strict time constraints
  static class Trie {
    static class Node {
      Node[] nodes = new Node[2];
      int leaf;
    }

    Node root = new Node();

    void insert(int num) {
      Node node = root;
      for (int i = 31; i >= 0; i--) {
        int bit = num >>> i & 1;
        if (node.nodes[bit] == null) node.nodes[bit] = new Node();
        node = node.nodes[bit];
      }
      node.leaf = num;
    }

    int match(int num) {
      Node node = root;
      for (int i = 31; i >= 0; i--) {
        int bit = num >>> i & 1;
        if (node.nodes[bit] != null) {
          node = node.nodes[bit];
        } else {
          node = node.nodes[bit ^ 1];
        }
      }
      return num ^ node.leaf;
    }
  }

  static int minimumXOR(int[] arr) {
    Trie trie = new Trie();
    trie.insert(arr[0]);
    int ans = Integer.MAX_VALUE;
    for (int i = 1; i < arr.length; i++) {
      ans = Math.min(ans, trie.match(arr[i]));
      trie.insert(arr[i]);
    }
    return ans;
  }


  // Solution to sort and check the adjacent pairs works
  public static void main(String[] args) throws IOException {
    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(obj.readLine());
    while (t-- > 0) {
      int size = Integer.parseInt(obj.readLine());
      int[] arr = new int[size];
      String[] ele = obj.readLine().split(" ");
      for (int i = 0; i < size; i++) {
        arr[i] = Integer.parseInt(ele[i]);
      }
      Arrays.sort(arr);
      int ans = Integer.MAX_VALUE;
      for (int i = 0; i < arr.length - 1; i++) {
        ans = Math.min(ans, arr[i] ^ arr[i + 1]);
      }
      System.out.println(ans);
    }
  }
}
