#include <stack>
#include <vector>
using namespace std;

class Solution
{
public:
	bool validateStackSequences(vector<int> &pushed, vector<int> &popped)
	{
		stack<int> s;
		int a = 0, b = 0;

		while (a < pushed.size() && b < pushed.size())
		{
			if (s.empty() || s.top() != popped[b])
			{
				s.push(pushed[a++]);
			}
			else
			{
				s.pop();
				b++;
			}
		}

		if (a != pushed.size() && b == popped.size())
			return false;

		while (b != popped.size() && s.top() == popped[b])
		{
			s.pop();
			b++;
		}

		return s.empty();
	}
};
