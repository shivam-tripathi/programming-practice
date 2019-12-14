/**
 * Contruct a BST from preorder traversal.
 **/

import java.util.*;


class Node {
	int item;
	Node left;
	Node right;
	Node(int item) {
		this.item = item;
	}
}

class Pair<t1, t2> {
	t1 first;
	t2 second;
	Pair(t1 f, t2 s) {
		first = f;
		second = s;
	}
}

class TreeNode extends Pair<Integer, Node> {
	TreeNode(int index, Node node) {
		super(index, node);
	}

	int getIndex() {
		return first;
	}

	Node getNode() {
		return second;
	}

	void setIndex(int index) {
		first = index;
	}

	void setNode(Node node) {
		second = node;
	}
}

// class State extends Pair<Integer, Node> {}

class BST {
	Node root;
	BST() {}
	BST(Node node) {
		root = node;
	}

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

	void preorder(Node node, ArrayList<Integer> preOrderArr) {
		if (node != null) {
			preOrderArr.add(node.item);
			preorder(node.left, preOrderArr);
			preorder(node.right, preOrderArr);
		}
	}

	ArrayList<Integer> preOrder() {
		ArrayList<Integer> preOrderArr = new ArrayList<>();
		preorder(root, preOrderArr);
		return preOrderArr;
	}
}

class Solution {

	// o(n^2): find the NGE from the root, split the remaining array into two parts.
	Node bstFromPreOrder(ArrayList<Integer> pre, int begin, int end) {
		if (begin > end) {
			return null;
		}

		int beginItem = pre.get(begin);
		Node node = new Node(beginItem);

		int rightBegin = -1;
		for (int i = begin + 1; i <= end; i++) {
			if (Integer.compare(pre.get(i), beginItem) > 0) {
				rightBegin = i;
				break;
			}
		}

		if (rightBegin != -1) {
			node.left = bstFromPreOrder(pre, begin + 1, rightBegin - 1);
			node.right = bstFromPreOrder(pre, rightBegin, end);
		} else {
			node.left = bstFromPreOrder(pre, begin + 1, end);
		}

		return node;
	}

	BST bstFromPreOrder(ArrayList<Integer> pre) {
		Node root = bstFromPreOrder(pre, 0, pre.size() - 1);
		return new BST(root);
	}

	Node bstFromPreOrderNGE(ArrayList<Integer> pre, int[] nge, int begin, int end) {
		if (begin > end) {
			return null;
		}

		Node node = new Node(pre.get(begin));
		int ngeIndex = nge[begin];
		if (ngeIndex > end || ngeIndex == -1) {
			node.left = bstFromPreOrderNGE(pre, nge, begin + 1, end);
		} else {
			node.left = bstFromPreOrderNGE(pre, nge, begin + 1, ngeIndex - 1);
			node.right = bstFromPreOrderNGE(pre, nge, ngeIndex, end);
		}

		return node;
	}

	// can be optimised to O(n) if we can store all NGE in O(n) using stack before
	// we start constructing BST. abcdefg: If e is the NGE for a, then NGE for b
	// cannot be further from e in extreme case. It will be between c and e.
	BST bstFromPreOrderNGE(ArrayList<Integer> pre) {
		// find nge for all items in O(n)
		int[] nge = new int[pre.size()];
		Stack<Pair<Integer, Integer>> stack = new Stack<>();

		for (int i = pre.size() - 1; i >= 0; i--) {
			int ngeIndex = -1;
			while (!stack.empty()) {
				Pair<Integer, Integer> top = stack.peek();
				if (Integer.compare(pre.get(i), top.first) > 0) {
					stack.pop();
				} else {
					ngeIndex = top.second;
					break;
				}
			}
			stack.add(new Pair<Integer, Integer>(pre.get(i), i));
			nge[i] = ngeIndex;
		}

		Node node = bstFromPreOrderNGE(pre, nge, 0, pre.size() - 1);
		return new BST(node);
	}

	// BST from space split action. Takes O(n) time.
	// Somewhat complicated implementation in java, because of pass by value only.
	// Pass by reference like behavior can be mimicked by updating the value at the
	// end of function call. Here we do the same: Instead of just returning the new node
	// we also return the lastest index to be parsed - and updated the calling function's
	// copy. It's important to understand this behavior, we DO NOT need to pass complex
	// objects as arguments - just need to return the reference variable as well.
	TreeNode bstFromPreOrderSpaceSplit(ArrayList<Integer> pre, int index, int min, int max) {
		if (index >= pre.size() || (pre.get(index) <= min || pre.get(index) >= max)) {
			return new TreeNode(index, null);
		}

		int item = pre.get(index++);

		TreeNode leftTreeNode = bstFromPreOrderSpaceSplit(pre, index, min, item);
		index = leftTreeNode.getIndex();

		TreeNode rightTreeNode = bstFromPreOrderSpaceSplit(pre, index, item - 1, max);
		index = rightTreeNode.getIndex();

		Node node = new Node(item);
		node.left = leftTreeNode.getNode();
		node.right = rightTreeNode.getNode();
		return new TreeNode(index, node);
	}

	BST bstFromPreOrderSpaceSplit(ArrayList<Integer> pre) {
		TreeNode rootPair = bstFromPreOrderSpaceSplit(pre, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return new BST(rootPair.second);
	}


	// - Stack based O(n) method. Best method.
	// - Stack contains all the unprocessed nodes (whose either right is missing or right are missing). Once right node is
	// set, the node has been processed. As left is processed before right, if there is no left and right is done, it
	// means the left does not exist.
	// - We traverse back in the tree (through stack) till we find the parent of the current node > the present item
	// to processed.
	// - If there is no such node (whose parent is greater than equal to the item) then we add the item as the left node
	// to the last node pushed (it is the parent of null node).
	// - If we find the node whose parent is greater than the item, we add the item as the right node to this node. We
	// insert the item node into the stack and not the present node, as both it's left and right nodes have been processed.
	BST bstFromPreOrderStack(ArrayList<Integer> pre) {
		Stack<Node> stack = new Stack<>();
		Node root = new Node(pre.get(0));
		stack.push(root); // Assuming pre.size() > 0

		for (int i = 1; i < pre.size(); i++) {
			int item = pre.get(i);
			Node temp = null;
			// Find temp such that parent of temp is greater than equal to item. Stack should not be empty
			while(!stack.empty() && Integer.compare(stack.peek().item, item) < 0) {
				temp = stack.pop();
			}

			if (temp == null) {
				temp = stack.peek(); // Node stays in stack as only left sub tree is set
				temp.left = new Node(item);
				stack.push(temp.left);
			} else {
				temp.right = new Node(item); // Node (popped before) is not reinserted as right node is also set now.
				stack.push(temp.right);
			}
		}

		// Clear the stack
		while (!stack.empty()) stack.pop();

		return new BST(root);
	}
}

class Main {
	public static void main(String[] args) {
		/* Generate and store the base BST */
		BST tree = new BST();
		Random rand = new Random();
		ArrayList<Integer> elements = new ArrayList<>();
		int size = 15;
		for (int i = 0; i < size; i++) {
			int item = rand.nextInt(100);
			elements.add(item);
			tree.insert(item);
		}
		System.out.print("Element: "); for (Integer item: elements) System.out.print(item + " "); System.out.println();
		ArrayList<Integer> preOrder = tree.preOrder();
		System.out.print("Preoder: "); for (Integer item: preOrder) System.out.print(item + " "); System.out.println();

		/* Generate and compare the nge O(n2) method against base tree */
		BST generatedTree = (new Solution()).bstFromPreOrder(preOrder);
		ArrayList<Integer> generatedPreOrder = generatedTree.preOrder();
		if (generatedPreOrder.size() != preOrder.size()) { System.out.println("Preorder: Sizes don't match"); System.exit(-1); }
		boolean matches = true;
		for (int i = 0; i < preOrder.size(); i++) {
			if (!preOrder.get(i).equals(generatedPreOrder.get(i))) {
				matches = false;
				break;
			}
		}
		System.out.println("generatedPreOrder match? " + matches);

		/* Generate and compare the NGE O(n) method against base tree */
		BST generatedTreeNGE = (new Solution()).bstFromPreOrderNGE(preOrder);
		ArrayList<Integer> generatedPreOrderNGE = generatedTreeNGE.preOrder();
		if (generatedPreOrderNGE.size() != preOrder.size()) { System.out.println("NGE: Sizes don't match"); System.exit(-1); }
		boolean matchesNGE = true;
		for (int i = 0; i < preOrder.size(); i++) {
			if (!preOrder.get(i).equals(generatedPreOrderNGE.get(i))) {
				matchesNGE = false;
				break;
			}
		}
		System.out.println("generatedPreOrderNGE match? " + matchesNGE);

		/* Generate and compare the space split tree against base tree */
		BST generatedTreeSpaceSplit = (new Solution()).bstFromPreOrderSpaceSplit(preOrder);
		ArrayList<Integer> generatedPreOrderSpaceSplit = generatedTreeSpaceSplit.preOrder();
		if (generatedPreOrderSpaceSplit.size() != preOrder.size()) { System.out.println("SpaceSplit: Sizes don't match"); System.exit(-1); }
		boolean matchesSpaceSplit = true;
		for (int i = 0; i < preOrder.size(); i++) {
			if (!preOrder.get(i).equals(generatedPreOrderSpaceSplit.get(i))) {
				matchesSpaceSplit = false;
				//break;
			}
		}
		System.out.println("generatedPreOrderSpaceSplit match? " + matchesSpaceSplit);

		BST generatedTreeStack = (new Solution()).bstFromPreOrderStack(preOrder);
		ArrayList<Integer> generatedPreOrderStack = generatedTreeStack.preOrder();
		if (generatedPreOrderStack.size() != preOrder.size()) { System.out.println("Stack: Sizes don't match"); System.exit(-1); }
		boolean matchesStack = true;
   		for (int i = 0; i < preOrder.size(); i++) {
			if (!preOrder.get(i).equals(generatedPreOrderStack.get(i))) {
				matchesStack = false;
				break;
			}
		}
		System.out.println("generatedPreOrderStack matches? " + matchesStack);
	}
}
