package com.leet.hard;

/**
 * 895. Maximum Frequency Stack
 * https://leetcode.com/problems/maximum-frequency-stack/
 * Hard
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 * FreqStack has two functions:
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and
 * returned.
 * Example 1:
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 * pop() -> returns 4.
 * The stack becomes [5,7].
 * Note:
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */

import java.util.*;

class MaximumFrequencyStackBinaryHeap {
  static class HeapItem {
    int item;
    int freq;
    Stack<Integer> lastSeen;

    HeapItem(int item) {
      this.item = item;
      lastSeen = new Stack<>();
    }
  }

  static class BinaryHeap {
    int cap = 10000 + 10;
    HeapItem[] items = new HeapItem[cap];
    int head = 0;
    int lastUpdate = 0;

    void insert(int item) {
      HeapItem ins = new HeapItem(item);
      ins.lastSeen.push(lastUpdate++);
      items[head++] = ins;
      heapifyUp(head - 1);
    }

    int extract() {
      int itemToReturn = items[0].item;
      items[0].freq--;
      items[0].lastSeen.pop();
      heapifyDown(0);
      return itemToReturn;
    }

    // Establishes whether potentialIdx should be before curIdx
    boolean shouldSwap(int curIdx, int potentialIdx) {
      if (curIdx < 0 || curIdx >= head || potentialIdx < 0 || potentialIdx >= head) return false;
      HeapItem curHeapItem = items[curIdx];
      HeapItem potentialHeapItem = items[potentialIdx];
      return potentialHeapItem.freq > curHeapItem.freq || (potentialHeapItem.freq == curHeapItem.freq && potentialHeapItem.lastSeen.peek() > curHeapItem.lastSeen.peek());
    }

    void swapHeapItems(int curIdx, int nextIdx) {
      HeapItem curHeapItem = items[curIdx];
      items[curIdx] = items[nextIdx];
      items[nextIdx] = curHeapItem;
    }

    void heapifyDown(int curIdx) {
      if (curIdx >= head) return;
      int leftIdx = curIdx * 2 + 1;
      int rightIdx = curIdx * 2 + 2;
      int swapIdx = curIdx;
      if (shouldSwap(curIdx, leftIdx)) {
        swapIdx = leftIdx;
      }
      if (shouldSwap(swapIdx, rightIdx)) {
        swapIdx = rightIdx;
      }
      if (curIdx != swapIdx) {
        swapHeapItems(curIdx, swapIdx);
        heapifyDown(swapIdx);
      }
    }

    void update(int item) {
      for (int i = 0; i < head; i++) {
        if (items[i].item == item) {
          items[i].freq++;
          items[i].lastSeen.push(lastUpdate++);
          heapifyUp(i);
          return;
        }
      }
      insert(item);
    }

    void heapifyUp(int curIdx) {
      if (curIdx < 0) return;
      int parentIdx = (curIdx - 1) / 2;
      if (shouldSwap(parentIdx, curIdx)) {
        swapHeapItems(parentIdx, curIdx);
        heapifyUp(parentIdx);
      }
    }
  }

  static class FreqStack {
    BinaryHeap heap;

    public FreqStack() {
      heap = new BinaryHeap();
    }

    public void push(int x) {
      heap.update(x);
    }

    public int pop() {
      return heap.extract();
    }
  }
}

class MaximumFrequencyStackOfStackSolution {
  static class FreqStack {
    Map<Integer, Integer> freq;
    // Map<Integer, Stack<Integer>> group;
     List<Stack<Integer>> group;
    int maxFreq;


    public FreqStack() {
      freq = new HashMap<>();
      // group = new HashMap<>();
       group = new ArrayList<>();
       group.add(new Stack<Integer>());
      maxFreq = 0;
    }

    public void push(int x) {
      int curFreq = freq.merge(x, 1, Integer::sum);
      // if (!group.containsKey(curFreq)) group.put(curFreq, new Stack<>());
      // group.get(curFreq).add(x);
      if (curFreq >= group.size()) {
        group.add(new Stack<Integer>());
      }
      group.get(curFreq).add(x);
      maxFreq = Math.max(maxFreq, curFreq);
    }

    public int pop() {
      int top = group.get(maxFreq).pop();
      freq.put(top, freq.get(top) - 1);
      if (group.get(maxFreq).size() <= 0) maxFreq--;
      return top;
    }
  }
}

public class MaximumFrequencyStack {
  public static void main(String[] args) {
//    var freqStackBinaryHeap = new MaximumFrequencyStackBinaryHeap.FreqStack();
//    freqStackBinaryHeap.push(23);
    var freqStack = new MaximumFrequencyStackOfStackSolution.FreqStack();
    freqStack.push(23);
    freqStack.pop();
  }
}
