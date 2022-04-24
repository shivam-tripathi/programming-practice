#include <unordered_map>
#include <string>
#include <iostream>
using namespace std;

/*
299. Bulls and Cows
https://leetcode.com/problems/bulls-and-cows/
Medium

You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend
makes a guess, you provide a hint with the following info:
		The number of "bulls", which are digits in the guess that are in the correct position.
		The number of "cows", which are digits in the guess that are in your secret number but are
		located in the wrong position. Specifically, the non-bull digits in the guess that could be
		rearranged such that they become bulls.

Given the secret number secret and your friend's guess guess, return the hint for your friend's
guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
Note that both secret and guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
	|
"7810"

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
	|      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be
rearranged to allow one 1 to be a bull.

Constraints:
		1 <= secret.length, guess.length <= 1000
		secret.length == guess.length
		secret and guess consist of digits only.
*/

class Solution
{
public:
	int match_or_update(unordered_map<int, int> &a, unordered_map<int, int> &b, int elem)
	{
		// elem is in b, so we match it and return
		if (b.find(elem) != b.end() && b[elem] > 0)
		{
			b[elem]--;
			return 1;
		}

		// elem is not in b, so we add it to a
		if (a.find(elem) == a.end())
		{
			a[elem] = 0;
		}

		a[elem]++;

		return 0;
	}

	void print_freq(string msg, unordered_map<int, int> &freq)
	{
		cout << msg << " size=" << freq.size() << " items=";
		for (auto p : freq)
		{
			cout << p.first << "->" << p.second << ";";
		}
		cout << endl;
	}

	string getHint(string secret, string guess)
	{

		int bulls = 0;
		int cows = 0;
		unordered_map<int, int> secret_freq;
		unordered_map<int, int> guess_freq;

		for (int i = 0; i < secret.size(); i++)
		{
			if (secret[i] == guess[i])
			{
				bulls++;
			}
			else
			{
				cows += match_or_update(secret_freq, guess_freq, secret[i]);
				cows += match_or_update(guess_freq, secret_freq, guess[i]);
			}
		}

		return to_string(bulls) + "A" + to_string(cows) + "B";
	}
};