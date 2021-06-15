package com.leet.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class LinkedList<T> {
  public LinkedList() {
  }

  @AllArgsConstructor
  @Setter
  @Getter
  public static class Node<T2> {
    public T2 value;
    public Node<T2> next;
  }

  public Node<T> head = null;
  public Node<T> tail = null;

  public LinkedList(Node<T> node) {
    head = tail = node;
    while (tail != null && tail.next != null) {
      tail = tail.next;
    }
  }

  public LinkedList<T> addNode(T value) {
    if (head == null) {
      head = tail = new Node<T>(value, null);
    } else {
      tail.next = new Node<T>(value, null);
      tail = tail.next;
    }
    return this;
  }

  @Override
  public String toString() {
    Node<T> node = head;
    StringBuilder builder = new StringBuilder();
    while (node != null) {
      builder.append(node.value);
      node = node.next;
    }
    return builder.toString();
  }
}
