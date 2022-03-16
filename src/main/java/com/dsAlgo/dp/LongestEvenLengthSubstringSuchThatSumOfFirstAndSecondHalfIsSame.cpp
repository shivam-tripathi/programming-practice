#include <string>
#include <iostream>
using namespace std;

/*

https://www.geeksforgeeks.org/longest-even-length-substring-sum-first-second-half/

Longest Even Length Substring such that Sum of First and Second Half is same
		Difficulty Level : Hard
		Last Updated : 25 Feb, 2022

Given a string ‘str’ of digits, find the length of the longest substring of ‘str’,
such that the length of the substring is 2k digits and sum of left k digits is
equal to the sum of right k digits.

Examples :

Input: str = "123123"
Output: 6
The complete string is of even length and sum of first and second
half digits is same

Input: str = "1538023"
Output: 4
The longest substring with same first and second half sum is "5380"
*/

int longestEventLengthSubstringSuchThatSumOfFirstAndSecondHalfIsSame(string s)
{
	int ans = 0;
	for (int begin = 0; begin < s.size(); begin++)
	{
		// end is not inclusive for right end
		// mid is not inclusive for left end
		int left = 0, right = 0, mid = begin, end = begin;

		while (end < s.size())
		{
			right += s[end++] - '0';

			bool isEven = ((end - begin) % 2) == 0;

			if (isEven)
			{
				while (right > left && mid < end)
				{
					int num = s[mid++] - '0';
					left += num;
					right -= num;
				}
				if (right == left)
				{
					ans = max(ans, end - begin);
				}
			}
		}
	}
	return ans;
}

int main()
{
	string str = "153803";
	cout << longestEventLengthSubstringSuchThatSumOfFirstAndSecondHalfIsSame(str) << endl;
}