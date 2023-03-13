'''
652. Find Duplicate Subtrees
Medium
https://leetcode.com/problems/find-duplicate-subtrees/

Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.

Example 1:
Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]

Example 2:
Input: root = [2,1,1]
Output: [[1]]

Example 3:
Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]

Constraints:
	The number of the nodes in the tree will be in the range [1, 5000]
	-200 <= Node.val <= 200
'''
from typing import Optional, List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def dfs(self, node, reg, ans) -> str:
        if not node:
            return '()'
        left = self.dfs(node.left, reg, ans)
        right = self.dfs(node.right, reg, ans)
        key = f'{node.val}({left}{right})'
        if key in reg:
            if reg[key]:
                reg[key] = False
                ans.append(node)
        else:
            reg[key] = True
        return key

    def findDuplicateSubtrees(self, root: Optional[TreeNode]) -> List[Optional[TreeNode]]:
        ans = []
        self.dfs(root, {}, ans)
        return ans
