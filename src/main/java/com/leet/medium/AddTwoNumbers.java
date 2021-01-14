package com.leet.medium;

import com.leet.common.ListNode;

public class AddTwoNumbers {
  static class ReturnValue {
    ListNode node;
    int carry;

    ReturnValue(ListNode node, int carry) {
      this.node = node;
      this.carry = carry;
    }
  }

  public ListNode addTwoNumberUtil(ListNode l1, ListNode l2, int prevCarry) {
    if (l1 == null && l2 == null) {
      return prevCarry != 0 ? new ListNode(prevCarry) : null;
    }

    int sum = 0;
    ListNode next;

    if (l1 == null) {
      sum = l2.val + prevCarry;
    } else if (l2 == null) {
      sum = l1.val + prevCarry;
    } else {
      sum = l1.val + l2.val + prevCarry;
    }

    int carry = sum / 10;
    int toSave = sum % 10;

    ListNode pres = new ListNode(toSave);
    next = addTwoNumberUtil(l1 == null ? l1 : l1.next, l2 == null ? l2 : l2.next, carry);
    pres.next = next;

    return pres;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addTwoNumberUtil(l1, l2, 0);
  }

  public static void main(String[] args) {
    try {
      AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
      ListNode l1 = new ListNode(2).withNode(4).withNode(3);
      ListNode l2 = new ListNode(5).withNode(6).withNode(4);
      System.out.println(l1);
      System.out.println(l2);
      System.out.println(addTwoNumbers.addTwoNumbers(l1, l2));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
