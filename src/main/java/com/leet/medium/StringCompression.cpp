#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
	int compress(vector<char> &chars)
	{
		int count = 0;
		char prev = 0;
		int size = 0;
		vector<char> ans;
		for (int i = 0; i <= chars.size(); i++)
		{
			if (i < chars.size() && chars[i] == prev)
			{
				count += 1;
			}
			else
			{
				if (count > 0)
				{
					chars[size++] = prev;
				}
				if (count > 1)
				{
					for (char digit : to_string(count))
					{
						chars[size++] = digit;
					}
				}
				if (i < chars.size())
				{
					prev = chars[i];
					count = 1;
				}
			}
		}
		return size;
	}
};