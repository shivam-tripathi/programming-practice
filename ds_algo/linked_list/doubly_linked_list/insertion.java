import java.util.*;

class Node {
	public int element;
	public Node prev;
	public Node next;
	int size;
	Node (int element) {
		this.element = element;
	}
}

class DoublyLinkedList {
	public Node head;
	public Node tail;
	public int size;

	private void insertNodeBetween(Node ins, Node prev, Node next) throws NullPointerException {
		if (next != null) {
			ins.next = next;
			next.prev = ins;
		}

		if (prev != null) {
			ins.prev = prev;
			prev.next = ins;
		}
	}

	/* Insert at position from front */
	public void insertFront(int element, int position) throws NullPointerException {
		if (position > this.size) {
			throw new NullPointerException(String.format("Position out of bound %d > %d", position, this.size));
		}

		Node temp = this.head;
		Node prev = null;
		Node next = temp;
		int index = 0;
		while (temp != null && index != position) {
			prev = temp;
			temp = temp.next;
			next = temp;
			index++;
		}

		Node ins = new Node(element);
		this.insertNodeBetween(ins, prev, next);


		if (position == 0) {
			this.head = ins;
		}

		if (position == this.size) {
			this.tail = ins;
		}

		this.size++;
	}

	/* Insert at position from back */
	public void insertBack(int element, int position) throws NullPointerException {
		if (position > this.size) {
			throw new NullPointerException(String.format("Position out of bound %d > %d", position, this.size));
		}

		Node temp = this.tail;
		Node prev = temp;
		Node next = null;
		int index = 0;
		while (temp != null && index != position) {
			next = temp;
			temp = temp.prev;
			prev = temp;
			index++;
		}

		Node ins = new Node(element);
		this.insertNodeBetween(ins, prev, next);

		if (position == 0) {
			this.tail = ins;
		}

		if (position == this.size) {
			this.head = ins;
		}

		this.size++;
	}

	public void prepend(int element) throws NullPointerException {
		this.insertFront(element, 0);
	}

	public void append(int element) throws NullPointerException {
		this.insertBack(element, 0);
	}

	public void print() {
		Node temp = this.head;
		System.out.print("[ ");
		while(temp != null) {
			System.out.print(temp.element + " ");
			temp = temp.next;
		}
		System.out.println("]");
	}
}

class Main {
	public static void main(String[] args) throws NullPointerException {
		DoublyLinkedList ll = new DoublyLinkedList();

		ll.prepend(10);
		ll.print();

		ll.append(123);
		ll.print();

		ll.prepend(20);
		ll.print();

		ll.prepend(30);
		ll.print();

		ll.append(1234);
		ll.print();

		ll.insertFront(2, 2);
		ll.print();

		ll.insertBack(-2, 2);
		ll.print();

		ll.append(12345);
		ll.print();

		ll.insertFront(-6, ll.size);
		ll.print();
	}

}
