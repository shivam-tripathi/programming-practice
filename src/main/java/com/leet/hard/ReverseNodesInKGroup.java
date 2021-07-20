package com.leet.hard;

/**
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the
 * number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1: Input: head = [1,2,3,4,5], k = 2 Output: [2,1,4,3,5]
 *
 * Example 2: Input: head = [1,2,3,4,5], k = 3 Output: [3,2,1,4,5]
 *
 * Example 3: Input: head = [1,2,3,4,5], k = 1 Output: [1,2,3,4,5]
 *
 * Example 4: Input: head = [1], k = 1 Output: [1]
 *
 * Constraints: The number of nodes in the list is in the range sz. 1 <= sz <= 5000 0 <= Node.val
 * <= 1000 1 <= k <= sz
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 */
import com.leet.common.ListNode;

// We try to do it in batches.
// For every batch, the first element is the cur batch's tail. We then try to reverse the list of
// remaining size < k. This operation returns get two things - the new head and the next tail
// (basically the last element we reversed in the chain and the element where we broke reversing action.
// Then it is simple, we point the prev batch's tail to the cur head, save cur tail as prev tail and
// process the next batch.
public class ReverseNodesInKGroup {
  public ListNode[] reverseKGroup(ListNode node, int k, ListNode prev) {
    if (node == null || k == 0) {
      return new ListNode[] {prev, node};
    }

    ListNode next = node.next;
    node.next = prev;
    return reverseKGroup(next, k - 1, node);
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode sentinel = new ListNode(0, head);
    ListNode prevTail = sentinel;

    // Find the size
    int size = 0;
    ListNode node = head;
    while (node != null) {
      node = node.next;
      size++;
    }

    ListNode curTail = head;

    while (curTail != null) {
      if (size < k) {
        prevTail.next = curTail; // we do not reverse if size < k
        break;
      }
      ListNode[] nodes = reverseKGroup(curTail, k, null); // Reverse this batch
      ListNode curHead = nodes[0]; // Get the cur head
      ListNode nextTail = nodes[1]; // Get the next tail
      prevTail.next = curHead; // prevTail now points to curHead
      prevTail = curTail; // Save curTail as prevTail
      curTail = nextTail; // Move processing to next batch
      size -= k;
    }

    return sentinel.next;
  }
}
