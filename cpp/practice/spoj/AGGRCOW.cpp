/**
AGGRCOW - Aggressive cows
https://www.spoj.com/problems/AGGRCOW/
#binary-search

Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls. The stalls are located
along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).

His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once
put into a stall. To prevent the cows from hurting each other, FJ wants to assign the cows to the
stalls, such that the minimum distance between any two of them is as large as possible. What is the
largest minimum distance?

Input
t – the number of test cases, then t test cases follows.
* Line 1: Two space-separated integers: N and C
* Lines 2..N+1: Line i+1 contains an integer stall location, xi

Output
For each test case output one integer: the largest minimum distance.
Example

Input:
1
5 3
1
2
8
4
9

Output:
3
*/

#include <bits/stdc++.h>
using namespace std;

int solve(vector<int> &stall, int cows)
{
	sort(stall.begin(), stall.end());
	int low = stall[0], high = stall.size() - 1;
	int ans = 0;
	while (low < high)
	{
		int mid = low + (high - low) / 2;
		int count = 1, prev = stall[0];
		for (int i = 0; i < stall.size() && count < cows; i++)
		{
			if (stall[i] - prev >= mid)
			{
				count++;
				prev = stall[i];
			}
		}
		if (count == cows)
		{
			ans = max(ans, mid);
		}
	}

	return ans;
}

int main()
{
	int t;
	cin << t;
	while (t--)
	{
		int stalls = 0, cows = 0;
		cin >> stalls >> cows;
		vector<int> stall(stalls);
		for (int i = 0; i < stalls; i++)
		{
			cin >> stall[i];
		}
		if (stalls > 0)
		{
			cout << solve(arr, stalls, cows) << endl;
		}

		cout << 0 << endl;
	}
}
