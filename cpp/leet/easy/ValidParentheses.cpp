#include <stack>
#include <string>
using namespace std;

class Solution
{
public:
	bool isValid(string s)
	{
		stack<char> chars;
		for (auto ch : s)
		{
			if (ch == '(' || ch == '{' || ch == '[')
			{
				chars.push(ch);
			}
			else
			{
				if (chars.size() == 0)
					return false;
				char top = chars.top();
				if (ch == ')' && top != '(')
					return false;
				if (ch == '}' && top != '{')
					return false;
				if (ch == ']' && top != '[')
					return false;
				chars.pop();
			}
		}

		return chars.size() == 0;
	}
};
