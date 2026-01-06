function flattenUtil(node: TreeNode | null, tail:  TreeNode | null = null): TreeNode {
  if (node == null) return tail;
  const right = flattenUtil(node.right, tail);
  node.right = flattenUtil(node.left, right);
  node.left = null;
  return node;
}

function flatten(root: TreeNode | null): void {
  flattenUtil(root, null);
};
