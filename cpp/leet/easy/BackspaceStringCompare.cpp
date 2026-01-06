#include <vector>
#include <string>
using namespace std;

/*
844. Backspace String Compare
https://leetcode.com/problems/backspace-string-compare/submissions/
Easy

Given two strings s and t, return true if they are equal when both are typed into empty text
editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Constraints:
		1 <= s.length, t.length <= 200
		s and t only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(n) time and O(1) space?
*/

class Solution
{
public:
	int move(string &str, vector<char> &chars)
	{
		int size = 0;
		for (auto ch : str)
		{
			if (ch == '#')
			{
				if (size > 0)
					size--;
			}
			else
			{
				if (chars.size() == size)
				{
					chars.push_back(ch);
				}
				else
				{
					chars[size] = ch;
				}
				size++;
			}
		}
		return size;
	}

	bool backspaceCompare(string s, string t)
	{
		vector<char> schars, tchars;
		int asize = move(s, schars), bsize = move(t, tchars);
		if (asize != bsize)
			return false;
		for (int i = 0; i < asize; i++)
		{
			if (schars[i] != tchars[i])
			{
				return false;
			}
		}
		return true;
	}
};
