/**
988. Smallest String Starting From Leaf
https://leetcode.com/problems/smallest-string-starting-from-leaf/
Medium

You are given the root of a binary tree where each node has a value in the range [0, 25]
representing the letters 'a' to 'z'.

Return the lexicographically smallest string that starts at a leaf of this tree and ends at the
root.

As a reminder, any shorter prefix of a string is lexicographically smaller.
For example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.

Example 1:
	Input: root = [0,1,2,3,4,3,4]
	Output: "dba"

Example 2:
	Input: root = [25,1,3,1,3,0,2]
	Output: "adz"

Example 3:
	Input: root = [2,2,1,null,1,0,null,0]
	Output: "abc"

Constraints:
	The number of nodes in the tree is in the range [1, 8500].
	0 <= Node.val <= 25
 */

class TreeNode {
  val: number;
  left: TreeNode | null;
  right: TreeNode | null;
  constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
    this.val = val === undefined ? 0 : val;
    this.left = left === undefined ? null : left;
    this.right = right === undefined ? null : right;
  }
}

const chars = [
  "a",
  "b",
  "c",
  "d",
  "e",
  "f",
  "g",
  "h",
  "i",
  "j",
  "k",
  "l",
  "m",
  "n",
  "o",
  "p",
  "q",
  "r",
  "s",
  "t",
  "u",
  "v",
  "w",
  "x",
  "y",
  "z",
];

function solve(
  node: TreeNode | null,
  running: string[],
  idx: number,
  ans: string | null,
): string | null {
  if (!node) {
    return ans;
  }

  if (idx < running.length) {
    running[idx] = chars[node.val];
  } else {
    running.push(chars[node.val]);
  }

  idx += 1;

  if (!node.left && !node.right) {
    const key = running.slice(0, idx).reverse().join("");
    if (!ans || key < ans) {
      return key;
    }
    return ans;
  }

  const left = solve(node.left, running, idx, ans);
  const right = solve(node.right, running, idx, ans);

  if (!left || !right) return left || right;
  return left < right ? left : right;
}

function smallestFromLeaf(root: TreeNode | null): string {
  return solve(root, [], 0, null) as string;
}
