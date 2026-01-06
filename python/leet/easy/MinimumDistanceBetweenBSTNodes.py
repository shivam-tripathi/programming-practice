'''
783. Minimum Distance Between BST Nodes
https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
Easy

Given the root of a Binary Search Tree (BST), return the minimum difference between the values of
any two different nodes in the tree.

Example 1:
Input: root = [4,2,6,1,3]
Output: 1

Example 2:
Input: root = [1,0,48,null,null,12,49]
Output: 1

Constraints:
    The number of nodes in the tree is in the range [2, 100].
    0 <= Node.val <= 105
'''


from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def minDiffInBST(self, root: Optional[TreeNode]) -> int:
        ans = float('inf')
        def solve(node):
            nonlocal ans
            min_val, max_val = node.val, node.val
            if node.left is not None:
                min_left, max_left = solve(node.left)
                ans = min(ans, abs(node.val-max_left))
                min_val, max_val = min(min_val, min_left), max(max_val, max_left)
            if node.right is not None:
                min_right, max_right = solve(node.right)
                ans = min(ans, abs(min_right-node.val))
                min_val, max_val = min(min_val, min_right), max(max_val, max_right)
            return (min_val, max_val)
        solve(root)
        return int(ans)
