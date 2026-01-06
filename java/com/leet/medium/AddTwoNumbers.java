package com.leet.medium;
/*
2. Add Two Numbers
https://leetcode.com/problems/add-two-numbers/
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4] // 2->4->3 and 5->6->4
Output: [7,0,8] // 2+5, 4+6, 3+4+carry
Explanation: 342 + 465 = 807.

Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 */

import com.leet.common.ListNode;

public class AddTwoNumbers {
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
