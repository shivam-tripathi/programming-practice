package com.leet.easy;

import com.leet.common.TreeNode;

public class FindModeInBinarySearchTree {
  public int handleValue(int val) {
    return 0;
  }

  public int inorder(TreeNode root) {
    var node = root;
    while (node != null) {
      if (node.left == null) {
        handleValue(node.val);
        node = node.right;
      }
    }
    return 0;
  }
}
