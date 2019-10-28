import java.util.*;

class Node {
	public Node prev;
	public Node next;
	public int value;
	Node(int data) {
		this.value = data;
	}
}

class DoublyLinkedList {
	private Node head;
	public int size;

	/* Inserts between two node. Throws error if node to be inserted is null */
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

	public void insertFromFront(int value, int position) throws NullPointerException {
		if (position > this.size) {
			throw new NullPointerException(String.format("Position greater than size %d > %d", position, this.size));
		}

		Node temp = head;
		Node prev = null;
		Node next = temp;
		int index = 0;
		while(temp != null && index != position) {
			prev = temp;
			temp = temp.next;
			next = temp;
			index++;
		}

		Node ins = new Node(value);
		this.insertNodeBetween(ins, prev, next);
		this.size++;

		if (position == 0) {
			this.head = ins;
		}
	}

	public void prepend(int value) throws NullPointerException {
		this.insertFromFront(value, 0);
	}

	public void append(int value) throws NullPointerException {
		this.insertFromFront(value, this.size);
	}

	public int deleteAt(int position) throws NullPointerException {
		if (position >= this.size) {
			throw new NullPointerException(String.format("Position out of bounds %d > %d", position, this.size - 1));
		}

		Node temp = this.head;
		int index = 0;
		while(temp != null && index != position) {
			temp = temp.next;
			index++;
		}

		Node prev = temp.prev;
		Node next = temp.next;

		if (next != null) {
			next.prev = prev;
		}

		if (prev != null) {
			prev.next = next;
		}

		if (position == 0) {
			this.head = next;
		}

		this.size--;

		int returnValue = temp.value;
		temp = null;
		return returnValue;
	}

	public int pop() {
		return this.deleteAt(this.size - 1);
	}

	public int remove() {
		return this.deleteAt(0);
	}

	public void print() {
		Node temp = head;
		System.out.print("[ ");
		while (temp != null) {
			System.out.print(temp.value + " ");
			temp = temp.next;
		}
		System.out.println("]");
	}
}

class Main {
	public static void main(String[] args) {
		try {
			DoublyLinkedList ll = new DoublyLinkedList();
			for (int i = 0; i < 10; i++) {
				ll.append(i);
				ll.print();
			}

			Random random = new Random();
			int done = 0;
			for (int i = 0; done < 10; i++) {
				int r = random.nextInt(10 - done);
				ll.deleteAt(r);
				ll.print();
				done++;
			}

			// ll.remove();
			// ll.print();
			// ll.pop();
			// ll.print();
			// ll.remove();
			// ll.print();
			// ll.pop();
			// ll.print();
			// ll.deleteAt(5);
			// ll.print();
			// ll.deleteAt(3);
			// ll.print();

		} catch (Exception e) {
			System.out.println("Exception error " + e);
		}
	}
}
