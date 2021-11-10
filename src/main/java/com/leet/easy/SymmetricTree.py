# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetricUtil(self, left: Optional[TreeNode], right: Optional[TreeNode]) -> bool:
        if left is None and right is None:
            return True
        if left is None or right is None:
            return False
        if left.val != right.val:
            return False
        if not self.isSymmetricUtil(left.right, right.left):
            return False
        if not self.isSymmetricUtil(left.left, right.right):
            return False
        return True

    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        return self.isSymmetricUtil(root.left, root.right)
