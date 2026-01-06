package com.dsAlgo.tree.avl;

import java.util.Map;

public class InsertionAVL {
  static class Node {
    int key, height;
    Node left, right;
    Node (int d) {
      key = d;
      height = 1;
    }

    static int getHeight(Node node) {
      return node == null ? 0 : node.height;
    }

    static int getBalance(Node node) {
      return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }
  }

  static class AVLTree {
    Node root;

    Node rightRotate(Node node) {
      Node left = node.left;
      Node leftRight = left.right;
      left.right = node;
      node.left = leftRight;
      left.height = Math.max(Node.getHeight(left.left), Node.getHeight(left.right));
      return left;
    }

    Node leftRotate(Node node) {
      Node right = node.right;
      Node rightLeft = right.left;
      right.left = node;
      node.right = rightLeft;
      right.height = Math.max(Node.getHeight(right.left), Node.getHeight(right.right));
      return right;
    }

    Node insert(Node node, int key) {
      if (node == null) return new Node(key);
      if (key < node.key) node.left = insert(node.left, key);
      else if (key > node.key) node.right = insert(node.right, key);
      else return node; // no duplicates

      node.height = Math.max(Node.getHeight(node.left), Node.getHeight(node.right));
      int balance = Node.getBalance(node);

      if (balance > 1) {
        if (key > node.left.key) {

        }
        if (key < node.key) {

        }
      }
      if (balance < -1) {
        if (key > node.key) {

        }
        if (key < node.key) {

        }
      }
      return root;
    }
  }
}
