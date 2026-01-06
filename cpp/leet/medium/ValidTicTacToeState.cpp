#include <vector>
#include <string>
using namespace std;

/*
794. Valid Tic-Tac-Toe State

https://leetcode.com/problems/valid-tic-tac-toe-state/

Medium

Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to
reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character
represents an empty square.

Here are the rules of Tic-Tac-Toe:
		Players take turns placing characters into empty squares ' '.
		The first player always places 'X' characters, while the second player always places 'O'
		characters.
		'X' and 'O' characters are always placed into empty squares, never filled ones.
		The game ends when there are three of the same (non-empty) character filling any row, column, or
		diagonal.
		The game also ends if all squares are non-empty.
		No more moves can be played if the game is over.

Example 1:
Input: board = ["O  ","   ","   "]
Output: false
Explanation: The first player always plays "X".

Example 2:
Input: board = ["XOX"," X ","   "]
Output: false
Explanation: Players take turns making moves.

Example 3:
Input: board = ["XOX","O O","XOX"]
Output: true

Constraints:
		board.length == 3
		board[i].length == 3
		board[i][j] is either 'X', 'O', or ' '.
*/

class Solution
{
public:
	bool is_winner(vector<string> &board, char sign)
	{
		for (int i = 0; i < board.size(); i++)
		{
			if (board[i][0] == sign && board[i][1] == sign && board[i][2] == sign)
			{
				return true;
			}

			if (board[0][i] == sign && board[1][i] == sign && board[2][i] == sign)
			{
				return true;
			}
		}
		if (board[0][0] == board[1][1] && board[2][2] == board[1][1] && board[1][1] == sign)
		{
			return true;
		}
		if (board[0][2] == board[1][1] && board[2][0] == board[1][1] && board[1][1] == sign)
		{
			return true;
		}
		return false;
	}

	bool validTicTacToe(vector<string> &board)
	{
		int xs = 0, os = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == 'X')
				{
					xs++;
				}
				else if (board[i][j] == 'O')
				{
					os++;
				}
			}
		}

		bool x_is_winner = is_winner(board, 'X');
		bool o_is_winner = is_winner(board, 'O');

		// both cannot be the winner
		if (x_is_winner && o_is_winner)
		{
			return false;
		}

		// if o is the winner, it will have the last move
		if (o_is_winner)
		{
			return xs == os;
		}

		// if x is the winner, it will have the last move
		if (x_is_winner)
		{
			return xs == (os + 1);
		}

		// if no one is the winner, x will have equal or one more move than o
		return xs == (os + 1) || xs == os;
	}
};