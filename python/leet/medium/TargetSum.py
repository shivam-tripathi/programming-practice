from typing import List, Dict


class Solution:
    def in_range(self, a: List[int], b: List[int]) -> bool:
        r = a[2] * a[2]
        dx, dy = a[0] - b[0], a[1] - b[1]
        dist = dx*dx + dy*dy
        return dist <= r

    def solve(self, bombs: List[List[int]], adj: Dict[int, List[int]], visited: Dict[int, bool], cur: int) -> int:
        ans = 1
        visited[cur] = True
        for next in adj[cur]:
            if not visited.get(next):
                ans += self.solve(bombs, adj, visited, next)
        return ans

    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        ans = 0

        adj = {}
        for i in range(len(bombs)):
            adj[i] = []
            for j in range(len(bombs)):
                if j != i and self.in_range(bombs[i], bombs[j]):
                    adj[i].append(j)

        for i in range(len(bombs)):
            res = self.solve(bombs, adj, {}, i)
            ans = max(ans, res)
        return ans
