class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        a = [int(e) for e in version1.split(".")]
        b = [int(e) for e in version2.split(".")]

        for i in range(min(len(a), len(b))):
            if a[i] < b[i]:
                return -1
            if a[i] > b[i]:
                return 1

        for i in range(min(len(a), len(b)), len(a)):
            if a[i] > 0:
                return 1

        for i in range(min(len(a), len(b)), len(b)):
            if b[i] > 0:
                return -1

        return 0
