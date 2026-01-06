#include <vector>
#include <iostream>
using namespace std;

class Solution
{
public:
	vector<vector<int>> get_permutations(
			vector<int> &nums,
			int done,
			vector<int> &cur,
			int size,
			vector<vector<int>> &ans)
	{
		if (size == nums.size())
		{
			vector<int> cpy(cur.begin(), cur.end());
			ans.push_back(cpy);
			return ans;
		}

		for (int i = 0; i < nums.size(); i++)
		{
			if (!(done & (1 << i)))
			{
				cur[size] = i;
				get_permutations(nums, done | (1 << i), cur, size + 1, ans);
			}
		}

		return ans;
	}

	vector<vector<int>> permuteUnique(vector<int> &nums)
	{
		vector<vector<int>> ans;
		vector<int> cur(nums.size(), 0);
		return get_permutations(nums, 0, cur, 0, ans);
	}
};

int main()
{
	vector<int> arr = {1, 1, 2};
	Solution solution;
	vector<vector<int>> ans = solution.permuteUnique(arr);
	for (auto slice : ans)
	{
		for (auto elem : slice)
		{
			cout << elem << ",";
		}
		cout << endl;
	}
}