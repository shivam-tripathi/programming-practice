from typing import List

class Solution:
    def is_palindrome(self, s: str, i: int, j: int, dp: dict[int, dict[int, int]]):
        if dp.get(i, {}).get(j) is not None:
            return dp[i][j]

        if i >= j:
            success = True
        elif s[i] != s[j]:
            success = False
        else:
            success = self.is_palindrome(s, i+1, j-1, dp)

        if i not in dp:
            dp[i] = {}
        dp[i][j] = success

        return success

    def solve(self, s, start, dp, running, r_size, ans):
        if start >= len(s):
            ans.append([*running[:r_size]])
            return

        for end in range(start, len(s)):
            if self.is_palindrome(s, start, end, dp) is True:
                sub_str = s[start:end+1]
                if r_size >= len(running):
                    running.append(sub_str)
                else:
                    running[r_size] = sub_str
                self.solve(s, end+1, dp, running, r_size+1, ans)

    def partition(self, s: str) -> List[List[str]]:
        ans = []
        self.solve(s, 0, {}, [], 0, ans)
        return ans