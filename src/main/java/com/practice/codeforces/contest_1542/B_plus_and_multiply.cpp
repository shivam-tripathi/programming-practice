#include <bits/stdc++.h>
using namespace std;

int main()
{
	int t;
	cin >> t;
	while (t--)
	{
		long long int n, a, b;
		cin >> n >> a >> b;

		long long int prev = -1;
		bool ans = false;

		double max_pow = log(INT_MAX) / log(a);
		long long int base = 1;
		int cur_pow = 0;

		while (base <= n && base != prev && cur_pow <= max_pow)
		{
			long long int diff = n - base;
			if (diff % b == 0)
			{
				ans = true;
				break;
			}
			prev = base;
			base = base * a;
			cur_pow++;
		}

		cout << (ans ? "Yes" : "No") << endl;
	}
}
