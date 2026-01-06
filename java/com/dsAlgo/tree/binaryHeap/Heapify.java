package com.dsAlgo.tree.binaryHeap;

import java.util.*;

class HeapifyBinaryHeap extends BinaryHeap {
  int extractMin() throws Exception {
    if (heap.size() == 0) {
      throw new Exception("Heap is empty");
    }

    /***** NOTE:: HANDLE THIS CASE SEPARATELY *****/
    if (heap.size() == 1) {
      return heap.get(0);
    }

    int minElement = heap.get(0);
    int element = heap.get(heap.size() - 1);
    heap.set(0, element);

    heap.remove(heap.size() - 1);

    heapify(0);
    return minElement;
  }

  void heapify(int index) {
    int minIndex = index;
    if (getLeft(index) < heap.size() && heap.get(getLeft(index)) < heap.get(index)) {
      minIndex = getLeft(index);
    }

    if (getRight(index) < heap.size() && heap.get(getRight(index)) < heap.get(minIndex)) {
      minIndex = getRight(index);
    }

    if (minIndex != index) {
      int temp = heap.get(index);
      heap.set(index, heap.get(minIndex));
      heap.set(minIndex, temp);
      heapify(minIndex);
    }
  }
}

public class Heapify {
  public static void main(String[] args) throws Exception {
    Random random = new Random();
    int size = 20;
    int arr[] = {-15, -8, -11, -6, -1, -9, -5, 14, 0, 1, 3, 6, 14, -2, 19, 16, 19, 0, 8, 5};
    HeapifyBinaryHeap binaryHeap = new HeapifyBinaryHeap();
    for (int i = 0; i < size; i++) {
      // binaryHeap.addKey(random.nextInt(size) * (random.nextBoolean() ? -1 : 1));
      binaryHeap.addKey(arr[i]);
    }
    binaryHeap.printHeap();

    for (int i = 0; i < size; i++) {
      System.out.println("MIN: " + binaryHeap.extractMin());
    }
  }
}
