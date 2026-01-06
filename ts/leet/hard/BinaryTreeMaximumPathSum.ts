import { TreeNode } from "../common/utils.ts";

function maxPathSumUtil(
  node: TreeNode | null,
): { straight: number; max: number } {
  if (node === null) {
    return { straight: 0, max: 0 };
  }

  // max for this subtree - its either current node, somewhere below in left, somewhere below right, combine or straight
  let max = node.val;
  // combines straight left and straight right and the current node
  let combine = node.val;
  // combines current node with max of straight left and right
  let straight = node.val;

  if (node.left) {
    const left = maxPathSumUtil(node.left);
    combine += left.straight;
    max = Math.max(max, left.max);
    straight = Math.max(left.straight + node.val, straight);
  }

  if (node.right) {
    const right = maxPathSumUtil(node.right);
    combine += right.straight;
    max = Math.max(max, right.max);
    straight = Math.max(right.straight + node.val, straight);
  }

  max = Math.max(straight, Math.max(max, combine));

  return { straight, max };
}

export function maxPathSum(root: TreeNode | null): number {
  const { max: ans } = maxPathSumUtil(root);
  return ans;
}
