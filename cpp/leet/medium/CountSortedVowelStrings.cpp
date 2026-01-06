#include <map>
#include <iostream>
using namespace std;

class Solution
{
public:
	int count(int size, int limit, int start, map<int, map<int, int>> &dp)
	{
		cout << size << "<-" << start << endl;
		int ans = 0;
		if (size == limit)
		{
			return 1;
		}
		if (dp.find(size) != dp.end() && dp[size].find(start) != dp[size].end())
		{
			return dp[size][start];
		}
		for (int i = start; i < 5; i++)
		{
			ans += count(size + 1, limit, i, dp);
		}
		dp[size][start] = ans;
		return ans;
	}

	int countVowelStrings(int n)
	{
		map<int, map<int, int>> dp;
		return count(0, n, 0, dp);
	}
};

int main()
{
	Solution solution;
	cout << solution.countVowelStrings(50) << endl;
}