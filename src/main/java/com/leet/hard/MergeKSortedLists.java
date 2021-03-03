package com.leet.hard;

/**
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * Hard
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * Input: lists = []
 * Output: []
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 * Constraints:
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 */

import com.leet.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
  // Priority queue based solution. Very readable. Not so good in performance.
  public ListNode mergeKLists(ListNode[] lists) {
    var pq = new PriorityQueue<ListNode>(Comparator.comparingInt(l -> l.val));
    for (ListNode list : lists) {
      pq.offer(list);
    }
    ListNode sentinel = new ListNode(0);
    var node = sentinel;
    while (!pq.isEmpty()) {
      var top = pq.poll();
      var next = top.next;
      node.next = top;
      top.next = null;
      node = top;
      if (next != null) pq.offer(next);
    }
    return sentinel.next;
  }

  // 100% similar to merge k sorted arrays - k way merge sort
  public ListNode mergeKListsUtil(ListNode[] listNodes, int begin, int end) {
    if (end <= begin) return listNodes[begin];
    ListNode left, right;
    if (end - begin > 1) {
      int mid = begin + (end - begin) / 2;
      left = mergeKListsUtil(listNodes, begin, mid);
      right = mergeKListsUtil(listNodes, mid + 1, end);
    } else {
      left = listNodes[begin];
      right = listNodes[end];
    }

    if (left == null) return right;
    if (right == null) return left;

    // Merge two lists
    ListNode sentinel = new ListNode(0);
    ListNode cur = sentinel;

    while (left != null && right != null) {
      boolean nextIsLeft = left.val < right.val;
      var node = nextIsLeft ? left : right;
      cur.next = node;
      cur = node;
      node = node.next;
      cur.next = null;
      if (nextIsLeft) left = node;
      else right = node;
    }
    while (left != null) {
      cur.next = left;
      cur = left;
      left = left.next;
      cur.next = null;
    }
    while (right != null) {
      cur.next = right;
      cur = right;
      right = right.next;
      cur.next = null;
    }
    return sentinel.next;
  }

  public ListNode mergeKLists_2(ListNode[] lists) {
    if (lists.length == 0) return null;
    return mergeKListsUtil(lists, 0, lists.length - 1);
  }
}
