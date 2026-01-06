package com.leet.medium;

/**
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * Medium
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
 * list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the list is n.
 *     1 <= n <= 500
 *     -500 <= Node.val <= 500
 *     1 <= left <= right <= n
 *
 *
 * Follow up: Could you do it in one pass?
 */

import com.leet.common.ListNode;

public class ReverseLinkedListII {
  public ListNode reverseBetween(ListNode head, int left, int right) {
    if (left == right) return head; // Nothing to flip

    ListNode sentinel = new ListNode(0, head);
    ListNode node = head, prev = sentinel; // node and prev are iterating nodes
    ListNode start = null, before = null; // two nodes to store start and prev of start

    int pos = 1; // Current position

    while (node != null) {
      if (pos < left) { // current position is less than left - keep iterating
        prev = node;
        node = node.next;
      } else if (pos == left) { // we encounter start of sublist which is to be flipped
        before = prev;
        start = prev = node;
        node = node.next;
      } else if (pos > left) { // we are going through the sublist
        ListNode nextNode = node.next;
        node.next = prev;
        prev = node;
        if (pos == right) { // we encounter the end of flipped list
          assert before != null;
          before.next = node;
          start.next = nextNode;
          break;
        }
        node = nextNode;
      }
      pos++;
    }
    return sentinel.next;
  }
}
