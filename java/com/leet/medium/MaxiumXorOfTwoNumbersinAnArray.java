package com.leet.medium;

import lombok.ToString;

public class MaxiumXorOfTwoNumbersinAnArray {
  @ToString
  static class Node {
    Node[] nodes = new Node[2];
    int leaf;
    boolean hasLeaf;
  }

  static class Trie {
    int count = 0;
    Node root = new Node();

    int insert(int num) {
      count++;
      int max = findMax(root, 0, num);
      int xor = num ^ max;
      insert(root, 0, num, num);
      return count == 1 ? 0 : xor;
    }

    int findMax(Node node, int idx, int num) {
      int bit = (num & (1<<31)) == 0 ? 0 : 1;
      System.out.println(bit);
      if (idx == 31) {
        if (node.nodes[bit ^ 1] != null && node.nodes[bit ^ 1].hasLeaf) {
          return node.nodes[bit ^ 1].leaf;
        }
        return node.nodes[bit].leaf;
      }
      if (node.nodes[bit ^ 1] != null) {
        return findMax(node.nodes[bit ^ 1], idx + 1, num << 1);
      } else if (node.nodes[bit] != null) {
        return findMax(node.nodes[bit], idx + 1, num << 1);
      } else {
        return 0;
      }
    }

    void insert(Node node, int idx, int cur, int num) {
      int bit = (cur & (1<<31)) == 0 ? 0 : 1;
      System.out.println(bit);
      if (node.nodes[bit] == null) {
        node.nodes[bit] = new Node();
      }
      if (idx == 31) {
        node.nodes[bit].hasLeaf = true;
        node.nodes[bit].leaf = num;
      } else {
        insert(node.nodes[bit], idx + 1, cur << 1, num);
      }
    }
  }

  public static int findMaximumXOR(int[] nums) {
    Trie trie = new Trie();
    int ans = 0;
    for (int num : nums) {
      ans = Math.max(ans, trie.insert(num));
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{4,6,7};
    System.out.println(findMaximumXOR(nums));
  }
}
