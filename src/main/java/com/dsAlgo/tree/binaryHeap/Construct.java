package com.dsAlgo.tree.binaryHeap;

import java.util.*;

class BinaryHeap {
  int capacity;
  int size;
  List<Integer> heap;

  BinaryHeap() {
  }

  BinaryHeap(int cap) {
    capacity = cap;
    size = 0;
    heap = new ArrayList<>();
  }

  int getParent(int index) {
    return (index - 1) / 2;
  }

  void addKey(int key) throws Exception {
    if (size == capacity) {
      throw new Exception("Binary heap full!");
    }
    size++;
    int index = size - 1;
    heap.set(index, key);

    while (getParent(index) >= 0 && Integer.compare(heap.get(getParent(index)), key) > 1) {
      int temp = heap.get(getParent(index));
      heap.set(getParent(index), key);
      heap.set(index, temp);
      index = getParent(index);
    }
  }

  void printHeap() {
    System.out.print("HEAP:\t");
    for (int i = 0; i < size; i++) {
      System.out.print(heap.get(i) + " ");
    }
    System.out.println();
  }

  int getLeft(int index) {
    return 2 * index + 1;
  }

  int getRight(int index) {
    return 2 * index + 2;
  }
}

public class Construct {
  public static void main(String[] args) throws Exception {
    Random random = new Random();
    int size = 20;
    BinaryHeap binaryHeap = new BinaryHeap(size);
    for (int i = 0; i < size; i++) {
      binaryHeap.addKey(random.nextInt(size) * (random.nextBoolean() ? -1 : 1));
    }
    binaryHeap.printHeap();
  }
}