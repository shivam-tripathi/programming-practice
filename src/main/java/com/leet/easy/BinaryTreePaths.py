from typing import Optional, List

# Definition for a binary tree node.


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def solve_paths(self, node: Optional[TreeNode], running: List[TreeNode], idx: int, paths: List[int]):
        if not node:
            return

        if idx < len(running):
            running[idx] = node.val
        else:
            running.append(node.val)
        idx += 1

        if (node.left is None) and (node.right is None):
            path = '->'.join(map(str, running[:idx]))
            paths.append(path)
            return

        self.solve_paths(node.left, running, idx, paths)
        self.solve_paths(node.right, running, idx, paths)

    def binaryTreePaths(self, root: Optional[TreeNode]) -> List[str]:
        paths = []
        self.solve_paths(root, [], 0, paths)
        return paths
