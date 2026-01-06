#include <vector>
#include <unordered_map>
using namespace std;

class Solution
{
public:
	int subarraySum(vector<int> &nums, int k)
	{
		vector<int> prefix(nums.size());
		int ans = 0;
		int running = 0;
		for (int i = 0; i < nums.size(); i++)
		{
			running += nums[i];
			prefix[i] = running;
			if (running == k)
				ans++;
		}

		unordered_map<int, int> freq;

		for (int i = prefix.size() - 1; i >= 0; i--)
		{
			int cmpl = k + prefix[i];
			if (freq.find(cmpl) != freq.end())
			{
				int cur = freq[cmpl];
				ans += cur;
			}
			if (freq.find(prefix[i]) == freq.end())
			{
				freq[prefix[i]] = 0;
			}
			freq[prefix[i]]++;
		}

		return ans;
	}
};