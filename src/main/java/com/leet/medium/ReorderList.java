package com.leet.medium;

/**
 * Reorder List
 * Medium
 * https://leetcode.com/problems/reorder-list/
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 *
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4] Output: [1,4,2,3]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5] Output: [1,5,2,4,3]
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 5 * 104]. 1 <= Node.val <= 1000
 */
import com.leet.common.ListNode;

public class ReorderList {
  public ListNode reorderList(ListNode cur, ListNode head) {
    if (cur == null) return null;

    ListNode node = head;
    // If it is not the last node, continue reordering
    if (cur.next != null) {
      node = reorderList(cur.next, head);
      // If return is null, do not proceed
      if (node == null) return null;
    }

    // In case it is an odd sized list, cur will become equal to node
    if (node == cur) {
      node.next = null;
      return null;
    }

    // In case it is an even sized list, node.next is already cur and we do not need to proceed
    if (node.next == cur) {
      cur.next = null;
      return null;
    }

    // node will point to curr node and curr node will point to next of node and we return next of node
    ListNode next = node.next;
    node.next = cur;
    cur.next = next;
    return next;
  }

  public void reorderList(ListNode head) {
    reorderList(head, head);
  }
}
