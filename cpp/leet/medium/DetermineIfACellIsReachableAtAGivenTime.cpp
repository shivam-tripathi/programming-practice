/*
2849. Determine if a Cell Is Reachable at a Given Time
https://leetcode.com/problems/determine-if-a-cell-is-reachable-at-a-given-time/
Medium
Companies

You are given four integers sx, sy, fx, fy, and a non-negative integer t.

In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its
adjacent cells.

Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.

A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can
visit the same cell several times.
*/

#include <math.h>
#include <algorithm>
using namespace std;

class Solution
{
public:
	bool isReachableAtTime(int sx, int sy, int fx, int fy, int t)
	{
		if (sx == fx && sy == fy)
		{
			return t == 0;
		}

		int distX = abs(sx - fx);
		int distY = abs(sy - fy);
		int time = max(distX, distY);

		return time <= t;
	}
};
