package com.leet.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
  // O(nlogn) - use sort (basic, slowest)
  public int sortingBased(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  // O(nlogk) - use binary heap (priority queue) of size k. Insert first k elements. Then remove the smallest at the top
  // if there is a number greater than it. Continue for all numbers. At the end you have sorted k numbers in the heap.
  // For insert in tree of size k all n elements - nlogk
  public int heapBased(int[] nums, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < k && i < nums.length; i++) {
      queue.add(nums[i]);
    }

    for (int i = k; i < nums.length; i++) {
      Integer top = queue.peek();
      assert top != null;
      if (nums[i] > top) {
        queue.remove();
        queue.add(nums[i]);
      }
    }

    assert queue.peek() != null;
    return queue.peek();
  }

  // average case O(n) - Quick select. Since we do not need absolute ordering but rather partitioning, we can use quick
  // select here (basically partitioning algorithm for quick sort). This is average case O(n), but worst can be n^2. We
  // offset this by using random pivot from the yet to be sorted array.
  void swap(int[] nums, int a, int b) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }

  public int quickSelect(int[] nums, int low, int high, int k) {
    int random = low + (int) (Math.random() * (high - low));
    swap(nums, low, random);
    int pivot = low;

    for (int i = low; i < high; i++) {
      if (nums[i] < nums[high]) {
        swap(nums, i, pivot);
        pivot++;
      }
    }
    swap(nums, pivot, high);
    int count = high - pivot + 1;
    if (count == k) return nums[pivot];
    if (count < k) return quickSelect(nums, low, pivot - 1, k);
    return quickSelect(nums, pivot, high, k);
  }

  public int findKthLargest(int[] nums, int k) {
    return 0;
  }
}
