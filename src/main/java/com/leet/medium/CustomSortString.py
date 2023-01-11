class Solution:
    def customSortString(self, order: str, s: str) -> str:
        counts = {}
        for ch in s:
            counts[ch] = counts.get(ch, 0) + 1

        ans = []
        for ch in order:
            count = counts.get(ch, 0)
            while count:
                ans.append(ch)
                count -= 1
            counts[ch] = 0

        for ch in counts:
            count = counts[ch]
            while count:
                ans.append(ch)
                count -= 1

        return ''.join(ans)
