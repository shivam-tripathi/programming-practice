package com.leet.easy;

/**
 * 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 * Easy
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 * Input: head = []
 * Output: []
 * Constraints:
 *     The number of nodes in the list is the range [0, 5000].
 *     -5000 <= Node.val <= 5000
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

import com.leet.common.ListNode;

public class ReverseLinkedList {
  // Recursive
  public ListNode reverseList(ListNode node, ListNode prev) {
    if (node == null) return prev;
    var next = node.next;
    node.next = prev;
    return reverseList(next, node);
  }

  public ListNode reverseListRecursive(ListNode node) {
    return reverseList(node, null);
  }

  // Iterative
  public ListNode reverseListIterative(ListNode head) {
    ListNode node = head, prev = null;
    while (node != null) {
      var next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }
    return prev;
  }
}
