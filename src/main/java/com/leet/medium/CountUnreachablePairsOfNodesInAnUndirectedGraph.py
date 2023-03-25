from typing import List

'''
2316. Count Unreachable Pairs of Nodes in an Undirected Graph
Medium
https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/

You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an
undirected edge connecting nodes ai and bi.

Return the number of pairs of different nodes that are unreachable from each other.



Example 1:

Input: n = 3, edges = [[0,1],[0,2],[1,2]]
Output: 0
Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we
return 0.

Example 2:

Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
Output: 14
Explanation: There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.

Constraints:
    1 <= n <= 105
    0 <= edges.length <= 2 * 105
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    There are no repeated edges.
'''


class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        adj = {}
        for [a, b] in edges:
            if a not in adj:
                adj[a] = []
            if b not in adj:
                adj[b] = []
            adj[a].append(b)
            adj[b].append(a)

        groups = []
        visited = [False] * n

        def traverse(idx) -> int:
            if visited[idx]:
                return 0
            visited[idx] = True
            count = 1
            for child in adj.get(idx, []):
                count += traverse(child)
            return count

        cum, ans = 0, 0
        for i in range(0, n):
            if not visited[i]:
                cur = traverse(i)
                ans += cum * cur
                cum += cur

        return ans
