/**
 * Definition for a binary tree node.
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

const MOD = 1e9 + 7;

function dfs(node: TreeNode | null, values: Map<TreeNode, number>): number {
  if (node) {
    const left = dfs(node.left, values);
    const right = dfs(node.right, values);
    const value = left + right + node.val;
    values.set(node, value);
    return value;
  }
  return 0;
}

function solve(
  node: TreeNode | null,
  values: Map<TreeNode, number>,
  parent: number,
): number {
  if (node !== null) {
    const cur = parent * (values.get(node) ?? 0);
    const leftParent = parent + node.val +
      (node.right ? values.get(node.right) ?? 0 : 0);
    const left = solve(node.left, values, leftParent);
    const rightParent = parent + node.val +
      (node.left ? values.get(node.left) ?? 0 : 0);
    const right = solve(node.right, values, rightParent);
    return Math.max(cur, Math.max(left, right));
  }
  return 1;
}

function maxProduct(root: TreeNode | null): number {
  const values = new Map<TreeNode, number>();
  dfs(root, values);
  return solve(root, values, 0) % MOD;
}
