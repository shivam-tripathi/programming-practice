package com.leet.common;

public class DoublyLinkedList<T> {
	public DoublyListNode<T> head;
	public DoublyListNode<T> tail;
	public int size;

	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public DoublyListNode<T> addFirst(T val) {
		DoublyListNode<T> node = new DoublyListNode<T>(val);
		if (this.size == 0) {
			this.head = this.tail = node;
		} else {
			node.next = this.head;
			this.head.prev = node;
			this.head = node;
		}
		this.size++;
		return node;
	}

	public DoublyListNode<T> addLast(T val) {
		DoublyListNode<T> node = new DoublyListNode<T>(val);
		if (this.size == 0) {
			this.head = node;
			this.tail = node;
		} else {
			node.prev = this.tail;
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;
		return node;
	}

	public DoublyListNode<T> removeFirst() {
		if (this.head == null) {
			return null;
		}
		DoublyListNode<T> removed = this.head;
		if (this.size == 1) {
			this.head = this.tail = null;
		} else {
			this.head = this.head.next;
			this.head.prev = null;
		}
		this.size--;
		return removed;
	}

	public DoublyListNode<T> removeLast() {
		if (this.tail == null) {
			return null;
		}
		DoublyListNode<T> removed = this.tail;
		if (this.size == 1) {
			this.head = this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}
		this.size--;
		return removed;
	}
}
