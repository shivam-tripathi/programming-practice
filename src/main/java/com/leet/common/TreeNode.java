package com.leet.common;

import java.util.Arrays;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public static TreeNode construct(int index, Integer[] nodes) {
    if (index >= nodes.length || nodes[index] == null) {
      return null;
    }
    TreeNode node = new TreeNode(nodes[index]);
    node.left = construct(index * 2 + 1, nodes);
    node.right = construct(index * 2 + 2, nodes);
    return node;
  }

  public static TreeNode create(int[] nodes) {
    return construct(0, Arrays.stream(nodes).boxed().toArray(Integer[]::new));
  }

  private String stringify(TreeNode node) {
    if (node == null) {
      return null;
    }
    System.out.println(node.val);
    return node.val + " -> {L: [" + stringify(node.left) + "], R: [" + stringify(node.right) + "]}";
  }

  public String toString() {
    return stringify(this);
  }
}
