from typing import List


class Solution:
    def match(self, s, start_idx, running, r_idx, ans):
        if start_idx >= len(s) or r_idx >= 4:
            if start_idx == len(s) and r_idx == 4:
                ans.append('.'.join(map(str, running)))
            return

        num = 0
        for offset in range(0, 3 if s[start_idx] != '0' else 1):
            s_idx = start_idx + offset
            if s_idx >= len(s):
                break
            num = (num * 10) + ord(s[s_idx]) - ord('0')
            if num > 255:
                break
            running[r_idx] = num
            self.match(s, s_idx+1, running, r_idx+1, ans)

    def restoreIpAddresses(self, s: str) -> List[str]:
        ans = []
        self.match(s, 0, [0, 0, 0, 0], 0, ans)
        return ans
