def get_idx(ch: str):
    return ord(ch) - 97

class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        counts = [0] * 26
        for ch in s1:
            counts[get_idx(ch)] += 1

        running, left, right = [0] * 26, 0, 0

        while right < len(s2):
            ch = s2[right]
            right, ch_idx = right + 1, get_idx(ch)
            running[ch_idx] += 1
            while left < right and running[ch_idx] > counts[ch_idx]:
                left_idx = get_idx(s2[left])
                running[left_idx] -= 1
                left += 1
            if running == counts:
                return True

        return False
