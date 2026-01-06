from typing import Optional, List


class TreeNode:
    # Definition for a binary tree node.
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []
        ans, step, cur = [], 1, [root]
        while cur:
            next, iter = [], [0] * len(cur)
            pos = 0 if step == 1 else len(cur)-1
            for node in cur:
                if node.left:
                    next.append(node.left)
                if node.right:
                    next.append(node.right)
                iter[pos] = node.val
                pos += step
            step = 1 if step < 0 else -1
            ans.append(iter)
            cur = next

        return ans


if __name__ == '__main__':
    sol = Solution()
    root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right.left = TreeNode(15)
    root.right.right = TreeNode(7)
    print(sol.zigzagLevelOrder(root))
