import java.util.*;

class Node {
	public Node prev;
	public Node next;
	public int value;
	Node (int value) {
		this.value = value;
	}
}

class DoublyLinkedList {
	private Node head;
	public int size;

	private void insertNodeBetween(Node ins, Node prev, Node next) {
		if (next != null) {
			ins.next = next;
			next.prev = ins;
		}

		if (prev != null) {
			ins.prev = prev;
			prev.next = ins;
		}
	}

	public void insertAt(int element, int position) {
		if (position > this.size) {
			throw new NullPointerException(String.format("Position out of bounds %d / %d", position, this.size));
		}

		int index = 0;
		Node temp = this.head;
		Node prev = null;
		Node next = temp;
		while (temp != null && index != position) {
			index++;
			prev = temp;
			temp = temp.next;
			next = temp;
		}

		Node ins = new Node(element);
		this.insertNodeBetween(ins, prev, next);

		if (position == 0) {
			this.head = ins;
		}

		this.size++;
 	}

 	public void emplace(int element) {
 		this.insertAt(element, 0);
 	}

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

 	public void print() {
 		Node node = this.head;
 		System.out.print("[ ");
 		while (node != null) {
 			System.out.print(node.value + " ");
 			node = node.next;
 		}

 		System.out.println("]");
 	}
}

class Main {
	public static void main(String[] args) throws NullPointerException {
		DoublyLinkedList dll = new DoublyLinkedList();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			dll.emplace(random.nextInt(100));
		}
		dll.print();
		dll.reverse();
		dll.print();
	}
}