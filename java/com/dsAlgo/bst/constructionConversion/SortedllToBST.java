package com.dsAlgo.bst.constructionConversion;

import java.util.*;

public class SortedllToBST {
  static class LinkedList {
    static class Node {
      int item;
      Node next;

      Node(int item) {
        this.item = item;
      }

      @Override
      public String toString() {
        return "[" + item + "]";
      }
    }

    Node head, tail;
    private int size;

    void insert(int item) {
      if (head == null) {
        head = tail = new Node(item);
        size = 1;
      } else {
        tail.next = new Node(item);
        tail = tail.next;
        size++;
      }
    }

    int size() {
      return size;
    }

    @Override
    public String toString() {
      Node temp = head;
      StringBuilder buf = new StringBuilder();
      while (temp != null) {
        buf.append(temp.item + "->");
        temp = temp.next;
      }
      buf.append("null");
      return buf.toString();
    }
  }

  static class BST {
    static class Node {
      int item;
      Node left;
      Node right;

      Node(int item) {
        this.item = item;
      }

      @Override
      public String toString() {
        return "(" + item + ")    ->(" + left + ")      ->(" + right + ")";
      }
    }

    Node root;

    BST(LinkedList list) {
      StructNodes treeNodes = fromSortedLinkedList(list.head, 0, list.size() - 1);
      root = treeNodes.node;
    }


    ArrayList<Integer> inOrder(Node node, ArrayList<Integer> arr) {
      if (node == null) {
        return arr;
      }

      inOrder(node.left, arr);
      arr.add(node.item);
      return inOrder(node.right, arr);
    }

    ArrayList<Integer> inOrder() {
      return inOrder(root, new ArrayList<Integer>());
    }

    class StructNodes {
      LinkedList.Node llNode;
      Node node;

      StructNodes(LinkedList.Node lln, Node n) {
        llNode = lln;
        node = n;
      }
    }

    StructNodes fromSortedLinkedList(LinkedList.Node llNode, int begin, int end) {
      if (begin > end) {
        return new StructNodes(llNode, null);
      }

      int mid = (begin + end) / 2;

      StructNodes leftNodes = fromSortedLinkedList(llNode, begin, mid - 1);
      LinkedList.Node linkedNode = leftNodes.llNode;
      Node leftTree = leftNodes.node;

      Node node = new Node(linkedNode.item);
      linkedNode = linkedNode.next;

      StructNodes rightNodes = fromSortedLinkedList(linkedNode, mid + 1, end);
      linkedNode = rightNodes.llNode;
      Node rightTree = rightNodes.node;

      node.left = leftTree;
      node.right = rightTree;

      return new StructNodes(linkedNode, node);
    }
  }

  public static void main(String[] args) {
    Random random = new Random();
    ArrayList<Integer> arr = new ArrayList<>();
    int size = 20;
    for (int i = 0; i < size; i++) {
      arr.add(random.nextInt(100));
    }

    Collections.sort(arr);
    LinkedList list = new LinkedList();
    for (Integer item : arr) {
      list.insert(item);
    }

    System.out.println(list + " Size: " + list.size());

    BST tree = new BST(list);
    for (Integer item : tree.inOrder()) System.out.print(item + "->");
    System.out.println();
  }
}

