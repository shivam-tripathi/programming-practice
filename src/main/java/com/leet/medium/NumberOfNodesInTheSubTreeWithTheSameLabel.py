from typing import List


class Solution:
    def dfs(self, adj, mapper, labels, node, visited, collect):
        if visited[node]:
            if len(collect) > 0:
                for label in collect[node] or {}:
                    if label not in mapper:
                        mapper[label] = 0
                    mapper[label] += collect[node][label]
            return
        visited[node] = True
        label = labels[node]
        if label not in mapper:
            mapper[label] = 0
        for child in adj[node]:
            self.dfs(adj, mapper, labels, child, visited, collect)
        mapper[label] += 1
        collect[node] = mapper

    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        adj = [[] for _ in range(0, n)]
        for edge in edges:
            adj[edge[0]].append(edge[1])
            adj[edge[1]].append(edge[0])
        collect = [None] * n
        ans = [0] * n
        visited = [False] * n
        for node in range(0, n):
            self.dfs(adj, {}, labels, node, visited, collect)
            ans[node] = collect[node][labels[node]]
        return ans


solution = Solution()

for test in [
    [7, [[0, 1], [0, 2], [1, 4], [1, 5], [2, 3], [2, 6]],
        "abaedcd", [2, 1, 1, 1, 1, 1, 1]],
    [4, [[0, 1], [1, 2], [0, 3]], "bbbb", [4, 2, 1, 1]],
    [5, [[0, 1], [0, 2], [1, 3], [0, 4]], 'aabab', [3, 2, 1, 1, 1]]

]:
    n, edges, labels, expected = test
    ans = solution.countSubTrees(n, edges, labels)
    print(ans, '==', expected)
    assert ans == expected
