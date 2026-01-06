package com.leet.common;

public class DoublyListNode<T> {
	public T value;
	public DoublyListNode<T> prev;
	public DoublyListNode<T> next;

	public DoublyListNode(T val) {
		this.value = val;
	}
}
