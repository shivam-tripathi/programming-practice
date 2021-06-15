package com.leet.medium;

/**
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * Medium
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
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
    if (left == right) return head;

    ListNode sentinel = new ListNode();
    sentinel.next = head;

    ListNode l = null, lprev = null, r = null, rnext = null;

    ListNode prev = sentinel, node = sentinel;
    int pos = 0;
    while (node != null && pos <= right) {
      if (pos == left) {
        lprev = prev;
        l = node;
      }
      if (pos == right) {
        rnext = node.next;
        r = node;
      }
      pos++;
      prev = node;
      node = node.next;
    }

    node = l;
    prev = null;
    while (node != rnext) {
      var next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }

    l.next = rnext;
    lprev.next = r;

    return sentinel.next;
  }
}
