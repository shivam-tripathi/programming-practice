#include <vector>
using namespace std;

class Solution
{
	void reverse(vector<int> &nums, int left, int right)
	{
		while (left < right)
		{
			swap(nums[left++], nums[right--]);
		}
	}

public:
	void nextPermutation(vector<int> &nums)
	{
		int flip = nums.size() - 2;
		while (flip >= 0 && nums[flip] >= nums[flip + 1])
		{
			flip--;
		}
		if (flip < 0)
		{
			sort(nums.begin(), nums.end());
			return;
		}
		int pos = nums.size() - 1;
		while (pos > flip && nums[pos] <= nums[flip])
		{
			pos--;
		}
		swap(nums[pos], nums[flip]);
		sort(nums.begin() + flip + 1, nums.end());
	}
};
