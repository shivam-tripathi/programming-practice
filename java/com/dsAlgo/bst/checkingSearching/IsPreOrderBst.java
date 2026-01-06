package com.dsAlgo.bst.checkingSearching; /**
 * Check if a given array can represent Preorder Traversal of Binary Search Tree.
 * Given an array of numbers, return true if given array can represent preorder
 * traversal of a Binary Search Tree, else return false.
 * Expected time complexity is O(n).
 * <p>
 * All methods used to contruct a BST from preorder traversal can be used.
 * The stack based method is most optimized and simple used in contruction of BST from
 * preorder. Here it has been used with slight tweaking.
 * Please take a note of stack based NGE solution. It has important concepts.
 */

import java.util.*;

class IsPreOrderBstSolution {
  static class TNode {
    static class Node {
      int item;
      Node left;
      Node right;

      Node(int item) {
        this.item = item;
      }
    }
    int index;
    Node node;

    TNode(int index, Node node) {
      this.index = index;
      this.node = node;
    }
  }

  class Bst {
    TNode.Node root;

    Bst() {
    }

    Bst(TNode.Node node) {
      root = node;
    }

    void insert(TNode.Node node, int item) {
      if (Integer.compare(item, node.item) < 0) {
        if (node.left != null) {
          insert(node.left, item);
        } else {
          node.left = new TNode.Node(item);
        }
      } else {
        if (node.right != null) {
          insert(node.right, item);
        } else {
          node.right = new TNode.Node(item);
        }
      }
    }

    void insert(int item) {
      if (root == null) {
        root = new TNode.Node(item);
      } else {
        insert(root, item);
      }
    }

    void preOrder(TNode.Node node, ArrayList<Integer> pre) {
      if (node != null) {
        pre.add(node.item);
        preOrder(node.left, pre);
        preOrder(node.right, pre);
      }
    }

    ArrayList<Integer> preOrder() {
      ArrayList<Integer> pre = new ArrayList<>();
      preOrder(root, pre);
      return pre;
    }
  }


  TNode preOrderBySpaceSplit(ArrayList<Integer> pre, int index, int min, int max) {
    if (index >= pre.size() || pre.get(index) <= min || pre.get(index) >= max) {
      return new TNode(index, null);
    }

    int item = pre.get(index++);
    TNode leftTNode = preOrderBySpaceSplit(pre, index, min, item);
    index = leftTNode.index;
    TNode rightTNode = preOrderBySpaceSplit(pre, index, item - 1, max);
    index = rightTNode.index;

    TNode.Node node = new TNode.Node(item);
    node.left = leftTNode.node;
    node.right = rightTNode.node;
    return new TNode(index, node);
  }

  boolean isPreorderBstSpaceSplit(ArrayList<Integer> pre) {
    TNode rootTNode = preOrderBySpaceSplit(pre, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    Bst tree = new Bst(rootTNode.node);
    ArrayList<Integer> generatedPreOrder = tree.preOrder();
    if (generatedPreOrder == null || generatedPreOrder.size() != pre.size()) {
      return false;
    }

    for (int i = 0; i < pre.size(); i++) {
      if (!pre.get(i).equals(generatedPreOrder.get(i))) {
        return false;
      }
    }
    return true;
  }

  // Notice that left item in preorder is always present next to the centre item.
  // However, right item is present after the entire left subtree.
  // Suppose we start from first element. It is the root. We check the next item. If it is less than centre - we
  // just push it to the left of centre. We continue this till we find an item which is bigger than the last item we
  // added as left node. This is the item which will be added as right node for one of the nodes (centre node for
  // this right node) in the entire left subtree. How do we find this node? Definitely the parent of this
  // centre node would be bigger than this to be right node. Once we hit such a node, we can assign this element as
  // it's right subnode. We keep repeating the process to build a tree.

  // For this problem - to find the node whose parent is bigger than right node - we use a stack. Also, as it's
  // a binary tree, once right side has been handled, we can remove the node from the stack. The condition check (which
  // will make it a BST) is the item to be inserted now is greater than the last popped node (it was the centre for the
  // present right sub tree). We are basically shifting the min window range. When we find an element greater than the
  // present window range, we shift it to new centre so the element could be added. The beginning value of last popped
  // node is -inf.
  boolean isPreorderBstNGE(ArrayList<Integer> pre) {
    Stack<Integer> stack = new Stack<>();
    int root = Integer.MIN_VALUE;

    for (Integer integer : pre) {
      if (integer < root) {
        return false;
      }

      while (!stack.empty() && stack.peek() < integer) {
        root = stack.pop();
      }

      stack.add(integer);
    }

    return true;
  }
}

public class IsPreOrderBst {
  public static void main(String[] args) {
    IsPreOrderBstSolution solution = new IsPreOrderBstSolution();
    // Integer[] preArr = {40, 30, 35, 20, 80, 100};
    Integer[] preArr = {8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 13, 14, 15};
    ArrayList<Integer> pre = new ArrayList<>(Arrays.asList(preArr));

    for (Integer item : pre) System.out.print(item + " ");
    System.out.println();
    System.out.println("Is preorder a bst (space split) ? " + solution.isPreorderBstSpaceSplit(pre));
    System.out.println("Is preorder a bst (NGE stack) ? " + solution.isPreorderBstNGE(pre));
  }
}
