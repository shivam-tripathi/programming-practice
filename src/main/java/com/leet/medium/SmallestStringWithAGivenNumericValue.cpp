#include <string>
#include <vector>
using namespace std;

class Solution
{
public:
	string getSmallestString(int n, int k)
	{
		vector<int> vec(n);
		int sum = n, pos = n - 1;

		while (pos >= 0)
		{
			int offset = k - sum;
			if (offset <= 0)
				break;
			int diff = min('z' - 'a', offset);
			vec[pos] += diff;
			sum += diff;
			pos -= 1;
		}

		string ans = "";
		for (auto diff : vec)
		{
			ans += (diff + 'a');
		}
		return ans;
	}
};