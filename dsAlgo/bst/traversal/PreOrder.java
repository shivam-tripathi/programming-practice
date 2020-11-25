import java.util.*;

class BST {
	class Node { int item; Node left; Node right; Node(int item) { this.item = item; } }

	Node root;
	Node insert(Node node, int item) {
		if (node == null) {
			node = new Node(item);
		} else if (Integer.compare(node.item, item) > 0) {
			node.left = insert(node.left, item);
		} else {
			node.right = insert(node.right, item);
		}
		return node;
	}
	void insert(int item) {
		root = insert(root, item);
	}

	void preOrder(Node node, ArrayList<Integer> preOrderArr) {
		if (node != null) {
			preOrderArr.add(node.item);
			preOrder(node.left, preOrderArr);
			preOrder(node.right, preOrderArr);
		}
	}

	ArrayList<Integer> preOrder() {
		ArrayList<Integer> preOrderArr = new ArrayList<>();
		preOrder(root, preOrderArr);
		return preOrderArr;
	}
}

class Main {
	public static void main(String[] args) {
		// elements 865 452 329 915 917 847 530 725 174 897 913 777 923 35 775 35 963 362 682 388
		// preorder 865 452 329 174 35 35 362 388 847 530 725 682 777 775 915 897 913 917 923 963
		int size = 20;
		Bst tree = new Bst();
		ArrayList<Integer> elements = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			int item = rand.nextInt(1000);
			elements.add(item);
			tree.insert(item);
		}

		for (Integer item: elements) System.out.print(item + " "); System.out.println();
		ArrayList<Integer> pre = tree.preOrder();
		for (Integer item: pre) System.out.print(item + " "); System.out.println();
	}
}
