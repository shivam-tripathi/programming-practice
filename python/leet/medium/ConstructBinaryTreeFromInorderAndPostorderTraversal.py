from typing import List, Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def solve(self, postorder, mapping, left, right, idx):
        if idx < 0 or left > right:
            return None, idx
        node = TreeNode(postorder[idx])
        in_idx = mapping[postorder[idx]]
        idx -= 1
        node.right, idx = self.solve(postorder, mapping, in_idx+1, right, idx)
        node.left, idx = self.solve(postorder, mapping, left, in_idx-1, idx)
        return node, idx


    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        mapping = {}
        for idx in range(0, len(inorder)):
            mapping[inorder[idx]] = idx
        root, _ = self.solve(postorder, mapping, 0, len(postorder)-1, len(postorder)-1)
        return root

