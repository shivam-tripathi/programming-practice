class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s

        ans = [[] for i in range(0, numRows)]
        row, down = 0, True

        for i in range(0, len(s)):
            ch = s[i]
            ans[row].append(ch)

            if row == (numRows-1) and i != 0:
                down = False
            elif row == 0:
                down = True

            if down:
                row += 1
            else:
                row -= 1

        return ''.join([''.join(row) for row in ans])
