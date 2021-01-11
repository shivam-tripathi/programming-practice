package com.leet.common;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;
  public TreeNode() {}
  public TreeNode(int val) { this.val = val; }
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
    // System.out.printf("%d -> %d, %d\n", node.val, node.left != null ? node.left.val : null, node.right != null ? node.right.val : null);
    return node;
  }
}
