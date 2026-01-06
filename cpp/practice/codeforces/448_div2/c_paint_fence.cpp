#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int solve(const vector<int> &a, int l, int r, int base)
{
	if (l > r)
		return 0;
	// find the minimum height in current segment
	int minVal = a[l];
	for (int i = l; i <= r; i++)
	{
		minVal = min(minVal, a[i]);
	}
	// Option 1: paint each plank individually
	int strokes = r - l + 1;
	// Option 2: paint horizontally up to the minimum and then solve for remaining segments above the painted baseline
	int horizontalStrokes = minVal - base;

	int i = l;
	while (i <= r)
	{
		if (a[i] == minVal)
		{
			i++;
			continue;
		}
		int j = i;
		while (j <= r && a[j] > minVal)
			j++;
		horizontalStrokes += solve(a, i, j - 1, minVal);
		i = j;
	}
	return min(strokes, horizontalStrokes);
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int n;
	cin >> n;
	vector<int> a(n);
	for (int i = 0; i < n; i++)
	{
		cin >> a[i];
	}
	cout << solve(a, 0, n - 1, 0) << "\n";
	return 0;
}
