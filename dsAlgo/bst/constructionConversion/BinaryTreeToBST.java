/**
 * Binary Tree to Binary Search Tree Conversion.
 * Given a Binary Tree, convert it to a Binary Search Tree.
 * The conversion must be done in such a way that keeps the original structure of Binary Tree.
 *
 * Sort all elements, and overwrite the tree doing inorder.
 */

import java.util.*;

class Node {
	int item;
	Node left;
	Node right;
	Node(int item) {
		this.item = item;
	}
}

class BT {
	Node root;
	void inOrder(Node node, ArrayList<Integer> in) {
		if (node != null) {
			inOrder(node.left, in);
			in.add(node.item);
			inOrder(node.right, in);
		}
	}

	ArrayList<Integer> inOrder() {
		ArrayList<Integer> in = new ArrayList<>();
		inOrder(root, in);
		return in;
	}

	int replaceInorder(Node node, ArrayList<Integer> newInorder, int index) {
		if (node == null) {
			return index;
		}

		index = replaceInorder(node.left, newInorder, index);
		node.item = newInorder.get(index++);
		return replaceInorder(node.right, newInorder, index);
	}

	void replaceInorder(ArrayList<Integer> rep) {
		replaceInorder(root, rep, 0);
	}
}

class Main {
	public static void main(String[] args) {
		BT bt = new BT();
		bt.root = new Node(1);
		Queue<Node> queue = new LinkedList<>();
		queue.add(bt.root);
		int index = 2;
		while (!queue.isEmpty() && index < 16) {
			Node node = queue.remove();
			node.left = new Node(index++);
			node.right = new Node(index++);
			queue.add(node.left);
			queue.add(node.right);
		}

		ArrayList<Integer> inOrder = bt.inOrder();
		for (Integer item: inOrder) System.out.print(item + " "); System.out.println();
		Collections.sort(inOrder);
		bt.replaceInorder(inOrder);
		inOrder = bt.inOrder();
		for (Integer item: inOrder) System.out.print(item + " "); System.out.println();
	}
}



