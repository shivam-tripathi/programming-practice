/**
 * Check if a binary tree is BST or not.
 * Local checks at node level are not sufficient, we need to establish
 * that the maximum of left subtree is smaller than the root and the
 * minimum of right subtree is larger than the root.
 *
 * Alternate way to think of this: The center node divides the solution
 * space into two parts. All nodes of left subtree must fall to the left
 * part of the space and all nodes of right subtree must fall to the right
 * subtree of the space recursively till the leaf node. (SIMPLER, MUST BE
 * FOLLOWED).
 *
 * Another method is to find inorder traversal and check if it's sorted or
 * not.
 *
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

class Status {
	int min;
	int max;
	boolean isBst;
	Status(int f, int s) {
		min = f;
		max = s;
		isBst = true;
	}
	Status(boolean status) {
		isBst = status;
	}
}

class BinaryTree {
	Node root;

	Status isBst(Node node) {
		if (node == null) {
			return null;
		}

		Status leftTree = isBst(node.left);
		Status rightTree = isBst(node.right);

		if ((leftTree != null && (!leftTree.isBst || leftTree.max >= node.item)) ||
			(rightTree != null && (!rightTree.isBst || rightTree.min < node.item))
		) {
			return new Status(false);
		}

		int maximumMinimumItem = leftTree != null ? leftTree.min : node.item;
		int minimumMaximumItem = rightTree != null ? rightTree.max : node.item;

		return new Status(maximumMinimumItem, minimumMaximumItem);
	}

	boolean isBst() {
		return isBst(root).isBst;
	}

	boolean isBstRange(Node node, int min, int max) {
		if (node == null) {
			return true;
		} else if (Integer.compare(node.item, min) < 0 || Integer.compare(node.item, max) > 0) {
			return false;
		}

		return isBstRange(node.left, min, node.item - 1) && isBstRange(node.right, node.item, max);
	}

	boolean isBstRange() {
		return isBstRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean isBstInorder() {
		class State {
			Node node;
			int done;
			State(Node node) {
				this.node = node;
				done = 0;
			}
		}

		Stack<State> stack = new Stack<>();
		stack.push(new State(root));

		ArrayList<Integer> inorder = new ArrayList<>();

		while (!stack.empty()) {
			State state = stack.peek();
			if (state.done == 2 || state.node == null) {
				stack.pop();
			} else if (state.done == 1) {
				state.done++;
				stack.add(new State(state.node.right));
				inorder.add(state.node.item);
			} else {
				state.done++;
				stack.add(new State(state.node.left));
			}
		}

		int prev = inorder.get(0);
		for (int i = 1; i < inorder.size(); i++) {
			if (inorder.get(i) < prev) {
				return false;
			}
		}
		return true;
	}
}

class Main {
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
	    tree.root = new Node(4);
	    tree.root.left = new Node(2);
	    tree.root.right = new Node(5);
	    tree.root.left.left = new Node(1);
	    tree.root.left.right = new Node(3);
	    // tree.root.left.right.left = new Node(100);

	    System.out.println(tree.isBst());
	    System.out.println(tree.isBstRange());
		System.out.println(tree.isBstInorder());
	}
}
