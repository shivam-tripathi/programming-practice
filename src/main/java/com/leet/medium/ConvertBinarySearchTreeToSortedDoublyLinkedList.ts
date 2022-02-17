/**
426. Convert Binary Search Tree to Sorted Doubly Linked List
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
Medium

Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree
node should point to its predecessor, and the right pointer should point to its successor. You
should return the pointer to the smallest element of the linked list.

Example 1:
Input: root = [4,2,5,1,3]
Output: [1,2,3,4,5]
Explanation: The figure below shows the transformed BST. The solid line indicates the successor
relationship, while the dashed line means the predecessor relationship.

Example 2:
Input: root = [2,1,3]
Output: [1,2,3]

Example 3:
Input: root = []
Output: []
Explanation: Input is an empty tree. Output is also an empty Linked List.

Example 4:
Input: root = [1]
Output: [1]

Constraints:
		-1000 <= Node.val <= 1000
		Node.left.val < Node.val < Node.right.val
		All values of Node.val are unique.
		0 <= Number of Nodes <= 2000
 */

class ListNode {
	next: ListNode | null = null;
	prev: ListNode | null = null;

	constructor(public val: number) { }
}

class TreeNode {
	left: TreeNode | null = null;
	right: TreeNode | null = null;

	constructor(public val: number) { }

	static fromArr(arr: number[], index = 0) {
		if (index >= arr.length) {
			return null;
		}
		const node = new TreeNode(arr[index]);
		node.left = TreeNode.fromArr(arr, 2 * index + 1);
		node.right = TreeNode.fromArr(arr, 2 * index + 2);
		return node;
	}
}

class Solution {
	// Get the leftmost and right most nodes of the subtree and make connections with the current node
	private solve(node: TreeNode | null): { leftmost: ListNode | null, rightmost: ListNode | null } {
		if (node == null) {
			return { leftmost: null, rightmost: null };
		}

		const listnode = new ListNode(node.val);

		const left = this.solve(node.left);
		if (left.rightmost !== null) {
			left.rightmost.next = listnode;
			listnode.prev = left.rightmost;
		}


		const right = this.solve(node.right);
		if (right.leftmost !== null) {
			right.leftmost.prev = listnode;
			listnode.next = right.leftmost;
		}

		return { leftmost: left.leftmost ?? listnode, rightmost: right.rightmost ?? listnode };
	}

	public convert(root: TreeNode | null): ListNode | null {
		if (root === null) {
			return null;
		}
		const head = new ListNode(0);
		const { leftmost, rightmost } = this.solve(root);
		if (leftmost !== null && rightmost !== null) {
			leftmost.prev = rightmost;
			rightmost.next = leftmost;
			head.next = leftmost;
		}
		return head;
	}
}

function main() {
	[
		[4, 2, 5, 1, 3],
		[2, 1, 3],
		[]
	].forEach(inp => {
		const root = TreeNode.fromArr(inp);
		const solution = new Solution();
		const head = solution.convert(root)

		let node: ListNode | null = head?.next ?? null;
		do {
			console.log(node?.prev?.val, '->', node?.val, '->', node?.next?.val);
			node = node?.next ?? null;
		} while (node && node !== head?.next);
		console.log();
	});
}

main();