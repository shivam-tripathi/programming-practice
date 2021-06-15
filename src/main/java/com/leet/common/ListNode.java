package com.leet.common;

public class ListNode {
  public int val;
  public ListNode tail;
  public ListNode next;

  public ListNode() {}

  public ListNode(int val) {
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
    b.append("[").append(" ");
    while (node != null) {
      b.append(node.val).append(" ");
      node = node.next;
    }
    b.append("]");
    return b.toString();
  }
}
