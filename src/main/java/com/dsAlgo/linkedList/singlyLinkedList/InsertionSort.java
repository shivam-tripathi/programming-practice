package com.dsAlgo.linkedList.singlyLinkedList;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
class InsertSortSolution {
    public ListNode insertionSortList(ListNode A) {
        if (A == null) {
            return null;
        }

        ListNode start = A;
        ListNode prev = null;
        ListNode pres = A;

        while(pres != null) {
            ListNode after = start;
            ListNode before = null;
            ListNode ahead = pres.next;

            while (after.val < pres.val && after != ahead) {
                before = after;
                after = after.next;
            }

            if (after == pres) {
                prev = pres;
                pres = ahead;
                continue;
            }


            if (before != null) {
                before.next = pres;
            } else {
                start = pres;
            }
            pres.next = after;
            prev.next = ahead;
            pres = ahead;
        }
        return start;
    }
}

class ListNode {
    ListNode next;
    int val;
    ListNode(int value) {
        this.val = value;
    }
}

public class InsertionSort {
    public static void main(String[] args) {
        InsertSortSolution solution = new InsertSortSolution();
        ListNode start = new ListNode(0);
        ListNode prev = start;

        for (int i = 10; i > 0; i--) {
            ListNode pres = new ListNode(i);
            prev.next = pres;
            prev = pres;
        }

        prev = start;
        while(prev != null) {
            System.out.print(prev.val + " ");
            prev = prev.next;
        }
        System.out.println();

        ListNode newStart = solution.insertionSortList(start);
        while(newStart != null) {
            System.out.print(newStart.val + " ");
            newStart = newStart.next;
        }
        System.out.println();
    }
}