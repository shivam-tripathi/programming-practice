package com.leet.medium;

/*
Imagine search space to be infinite straight line and each binary tree cutting it into two halves. With each subsequent
cut, the search space is effectively divided into a (min, max) range.

Because it is difficult to represent min (-inf) and max (+inf) at the start, we use null as a placeholder. If
min == null or max == null, it represent -+ inf respectively.
 */

public class ValidateBinaryTree {
  static public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    static TreeNode construct(int index, Integer[] nodes) {
      if (index >= nodes.length || nodes[index] == null) {
        return null;
      }
      TreeNode node = new TreeNode(nodes[index]);
      node.left = construct(index * 2 + 1, nodes);
      node.right = construct(index * 2 + 2, nodes);
//      System.out.printf("%d -> %d, %d\n", node.val, node.left != null ? node.left.val : null, node.right != null ? node.right.val : null);
      return node;
    }
  }

  public static boolean isValidBST(TreeNode node, Integer min, Integer max) {
    if (node == null) {
      return true;
    }

    boolean isValidNode = (min == null || min.compareTo(node.val) < 0) && (max == null || max.compareTo(node.val) > 0);
    if (isValidNode) {
      return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    return false;
  }

  public static boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  public static void main(String[] args) {
    TreeNode root = TreeNode.construct(0, new Integer[]{2,1,3});
    System.out.println(isValidBST(root));
  }
}

