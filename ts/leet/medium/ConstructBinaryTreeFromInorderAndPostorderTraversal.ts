import { assertEquals } from "https://deno.land/std@0.119.0/testing/asserts.ts";

/**
 * Definition for a binary tree node.
 * }
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

class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  inorder_idx: number;
  postorder_idx: number;

  constructor(private inorder: number[], private postorder: number[]) {
    this.inorder_idx = inorder.length - 1;
    this.postorder_idx = postorder.length - 1;
  }

  buildTreeUtil(parent?: number): TreeNode | null {
    if (this.postorder_idx < 0) {
      return null;
    }

    const node = new TreeNode(this.postorder[this.postorder_idx]);
    this.postorder_idx -= 1;

    // right exists
    if (node.val !== this.inorder[this.inorder_idx]) {
      const right = this.buildTreeUtil(node.val);
      node.right = right;
    }

    // current node
    this.inorder_idx -= 1;

    // left exists
    if (this.inorder[this.inorder_idx] !== parent) {
      const left = this.buildTreeUtil(parent);
      node.left = left;
    }

    return node;
  }
}

function buildTree(inorder: number[], postorder: number[]): TreeNode | null {
  const solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal(
    inorder,
    postorder,
  );
  return solution.buildTreeUtil();
}

function get_inorder(node: TreeNode | null, ans: number[] = []) {
  if (!node) {
    return ans;
  }
  get_inorder(node.left, ans);
  ans.push(node.val);
  get_inorder(node.right, ans);
  return ans;
}

function get_postorder(node: TreeNode | null, ans: number[] = []) {
  if (!node) {
    return ans;
  }
  get_postorder(node.left, ans);
  get_postorder(node.right, ans);
  ans.push(node.val);
  return ans;
}

function get_preorder(node: TreeNode | null, ans: number[] = []) {
  if (!node) {
    return ans;
  }
  ans.push(node.val);
  get_preorder(node.left, ans);
  get_preorder(node.right, ans);
  return ans;
}

Deno.test({
  name: "Construct Binary Tree from inorder and postorder traversal",
  fn() {
    const root = new TreeNode(
      3,
      new TreeNode(9),
      new TreeNode(20, new TreeNode(15), new TreeNode(7)),
    );
    const inorder = get_inorder(root);
    const postoder = get_postorder(root);
    const bint = buildTree(inorder, postoder);
    const preorder = get_preorder(bint);
    assertEquals(preorder, [3, 9, 20, 15, 7]);
  },
});
