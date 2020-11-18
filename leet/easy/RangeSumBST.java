class Solution {
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        } else if (node.val < low) {
            return rangeSumBST(node.right, low, high);
        } else if (node.val > high) {
            return rangeSumBST(node.left, low, high);
        } else {
            return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
        }
    }
}

