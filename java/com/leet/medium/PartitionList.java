package com.leet.medium;

import com.leet.common.ListNode;

public class PartitionList {
  public ListNode partition(ListNode inp, int x) {
    if (inp == null) {
      return null;
    }

    ListNode head = new ListNode(0), tail = new ListNode(0);
    ListNode tailHead = head, node = inp, tailTail = tail;
    while (node != null) {
      var nextNode = node.next;
      if (node.val < x) {
        tailHead.next = node;
        tailHead = tailHead.next;
        tailHead.next = null;
      } else {
        tailTail.next = node;
        tailTail = node;
        tailTail.next = null;
      }
      node = nextNode;
    }
    tailHead.next = tail.next;
    return head.next;
  }
}
