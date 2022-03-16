#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

/*
670. Maximum Swap
https://leetcode.com/problems/maximum-swap/
Medium

You are given an integer num. You can swap two digits at most once to get the maximum valued number.
Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: num = 9973
Output: 9973
Explanation: No swap.

Constraints:
	0 <= num <= 108
*/

class Solution
{
public:
	int maximumSwap(int num)
	{
		vector<int> digits, sorted;
		unordered_map<int, int> mapped;
		int tmp = num;
		while (tmp)
		{
			int digit = tmp % 10;
			digits.push_back(digit);
			sorted.push_back(digit);
			if (mapped.find(digit) == mapped.end())
			{
				mapped[digit] = digits.size() - 1;
			}
			tmp /= 10;
		}

		sort(sorted.begin(), sorted.end());

		bool same = true;

		for (int i = digits.size() - 1; i >= 0; i--)
		{
			if (digits[i] != sorted[i])
			{
				int index = mapped[sorted[i]];
				swap(digits[i], digits[index]);
				same = false;
				break;
			}
		}

		if (same)
			return num;

		int ans = 0, offset = 1;
		for (auto num : digits)
		{
			ans = ans + num * offset;
			offset *= 10;
		}

		return ans;
	}
};
