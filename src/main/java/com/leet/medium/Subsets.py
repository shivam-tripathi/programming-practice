class Solution:
    def solve(self, subset: List[int], size: int, pos: int, nums: List[int], ans: List[List[int]]):
        if pos >= len(nums):
            # O(N)
            ans.append(subset[:size])
            return ans

        # O(2^N)
        subset[size] = nums[pos]
        self.solve(subset, size+1, pos+1, nums, ans)
        self.solve(subset, size, pos+1, nums, ans)

        return ans

    def subsets(self, nums: List[int]) -> List[List[int]]:
        return self.solve([0] * len(nums), 0, 0, nums, [])
