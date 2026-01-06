#include <stack>
#include <vector>
#include <string>
using namespace std;

// Basic idea: use a stack to keep track of the number of opening brackets
// and their positions in the iteration stack in waiting state. If we encounter a
// closing bracket we move them into finished state and add the closing bracket
// into iteration stack as well. If there are no opening brackets, then this bracket
// is invalid and so skip it.
class Solution
{
public:
	string minRemoveToMakeValid(string s)
	{
		vector<char> stck(s.size());
		stack<int> open;
		int size = 0;

		for (auto ch : s)
		{
			if (ch == '(')
			{
				stck[size] = '?';
				open.push(size);
				size++;
			}
			else if (ch == ')')
			{
				if (open.size() != 0)
				{
					stck[open.top()] = '(';
					open.pop();
					stck[size++] = ')';
				}
			}
			else
			{
				stck[size++] = ch;
			}
		}

		string ans = "";
		for (int i = 0; i < size; i++)
		{
			if (stck[i] != '?')
			{
				ans += stck[i];
			}
		}

		return ans;
	}
};