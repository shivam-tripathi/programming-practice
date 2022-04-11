#include <string>
#include <climits>
using namespace std;

class Solution
{
public:
	int myAtoi(string s)
	{
		int start = 0;
		while (start < s.size() && s[start] == ' ')
		{
			start++;
		}

		if (start == s.size())
		{
			return 0;
		}

		int sign = s[start] == '-' ? -1 : 1;
		if (s[start] == '-' || s[start] == '+')
		{
			start++;
		}

		int base = sign < 0 ? INT_MIN : INT_MAX;
		int limit = sign < 0 ? -(INT_MIN / 10) : INT_MAX / 10;
		int range = sign < 0 ? -(INT_MIN % 10) : INT_MAX % 10;

		int ans = 0;

		for (int i = start; i < s.size(); i++)
		{
			int digit = s[i] - '0';
			if (digit < 0 || digit > 9)
			{
				break;
			}
			if (ans < limit || (ans == limit && digit < range))
			{
				ans = ans * 10 + digit;
			}
			else
			{
				return base;
			}
		}

		return sign * ans;
	}
};