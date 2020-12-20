package com.leet.medium;

import com.leet.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public class BinarySearchTreeIterator {
  interface BSTIterator {
    int next();
    boolean hasNext();
  }
  static class BSTIteratorQueue implements BSTIterator {
    Queue<TreeNode> nodes = new LinkedList<>();

    public BSTIteratorQueue(TreeNode root) {
      insert(root);
    }

    void insert(TreeNode node) {
      if (node == null) {
        return;
      }
      insert(node.left);
      nodes.add(node);
      insert(node.right);
    }

    public int next() {
      return nodes.remove().val;
    }

    public boolean hasNext() {
      return !nodes.isEmpty();
    }
  }
  static class BSTIteratorStack implements BSTIterator {
    Stack<TreeNode> stack = new Stack<>();

    public BSTIteratorStack(TreeNode root) {
      insert(root);
    }

    void insert(TreeNode node) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    public int next() {
      TreeNode top = stack.pop();
      insert(top.right);
      return top.val;
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }
  }
  public static void main(String[] args) {
    TreeNode root = TreeNode.construct(0, new Integer[]{7, 3, 15, null, null, 9, 20});

    Consumer<BSTIterator> consumer = (iterator) -> {
      while (iterator.hasNext()) {
        System.out.printf("%d ", iterator.next());
      }
      System.out.println();
    };

    consumer.accept(new BSTIteratorQueue(root));
    consumer.accept(new BSTIteratorStack(root));
  }
}


// - Method using queue basically computes and stores the entire inorder traversal and then iterates over them
// - Method using stack computes inorder lazily. At any node, we only need to know the chain of the parents required to
// reach the node so we can backtrack to previous state. So we only traverse left, keeping parents in stack.
// We traverse through all left nodes till we hit leaf. Once done, we know that for any node, we have
// computed it's left subtree inorder. All we need to do is compute right subtree inorder. So we repeat the process.

// Complexity for hasNext() and next() is O(1) amortized.
