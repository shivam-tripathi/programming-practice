package com.leet.easy;

/**
 * 160. Intersection of Two Linked Lists
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * Easy
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * begin to intersect at node c1.
 * Example 1:
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * Example 2:
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Reference of the node with value = 2
 * Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 * Example 3:
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: null
 * Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Each value on each linked list is in the range [1, 10^9].
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */

import com.leet.common.ListNode;

public class IntersectionOfTwoLinkedLists {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode p1 = headA, p2 = headB;
    while (p1 != p2) {
      if (p1 != null) {
        p1 = p1.next;
      } else {
        p1 = headB;
      }
      if (p2 != null) {
        p2 = p2.next;
      } else {
        p2 = headB;
      }
    }
    return p1;
  }
}

/*
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
1 -> 2 -------/
Let distance to intersection for List a be lA and list b be Lb (4 and 2 in this case respectively).
One of the ways we can find intersection if we move pointers such that pointers A is at A3 and B is B1 (basically move
the list with longer distance to intersection by difference in distances to intersection).
We can achieve this by moving regularly moving the first pointer and second pointer till we hit null, at which point the
pointer for list with smaller distance to intersection (in this case list B) will be ahead by abs(lA - lB) (in this case
4-2 = 2). This will basically push the list with longer distance ahead by difference in lengths, making it equal in
length and moreover equal in distance to intersection. Now a simple iteration will give intersection point. In case of
no intersection, the virtual intersection will occur at null position.
 */
