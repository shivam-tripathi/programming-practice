/**
 * Convert inOrder traveral (a sorted array to BST)
 * */

import java.util.*;


class BST {
	class Node {
		int item;
		Node left;
		Node right;
		Node(int item) {
			this.item = item;
		}
	}

	Node root;

	BST (ArrayList<Integer> sortedArr) {
		this.root = fromSortedArr(sortedArr, 0, sortedArr.size() - 1);
	}

	Node fromSortedArr(ArrayList<Integer> arr, int begin, int end) {
		if (begin > end) {
			return null;
		}

		int mid = (begin + end) / 2;
		Node node = new Node(arr.get(mid));
		node.left = fromSortedArr(arr, begin, mid - 1);
		node.right = fromSortedArr(arr, mid + 1, end);
		return node;
	}

	void insert(Node node, int item) {
		if (Integer.compare(node.item, item) > 0) {
			if (node.left != null) {
				insert(node.left, item);
			} else {
				node.left = new Node(item);
			}
		} else {
			if (node.right != null) {
				insert(node.right, item);
			} else {
				node.right = new Node(item);
			}
		}
	}

	void insert(int item) {
		if (root == null) {
			root = new Node(item);
		} else {
			insert(root, item);
		}
	}

	void inOrder(Node node, ArrayList<Integer> arr) {
		if (node != null) {
			inOrder(node.left, arr);
			arr.add(node.item);
			inOrder(node.right, arr);
		}
	}

	ArrayList<Integer> inOrder() {
		ArrayList<Integer> arr = new ArrayList<>();
		inOrder(root, arr);
		return arr;
	}
}

class Main {
	public static void main(String[] args) {
		Random random = new Random();
		int size = 20;
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int item = random.nextInt();
			arr.add(item);
		}

		Collections.sort(arr);
		BST tree = new BST(arr);
		ArrayList<Integer> inOrder = tree.inOrder();

		boolean matches = true;
		for (int i = 0; i < size; i++) {
			if (!arr.get(i).equals(inOrder.get(i))) {
				matches = false;
				break;
			}
		}

		System.out.println("Successsfully generated BST? " + (matches && arr.size() == inOrder.size()));

	}
}
