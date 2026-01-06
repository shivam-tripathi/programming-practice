package com.leet.easy;

/*
589. N-ary Tree Preorder Traversal
https://leetcode.com/problems/n-ary-tree-preorder-traversal/
Easy
Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the
null value (See examples)

Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]

Example 2:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]

Constraints:
    The number of nodes in the tree is in the range [0, 104].
    0 <= Node.val <= 104
    The height of the n-ary tree is less than or equal to 1000.

Follow up: Recursive solution is trivial, could you do it iteratively?
* */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NaryTreePreorderTraversal {
  static class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  public void preorderUtil(Node node, List<Integer> ans) {
    if (node == null) return;
    ans.add(node.val);
    for (int i = 0; i < node.children.size(); i++) {
      preorderUtil(node.children.get(i), ans);
    }
  }

  public void preorderUtilIter(Node node, List<Integer> ans) {
    var stack = new Stack<Node>();
    stack.add(node);
    while(!stack.isEmpty()) {
      Node top = stack.pop();
      ans.add(top.val);
      for (int i = top.children.size() - 1; i >= 0; i--) {
        stack.add(top.children.get(i));
      }
    }
  }

  public List<Integer> preorder(Node root) {
    List<Integer> ans = new ArrayList<Integer>();
    preorderUtil(root, ans);
    return ans;
  }
}
