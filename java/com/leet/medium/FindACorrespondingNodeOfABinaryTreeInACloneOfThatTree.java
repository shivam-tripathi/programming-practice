package com.leet.medium;

import com.leet.common.TreeNode;

public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
  public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
    if (original == null || original == target) {
      return cloned;
    }
    TreeNode node = getTargetCopy(original.left, cloned.left, target);
    if (node == null) {
      node = getTargetCopy(original.right, cloned.right, target);
    }
    return node;
  }
}
