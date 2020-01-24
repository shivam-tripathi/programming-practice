/** 
  * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two end nodes. 
  * Can be extened to n-ary tree easily. Compute max depth in a subtree and max ans in the subtree. 
  * If max depth in subtrees + second max depth of subtrees + 1 > max ans in the subtree, then this is the answer for the 
  * present node. Depth of this node is the max depth in the subtrees + 1.
  **/
import java.util.*;
import java.lang.*;

class Pair <T1, T2> {
	T1 first;
	T2 second;
	Pair(T1 f, T2 s) {
		first = f;
		second = s;
	}
}

// Bidirectional uncyclic graph with maximum degree 3
class Tree {
	static class Node {
		int value;
		Node left;
		Node right;
		Node(int k) {
			value = k;
		}
	}

	Node root;
	int maxValue = 0;

	Pair<Integer, Integer> diameter(Node node) {
		int maxSubAns = 0, leftDepth = 0, rightDepth = 0;
		if (node.left != null) {
			Pair<Integer, Integer> leftPair = diameter(node.left);
			maxSubAns = Math.max(maxSubAns, leftPair.second);
			leftDepth = leftPair.first;
		}
		if (node.right != null) {
			Pair<Integer, Integer> rightPair = diameter(node.right);
			maxSubAns = Math.max(maxSubAns, rightPair.second);
			rightDepth = rightPair.first;
		}

		int ans = Math.max(maxSubAns, leftDepth + 1 + rightDepth);
		return new Pair<Integer, Integer>(Math.max(leftDepth, rightDepth) + 1, ans);
	}

	int diameter() {
		Pair<Integer, Integer> tPair = diameter(root);
		return tPair.second;
	}
}


class Main {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.root = new Tree.Node(1);
		tree.root.left = new Tree.Node(2);
		tree.root.right = new Tree.Node(3);
		tree.root.left.left = new Tree.Node(4);
		tree.root.left.right = new Tree.Node(5);

		int diameter = tree.diameter();
		System.out.printf("Diameter of the given tree is: %d\n", diameter);
	}
}
