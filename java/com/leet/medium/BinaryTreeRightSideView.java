package com.leet.medium;

/**
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the
 * nodes you can see ordered from top to bottom.
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 100].
 *     -100 <= Node.val <= 100
 */

import com.leet.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
  static int rightSideView(TreeNode node, List<Integer> list, int depth, int depthFrom) {
    if (node == null) return depthFrom;
    if (depth > depthFrom) {
      list.add(node.val);
      depthFrom++;
    }
    depthFrom = rightSideView(node.right, list, depth + 1, depthFrom);
    return rightSideView(node.left, list, depth + 1, depthFrom);
  }

  // DFS
  public static List<Integer> rightSideView(TreeNode root) {
    var l = new ArrayList<Integer>();
    rightSideView(root, l, 1, 0);
    return l;
  }

  // BFS
  public static List<Integer> rightSideView_2(TreeNode root) {
    var list = new ArrayList<Integer>();
    if (root == null) return list;
    Queue<Object[]> queue = new LinkedList<>();
    queue.offer(new Object[] {root, 1});
    while (!queue.isEmpty()) {
      Object[] top = queue.poll();
      TreeNode node = (TreeNode) top[0];
      int depth = (Integer) top[1];
      if (depth > list.size()) {
        list.add(node.val);
      }
      if (node.right != null) {
        queue.add(new Object[] {node.right, depth+1});
      }
      if(node.left != null) {
        queue.add(new Object[] {node.left, depth+1});
      }
    }
    return list;
  }

  public static void main(String[] args) {
    // [1,2,3,null,5,null,4]
    TreeNode root = TreeNode.construct(0, new Integer[]{1,2,3,null,5,null,4});
    System.out.println(rightSideView(root));
  }
}
