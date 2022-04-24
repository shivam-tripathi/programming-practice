#include <vector>
#include <unordered_map>
using namespace std;

/*
1248. Count Number of Nice Subarrays
https://leetcode.com/problems/count-number-of-nice-subarrays/
Medium

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are
k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:
Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:
	1 <= nums.length <= 50000
	1 <= nums[i] <= 10^5
	1 <= k <= nums.length
*/

// Note: Ditto same as https://leetcode.com/problems/subarray-sum-equals-k

class Solution
{
public:
	int numberOfSubarrays(vector<int> &nums, int k)
	{
		unordered_map<int, int> freq;
		freq[0] = 1;
		int running = 0, ans = 0;
		for (auto num : nums)
		{
			running += num & 1;
			if (freq[running - k])
			{
				ans += freq[running - k];
			}
			freq[running] += 1;
		}
		return ans;
	}
};
