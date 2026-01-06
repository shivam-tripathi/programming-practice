/*
55. Jump Game
https://leetcode.com/problems/jump-game/
Medium

You are given an integer array nums. You are initially positioned at the array's first index, and
each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

Constraints:
	1 <= nums.length <= 104
	0 <= nums[i] <= 105
*/

#include <vector>
#include <queue>
using namespace std;

class Solution
{
public:
	bool canJump(vector<int> &nums)
	{
		queue<int> q;
		q.push(0);
		int last = 0;

		while (q.size())
		{
			int pos = q.front();
			if (pos == (nums.size() - 1))
			{
				return true;
			}
			for (int i = last + 1; i <= (pos + nums[pos]) && i < nums.size(); i++)
			{
				q.push(i);
			}
			last = max(last, pos + nums[pos]);
			q.pop();
		}

		return false;
	}
};