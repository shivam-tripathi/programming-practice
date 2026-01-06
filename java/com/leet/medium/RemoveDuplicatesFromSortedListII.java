package com.leet.medium;

public class RemoveDuplicatesFromSortedListII {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    public ListNode addNextNode(int val) {
      next = new ListNode(val);
      return next;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      ListNode node = this;
      while (node != null) {
        sb.append(node.val).append(";");
        node = node.next;
      }
      sb.append("]");
      return sb.toString();
    }
  }

  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode sentinel = new ListNode(0); // Common technique: use fake head; return fakeHead.next
    ListNode prev = sentinel, cur = head;
    // We know here cur is not null
    do {
      // Assert if this value is to be included or not
      if (cur.next != null && cur.next.val == cur.val) {
        int valToIgnore = cur.val;
        do {
          cur = cur.next;
        } while (cur != null && cur.val == valToIgnore); // Ignore all nodes with this value
      } else {
        prev.next = cur; // Add cur to resultant linked list
        prev = cur; // Move to cur node
        cur = cur.next; // Move cur to next node
//        prev.next = null; // IMP! End the list to avoid spillover connections to old linkedList
      }
    } while (cur != null);
    prev.next = null; // Close for addition
    return sentinel.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.addNextNode(2).addNextNode(2).addNextNode(2);
    System.out.println(deleteDuplicates(head));
  }
}
