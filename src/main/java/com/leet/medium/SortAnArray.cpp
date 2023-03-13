#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
	void quickSort(vector<int> &nums, int left, int right)
	{
		if (left >= right)
		{
			return;
		}
		int rIdx = left + rand() % (right - left);
		int pivot = nums[rIdx];
		int less = left, more = right, idx = left;
		while (idx <= more)
		{
			if (nums[idx] == pivot)
			{
				idx += 1;
			}
			else if (nums[idx] < pivot)
			{
				if (less != idx)
				{
					swap(nums[less], nums[idx]);
				}
				less += 1;
				idx += 1;
			}
			else if (nums[idx] > pivot)
			{
				swap(nums[more], nums[idx]);
				more -= 1;
			}
		}
		for (int i = less; i <= more; i++)
		{
			nums[i] = pivot;
		}
		quickSort(nums, left, less - 1);
		quickSort(nums, more + 1, right);
	}

	vector<int> sortArray(vector<int> &nums)
	{
		quickSort(nums, 0, nums.size() - 1);
		return nums;
	}
};