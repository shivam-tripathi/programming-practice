#include <vector>
using namespace std;

/*
74. Search a 2D Matrix
https://leetcode.com/problems/search-a-2d-matrix/
Medium

Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
This matrix has the following properties:

		Integers in each row are sorted from left to right.
		The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Constraints:
		m == matrix.length
		n == matrix[i].length
		1 <= m, n <= 100
		-104 <= matrix[i][j], target <= 104
*/

// could be done in a single binary search, as it is not a 2D matrix but just a big sorted array
class Solution
{
public:
	bool searchMatrix(vector<vector<int>> &matrix, int target)
	{
		int rows = matrix.size(), cols = matrix[0].size();

		// First we identify the row
		int low = 0, high = matrix.size() - 1;
		while (low < high)
		{
			int mid = low + (high - low + 1) / 2;
			if (matrix[mid][0] <= target)
			{
				low = mid;
			}
			else
			{
				high = mid - 1;
			}
		}

		int row = low;
		low = 0, high = cols - 1;

		// Next we identify the column
		while (low <= high)
		{
			int mid = low + (high - low) / 2;
			if (matrix[row][mid] == target)
			{
				return true;
			}
			if (matrix[row][mid] < target)
			{
				low = mid + 1;
			}
			else
			{
				high = mid - 1;
			}
		}

		return false;
	}
};