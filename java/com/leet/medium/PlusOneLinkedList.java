package com.leet.medium;

/**
 * Leetcode 369
 *
 * Plus One Linked List
 *
 * https://leetcode.com/problems/plus-one-linked-list/
 *
 * Given a non-negative integer represented as non-empty a singly linked list of
 * digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number
 * 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of
 * the list.
 *
 * Example: Input: 1->2->3 Output: 1->2->4
 */

import java.util.Arrays;

/*
 * Ask for contraints. If number in linked list is positive, then it's
 * straightforward. Don't forget the carry at the end;
 */

import com.leet.common.LinkedList;

public class PlusOneLinkedList {
  public static int plusOneRecur(LinkedList.Node<Integer> node) {
    if (node == null) {
      return 1;
    }
    int value = node.value + plusOneRecur(node.next);
    node.setValue(value % 10);
    return value / 10;
  }

  public static LinkedList.Node<Integer> plusOneLinkedList(LinkedList.Node<Integer> node) {
    int carry = plusOneRecur(node);
    if (carry != 0) {
      return new LinkedList.Node<>(1, node);
    }
    return node;
  }

  public static void main(String[] args) {
    LinkedList<Integer> linkedList = new LinkedList<>();
    Arrays.stream(String.valueOf(Integer.MAX_VALUE).split("")).map(Integer::parseInt).forEach(linkedList::addNode);
    System.out.println(linkedList);
    LinkedList<Integer> newLinkedList = new LinkedList<Integer>(plusOneLinkedList(linkedList.head));
    System.out.println(newLinkedList);
  }
}

/*
 * - Either reverse list or use recursion - In recursion, in base case return
 * carry as 1. In all other case add carry to value, update value % 10, return
 * carry as value / 10 - Don't forget to add the final carry
 *
 * - In reverse, continue carry till node is null. In case carry is 1, create a
 * new node and attach it or else return null.
 */
