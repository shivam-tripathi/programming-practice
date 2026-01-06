package com.leet.easy;

/**
 * 653. Two Sum IV - Input is a BST
 *
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 *
 * Easy
 *
 * Given the root of a Binary Search Tree and a target number k, return true if
 * there exist two elements in the BST such that their sum is equal to the given
 * target.
 *
 * Example 1: Input: root = [5,3,6,2,4,null,7], k = 9 Output: true
 *
 * Example 2: Input: root = [5,3,6,2,4,null,7], k = 28 Output: false
 *
 * Example 3: Input: root = [2,1,3], k = 4 Output: true
 *
 * Example 4: Input: root = [2,1,3], k = 1 Output: false
 *
 * Example 5: Input: root = [2,1,3], k = 3 Output: true
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104]. -104 <= Node.val <=
 * 104 root is guaranteed to be a valid binary search tree. -105 <= k <= 105
 *
 *
 * This solutions is log n space complexity
 */

import java.util.List;
import java.util.Stack;

import com.leet.common.TreeNode;

enum Traversal {
  INORDER, REVERSE_INORDER,
}

class BinarySearchIterator {
  private TreeNode node;
  private TreeNode nextNode;
  private Stack<TreeNode> stack;
  private Traversal traversal;

  BinarySearchIterator(TreeNode node, Traversal traversal) {
    this.node = node;
    this.traversal = traversal;
    this.stack = new Stack<TreeNode>();
  }

  private TreeNode next() {
    if (node != null || stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        if (traversal == Traversal.INORDER) {
          node = node.left;
        } else if (traversal == Traversal.REVERSE_INORDER) {
          node = node.right;
        }
      }
    }
    TreeNode top = stack.pop();
    if (traversal == Traversal.INORDER) {
      node = top.right;
    } else if (traversal == Traversal.REVERSE_INORDER) {
      node = top.left;
    }

    return top;
  }

  TreeNode peek() {
    if (nextNode == null) {
      nextNode = next();
    }
    return nextNode;
  }

  void pop() {
    nextNode = next();
  }
}

public class TwoSumIVInputIsABST {

  public void collect(TreeNode node, List<Integer> nums) {
    if (node == null) {
      return;
    }
    collect(node.left, nums);
    nums.add(node.val);
    collect(node.right, nums);
  }

  public boolean findTarget(TreeNode root, int k) {
    BinarySearchIterator low = new BinarySearchIterator(root, Traversal.INORDER);
    BinarySearchIterator high = new BinarySearchIterator(root, Traversal.REVERSE_INORDER);
    while (true) {
      TreeNode nodeA = low.peek();
      TreeNode nodeB = high.peek();
      if (nodeA.val >= nodeB.val) {
        break;
      }
      int a = nodeA.val, b = nodeB.val;
      if (a + b == k) {
        return true;
      }
      if (a + b < k) {
        low.pop();
      }
      if (a + b > k) {
        high.pop();
      }
    }

    return false;
  }
}
