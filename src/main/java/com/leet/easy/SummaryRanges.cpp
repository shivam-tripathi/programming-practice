#include <vector>
#include <string>
#include <iostream>
#include <sstream>
using namespace std;

class Solution
{
public:
	string toRange(int begin, int end)
	{
		ostringstream os;
		if (begin == end)
		{
			return to_string(begin);
		}
		return to_string(begin) + "->" + to_string(end);
	}

	vector<string> summaryRanges(vector<int> &nums)
	{
		vector<string> ans;

		if (nums.size() == 0)
		{
			return ans;
		}

		int start = 0, end = 0;
		for (int i = 1; i < nums.size(); i++)
		{
			if (nums[i] == (nums[i - 1] + 1))
			{
				end = i;
			}
			else
			{
				ans.push_back(toRange(nums[start], nums[end]));
				start = end = i;
			}
		}

		ans.push_back(toRange(nums[start], nums[end]));

		return ans;
	}
};