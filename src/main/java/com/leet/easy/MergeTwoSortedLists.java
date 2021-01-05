package com.leet.easy;

public class MergeTwoSortedLists {
  public static class ListNode {
    int val;
    ListNode tail;
    ListNode next;

    ListNode(int val) {
      this.val = val;
      tail = this;
    }

    public ListNode withNode(int val) {
      this.tail.next = new ListNode(val);
      this.tail = this.tail.next;
      return this;
    }

    @Override
    public String toString() {
      ListNode node = this;
      StringBuilder b = new StringBuilder();
      while (node != null) {
        b.append(node.val);
        node = node.next;
      }

      return b.toString();
    }
  }

  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // Do not forget case when either of nodes can be null - else out of the initial loop it will give null pointer error
    //    if (l1 == null) {
    //      return l2;
    //    }
    //
    //    if (l2 == null) {
    //      return l1;
    //    }

    // Placeholder head value would simplify it - just return head.next
    ListNode head = new ListNode(0);
    ListNode node = head;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        node.next = l1;
        node = node.next;
        l1 = l1.next; // Do not forget to move the listNode head to next value in BOTH cases
      } else {
        node.next = l2;
        node = node.next;
        l2 = l2.next; // Do not forget to move the listNode head to next value in BOTH cases
      }
    }

    // If still remaining, simply copy the values
    if (l1 != null) {
      node.next = l1;
    }
    if (l2 != null) {
      node.next = l2;
    }

    return head.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1).withNode(2).withNode(4);
    ListNode l2 = new ListNode(1).withNode(3).withNode(4);
    System.out.println(mergeTwoLists(l1, l2));
  }
}
