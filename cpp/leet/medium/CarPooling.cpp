#include <vector>
#include <algorithm>
using namespace std;

class Solution
{
public:
	bool carPooling(vector<vector<int>> &trips, int capacity)
	{
		vector<vector<int>> pickups, dropoffs;
		for (auto trip : trips)
		{
			pickups.push_back({trip[1], trip[0]});
			dropoffs.push_back({trip[2], trip[0]});
		}

		sort(pickups.begin(), pickups.end());
		sort(dropoffs.begin(), dropoffs.end());

		int p = 0, d = 0;
		int cur = 0;
		while (p < pickups.size())
		{
			if (pickups[p][0] < dropoffs[d][0])
			{
				cur += pickups[p++][1];
				if (cur > capacity)
					return false;
			}
			else
			{
				cur -= dropoffs[d++][1];
			}
		}

		return true;
	}
};