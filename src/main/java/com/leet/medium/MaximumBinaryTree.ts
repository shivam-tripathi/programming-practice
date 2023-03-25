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

class SegmentTree {
  segments: number[];

  constructor(public nums: number[]) {
    const n = nums.length;
    this.segments = new Array(
      2 * Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))) - 1,
    ).fill(0);
    this.build();
  }

  build(idx = 0, left = 0, right = this.nums.length - 1): number {
    if (left === right) {
      this.segments[idx] = left;
    } else {
      const mid = Math.floor(left + (right - left) / 2);
      const left_idx = this.build(2 * idx + 1, left, mid);
      const right_idx = this.build(2 * idx + 2, mid + 1, right);
      this.segments[idx] = this.nums[left_idx] > this.nums[right_idx]
        ? left_idx
        : right_idx;
    }
    console.log({ left, right, item: this.nums[this.segments[idx]] });
    return this.segments[idx];
  }

  get(
    target_left: number,
    target_right: number,
    left = 0,
    right = this.nums.length - 1,
    idx = 0,
  ): number {
    if (target_right < left || target_left > right) {
      console.log("ret", -1);
      return -1;
    }
    if (target_left <= left && right <= target_right) {
      console.log("ret segments at", idx, this.segments[idx]);
      return this.segments[idx];
    }
    const mid = Math.floor(left + (right - left) / 2);
    const left_idx = this.get(
      target_left,
      target_right,
      left,
      mid,
      2 * idx + 1,
    );
    const right_idx = this.get(
      target_left,
      target_right,
      mid + 1,
      right,
      2 * idx + 2,
    );
    if (left_idx === -1) return right_idx;
    if (right_idx === -1) return left_idx;
    const ans_idx = this.nums[left_idx] > this.nums[right_idx]
      ? left_idx
      : right_idx;
    console.log({ target_left, target_right, ans: this.nums[ans_idx] });
    return ans_idx;
  }
}

function build(st: SegmentTree, left: number, right: number): TreeNode | null {
  if (left > right) {
    return null;
  }
  const max_idx = st.get(left, right);
  console.log({ left, max_idx, right });
  if (max_idx > right || max_idx < left) {
    throw new Error("invalid max_idx");
  }
  const node = new TreeNode(st.nums[max_idx]);
  node.left = build(st, left, max_idx - 1);
  node.right = build(st, max_idx + 1, right);
  return node;
}

export function constructMaximumBinaryTree(nums: number[]): TreeNode | null {
  const s_tree = new SegmentTree(nums);
  return build(s_tree, 0, nums.length - 1);
}

constructMaximumBinaryTree([3, 2, 1, 6, 0, 5]);
constructMaximumBinaryTree([3, 2, 1]);
