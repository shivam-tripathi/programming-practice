#include <vector>
#include <algorithm>
using namespace std;

typedef unsigned long long int ll;

class Solution
{
public:
	long long minimalKSum(vector<int> &nums, int k)
	{
		ll prev = 0, remaining = k;
		ll ans = 0;

		sort(nums.begin(), nums.end());

		for (auto cur : nums)
		{
			if (cur > (prev + 1))
			{
				ll gap = min(cur - prev - 1, remaining);
				remaining -= gap;
				ans += (gap * prev) + (gap * (gap + 1) / 2);
			}
			if (remaining <= 0)
				break;
			prev = cur;
		}

		if (remaining > 0)
		{
			ans += (remaining * prev) + (remaining * (remaining + 1) / 2L);
		}

		return ans;
	}
};