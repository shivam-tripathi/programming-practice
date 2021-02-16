package com.leet.medium;

import com.leet.common.LinkedList;

import java.util.*;

/**
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to
 * any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has
 * its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
 * two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of
 * [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does
 * not point to any node.
 * Your code will only be given the head of the original linked list.
 */

public class CopyListWithRandomPointer {
  // Definition for a Node.
  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public Node copyRandomList_1(Node head) {
    if (head == null) return head;
    Node sentinel = new Node(0);
    Node prevNode = sentinel, node = head;
    Map<Node, Node> nodeMap = new HashMap<>();

    while(node != null) {
      Node curNode = new Node(node.val);
      curNode.random = node.random;

      nodeMap.put(node, curNode);
      node = node.next;

      prevNode.next = curNode;
      prevNode = curNode;
    }

    node = sentinel.next;
    while (node != null) {
      node.random = nodeMap.get(node.random);
      node = node.next;
    }

    return sentinel.next;
  }

  // Original list is not supposed to be modified
  // Idea to save copy of node as the present node's next pointer in the same chain
  // Then compute random by using randomNode.next for the clone
  // After this, just separate out both lists - returning original list back to it's shape and create new list from
  // cloned nodes. Extra space complexity is O(1) and time complexity is O(n*3)
  public Node copyRandomList_2(Node head) {
    if (head == null) return head;
    Node node = head;
    while (node != null) {
      Node copyNode = new Node(node.val);
      Node nextNode = node.next;
      node.next = copyNode;
      copyNode.next = nextNode;
      node = nextNode;
    }

    Node prevNode = head;
    node = head.next;
    while (node != null) {
      Node randomNode = prevNode.random;
      node.random = randomNode != null ? randomNode.next : null;
      prevNode = node.next;
      node = prevNode != null ? prevNode.next : null;
    }

    Node copy = head.next;
    node = head;
    Node copyNode = head.next;
    while(copyNode != null) {
      Node nextNode = copyNode.next;
      Node nextCopyNode = nextNode != null ? nextNode.next : null;
      node.next = nextNode;
      node = nextNode;
      copyNode.next = nextCopyNode;
      copyNode = nextCopyNode;
    }

    return copy;
  }

  public static void main(String[] args) {

  }
}
