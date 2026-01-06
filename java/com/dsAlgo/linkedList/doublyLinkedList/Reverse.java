package com.dsAlgo.linkedList.doublyLinkedList;

import java.util.*;

class ReversibleDLL extends DoublyLinkedList {
  public void reverse() {
    Node node = this.head;
    Node last = node;
    while (node != null) {
      last = node;

      Node temp = node.next;
      node.next = node.prev;
      node.prev = temp;
      node = temp;
    }

    this.head = last;
  }
}

public class Reverse  {
  public static void main(String[] args) throws NullPointerException {
    ReversibleDLL dll = new ReversibleDLL();
    Random random = new Random();
    for (int i = 0; i < 5; i++) {
      dll.emplace(random.nextInt(100));
    }
    dll.print();
    dll.reverse();
    dll.print();
  }
}