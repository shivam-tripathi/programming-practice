package com.leet.medium;

/*
Imagine search space to be infinite straight line and each binary tree cutting it into two halves. With each subsequent
cut, the search space is effectively divided into a (min, max) range.

Because it is difficult to represent min (-inf) and max (+inf) at the start, we use null as a placeholder. If
min == null or max == null, it represent -+ inf respectively.
 */

import com.leet.common.TreeNode;

public class ValidateBinaryTree {
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

