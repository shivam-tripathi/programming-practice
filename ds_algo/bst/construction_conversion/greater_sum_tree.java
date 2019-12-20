import java.util.*;

class Node {
	int item;
	Node left;
	Node right;
	Node(int item) {
		this.item = item;
	}
}

class BST {
	Node root;

	void insert(Node node, int item) {
		if (Integer.compare(node.item, item) > 0) {
			if (node.left == null) {
				node.left = new Node(item);
			} else {
				insert(node.left, item);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(item);
			} else {
				insert(node.right, item);
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

	void convertToGreaterSumTree() {
		convertToGreaterSumTree(root, 0);
	}

	int convertToGreaterSumTree(Node node, int rootSum) {
		if (node == null) {
			return 0 + rootSum;
		}

		int item = node.item;
		node.item = convertToGreaterSumTree(node.right, rootSum);
		return convertToGreaterSumTree(node.left, node.item + item);
	}

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
}

class Main {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		Random random = new Random();
		BST tree = new BST();
		int size = 15;
		for (int i = 0; i < size; i++) {
			int item = random.nextInt();
			arr.add(item);
			tree.insert(item);
		}

		ArrayList<Integer> revSum = new ArrayList<>(arr);
		Collections.sort(revSum);
		int sum = 0;
		for (int i = revSum.size() - 1; i >= 0; i--) {
			int item = revSum.get(i);
			revSum.set(i, sum);
			sum += item;
		}

		tree.convertToGreaterSumTree();
		ArrayList<Integer> gst = tree.inOrder();

		boolean matches = true;
		for (Integer item: revSum) System.out.printf("%d ", item); System.out.println();
		for (Integer item: gst) System.out.printf("%d ", item); System.out.println();
		for (int i = 0; i < revSum.size(); i++) {
			if (!revSum.get(i).equals(gst.get(i))) {
				matches = false;
				break;
			}
		}

		System.out.printf("GST matches? %b%n", matches);
	}
}


