package com.leet.easy;

import com.leet.common.ListNode;

public class RemoveDuplicatesFromSortedList {
  public static ListNode deleteDuplicates(ListNode head) {
    ListNode node = head;
    while (node != null) {
      if (node.next != null && node.next.val == node.val) {
        node.next = node.next.next;
      } else {
        node = node.next;
      }
    }
    return head;
  }
}
