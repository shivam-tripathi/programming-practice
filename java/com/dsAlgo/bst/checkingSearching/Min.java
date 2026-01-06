package com.dsAlgo.bst.checkingSearching;// Find the node with minimum value in a Binary Search Tree

import java.util.*;

public class Min {
	static class BST {
		class Node {
			int item;
			Node left;
			Node right;
			Node(int item) {
				this.item = item;
			}
		}

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

		int min(Node node) {
			if (node.left == null) {
				return node.item;
			}
			return min(node.left);
		}
		int min() {
			if (root == null) {
				return -1;
			}

			return min(root);
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		for (int t = 0; t < 100; t++) {
			ArrayList<Integer> arr = new ArrayList<>();
			int size = 25;
			for (int i = 0; i < size; i++) {
				arr.add(rand.nextInt());
			}

			// for (Integer item: arr) System.out.print(item + " "); System.out.println();

			int min = Collections.min(arr);

			BST bst = new BST();
			for (Integer integer : arr) {
				bst.insert(integer);
			}

			int minBst = bst.min();
			System.out.println((minBst == min) + " " + min + " " + minBst);
		}
	}
}

