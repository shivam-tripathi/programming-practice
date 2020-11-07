class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        int begin = 1, end = max, mid = -1;
        while (begin != mid) {
            mid = (begin + end) / 2;
            int val = 0;
            for (int i = 0; i < nums.length; i++) {
                int toAdd = nums[i] / mid + (nums[i] % mid == 0 ? 0 : 1);
                val += toAdd;
            }
            if (val <= threshold) {
                end = mid;
            }
            if (val > threshold) {
                begin = mid + 1;
            }
        }
        return begin;
    }
}

class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,5,7,11};
        int ans = new Solution().smallestDivisor(nums, 11);
        System.out.println(ans);
    }
}

/*
- Problem can be broken into two parts:
    1. Finding range of binary search
    2. Textbook binary search
- The range is [1, max(N)] because any number above max(N) will give same result - all numbers divided
by c >= max(N) will ceil to 1.
- Binary search is standard lowerbound (<=threshold)
*/
