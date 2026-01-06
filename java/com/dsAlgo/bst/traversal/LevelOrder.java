package com.dsAlgo.bst.traversal; /**
 * Check if the given array can represent Level Order Traversal of Binary Search Tree
 * Given an array of size n. The problem is to check whether the given array can
 * represent the level order traversal of a Binary Search Tree or not.
 */

import java.util.*;

public class LevelOrder {
	static class BST {
		class Node {
			int item; Node left; Node right; Node(int item) { this.item = item; }
		}

		Node root;

		Node insert(Node node, int item) {
			if (node == null) {
				return new Node(item);
			}

			if (Integer.compare(node.item, item) > 0) {
				node.left = insert(node.left, item);
			} else {
				node.right = insert(node.right, item);
			}

			return node;
		}

		void insert(int item) {
			root = insert(root, item);
		}

		ArrayList<Integer> levelOrder() {
			ArrayList<Integer> levelOrder = new ArrayList<>();
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);

			while (queue.size() != 0) {
				Node node = queue.remove();
				levelOrder.add(node.item);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}

			return levelOrder;
		}
	}

	public static void main(String[] args) {
		BST bst = new BST();
		int a[] = { 7, 4, 12, 3, 6, 8, 1, 5, 10 };
		for (int i = 0; i < a.length; i++) {
			bst.insert(a[i]);
		}

		ArrayList<Integer> arr = bst.levelOrder();
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i) == a[i]);
		}
	}
}

